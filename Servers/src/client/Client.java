package client;

/* import Libs/PKGs */
import java.net.*;
import java.io.*;

import DHCP_DNS.*;

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

        } catch(UnknownHostException u) {

            System.out.println(u);

        } catch(IOException i) {

            System.out.println(i);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }


        System.out.println("");
        System.out.println( PURPLE_FOREGROUND + "*****************" + RESET_COLOR );
        System.out.println( PURPLE_FOREGROUND + "*      DNS      *" + RESET_COLOR );
        System.out.println( PURPLE_FOREGROUND + "*****************" + RESET_COLOR );

        /* string to read message from input */
        String SearchQuery = "";
        String DNS_RESPONSE = ""; /* Getting IP/Domain Form DNS Server */

        /* keep reading until "stop" is input */
        while (!SearchQuery.equals("stop")) {

            try {

                System.out.print( PURPLE_FOREGROUND + "\n[ \uF422 ] Search... " + RESET_COLOR);
                SearchQuery = input.readLine();
                send.writeUTF(SearchQuery);
                DNS_RESPONSE = receive.readUTF();
                System.out.print( GREEN_FOREGROUND + "[DNS] Here Is " + YELLOW_FOREGROUND + DNS_RESPONSE + RESET_COLOR);

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
}
