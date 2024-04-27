package drawing;

import drawing.shapes.FreeDraw;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ShapesTablePanel extends JPanel {
    private DefaultTableModel tableModel;
    private JTable table;
    private ShapeDetailsTablePanel shapeDetailsTablePanel;

    public ShapesTablePanel(ShapeDetailsTablePanel shapeDetailsTablePanel) {
        this.shapeDetailsTablePanel = shapeDetailsTablePanel;
        tableModel = new DefaultTableModel(new Object[]{"Index", "Shape Type", "Values", "Color"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        table.removeColumn(table.getColumn("Values"));
        table.removeColumn(table.getColumn("Color"));
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String values = (String) tableModel.getValueAt(selectedRow, 2);
                        String shapeType = (String) tableModel.getValueAt(selectedRow, 1);
                        String color = (String) tableModel.getValueAt(selectedRow, 3);
                        shapeDetailsTablePanel.updateDetails(values, shapeType, color, selectedRow);
                    } else {
                        shapeDetailsTablePanel.clear();
                    }
                }
            }
        });
    }

    public void addShape(int index, String shapeType, String values, String color) {
        //System.out.println(values);
        if (!values.contains("rad: " + FreeDraw.getRd()/2)) {
            tableModel.addRow(new Object[]{index, shapeType, values, color});
        }

    }

    public void updateShape(int index, String shapeType, String values, String color) {
        tableModel.setValueAt(index, index, 0);
        tableModel.setValueAt(shapeType, index, 1);
        tableModel.setValueAt(values, index, 2);
        tableModel.setValueAt(color, index, 3);
    }

    public JTable getTable() {
        return table;
    }

    public void clear() {
        tableModel.setRowCount(0);
    }
}