package client;

/* import Libs/PKGs */
import java.net.*;
import java.io.*;
import java.util.ArrayList;

import DHCP.*;
public class Client {

    /* COLORS */
    public static final String RESET_COLOR = "\u001B[0m";

    public static final String BLACK_FOREGROUND  = "\u001B[30m";
    public static final String WHITE_FOREGROUND  = "\u001B[37m";
    public static final String RED_FOREGROUND    = "\u001B[31m";
    public static final String GREEN_FOREGROUND  = "\u001B[32m";
    public static final String YELLOW_FOREGROUND = "\u001B[33m";
    public static final String BLUE_FOREGROUND   = "\u001B[34m";
    public static final String PURPLE_FOREGROUND = "\u001B[35m";
    public static final String CYAN_FOREGROUND   = "\u001B[36m";

    public static final String BLACK_BACKGROUND  = "\u001B[40m";
    public static final String WHITE_BACKGROUND  = "\u001B[47m";
    public static final String RED_BACKGROUND    = "\u001B[41m";
    public static final String GREEN_BACKGROUND  = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND   = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND   = "\u001B[46m";

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
        System.out.println( RED_FOREGROUND + "[+] Generating IPs via " + BLACK_BACKGROUND + RED_FOREGROUND + " Operating System " + RESET_COLOR + "\n" );
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

        Thread.sleep(2000);
        System.out.println( GREEN_FOREGROUND + "[✔] D O N E \n" + RESET_COLOR );
        Thread.sleep(1000);

        System.out.println( GREEN_FOREGROUND + "[OS] Your Current Private IP Address Is : " + YELLOW_FOREGROUND + SELF_CLIENT_IP + "\n" + RESET_COLOR);

