class Greeting {
    static void print(String str, int sleep) throws InterruptedException {
        for (char c : str.toCharArray()) {
            System.out.print(c);
            Thread.sleep(sleep);
        }
    }

    static void printSymbol(String str, char symbol, int sleep) throws InterruptedException {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
            } else {
                System.out.print(symbol);
                Thread.sleep(sleep);
            }
        }
    }
}

public class Practice {
    public static void main(String[] args) {
        String string1 = "Welcome to CSTAD!";
        String string2 = "Don't give up this opportunity, do your best first";
        String string3 = "Downloading";
        int sleep = 300;

        Thread thread1 = new Thread(() -> {
            try {
                Greeting.print(string1, sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                thread1.join(); // wait for thread1 to finish
                System.out.println(); // ensure a new line
                Greeting.printSymbol(string2, '*', sleep);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                thread2.join(); // wait for thread 2 to finish
                System.out.println(); // ensure a new line
                Greeting.print(string2, sleep);
                System.out.println(); // ensure a new line
                Greeting.printSymbol(string2, '-', sleep);
                System.out.println(); // ensure a new line
                System.out.print("\n" + string3);
                Greeting.printSymbol(string3, '.', sleep);
                System.out.println("Complete 100%.");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
