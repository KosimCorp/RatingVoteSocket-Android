package com.example.myapplication;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public Client (String ip, int port) throws Exception {
        socket = new Socket(ip, port);
        socket.setKeepAlive(true);
        socket.setReceiveBufferSize(1024 * 10);

        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public <T> T sendMessage(String input) throws Exception {
        output.writeObject(input);

        return readMessage();
    }

    public <T> T readMessage() throws Exception {
        return (T) input.readObject();
    }
}
