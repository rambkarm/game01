package org.example;

public class Character {
    private String name;
    private String gender;
    private String eyesColor;
    private String hairColor;
    private Double hp;

    public Character(String name, String gender, String eyesColor, String hairColor, Double hp) {
        this.name = name;
        this.gender = gender;
        this.eyesColor = eyesColor;
        this.hairColor = hairColor;
        this.hp = hp;
    }

    public Character() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEyesColor() {
        return eyesColor;
    }

    public void setEyesColor(String eyesColor) {
        this.eyesColor = eyesColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", eyesColor='" + eyesColor + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", hp=" + hp +
                '}';
    }
}
