package drawing;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ShapeDetailsTablePanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public ShapeDetailsTablePanel() {
        String[] columnNames = {"Property", "Value"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int currentShapeIndex = getCurrentShapeIndex();
                    //System.out.println(currentShapeIndex);
                    String newValues = getNewValues();
                    //System.out.println(newValues);
                    SvgOutput.updateShape(currentShapeIndex, newValues);
                }
            }
        });
    }

    public String getNewValues() {
        StringBuilder newValues = new StringBuilder();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            newValues.append(tableModel.getValueAt(i, 1));
            if (i != tableModel.getRowCount() - 1) {
                newValues.append(", ");
            }
        }
        return newValues.toString();
    }

    public void updateDetails(String values, String shapeType, String color, int index) {
        clear();
        addDetail("Shape Type", shapeType);
        addDetail("Index", String.valueOf(index));
        addDetail("Color", color);
        if (shapeType.equals("Triangle")) {
            values = values.replace("X: [", "").replace("], Y: [", ", ").replace("]", "");
            String[] coordinates = values.split(", ");
            for (int i = 0; i < 3; i++) {
                addDetail("X" + (i + 1), coordinates[i]);
            }
            for (int i = 3; i < 6; i++) {
                addDetail("Y" + (i - 2), coordinates[i]);
            }
        } else {
            String[] valueArray = values.split(", ");
            for (String value : valueArray) {
                String[] propertyValue = value.split(": ");
                addDetail(propertyValue[0], propertyValue[1]);
            }
        }
    }

    public int getCurrentShapeIndex() {
        String value = (String) tableModel.getValueAt(1,1);
        return Integer.parseInt(value);
    }

    public void addDetail(String property, String value) {
        tableModel.addRow(new Object[]{property, value});
    }

    public void clear() {
        tableModel.setRowCount(0);
    }
}