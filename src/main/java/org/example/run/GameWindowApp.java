package org.example.run;



import org.example.model.CharacterException;
import org.example.model.Character;
import org.example.ui.CharacterWindowDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serial;

public class GameWindowApp extends JFrame implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final String AUTHOR = "Raf";

    public static void main(String[] args) {
        new GameWindowApp();
    }

    private Character currentCharacter;


    //etykietyf
    JLabel nameLabel = new JLabel("Name: ");
    JLabel genderLabel = new JLabel("Gender: ");
    JLabel eyeColorLabel = new JLabel("Eye color: ");
    JLabel hairColorLabel = new JLabel("Hair color: ");
    JLabel hpLabel = new JLabel("HP: ");

    //pola
    JTextField nameField = new JTextField(10);
    JTextField genderField = new JTextField(10);
    JTextField eyeColorField = new JTextField(10);
    JTextField hairColorField = new JTextField(10);
    JTextField hpField = new JTextField(10);

    //przyciski
    JButton newCharacterButton = new JButton("New character");
    JButton deleteCharacterButton = new JButton("Delete character");
    JButton editCharacterButton = new JButton("Change character");
    JButton loadFromDocumentButton = new JButton("Read data from document");
    JButton saveToDocumentButton = new JButton("Write data to the document");
    JButton infoButton = new JButton("About author");
    JButton exitButton = new JButton("End program");
    JButton serializableWriteButton = new JButton("Write serializable data to the document");
    JButton serializableReadButton = new JButton("Read serializable data from the document");

    public GameWindowApp() {
        setTitle("CharacterWindowsApp");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        nameLabel.setForeground(Color.white);
        genderLabel.setForeground(Color.white);
        eyeColorLabel.setForeground(Color.white);
        hairColorLabel.setForeground(Color.white);
        hpLabel.setForeground(Color.white);

        nameField.setEditable(false);
        genderField.setEditable(false);
        eyeColorField.setEditable(false);
        hairColorField.setEditable(false);
        hpField.setEditable(false);

        newCharacterButton.addActionListener(this);
        deleteCharacterButton.addActionListener(this);
        editCharacterButton.addActionListener(this);
        loadFromDocumentButton.addActionListener(this);
        saveToDocumentButton.addActionListener(this);
        infoButton.addActionListener(this);
        exitButton.addActionListener(this);
        serializableWriteButton.addActionListener(this);
        serializableReadButton.addActionListener(this);

        JPanel panel = new JPanel();

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(genderLabel);
        panel.add(genderField);

        panel.add(eyeColorLabel);
        panel.add(eyeColorField);

        panel.add(hairColorLabel);
        panel.add(hairColorField);

        panel.add(hpLabel);
        panel.add(hpField);

        panel.add(newCharacterButton);
        panel.add(deleteCharacterButton);
        panel.add(editCharacterButton);
        panel.add(loadFromDocumentButton);
        panel.add(saveToDocumentButton);
        panel.add(infoButton);
        panel.add(exitButton);
        panel.add(serializableWriteButton);
        panel.add(serializableReadButton);

        panel.add(new JLabel(new ImageIcon("src/main/java/com/company/laboratorium02/01.jpg")));

        panel.setBackground(new Color(6, 6, 28));

        setContentPane(panel);

        showCurrentCharacter();

        setVisible(true);
    }

    void showCurrentCharacter() {
        if (currentCharacter == null) {
            nameField.setText("");
            genderField.setText("");
            eyeColorField.setText("");
            hairColorField.setText("");
            hpField.setText("");
        } else {
            nameField.setText(currentCharacter.getName());
            genderField.setText("" + currentCharacter.getGender());
            eyeColorField.setText("" + currentCharacter.getEyeColor());
            hairColorField.setText("" + currentCharacter.getHairColor());
            hpField.setText("" + currentCharacter.getHp());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        try {
            if (eventSource == newCharacterButton) {
                currentCharacter = CharacterWindowDialog.createCharacter(this);
            }
            if (eventSource == deleteCharacterButton) {
                currentCharacter = null;
            }
            if (eventSource == saveToDocumentButton) {
                String fileName = JOptionPane.showInputDialog("Write the name of document");
                if (fileName == null || fileName.equals("")) return;
//                Character.writeToTheDocument(fileName, currentCharacter); todo
            }

            if (eventSource == serializableWriteButton) {
                String fileName = JOptionPane.showInputDialog("Write the name of document");
                if (fileName == null || fileName.equals("")) return;
//                Character.writeObjectToTheDocument(fileName, currentCharacter); todo
            }

            if (eventSource == loadFromDocumentButton) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    currentCharacter = null; //Character.readFromFile(f); todo
                }
            }

            if (eventSource == serializableReadButton) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    currentCharacter = null; //Character.readObjectToTheDocument(f); todo
                }
            }

            if (eventSource == editCharacterButton) {
                if (currentCharacter == null) throw new CharacterException("No characters were changed.");
                CharacterWindowDialog.changeCharacter(this, currentCharacter);
            }

            if (eventSource == infoButton) {
                JOptionPane.showMessageDialog(this, AUTHOR);
            }

            if (eventSource == exitButton) {
                System.exit(0);
            }

        } catch (CharacterException exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage(), "Unexpected error", JOptionPane.ERROR_MESSAGE);
        }

        showCurrentCharacter();
    }
}


