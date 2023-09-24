package tcp_echo;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class TCPEhoClient {
    public static void main(String[] args) throws Exception{
        if(args.length != 2){
            System.out.println(
                    "Syntax: TCPEchoClient <ServerIP> <ServerPort>");
            return;

        }
        int serverPort = Integer.parseInt(args[1]);

        Scanner keyboard = new Scanner(System.in);
        String message = keyboard.nextLine();
//ifelse

        SocketChannel channel = SocketChannel.open();
        channel.connect(
                new InetSocketAddress(args[0], serverPort));

        ByteBuffer buffer =
                ByteBuffer.wrap(message.getBytes());
        //read the buffer content and
        //write that to TCP channel
        channel.write(buffer);

        int bytesRead = channel.read(buffer);
        buffer.flip();
        byte[] b = new byte[bytesRead];
        buffer.get(b);
        String clientMessage = new String(b);
        System.out.println("Message from client" + clientMessage);



    }
}