        return SELF_CLIENT_IP;

    }

    /* initialize socket and input/output streams */
    private Socket clientSocket        = null;
    private DataInputStream  receive   = null;
    private DataOutputStream send      = null;
    private DataInputStream  input     = null;

    public Client(String ipAddress, int port) {

        /* establish a connection */
        try {

            String SUBNET_MASK  = "";
            String STATUS       = ""; /* Sending Approve to DHCP For The Offered IP */
            String DHCP_ACK     = ""; /* Receiving Approve To Client about The Offered IP */
            String DEFAULT_GATEWAY_IP_ADDRESS = "";
            String CLIENT_IP = "";


            clientSocket = new Socket(ipAddress, port);

            if (clientSocket.isConnected()){

                if (Server.IP_DISTRIBUTOR == 254){

                    System.out.println( RED_FOREGROUND + "[*] Connecting to : " + CYAN_FOREGROUND + "DHCP/DNS Server\n" + RESET_COLOR );
                    Thread.sleep(3000);
                    Server.IP_DISTRIBUTOR = 0;

                    System.out.println( RED_FOREGROUND + "[✘] No IPs Available\n" + RESET_COLOR);
                    Thread.sleep(1000);
                    CLIENT_IP = AUTO_PRIVATE_IP_ADDRESSING();

                    System.out.println( RED_FOREGROUND + "[*] Trying To Connect To " + CYAN_FOREGROUND + "DHCP Server " + RED_FOREGROUND + "After 5 Minutes\n\n" + RESET_COLOR );
                    Thread.sleep(300000);
                    // Thread.sleep(5000);



                    System.out.println( RED_FOREGROUND + "[+] Trying To Connecting to : " + CYAN_FOREGROUND + "DHCP/DNS Server\n" + RESET_COLOR );
                    // System.out.println( RED_FOREGROUND + " \uF175 " + RESET_COLOR );
                    Thread.sleep(1000);
                    System.out.println( GREEN_FOREGROUND + "[✔] Connected to " + CYAN_FOREGROUND + "DHCP/DNS Server : " + BLUE_FOREGROUND + clientSocket.getRemoteSocketAddress() + "\n" + RESET_COLOR );
                    Thread.sleep(1000);

                    /* receive output to the socket [initialize] */
                    receive = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

                    /* sends output to the socket [initialize] */
                    send = new DataOutputStream(clientSocket.getOutputStream());

                    /* takes input from terminal */
                    input  = new DataInputStream(System.in);

                    /* Setting an IP Address For The Client */
                    System.out.println( RED_FOREGROUND + "[DHCP DISCOVER] Connecting To " + CYAN_FOREGROUND + "DHCP Server " + RED_FOREGROUND + "To request an IP address\n" + RESET_COLOR );
                    Thread.sleep(1000);
                    /* Receiving The IP */
                    CLIENT_IP = receive.readUTF();
                    System.out.println( RED_FOREGROUND + "[DHCP OFFER] Your IP Address will be : " + YELLOW_FOREGROUND + CLIENT_IP + "\n" + RESET_COLOR );
                    STATUS = "OK";
                    Thread.sleep(1000);
                    System.out.println( RED_FOREGROUND + "[DHCP REQUEST] Requesting The IP : " + YELLOW_FOREGROUND + CLIENT_IP + "  From "  + CYAN_FOREGROUND + "DHCP Server \n" + RESET_COLOR );
                    send.writeUTF(STATUS);
                    Thread.sleep(1000);

                    DHCP_ACK = receive.readUTF();

                    if (DHCP_ACK.equals("OK")){

                        System.out.println( GREEN_FOREGROUND + "[DHCP ACK] Your IP     : " + YELLOW_FOREGROUND + CLIENT_IP + RESET_COLOR );
                        DEFAULT_GATEWAY_IP_ADDRESS = receive.readUTF();
                        System.out.println( GREEN_FOREGROUND + "      ==>  gateway     : " + YELLOW_FOREGROUND + DEFAULT_GATEWAY_IP_ADDRESS + RESET_COLOR );
                        SUBNET_MASK = receive.readUTF();
                        System.out.println( GREEN_FOREGROUND + "      ==>  subnet mask : " + YELLOW_FOREGROUND + SUBNET_MASK + RESET_COLOR );

                    }


                } else if (Server.IP_DISTRIBUTOR < 254) {

                    System.out.println( RED_FOREGROUND + "[*] Connecting to : " + CYAN_FOREGROUND + "DHCP/DNS Server\n" + RESET_COLOR );
                    // System.out.println( RED_FOREGROUND + " \uF175 " + RESET_COLOR );
                    Thread.sleep(1000);
                    System.out.println( GREEN_FOREGROUND + "[✔] Connected to " + CYAN_FOREGROUND + "DHCP/DNS Server : " + BLUE_FOREGROUND + clientSocket.getRemoteSocketAddress() + "\n" + RESET_COLOR );
                    Thread.sleep(1000);


                    /* receive output to the socket [initialize] */
                    receive = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));

                    /* sends output to the socket [initialize] */
                    send = new DataOutputStream(clientSocket.getOutputStream());

                    /* takes input from terminal */
                    input  = new DataInputStream(System.in);

                    /* Setting an IP Address For The Client */
                    System.out.println( RED_FOREGROUND + "[DHCP DISCOVER] Connecting To " + CYAN_FOREGROUND + "DHCP Server " + RED_FOREGROUND + "To request an IP address\n" + RESET_COLOR );
                    Thread.sleep(1000);
                    /* Receiving The IP */
                    CLIENT_IP = receive.readUTF();
                    System.out.println( RED_FOREGROUND + "[DHCP OFFER] Your IP Address will be : " + YELLOW_FOREGROUND + CLIENT_IP + "\n" + RESET_COLOR );
                    STATUS = "OK";
                    Thread.sleep(1000);
                    System.out.println( RED_FOREGROUND + "[DHCP REQUEST] Requesting The IP : " + YELLOW_FOREGROUND + CLIENT_IP + "  From "  + CYAN_FOREGROUND + "DHCP Server \n" + RESET_COLOR );
                    send.writeUTF(STATUS);
                    Thread.sleep(1000);

                    DHCP_ACK = receive.readUTF();

                    if (DHCP_ACK.equals("OK")){

                        System.out.println( GREEN_FOREGROUND + "[DHCP ACK] Your IP     : " + YELLOW_FOREGROUND + CLIENT_IP + RESET_COLOR );
                        DEFAULT_GATEWAY_IP_ADDRESS = receive.readUTF();
                        System.out.println( GREEN_FOREGROUND + "      ==>  gateway     : " + YELLOW_FOREGROUND + DEFAULT_GATEWAY_IP_ADDRESS + RESET_COLOR );
                        SUBNET_MASK = receive.readUTF();
                        System.out.println( GREEN_FOREGROUND + "      ==>  subnet mask : " + YELLOW_FOREGROUND + SUBNET_MASK + RESET_COLOR );

                    }

                    System.out.println("");
                    System.out.println( PURPLE_FOREGROUND + "##################" + RESET_COLOR );
                    System.out.println( PURPLE_FOREGROUND + "#      DHCP      #" + RESET_COLOR );
                    System.out.println( PURPLE_FOREGROUND + "##################" + RESET_COLOR );

                    /* string to read message from input */
                    String command = "";

                    /* keep reading until "stop" is input */
                    while (!command.equals("stop")) {

                        try {

                            System.out.print( PURPLE_FOREGROUND + "\n[command]$ " + RESET_COLOR);
                            command = input.readLine();
                            send.writeUTF(command);

                        } catch(IOException i) {

                            System.out.println(i);

                        }
                    }

                    /* close the connection */
                    try {

                        System.out.println( RED_FOREGROUND + "\n[*] Closing connection" + RESET_COLOR );
                        receive.close();
                        send.close();
                        clientSocket.close();

                    } catch(IOException i) {

                        System.out.println(i);

                    }

                }


            } else {

                System.out.println( RED_FOREGROUND + "[✘] DHCP Server Is Down" + RESET_COLOR);
                Thread.sleep(1000);
                CLIENT_IP = AUTO_PRIVATE_IP_ADDRESSING();
                System.out.println( RED_FOREGROUND + "[+] Trying To Connect To " + CYAN_FOREGROUND + "DHCP Server " + RED_FOREGROUND + "After 5 Minutes\n" + RESET_COLOR );
                Thread.sleep(300000);

            }


        } catch(UnknownHostException u) {

            System.out.println(u);

        } catch(IOException i) {

            System.out.println(i);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }

    }
}
