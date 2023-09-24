package tcp_echo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TCPEchoServer {
    public static void main(String[] args) throws Exception{

        ServerSocketChannel welcomeChannel =
                ServerSocketChannel.open();
        welcomeChannel.socket().bind(new InetSocketAddress(135));

        while(true) {
            SocketChannel serveChannel = welcomeChannel.accept();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //read from TCP channel and write into the buffer
            int bytesRead = serveChannel.read(buffer);
            buffer.flip();
            byte[] a = new byte[bytesRead];
            buffer.get(a);
            System.out.println(new String(a));
            buffer.rewind();
            serveChannel.write(buffer);
            serveChannel.close();
        }
    }
}
