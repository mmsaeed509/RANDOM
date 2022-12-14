/* importing Libs/PKGs needed */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Scanner;

public final class client {

    public String CLIENT_SUBNET_MASK = "";
    public String CLIENT_DEFAULT_GATEWAY = "";
    public String CLIENT_IP = "";
    int port = 7777;

    private void process() {

        DatagramSocket datagramSocket = null;

        try{

            InetAddress SERVER_ADDRESS = InetAddress.getByName("localhost");

            /* Create a DatagramSocket and bind it to any available local port number */
            datagramSocket = new DatagramSocket();

            String DHCP_DISCOVER = "DHCP DISCOVER";
            DHCP dhcp = new DHCP();
            String SERVER_MSG = "";

            /* Create a buffer to use it for send/receive packets from/to server */
            byte[] buffer = new byte[256];

            /* Create a DatagramPacket object to send packets to the server */
            DatagramPacket send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);

            /* Create a DatagramPacket object to receive packets from the server */
            DatagramPacket receive = new DatagramPacket(buffer, buffer.length);

            /* Getting an IP Address For The server */
            System.out.println("\n[DHCP DISCOVER] Connecting To DHCP Server To request an IP address\n");
            Thread.sleep(1000);

            /* Send the packet to the server */
            buffer = DHCP_DISCOVER.getBytes();
            send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);
            datagramSocket.send(send);



            /* Receive DHCP OFFER from the server */
            buffer = new byte[256];
            receive = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(receive);

            /* convert the received packet to string */
            SERVER_MSG = new String(receive.getData(),0,receive.getLength());

            /* Display the received string */
            System.out.println(SERVER_MSG);
            Thread.sleep(1000);

            System.out.println("[DHCP REQUEST] Requesting The IP From DHCP server\n");
            Thread.sleep(1000);

            /* Receive DHCP ACK with ip From Server */
            buffer = new byte[256];
            receive = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(receive);

            /* convert the received packet to string */
            SERVER_MSG = new String(receive.getData(),0,receive.getLength());

            CLIENT_IP = SERVER_MSG;
            CLIENT_DEFAULT_GATEWAY = dhcp.DEFAULT_GATEWAY;
            CLIENT_SUBNET_MASK = dhcp.SUBNET_MASK;

            /* Display the received string */
            System.out.println("[DHCP ACK] Your IP Address" + CLIENT_IP);
            System.out.println("      ==>  gateway     : " + CLIENT_DEFAULT_GATEWAY);
            System.out.println("      ==>  subnet mask : " + CLIENT_SUBNET_MASK);

            /* Send IP to the server For Saving */
            buffer = CLIENT_IP.getBytes();
            send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);
            datagramSocket.send(send);

            System.out.println("");
            System.out.println("*****************");
            System.out.println("*      DNS      *");
            System.out.println("*****************");

            /* string to read message from input */
            String SearchQuery = "";
            Scanner input= new Scanner(System.in);

            /* keep reading until "stop" is input */
            while (!SearchQuery.equals("stop")) {

                try {

                    System.out.print("\n[ \uF422 ] Search... ");
                    SearchQuery = input.nextLine();

                    /* Send Search Query to the DNS server */
                    buffer = SearchQuery.getBytes();
                    send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);
                    datagramSocket.send(send);

                    /* Receive DNS Server Response */
                    buffer = new byte[256];
                    receive = new DatagramPacket(buffer, buffer.length);
                    datagramSocket.receive(receive);

                    /* convert the received packet to string */
                    SERVER_MSG = new String(receive.getData(),0,receive.getLength());

                    /* Display the received string */
                    System.out.println(SERVER_MSG);



                } catch(IOException i) {

                    System.out.println(i);

                }
            }



        } catch(IOException e){

            e.printStackTrace();

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        } finally{

            /* close connection */
            if(datagramSocket != null){

                System.out.println("\n[*] Closing connection");
                datagramSocket.close();

            }

        }

    }

    public static void main(String[] args){
        new client().process();
    }
}
