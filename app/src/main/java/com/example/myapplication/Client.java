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

        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void sendMessage(String input) throws Exception {
        output.write(input.getBytes());
    }

    public <T> T readMessage() throws Exception {
        if (input == null)
            input = new ObjectInputStream(socket.getInputStream());

        return (T) input.readObject();
    }
}
