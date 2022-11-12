package org.example;

import java.util.Scanner;

public class ConsoleUserDialog {
    public int enterInt(String message) {
        System.out.println(message);
        Scanner scaner = new Scanner(System.in);
        String value = scaner.nextLine();
        int result = Integer.parseInt(value);
        return result;
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}

