package org.example.run;

import org.example.model.Character;
import org.example.ui.ConsoleUserDialog;
import org.example.model.Profile;

public class RunApp {
    private static final String MENU = """
            1 -- profile menu
            2 -- character menu
            3 -- games
            4 -- load
            5 -- save
            9 -- exit game""";

    private static final String PROFILE_MENU = """
            1 -- enter name
            2 -- enter age
            3 -- enter email
            4 -- enter password
            9 -- back to main menu""";

    private static final String CHARACTER_MENU = """
            1 -- enter name
            2 -- enter gender
            3 -- enter eyes color
            4 -- enter hair color
            9 -- back to main menu""";
    private final ConsoleUserDialog ui;

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
            choice = ui.enterInt("choose choice menu item: ");
            ui.printMessage("your choice is: " + choice);
            if (choice == 1) {
                createProfile();
            }
            if (choice == 2) {
                createCharacter();
            }
        } while (choice != 9);
    }

    private void createProfile() {
        ui.printMessage("---profile menu---");
        String name = ui.enterString("enter name: ");
        int age = ui.enterInt("enter age: ");
        String email = ui.enterString("enter email: ");
        String password = ui.enterString("enter password: ");
        ui.printMessage("---profile was updated---");
        Profile profile = new Profile(name, age, email, password);
        ui.printMessage(profile.toString());
    }

    private void createCharacter() {
        ui.printMessage("---character menu---");
        String name = ui.enterString("enter name: ");
        String gender = ui.enterString("enter gender:");
        String eyesColor = ui.enterString("enter eyes color: ");
        String hairColor = ui.enterString("enter hair color: ");
        double hp = ui.enterDouble("enter hp level: ");
        ui.printMessage("---character was updated---");
        Character character = new Character(name, gender, eyesColor, hairColor, hp);
        ui.printMessage(character.toString());
    }
}