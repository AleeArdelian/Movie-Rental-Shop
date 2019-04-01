package movie.rental.client.tcp;

import java.io.IOException;
import java.net.Socket;

import movie.rental.common.HelloServiceException;
import movie.rental.common.Message;

public class TCPClient {

    private String host;
    private int port;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Message sendAndReceive(Message request) {
        try (
                var socket = new Socket(host, port);
                var is = socket.getInputStream();
                var os = socket.getOutputStream()
        ) {
            request.writeTo(os);
            System.out.println("client - sent request: " + request);

            Message response = Message.builder().build();
            response.readFrom(is);
            System.out.println("client - received response: " + response);

            return response;

        } catch (IOException e) {
            e.printStackTrace();
            throw new HelloServiceException("client - exception connecting to" +
                    " server", e);
        }
    }
}

