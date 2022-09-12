package booking.console;

import java.util.Scanner;

public class IOConsole implements Console {
    private final Scanner in = new Scanner(System.in);

    public Scanner getIn() {
        return in;
    }

    @Override
    public void print(String line) {
        System.out.print(line);
    }

    @Override
    public String readLn() {
        return in.nextLine();
    }

    @Override
    public int readInt() {
        return in.nextInt();
    }

}
