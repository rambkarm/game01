package org.example;

import org.example.model.Profile;

public class OopLesson {
    public static void main(String[] args) {
        Profile profile =
                new Profile("sergey", 25, "ssaf@gmail.com", "abcdef");
        System.out.println(profile);
    }
}
