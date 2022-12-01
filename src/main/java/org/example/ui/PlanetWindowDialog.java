package org.example.ui;
;
import org.example.model.Character;
import org.example.model.EyeColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class PlanetWindowDialog extends JDialog implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    private Character character;


    JLabel nameLabel = new JLabel("Name: ");
    JLabel colourLabel = new JLabel("Color: ");
    JLabel massLabel = new JLabel("Mass: ");
    JLabel radiusLabel = new JLabel("Radius: ");
    JLabel satellitesLabel = new JLabel("Number of satellites: ");

    JTextField nameField = new JTextField(10);
    JComboBox<EyeColor> eyeColorField = new JComboBox<EyeColor>(EyeColor.values());
    JTextField hairColorField = new JTextField(10);
    JTextField hpField = new JTextField(10);
    JTextField genderField = new JTextField(10);

    JButton OKButton = new JButton("  OK  ");
    JButton CancelButton = new JButton("Cancel");

    public PlanetWindowDialog(Window parent, Character character) {
        super(parent, Dialog.ModalityType.DOCUMENT_MODAL);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(170, 250);
        setLocationRelativeTo(parent);
        setResizable(false);

        this.character = character;

        if (character == null) {
            setTitle("New planet");
        } else {
            setTitle(character.toString());
            nameField.setText(character.getName());
            genderField.setText("" + character.getGender());
            eyeColorField.setSelectedItem(character.getEyesColor());
            hairColorField.setText("" + character.getHairColor());
            hpField.setText("" + character.getHp());

        }
        OKButton.addActionListener(this);
        CancelButton.addActionListener(this);

        JPanel panel = new JPanel();

        nameLabel.setForeground(Color.white);
        colourLabel.setForeground(Color.white);
        massLabel.setForeground(Color.white);
        radiusLabel.setForeground(Color.white);
        satellitesLabel.setForeground(Color.white);

        panel.add(nameLabel);
        panel.add(nameField);

        panel.add(colourLabel);
        panel.add(eyeColorField);

        panel.add(massLabel);
        panel.add(hairColorField);

        panel.add(radiusLabel);
        panel.add(hpField);

        panel.add(satellitesLabel);
        panel.add(genderField);

        panel.add(OKButton);
        panel.add(CancelButton);

        setContentPane(panel);

        panel.setBackground(new Color(6, 6, 28));

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventsource = e.getSource();

        if (eventsource == OKButton) {
            try {
                if (character == null) {
                    character = new Character(nameField.getText(), genderField.getText(), eyeColorField.getToolTipText(),
                            hairColorField.getText(), Double.parseDouble(hpField.getText()));
                } else {
                    character.setName(nameField.getText());
                }


                dispose();
           // } catch (PlanetException exx) {
            //    JOptionPane.showMessageDialog(this, exx.getMessage(), "Unexpected error", JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Number format exception", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (eventsource == CancelButton) {
            dispose();
        }
    }

    public static Character createPlanet(Window parent) {
        PlanetWindowDialog dialog = new PlanetWindowDialog(parent, null);
        return dialog.character;
    }

    public static void changePlanet(Window parent, Character planet) {
        new PlanetWindowDialog(parent, planet);
    }
}
