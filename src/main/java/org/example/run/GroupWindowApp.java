package org.example.run;

import org.example.model.Character;
import org.example.model.CharacterException;
import org.example.ui.PlanetWindowDialog;
import org.example.ui.ViewGroupOfPlanets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serial;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

public class GroupWindowApp extends JDialog implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String AUTHOR = "Nazwa: Planety. Autor: Valeriia Tykhoniuk (266319). Data utworzenia: 15.11.2022";

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


    private final Vector<Character> currentGroupOfPlanets;
    JButton newPlanetButton = new JButton("New planet");
    JButton deletePlanetButton = new JButton("Delete planet");
    JButton editPlanetButton = new JButton("Change planet");
    JButton loadFromDocumentButton = new JButton("Read data from document");
    JButton saveToDocumentButton = new JButton("Write data to the document");
    JButton infoButton = new JButton("About author");
    JButton buttonSortMass = new JButton("Sort by the mass");
    JButton buttonSortColour = new JButton("Sort by the colour");

    ViewGroupOfPlanets viewList;

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

        currentGroupOfPlanets = group;

        viewList = new ViewGroupOfPlanets(currentGroupOfPlanets, 400, 250);
        viewList.refreshView();

        newPlanetButton.addActionListener(this);
        deletePlanetButton.addActionListener(this);
        editPlanetButton.addActionListener(this);
        loadFromDocumentButton.addActionListener(this);
        saveToDocumentButton.addActionListener(this);
        infoButton.addActionListener(this);

        buttonSortMass.addActionListener(this);
        buttonSortColour.addActionListener(this);

        JPanel panel = new JPanel();

        panel.add(viewList);

        panel.add(newPlanetButton);
        panel.add(deletePlanetButton);
        panel.add(editPlanetButton);
        panel.add(loadFromDocumentButton);
        panel.add(saveToDocumentButton);
        panel.add(infoButton);

        panel.add(buttonSortMass);
        panel.add(buttonSortColour);

        setContentPane(panel);

        panel.setBackground(new Color(186, 0, 255));

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

//        try {
            if (source == newPlanetButton) {
                Character newPlanet = PlanetWindowDialog.createPlanet(this);
                if (newPlanet != null) currentGroupOfPlanets.add(newPlanet);
            }

            if (source == editPlanetButton) {
                Iterator<Character> iterator = getIterator();
                if (iterator != null) {
                    PlanetWindowDialog.changePlanet(this, iterator.next());
                }
            }

            if (source == deletePlanetButton) {
                Iterator<Character> iterator = getIterator();
                if (iterator != null) {
                    iterator.next();
                    iterator.remove();
                }
            }

//            if (source == loadFromDocumentButton) {
//                JFileChooser fc = new JFileChooser();
//                int i = fc.showOpenDialog(this);
//                if (i == JFileChooser.APPROVE_OPTION) {
//                    File f = fc.getSelectedFile();
//                    Character planet = Character.readFromFile(f);
//                    currentGroupOfPlanets.add(planet);
//                }
//            }
//
//            if (source == saveToDocumentButton) {
//                Iterator<Character> iterator = getIterator();
//                if (iterator != null) {
//                    Character planet = iterator.next();
//                    String fileName = JOptionPane.showInputDialog("Write the name of document");
//                    if (fileName == null || fileName.equals("")) return;  // Cancel lub pusta nazwa pliku.
//                    Character.writeToTheDocument(fileName, planet);
//                }
//            }

//            if (source == buttonSortMass) {
//                currentGroupOfPlanets.sort(new Comparator<Character>() {
//                    @Override
//                    public int compare(Character o1, Character o2) {
//                        if (o1.getMass() < o2.getMass())
//                            return -1;
//                        if (o1.getMass() > o2.getMass())
//                            return 1;
//                        return 0;
//                    }
//                });
//            }
//
//            if (source == buttonSortColour) {
//                currentGroupOfPlanets.sort(new Comparator<Character>() {
//
//                    @Override
//                    public int compare(Character o1, Character o2) {
//                        return o1.getColour().toString().compareTo(o2.getColour().toString());
//                    }
//                });
//            }
//
//            if (source == infoButton) {
//                JOptionPane.showMessageDialog(this, AUTHOR);
//            }

//        } catch (CharacterException ex) {
//            JOptionPane.showMessageDialog(this, ex.getMessage(), "Unexpected error", JOptionPane.ERROR_MESSAGE);
//        }

        viewList.refreshView();
    }

    private Iterator<Character> getIterator() {
        int index = viewList.getSelectedIndex();
        if (index >= 0) {
            Iterator<Character> iterator = currentGroupOfPlanets.iterator();
            while (index-- > 0)
                iterator.next();
            return iterator;

        }
        return null;

    }
}

