package servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

public final class DHCP extends Thread {

    public String get_ip_domain_from_dns (String SEARCH_QUERY , ArrayList <DNS> DNS_TABLE){

        int index = 0;
        String domain_ip = "";

        for (int i = 0; i < DNS_TABLE.size(); i++) {

            if ( SEARCH_QUERY.equals( DNS_TABLE.get(i).domain ) || SEARCH_QUERY.equals( DNS_TABLE.get(i).domain_ip ) )
                index = i;
        }

        if ( SEARCH_QUERY.equals( DNS_TABLE.get(index).domain ) )
            domain_ip = DNS_TABLE.get(index).domain_ip;
        else if ( SEARCH_QUERY.equals( DNS_TABLE.get(index).domain_ip ) )
            domain_ip = DNS_TABLE.get(index).domain;
        else
            domain_ip = "this domain/ip not found in the DNS table";

        return domain_ip;

    }

    public void generating_IPs (ArrayList<String> availableIPs , String IP_RANGE , int IP_STARTER ){

        for (int i = 0; i < 251; i++) {

            availableIPs.add(IP_RANGE + IP_STARTER);
            IP_STARTER ++;

        }

    }

    static public String subnet_mask = "255.255.255.0";
    static public String default_gateway = "10.10.2.1";
    static public String ip_base = "10.10.2.";
    static public int first_ip = 4;
    static public int change_ip = 0;

    public ArrayList<String> available_IPs = new ArrayList<>();
    public ArrayList<String> usedIPs = new ArrayList<>();
    public ArrayList<String> clients = new ArrayList<>();
    public int clients_counter = 0;

    private DatagramSocket datagramSocket;
    private int port = 7777;


    public DHCP () throws SocketException {
        setName("UdpServer");
        datagramSocket = new DatagramSocket(port);
    }

    static public boolean server_status = false;

    public boolean is_server_running(){

        server_status = datagramSocket.isConnected();
        return server_status;

    }

    public void run(){

        ArrayList<DNS> dns_table = new ArrayList<>();

        DNS google   = new DNS("google.com","172.217.19.142");
        DNS facebook = new DNS("facebook.com","102.132.97.35");
        DNS youtube  = new DNS("youtube.com","142.250.203.238");
        DNS udemy    = new DNS("udemy.com","104.16.65.85");

        dns_table.add(google);
        dns_table.add(facebook);
        dns_table.add(youtube);
        dns_table.add(udemy);

        generating_IPs( available_IPs , ip_base , first_ip );


        System.out.println("\nWaiting for any client to connect on port : " + port + "\n");

        try{

            while (!isInterrupted()) {

                String client_message = "";
                String search_on_dns_table = "";
                byte[] buffer = new byte[256];
                DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
                DatagramPacket send = new DatagramPacket(buffer, buffer.length, receive.getAddress(), receive.getPort());
                datagramSocket.receive(receive);
                client_message = new String(receive.getData(), 0, receive.getLength());

                if(client_message.equals("want to connect")){

                    client_message = "Your IP Address will be : " + available_IPs.get(change_ip);
                    buffer = client_message.getBytes();
                    send = new DatagramPacket(buffer , buffer.length , receive.getAddress() , receive.getPort() );
                    datagramSocket.send(send);

                    client_message = available_IPs.get(change_ip);
                    buffer = client_message.getBytes();
                    send = new DatagramPacket(buffer , buffer.length , receive.getAddress() , receive.getPort() );
                    datagramSocket.send(send);

                    /* Save The New Client */
                    datagramSocket.receive(receive);
                    client_message = new String(receive.getData(), 0, receive.getLength());
                    clients.add(client_message);

                    System.out.println("Connected with : " + clients.get(clients_counter) + " client\n");
                    available_IPs.remove(change_ip);
                    clients_counter++;

                }

                while (!search_on_dns_table.equals("bye")) {

                    try {

                        datagramSocket.receive(receive);
                        client_message = new String(receive.getData(), 0, receive.getLength());

                        if (client_message.equals("bye")){

                            System.out.println("Disconnected With " + clients.get(clients_counter-1));

                            /* add IP to available IPs */
                            available_IPs.add(clients.get(clients_counter-1));
                            clients.remove(clients_counter-1);

                        }

                        search_on_dns_table = get_ip_domain_from_dns(client_message,dns_table);

                        /* send DNS Server Response */
                        buffer = search_on_dns_table.getBytes();
                        send = new DatagramPacket(buffer , buffer.length , receive.getAddress() , receive.getPort() );
                        datagramSocket.send(send);

                    } catch(IOException i) {

                        System.out.println(i);

                    }

                }


            }

        }

        catch(IOException e){

            e.printStackTrace();

        } finally{

            // close connection
            System.out.println("\nClosing connection");
            datagramSocket.close();
        }

    }


}
