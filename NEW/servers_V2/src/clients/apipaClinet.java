package clients;

import servers.DHCP;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class apipaClinet {

    public String subnet_mask = "";
    public String default_gateway = "";
    public String ip = "";

    int port = 7777;

    public String auto_generate_ip() throws InterruptedException {

        String self_ip = "";
        String ip = "169.254.";
        int ip_base = 1;
        int ip_base_2 = 0;
        ArrayList<String> available_IPs = new ArrayList<>();
        System.out.println("\nOperating System Is Generating \n" );
        for (int i = 0; i < 256; i++) {

            for (int j = 0; j < 254; j++) {

                available_IPs.add(ip + ip_base_2 + "." + ip_base);
                ip_base ++;

            }
            ip_base = 1;
            ip_base_2++;

        }

        for (int i = 0; i < available_IPs.size(); i++) {

            int RANDOM_IP = (int)(Math.random() * available_IPs.size());
            self_ip = available_IPs.get(RANDOM_IP);
            available_IPs.remove(RANDOM_IP);

        }

        System.out.println("Your Current Private IP Address Is : " + self_ip + "\n");

        return self_ip;

    }

    private void process() {

        DatagramSocket datagramSocket = null;

        try{

            InetAddress SERVER_ADDRESS = InetAddress.getByName("localhost");
            datagramSocket = new DatagramSocket();

            String ask_to_connect_to_server = "want to connect";
            String server_message = "";
            if (!DHCP.server_status){

                System.out.println("\nNo DHCP server is running!");

                ip=auto_generate_ip();
                default_gateway = "10.10.2.1";
                subnet_mask = " 255.255.0.0";

                System.out.println("Your IP Address : " + ip + "\nand gateway is : " + default_gateway + "    subnet mask is : " + subnet_mask);

                Thread.sleep(5000);

            }

            byte[] buffer = new byte[256];
            DatagramPacket send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);
            DatagramPacket receive = new DatagramPacket(buffer, buffer.length);
            System.out.println("\nConnecting To DHCP Server... \n");

            buffer = ask_to_connect_to_server.getBytes();
            send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);
            datagramSocket.send(send);
            buffer = new byte[256];
            receive = new DatagramPacket(buffer, buffer.length);
            datagramSocket.receive(receive);
            server_message = new String(receive.getData(),0,receive.getLength());
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
            send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);
            datagramSocket.send(send);

            System.out.println("\n* chat with DNS server *\n");

            String search_on_ip_domain = "";
            Scanner input= new Scanner(System.in);

            /* keep reading until "stop" is input */
            while (!search_on_ip_domain.equals("bye")) {

                try {

                    System.out.print("\nSearch on domain/ip on DNS table : ");
                    search_on_ip_domain = input.nextLine();
                    buffer = search_on_ip_domain.getBytes();
                    send = new DatagramPacket(buffer, buffer.length, SERVER_ADDRESS, port);
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



        }
        catch(IOException e){

            e.printStackTrace();

        } catch (InterruptedException e) {

            throw new RuntimeException(e);

        } finally{

            // close connection
            if(datagramSocket != null){

                System.out.println("Closing connection");
                datagramSocket.close();

            }

        }

    }



    public static void main(String[] args){

        new apipaClinet().process();

    }


}
