public class ServerPhone implements Runnable {

    Phone phone;
    String operation;

    public ServerPhone(Phone phone, String operation) {
        this.phone = phone;
        this.operation = operation;
    }

    @Override
    public void run() {

        while (true) {
            System.out.println("Waiting for client...");
            phone.accept();
            String a = phone.readLine();
            String b = phone.readLine();
            int result = calculate(operation, a, b);
            String message = a + " " + operation + " " + b + " = " + result;
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
            }
            phone.writeLine(message);
            System.out.println("Accepted: " + message);
            phone.close();
        }
    }

    private int calculate(String operation, String a, String b) {

        int x = Integer.parseInt(a);
        int y = Integer.parseInt(b);
        switch (operation) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "/":
                return x / y;
            case "*":
            default:
                return x * y;

        }

    }

}
