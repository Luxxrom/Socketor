import java.io.*;
import java.net.*;

public class Phone {

    private ServerSocket   server;
    private Socket         client;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Phone(String port) {  //server

        try {
            server = new ServerSocket(Integer.parseInt(port));
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public Phone(Phone phoneServer) {
        client = phoneServer.accept();
        createStreams();
    }

    public Phone(String ip, String port) {

        try {
            client = new Socket(ip, Integer.parseInt(port));
            createStreams();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private Socket accept() {

        try {
            return server.accept();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private void createStreams() {

        try {

            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void writeLine(String message) {

        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public String readLine() {

        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void close() {

        try {
            reader.close();
            writer.close();
            client.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void closeServer() {
        try {
            server.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
