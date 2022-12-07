/* importing Libs/PKGs needed */
import java.util.ArrayList;

public class DHCP {

    public void GENERATE_IP (ArrayList<String> availableIPs , String IP_RANGE , int IP_STARTER ){

        for (int i = 0; i < 251; i++) {

            availableIPs.add(IP_RANGE + IP_STARTER);
            IP_STARTER ++;

        }

    }

    public String SUBNET_MASK = "255.255.255.0";
    public String DEFAULT_GATEWAY = "10.10.2.1";

    /*
     * we will append number from the range (4 : 254) at the end.
     * as First Client IP address : 10.10.2.4
     * and Last Client IP address : 10.10.2.254
     */
    public String IP_RANGE = "10.10.2.";
    public int IP_STARTER = 4;
    public int IP_DISTRIBUTOR = 0; /* using while distributing IPs */

    public ArrayList<String> availableIPs = new ArrayList<>(); /* store all available IPs to distribute IPs over the network */
    public ArrayList<String> takenIPs = new ArrayList<>(); /* store all the taken IPs to prevent distributing them over the network */
    public ArrayList<String> CONNECTED_CLIENTS = new ArrayList<>(); /* store all connected clients */
    public int CONNECTED_CLIENTS_COUNTER = 0;

    /* THIS FUNCTION ONLY FOR TESTING */
    public void print (){

        System.out.println(availableIPs);

    }

    public static void main(String[] args) {

        DHCP dhcp = new DHCP();
        dhcp.print();

    }


    /* DHCP */

}
