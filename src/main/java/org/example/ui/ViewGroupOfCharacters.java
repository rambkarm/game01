package org.example.ui;

import org.example.model.Character;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.Serial;
import java.util.Vector;

public class ViewGroupOfCharacters extends JScrollPane {

    @Serial
    private static final long serialVersionUID = 1L;

    private Vector<Character> group;
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewGroupOfCharacters(Vector<Character> group, int width, int height) {
        this.group = group;
        setPreferredSize(new Dimension(width, height));
        setBorder(BorderFactory.createTitledBorder("All characters:"));

        String[] tableHeader = {"Name", "Gender", "Eyes color", "Hair color", "HP"};
        tableModel = new DefaultTableModel(tableHeader, 0);
        table = new JTable(tableModel) {

            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        setViewportView(table);
    }

    public void refreshView() {
        tableModel.setRowCount(0);
        for (Character c : group) {
            String[] row = {c.getName(), c.getName(), c.getGender(),
                    c.getEyesColor(), c.getHairColor(), c.getHp().toString()};
            tableModel.addRow(row);
        }
    }

    public int getSelectedIndex() {
        int index = table.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "No character was chosen", "Unexpected error", JOptionPane.ERROR_MESSAGE);
        }
        return index;
    }

}
