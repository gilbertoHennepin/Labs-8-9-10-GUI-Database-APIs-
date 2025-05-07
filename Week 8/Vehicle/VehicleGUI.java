package week8.vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehicleGUI extends JFrame {

    private JPanel mainPanel;
    private JTextField nameTextField;
    private JTextField malkeTextField;
    private JTextField modelTextField;
    private JCheckBox electricCheckBox;
    private JButton addButton;
    private JList vehicleList;
    private JButton deleteButton1;

    private DefaultListModel <Vehicle> vehicleListModel;

    VehicleGUI() {
        setTitle("Vehicle");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        vehicleListModel = new DefaultListModel<>();
        vehicleList.setModel(vehicleListModel);
        vehicleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // only one vehicle at a time


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String make = malkeTextField.getText();
                String model = modelTextField.getText();

                if (name.isBlank() || make.isBlank() || model.isBlank()) {
                    JOptionPane.showMessageDialog(VehicleGUI.this, "Please fill all the fields");
                    return;
                }

                boolean isElectric = electricCheckBox.isSelected();
                Vehicle vehicle = new Vehicle(name, make, model, isElectric);
                vehicleListModel.addElement(vehicle);

                nameTextField.setText("");
                malkeTextField.setText("");
                modelTextField.setText("");
                electricCheckBox.setSelected(false);
            }
        });

        deleteButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // -1 if nothing is selected
                int selectedVehicleIndex = vehicleList.getSelectedIndex();

                if (selectedVehicleIndex != -1) {
                    Vehicle selectedVehicle = vehicleListModel.getElementAt(selectedVehicleIndex);

                    if (JOptionPane.showConfirmDialog(VehicleGUI.this,
                            "Delete" + selectedVehicle.getName() + "?",
                            "Delete Vehicle", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        vehicleListModel.remove(selectedVehicleIndex);
                    }
                }


            }
        });
    }
}
