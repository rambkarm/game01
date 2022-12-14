package org.example.run;

import org.example.model.Character;
import org.example.ui.CharacterWindowDialog;
import org.example.ui.ViewGroupOfCharacters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serial;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import static org.example.run.GameWindowApp.AUTHOR;

public class GroupWindowApp extends JDialog implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {
        Vector<Character> group = new Vector<Character>();
//        try {
            Character availableCharacter;
            availableCharacter = new Character("misha", "male", "brown", "blue", 88.09);
            group.add(availableCharacter);

            availableCharacter = new Character("masha", "female", "brown", "blue", 88.09);
            group.add(availableCharacter);

            availableCharacter = new Character("sasha", "male", "brown", "blue", 88.09);
            group.add(availableCharacter);

//        } catch (CharacterException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Unexpected error", JOptionPane.ERROR_MESSAGE);
//        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GroupWindowApp(null, group);
            }
        });
    }


    private final Vector<Character> currentGroupOfCharacters;
    JButton newCharacterButton = new JButton("New character");
    JButton deleteCharacterButton = new JButton("Delete character");
    JButton editCharacterButton = new JButton("Change character");
    JButton loadFromDocumentButton = new JButton("Read data from document");
    JButton saveToDocumentButton = new JButton("Write data to the document");
    JButton infoButton = new JButton("About author");
    JButton buttonSortHP = new JButton("Sort by the HP");
    JButton buttonSortEyeColor = new JButton("Sort by the eye color");

    ViewGroupOfCharacters viewList;

    public GroupWindowApp(Window parent, Vector<Character> group) {

        super(parent, ModalityType.DOCUMENT_MODAL);

        setTitle("Changing the group");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setSize(450, 450);
        setResizable(false);
        if (parent != null) {
            Point location = parent.getLocation();
            location.translate(100, 100);
            setLocation(location);
        } else setLocationRelativeTo(null);

        currentGroupOfCharacters = group;

        viewList = new ViewGroupOfCharacters(currentGroupOfCharacters, 400, 250);
        viewList.refreshView();

        newCharacterButton.addActionListener(this);
        deleteCharacterButton.addActionListener(this);
        editCharacterButton.addActionListener(this);
        loadFromDocumentButton.addActionListener(this);
        saveToDocumentButton.addActionListener(this);
        infoButton.addActionListener(this);

        buttonSortHP.addActionListener(this);
        buttonSortEyeColor.addActionListener(this);

        JPanel panel = new JPanel();

        panel.add(viewList);

        panel.add(newCharacterButton);
        panel.add(deleteCharacterButton);
        panel.add(editCharacterButton);
        panel.add(loadFromDocumentButton);
        panel.add(saveToDocumentButton);
        panel.add(infoButton);

        panel.add(buttonSortHP);
        panel.add(buttonSortEyeColor);

        setContentPane(panel);

        panel.setBackground(new Color(186, 0, 255));

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

//        try {
            if (source == newCharacterButton) {
                Character newCharacter = CharacterWindowDialog.createCharacter(this);
                if (newCharacter != null) currentGroupOfCharacters.add(newCharacter);
            }

            if (source == editCharacterButton) {
                Iterator<Character> iterator = getIterator();
                if (iterator != null) {
                    CharacterWindowDialog.changeCharacter(this, iterator.next());
                }
            }

            if (source == deleteCharacterButton) {
                Iterator<Character> iterator = getIterator();
                if (iterator != null) {
                    iterator.next();
                    iterator.remove();
                }
            }

            if (source == loadFromDocumentButton) {
                JFileChooser fc = new JFileChooser();
                int i = fc.showOpenDialog(this);
                if (i == JFileChooser.APPROVE_OPTION) {
                    File f = fc.getSelectedFile();
                    Character character = null; // Character.readFromFile(f);  todo
                    currentGroupOfCharacters.add(character);
                }
            }

            if (source == saveToDocumentButton) {
                Iterator<Character> iterator = getIterator();
                if (iterator != null) {
                    Character character = iterator.next();
                    String fileName = JOptionPane.showInputDialog("Write the name of document");
                    if (fileName == null || fileName.equals("")) return;  // Cancel lub pusta nazwa pliku.
//                    Character.writeToTheDocument(fileName, character); todo
                }
            }

            if (source == buttonSortHP) {
                currentGroupOfCharacters.sort(new Comparator<Character>() {
                    @Override
                    public int compare(Character o1, Character o2) {
                        return (o1.getName().compareTo(o2.getName()));
                    }
                });
            }

            if (source == buttonSortEyeColor) {
                currentGroupOfCharacters.sort(new Comparator<Character>() {

                    @Override
                    public int compare(Character o1, Character o2) {
                        return o1.getEyeColor().compareTo(o2.getEyeColor());
                    }
                });
            }

            if (source == infoButton) {
                JOptionPane.showMessageDialog(this, AUTHOR);
            }

//        } catch (CharacterException ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage(), "Unexpected error", JOptionPane.ERROR_MESSAGE);
//        }

        viewList.refreshView();
    }

    private Iterator<Character> getIterator() {
        int index = viewList.getSelectedIndex();
        if (index >= 0) {
            Iterator<Character> iterator = currentGroupOfCharacters.iterator();
            while (index-- > 0)
                iterator.next();
            return iterator;

        }
        return null;

    }
}

