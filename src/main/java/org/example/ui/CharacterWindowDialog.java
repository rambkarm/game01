package org.example.ui;
;
import org.example.model.Character;
import org.example.model.CharacterColor;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class CharacterWindowDialog extends JDialog implements ActionListener {
    @Serial
    private static final long serialVersionUID = 1L;

    private Character character;


    JLabel nameLabel = new JLabel("Name: ");
    JLabel genderLabel = new JLabel("Gender: ");
    JLabel eyeColorLabel = new JLabel("Eye color: ");
    JLabel hairColorLabel = new JLabel("Hair color: ");
    JLabel hpLabel = new JLabel("HP: ");

    JTextField nameField = new JTextField(10);
    JTextField genderField = new JTextField(10);
    JComboBox<CharacterColor> eyeColorField = new JComboBox<CharacterColor>(CharacterColor.values());
    JComboBox<CharacterColor> hairColorField = new JComboBox<CharacterColor>(CharacterColor.values());
    JTextField hpField = new JTextField(10);

    JButton OKButton = new JButton("  OK  ");
    JButton CancelButton = new JButton("Cancel");

    public CharacterWindowDialog(Window parent, Character character) {
        super(parent, Dialog.ModalityType.DOCUMENT_MODAL);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(170, 250);
        setLocationRelativeTo(parent);
        setResizable(false);

        this.character = character;

        if (character == null) {
            setTitle("New character");
        } else {
            setTitle(character.toString());
            nameField.setText(character.getName());
            genderField.setText(character.getGender());
            eyeColorField.setSelectedItem(character.getEyeColor());
            hairColorField.setSelectedItem(character.getHairColor());
            hpField.setText("" + character.getHp());

        }
        OKButton.addActionListener(this);
        CancelButton.addActionListener(this);

        JPanel panel = new JPanel();

        nameLabel.setForeground(Color.white);
        genderLabel.setForeground(Color.white);
        eyeColorLabel.setForeground(Color.white);
        hairColorLabel.setForeground(Color.white);
        hpLabel.setForeground(Color.white);

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
                    character = new Character(nameField.getText(), genderField.getText(), eyeColorField.getSelectedItem().toString(),
                            hairColorField.getSelectedItem().toString(), Double.parseDouble(hpField.getText()));
                } else {
                    character.setName(nameField.getText());
                    character.setGender(genderField.getText());
                    character.setEyeColor(eyeColorField.getSelectedItem().toString());
                    character.setHairColor(hairColorField.getSelectedItem().toString());
                    character.setHp(Double.parseDouble(hpField.getText()));
                }

                dispose();
           // } catch (CharacterException exx) {
            //    JOptionPane.showMessageDialog(this, exx.getMessage(), "Unexpected error", JOptionPane.ERROR_MESSAGE);
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Number format exception", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (eventsource == CancelButton) {
            dispose();
        }
    }

    public static Character createCharacter(Window parent) {
        CharacterWindowDialog dialog = new CharacterWindowDialog(parent, null);
        return dialog.character;
    }

    public static void changeCharacter(Window parent, Character character) {
        new CharacterWindowDialog(parent, character);
    }
}
