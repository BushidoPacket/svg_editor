package drawing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShapesTablePanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;

    public ShapesTablePanel() {
        tableModel = new DefaultTableModel(new Object[]{"Shape Type", "Values", "Color"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(10);
    }

    public void addShape(String shapeType, String values, String color) {
        tableModel.addRow(new Object[]{shapeType, values, color});
    }

    public void updateShape(int index, String shapeType, String values, String color) {
        tableModel.setValueAt(shapeType, index, 0);
        tableModel.setValueAt(values, index, 1);
        tableModel.setValueAt(color, index, 2);
    }

    public JTable getTable() {
        return table;
    }

    public void clear() {
        tableModel.setRowCount(0);
    }
}