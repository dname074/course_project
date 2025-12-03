package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataReader {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getTextFromUser() {
        return scanner.nextLine();
    }

    public static int getIntFromUser() {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                DataPrinter.print("Musi zostać podana liczba");
                scanner.nextLine();
            }
        }

    }
}
