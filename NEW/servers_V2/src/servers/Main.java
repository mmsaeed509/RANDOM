package servers;

import java.net.SocketException;

public class Main {

    public static void main(String[] args) {

        try{
            DHCP dhcp = new DHCP();
            dhcp.start();
        }
        catch(SocketException e){
            e.printStackTrace();
        }

    }

}
