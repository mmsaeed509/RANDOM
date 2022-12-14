/* importing Libs/PKGs needed */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

public final class UdpServer extends Thread {

    private DatagramSocket datagramSocket;
    private int port = 7777;


    public UdpServer() throws SocketException {
        setName("UdpServer");

        /* Create a DatagramSocket instance and bind it to the specified port number */
        datagramSocket = new DatagramSocket(port);
    }

    static public boolean STATUS = false;
    public boolean DHCP_STATUS(){

        STATUS = datagramSocket.isConnected();
        return STATUS;

    }

    public String GET_QUERY (String SEARCH_QUERY , ArrayList <DNS> DNS_TABLE){

        int INDEX = 0;
        String QUERY = "";

        for (int i = 0; i < DNS_TABLE.size(); i++) {

            if ( SEARCH_QUERY.equals( DNS_TABLE.get(i).DOMAIN ) || SEARCH_QUERY.equals( DNS_TABLE.get(i).IP_DOMAIN ) )
                INDEX = i;
        }

        if ( SEARCH_QUERY.equals( DNS_TABLE.get(INDEX).DOMAIN ) )
            QUERY = DNS_TABLE.get(INDEX).IP_DOMAIN;
        else if ( SEARCH_QUERY.equals( DNS_TABLE.get(INDEX).IP_DOMAIN ) )
            QUERY = DNS_TABLE.get(INDEX).DOMAIN;
        else
            QUERY = "NOT FOUND";

        return QUERY;

    }

    public void run(){

        ArrayList<DNS> DNS_TABLE = new ArrayList<>();

        DNS google   = new DNS("google.com","172.217.19.142");
        DNS facebook = new DNS("facebook.com","102.132.97.35");
        DNS github   = new DNS("github.com","140.82.121.3");
        DNS youtube  = new DNS("youtube.com","142.250.203.238");
        DNS reddit   = new DNS("reddit.com","151.101.193.140");
        DNS udemy    = new DNS("udemy.com","104.16.65.85");

        DNS_TABLE.add(google);
        DNS_TABLE.add(facebook);
        DNS_TABLE.add(github);
        DNS_TABLE.add(youtube);
        DNS_TABLE.add(reddit);
        DNS_TABLE.add(udemy);

        DHCP dhcp = new DHCP();

        System.out.println("\n[*] Establishing The Server\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("[+] Generating IPs\n");
        dhcp.GENERATE_IP( dhcp.availableIPs , dhcp.IP_RANGE , dhcp.IP_STARTER );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("[✔] D O N E \n");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("[+] Waiting For Clients On PORT : " + port + "\n");

        try{

            while (!isInterrupted()) {

                String CLIENT_MSG = "";
                String SearchQuery = "";

                /* Create a buffer to use it for send/receive packets from/to client */
                byte[] buffer = new byte[256];

                /* Create a DatagramPacket object to receive packets from client */
                DatagramPacket receive = new DatagramPacket(buffer, buffer.length);

                /* Create a DatagramPacket object to send packets to the client */
                DatagramPacket send = new DatagramPacket(buffer, buffer.length, receive.getAddress(), receive.getPort());

                /* Receive a packet from a client */
                datagramSocket.receive(receive);

                /* convert the received packet to string */
                CLIENT_MSG = new String(receive.getData(), 0, receive.getLength());

                if(CLIENT_MSG.equals("DHCP DISCOVER")){

                    /* send DHCP OFFER to the client */
                    CLIENT_MSG = "[DHCP OFFER] Your IP Address will be : " + dhcp.availableIPs.get(dhcp.IP_DISTRIBUTOR) + "\n";
                    buffer = CLIENT_MSG.getBytes();
                    send = new DatagramPacket(buffer , buffer.length , receive.getAddress() , receive.getPort() );
                    datagramSocket.send(send);

                    /* send DHCP ACK with ip to the client */
                    CLIENT_MSG = dhcp.availableIPs.get(dhcp.IP_DISTRIBUTOR);
                    buffer = CLIENT_MSG.getBytes();
                    send = new DatagramPacket(buffer , buffer.length , receive.getAddress() , receive.getPort() );
                    datagramSocket.send(send);

                    /* Save The New Client */
                    datagramSocket.receive(receive);
                    CLIENT_MSG = new String(receive.getData(), 0, receive.getLength());
                    dhcp.CONNECTED_CLIENTS.add(CLIENT_MSG);

                    System.out.println("[✔] Connected with : " + dhcp.CONNECTED_CLIENTS.get(dhcp.CONNECTED_CLIENTS_COUNTER) + "\n");
                    dhcp.availableIPs.remove(dhcp.IP_DISTRIBUTOR);
                    dhcp.CONNECTED_CLIENTS_COUNTER++;

                }

                /* reads message from client until "stop" is sent*/
                while (!SearchQuery.equals("stop")) {

                    try {

                        /* Receive Search Query From Client */
                        datagramSocket.receive(receive);
                        CLIENT_MSG = new String(receive.getData(), 0, receive.getLength());

                        if (CLIENT_MSG.equals("stop")){

                            System.out.println("Disconnected With " + dhcp.CONNECTED_CLIENTS.get(dhcp.CONNECTED_CLIENTS_COUNTER-1));

                            /* add IP to available IPs */
                            dhcp.availableIPs.add(dhcp.CONNECTED_CLIENTS.get(dhcp.CONNECTED_CLIENTS_COUNTER-1));
                            dhcp.CONNECTED_CLIENTS.remove(dhcp.CONNECTED_CLIENTS_COUNTER-1);

                        }

                        SearchQuery = GET_QUERY(CLIENT_MSG,DNS_TABLE);

                        /* send DNS Server Response */
                        buffer = SearchQuery.getBytes();
                        send = new DatagramPacket(buffer , buffer.length , receive.getAddress() , receive.getPort() );
                        datagramSocket.send(send);

                    } catch(IOException i) {

                        System.out.println(i);

                    }

                }


            }

        }

        catch(IOException e){

            System.out.println("An ERROR Occurred!\n");
            e.printStackTrace();

        } finally{

            /* close connection */
            System.out.println("\n[*] Closing connection");
            datagramSocket.close();
        }

    }

    /* running the server */
    public static void main(String[] args){
        try{
            UdpServer udpServer = new UdpServer();
            udpServer.start();
        }
        catch(SocketException e){
            e.printStackTrace();
        }
    }

}
