package org.example;

public class RunApp {
    private static final String MENU = """
            1 -- Enter name
            9 -- Exit game""";
    private ConsoleUserDialog ui;

    public RunApp() {
        this.ui = new ConsoleUserDialog();
    }

    public static void main(String[] args) {
        new RunApp().run();
    }

    public void run() {
        int choice;
        do {
            ui.printMessage(MENU);
            choice = ui.enterInt("Choose choice menu item: ");
            ui.printMessage("your choice is: " + choice);
        } while (choice != 9);
    }
}