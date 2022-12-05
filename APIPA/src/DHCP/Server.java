package DHCP;

/* import Libs/PKGs */

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {

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
    private Socket serverSocket     = null;
    private ServerSocket server     = null;
    private DataInputStream receive =  null;
    private DataOutputStream send   = null;
    
    public static int IP_DISTRIBUTOR = 254; /* using while distributing IPs */


    /* the passed Parameter is Port Number */
    public Server(int port) {

        /* starts server and waits for a connection */
        try {

            String STATUS   = ""; /* Receiving Approve Form Client about The Offered IP */
            String DHCP_ACK = ""; /* Sending Approve To Client about The Offered IP */
            String SUBNET_MASK = "255.255.255.0";
            String DEFAULT_GATEWAY_IP_ADDRESS = "10.10.2.1";

            /*
             * we will append number from the range (4 : 254) at the end.
             * as First Client IP address : 10.10.2.4
             * and Last Client IP address : 10.10.2.254
             */
            String IP = "10.10.2.";
            int IP_RANGE = 4;
            ArrayList <String> availableIPs = new ArrayList<>(); /* store all available IPs to distribute IPs over the network */

            server = new ServerSocket(port);
            System.out.println( RED_FOREGROUND + "[*] Establishing The Server\n" + RESET_COLOR );
            // System.out.println( RED_FOREGROUND + " \uF175 " + RESET_COLOR );
            Thread.sleep(2000);
            System.out.println( RED_FOREGROUND + "[+] Generating IPs\n" + RESET_COLOR );
            Thread.sleep(2000);
            System.out.println( GREEN_FOREGROUND + "[✔] D O N E \n" + RESET_COLOR );
            Thread.sleep(1000);
            System.out.println( RED_FOREGROUND + "[+] Waiting for client on port : " + CYAN_FOREGROUND + port + "\n" + RESET_COLOR );

            serverSocket = server.accept();
            System.out.println( GREEN_FOREGROUND + "[✔] Connected With : " + CYAN_FOREGROUND + serverSocket.getRemoteSocketAddress() + "\n" + RESET_COLOR );
            Thread.sleep(1000);

            IP_DISTRIBUTOR = 0;
            /*
             * Generating IPs
             * we start adding No. 4 to IP (10.10.2.) at the end to generate the first IP which is 10.10.2.4
             * and store in the array, then increase IP_RANGE by one to generate the second IP which is 10.10.2.5
             * we repeat this till IP = 10.10.2.254
             * */
            for (int i = 0; i < 251; i++) {

                availableIPs.add(IP + IP_RANGE);
                IP_RANGE ++;

            }

            /* takes input from the client socket [initialize] */
            receive = new DataInputStream(new BufferedInputStream(serverSocket.getInputStream()));

            /* sends output to the socket [initialize] */
            send = new DataOutputStream(serverSocket.getOutputStream());

            /* Setting an IP Address For The Client */
            System.out.println( BLUE_FOREGROUND + "[DHCP OFFER] Setting : " + YELLOW_FOREGROUND + availableIPs.get(IP_DISTRIBUTOR) + BLUE_FOREGROUND + " Address For The New Client \n" + RESET_COLOR );
            send.writeUTF(availableIPs.get(IP_DISTRIBUTOR));
            Thread.sleep(1000);

            /* Receiving Client response */
            STATUS = receive.readUTF();
            if (STATUS.equals("OK")) {

                System.out.println( GREEN_FOREGROUND + "[DHCP ACK] Setting The IP : " + YELLOW_FOREGROUND + availableIPs.get(IP_DISTRIBUTOR) + GREEN_FOREGROUND + "  IS DONE ✔ \n" + RESET_COLOR );
                IP_DISTRIBUTOR++;/* increase IP_DISTRIBUTOR by one, as this ip is token */
                DHCP_ACK = "OK";
                /* Sending Approve To Client */
                send.writeUTF(DHCP_ACK);

                send.writeUTF(DEFAULT_GATEWAY_IP_ADDRESS);
                send.writeUTF(SUBNET_MASK);

            }

            /* Create a new thread object */
            ClientHandler clientSock = new ClientHandler(serverSocket);
            new Thread(clientSock).start();



            System.out.println("");
            System.out.println( PURPLE_FOREGROUND + "##################" + RESET_COLOR );
            System.out.println( PURPLE_FOREGROUND + "#      DHCP      #" + RESET_COLOR );
            System.out.println( PURPLE_FOREGROUND + "##################" + RESET_COLOR );

            String command = "";

            /* reads message from client until "stop" is sent*/
            while (!command.equals("stop")) {

                try {

                    command = receive.readUTF();
                    System.out.println( RED_FOREGROUND + "[command] : " + YELLOW_FOREGROUND + command + RESET_COLOR );


                } catch(IOException i) {

                    System.out.println(i);

                }
            }

            /* close connection */
            System.out.println( RED_FOREGROUND + "\n[*] Closing connection\n" + RESET_COLOR );
            serverSocket.close();
            receive.close();

        } catch(IOException i) {

            System.out.println(i);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }
    }
}
