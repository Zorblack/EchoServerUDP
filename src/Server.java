import java.net.*;

class Server
{
    public static void main(String[] args)
    {
        try
        {
            DatagramSocket ds = new DatagramSocket(2508);

            ds.setSoTimeout(1800000);

            while (true)
            {
                DatagramPacket pack = new DatagramPacket(new byte[512], 512);

                System.out.println("The server is ready to accept the message");

                ds.receive(pack);

                String inMessage = new String (pack.getData());

                inMessage.trim();

                System.out.println("The message from the client: " + inMessage);

                ds.connect(pack.getAddress(),pack.getPort());

                ds.send(pack);

                ds.disconnect();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
