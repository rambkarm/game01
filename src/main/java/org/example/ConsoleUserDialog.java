package org.example;

import java.util.Scanner;

public class ConsoleUserDialog {
    public int enterInt(String message) {
        boolean isError;
        int result = 0;
        do {
            isError = false;
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            String value = scanner.nextLine();
            try {
                result = Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                isError = true;
                System.out.println("Error! Please enter int value.");
            }
        } while (isError);

        return result;
    }

    public Double enterDouble(String message) {
        boolean isError;
        double result = 0.0;
        do {
            isError = false;
            System.out.println(message);
            Scanner scanner = new Scanner(System.in);
            String value = scanner.nextLine();
            try {
                result = Double.parseDouble(value);
            } catch (NumberFormatException ex) {
                isError = true;
                System.out.println("Error! Please enter double value.");
            }
        } while (isError);

        return result;
    }

    public String enterString(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        return value;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }


}


