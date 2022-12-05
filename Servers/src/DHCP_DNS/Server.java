package DHCP_DNS;

/* import Libs/PKGs */
import client.*;

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

    public String GET_QUERY(String SEARCH_QUERY , ArrayList <DNS> DNS_TABLE ) {

        String QUERY = RED_FOREGROUND + "NOT FOUND " + RESET_COLOR;
        int INDEX = 0;

        for (int i = 0; i < DNS_TABLE.size() ; i++) {

            if ( DNS_TABLE.get(i).DOMAIN.equals(SEARCH_QUERY) || DNS_TABLE.get(i).IP_DOMAIN.equals(SEARCH_QUERY) )
                INDEX = i;

        }

        if (DNS_TABLE.get(INDEX).DOMAIN.equals(SEARCH_QUERY))
            return DNS_TABLE.get(INDEX).IP_DOMAIN;

        if (DNS_TABLE.get(INDEX).IP_DOMAIN.equals(SEARCH_QUERY))
            return DNS_TABLE.get(INDEX).DOMAIN;
        else
            return QUERY;

    }


    /* initialize socket and input/output streams */
    private Socket serverSocket     = null;
    private ServerSocket server     = null;
    private DataInputStream receive =  null;
    private DataOutputStream send   = null;

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
            int IP_DISTRIBUTOR = 0; /* using while distributing IPs */
            ArrayList <String> availableIPs = new ArrayList<>(); /* store all available IPs to distribute IPs over the network */

            DNS google = new DNS("google.com","172.217.19.142");
            DNS facebook = new DNS("facebook.com","102.132.97.35");
            DNS github = new DNS("github.com","140.82.121.3");
            DNS youtube = new DNS("youtube.com","142.250.203.238");
            DNS reddit = new DNS("reddit.com","151.101.193.140");
            DNS udemy = new DNS("udemy.com","104.16.65.85");

            ArrayList <DNS> DNS_TABLE = new ArrayList<>();

            DNS_TABLE.add(google);
            DNS_TABLE.add(facebook);
            DNS_TABLE.add(github);
            DNS_TABLE.add(youtube);
            DNS_TABLE.add(reddit);
            DNS_TABLE.add(udemy);

            server = new ServerSocket(port);
            System.out.println( RED_FOREGROUND + "[*] Establishing The Server\n" + RESET_COLOR );
            // System.out.println( RED_FOREGROUND + " \uF175 " + RESET_COLOR );
            Thread.sleep(2000);
            System.out.println( RED_FOREGROUND + "[+] Generating IPs\n" + RESET_COLOR );
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

            Thread.sleep(2000);
            System.out.println( GREEN_FOREGROUND + "[✔] D O N E \n" + RESET_COLOR );
            Thread.sleep(1000);
            System.out.println( RED_FOREGROUND + "[+] Waiting for client on port : " + CYAN_FOREGROUND + port + "\n" + RESET_COLOR );

            serverSocket = server.accept();
            System.out.println( GREEN_FOREGROUND + "[✔] Connected With : " + CYAN_FOREGROUND + serverSocket.getRemoteSocketAddress() + "\n" + RESET_COLOR );
            Thread.sleep(1000);

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
            System.out.println( PURPLE_FOREGROUND + "*****************" + RESET_COLOR );
            System.out.println( PURPLE_FOREGROUND + "*      DNS      *" + RESET_COLOR );
            System.out.println( PURPLE_FOREGROUND + "*****************" + RESET_COLOR );

            String SearchQuery = "";
            String DNS_RESPONSE = ""; /* Sending IP/Domain To Client */
            int SEARCH_QUERY_INDEX = 0;
            String SEARCH_QUERY_TYPE = "";

            /* reads message from client until "stop" is sent*/
            while (!SearchQuery.equals("stop")) {

                try {

                    SearchQuery = receive.readUTF();
                    System.out.println( RED_FOREGROUND + "[SearchQuery] : " + YELLOW_FOREGROUND + SearchQuery + RESET_COLOR );
                    DNS_RESPONSE = GET_QUERY(SearchQuery , DNS_TABLE);
                    send.writeUTF(DNS_RESPONSE);


                } catch(IOException i) {

                    System.out.println(i);

                }
            }

            /* close connection */
            System.out.println( RED_FOREGROUND + "\n[*] Closing connection" + RESET_COLOR );
            serverSocket.close();
            receive.close();

        } catch(IOException i) {

            System.out.println(i);

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        }
    }
}
