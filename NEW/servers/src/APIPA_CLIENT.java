/* importing Libs/PKGs needed */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class APIPA_CLIENT {

    /* Automatic Private IP Addressing */
    public String AUTO_PRIVATE_IP_ADDRESSING() throws InterruptedException {

        String SELF_CLIENT_IP = "";
        /*
         * we will append number from the range (1: 254) at the end.
         * as First Client IP address : 169.254.0.1
         * and Last Client IP address : 169.254.0.254
         */
        String IP = "169.254.0.";
        int IP_RANGE = 1;
        ArrayList<String> availableIPs = new ArrayList<>(); /* store all available IPs to distribute IPs over the network */

        /*
         * Generating IPs
         * we start adding No. 4 to IP (10.10.2.) at the end to generate the first IP which is 10.10.2.4
         * and store in the array, then increase IP_RANGE by one to generate the second IP which is 10.10.2.5
         * we repeat this till IP = 10.10.2.254
         * */
        System.out.println("\n[+] Generating IPs via Operating System \n" );
        for (int i = 0; i < 254; i++) {

            availableIPs.add(IP + IP_RANGE);
            IP_RANGE ++;

        }

        /* Selecting A Random IP Address */
        for (int i = 0; i < availableIPs.size(); i++) {

            int RANDOM_IP = (int)(Math.random() * availableIPs.size());
            SELF_CLIENT_IP = availableIPs.get(RANDOM_IP);
            availableIPs.remove(RANDOM_IP);

        }

        Thread.sleep(1000);
        System.out.println("[✔] D O N E \n");
        Thread.sleep(1000);

        System.out.println("[OS] Your Current Private IP Address Is : " + SELF_CLIENT_IP + "\n");

        return SELF_CLIENT_IP;

    }

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

                System.out.println(UdpServer.STATUS);

                if (!UdpServer.STATUS){

                    System.out.println("\nNO DHCP server Is Running!");

                    CLIENT_IP=AUTO_PRIVATE_IP_ADDRESSING();
                    CLIENT_DEFAULT_GATEWAY = "NON";
                    CLIENT_SUBNET_MASK = " 255.255.0.0";

                    System.out.println("[DHCP ACK] Your IP Address" + CLIENT_IP);
                    System.out.println("      ==>  gateway     : "  + CLIENT_DEFAULT_GATEWAY);
                    System.out.println("      ==>  subnet mask : "  + CLIENT_SUBNET_MASK);

                    Thread.sleep(5000);

                }

                    /* Create a buffer to use it for send/receive packets from/to server */
                    byte[] buffer = new byte[256];

                    /* Create a DatagramPacket object to send packets to the server */
                    DatagramPacket send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);

                    /* Create a DatagramPacket object to receive packets from the server */
                    DatagramPacket receive = new DatagramPacket(buffer, buffer.length);

                    /* Getting an IP Address For The server */
                    System.out.println("\n[DHCP DISCOVER] Connecting To DHCP Server To request an IP address\n");

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

                    /* check if server is running */
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



            }
            catch(IOException e){

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

        new APIPA_CLIENT().process();

    }

}
