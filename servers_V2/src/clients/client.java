package clients;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Scanner;
import servers.*;

public class client {

    public String subnet_mask = "";
    public String default_gateway = "";
    public String ip = "";
    int port = 7777;

    private void process() {

        DatagramSocket datagramSocket = null;

        try{

            InetAddress server_address = InetAddress.getByName("localhost");
            datagramSocket = new DatagramSocket();

            String ask_to_connect_to_server = "want to connect";
            String server_message = "";
            byte[] buffer = new byte[256];
            DatagramPacket send = new DatagramPacket(buffer, buffer.length, server_address, port);
            DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
            System.out.println("\nConnecting To DHCP Server... \n");

            buffer = ask_to_connect_to_server.getBytes();
            send = new DatagramPacket(buffer, buffer.length, server_address, port);
            datagramSocket.send(send);

            buffer = new byte[256];
            receive = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(receive);

            server_message = new String(receive.getData(),0,receive.getLength());

            /* Display the received string */
            System.out.println(server_message);

            System.out.println("Requesting an IP address from DHCP server\n");
            buffer = new byte[256];
            receive = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(receive);

            server_message = new String(receive.getData(),0,receive.getLength());
            ip = server_message;
            default_gateway = DHCP.default_gateway;
            subnet_mask = DHCP.subnet_mask;

            System.out.println("Your IP Address : " + ip + "\nand gateway is : " + default_gateway + "    subnet mask is : " + subnet_mask);

            /* Send IP to the server For Saving */
            buffer = ip.getBytes();
            send = new DatagramPacket(buffer, buffer.length, server_address, port);
            datagramSocket.send(send);

            System.out.println("\n* chat with DNS server *\n");
            String search_on_ip_domain = "";
            Scanner input= new Scanner(System.in);

            while (!search_on_ip_domain.equals("bye")) {

                try {

                    System.out.print("\nSearch on domain/ip on DNS table : ");
                    search_on_ip_domain = input.nextLine();
                    buffer = search_on_ip_domain.getBytes();
                    send = new DatagramPacket(buffer, buffer.length, server_address, port);
                    datagramSocket.send(send);
                    buffer = new byte[256];
                    receive = new DatagramPacket(buffer, buffer.length);
                    datagramSocket.receive(receive);
                    server_message = new String(receive.getData(),0,receive.getLength());
                    System.out.println(server_message);

                } catch(IOException i) {

                    System.out.println(i);

                }
            }



        } catch(IOException e){

            e.printStackTrace();

        } finally{

            // close connection
            if(datagramSocket != null){

                System.out.println("\nClosing connection");
                datagramSocket.close();

            }

        }

    }

    public static void main(String[] args) {

        new client().process();

    }

}
