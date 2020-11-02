import java.io.*;

public class Socketor {
    public static void main(String[] args) throws IOException {

        if (args.length < 3) {
            System.out.println("Usage:\n" +
                    "java Socketor server 8000 2 / \n" +
                    "java Socketor client 127.0.0.1 8000 45 35");
            return;
        }

        Socketor socketor = new Socketor();

        if (args[0].equals("server")) socketor.runServer(args[1], args[2], args[3]);

        if (args[0].equals("client")) socketor.runClient(args[1], args[2], args[3], args[4]);

    }

    private void runServer(String port, String threadsCount, String operation) throws IOException {

        int threads = Integer.parseInt(threadsCount);
        Phone phone = new Phone(port);

        System.out.println("Started server with " + operation + " on " + port);

        for (int j = 0; j < threads; j++)
            new Thread(new ServerPhone(new Phone(phone), operation)).start();


     }

    private void runClient(String ip, String port, String a, String b) throws IOException {

        Phone phone = new Phone(ip, port);
        phone.writeLine(a);
        phone.writeLine(b);
        String answer = phone.readLine();
        System.out.println(answer);
        phone.close();

    }

 }
