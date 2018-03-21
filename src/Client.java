import java.net.*;
import java.io.*;
import java.util.Scanner;

class Client
{

    private String host;

    private int port;

    Client(String host, int port)
    {

        this.host = host;

        this.port = port;
    }

    private void sendMessage(String mes)
    {
        try
        {
            byte[] data = mes.getBytes();

            InetAddress address = InetAddress.getByName(host);

            DatagramPacket outPack = new DatagramPacket(data, data.length, address, port);

            DatagramSocket ds = new DatagramSocket();

            ds.connect(address,port);

            ds.send(outPack);

            ds.disconnect();

            DatagramPacket inPack = new DatagramPacket(new byte[512], 512);

            ds.setSoTimeout(1000);

            ds.receive(inPack);

            System.out.println("The server responds: " + new String(inPack.getData()));

        }

         catch(IOException e)
        {
            System.err.println(e);
        }

    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        Boolean quit = false;

        String message;

        System.out.println("Enter the host address");

        String host = sc.next();

        System.out.println("Enter the port");

        int port = sc.nextInt();

        System.out.println("The client will send a message to: " + host + ":" + port);

        System.out.println("To shut down the client, type <quit>");

        Client client = new Client(host, port);

        while (!quit)
        {
            System.out.println("The client waits for the message");

            message = sc.next();

            if (message.equals("quit"))
            {
                quit = true;
            }
            else
            {
                client.sendMessage(message);
            }
        }
    }
}
