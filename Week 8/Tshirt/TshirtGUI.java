package week8.tshirt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TShirtGUI extends JFrame {
    private JPanel mianPanel;
    private JComboBox sizeComboBox;
    private JLabel selectedSizeLabel;
    private JComboBox<String> colorComboBox;
    private JLabel selectedColorLabel;

    TShirtGUI() {
        setTitle("T-Shirt Order Form");
        setContentPane(mianPanel);
        setPreferredSize(new Dimension(300, 200));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        sizeComboBox.addItem("Small");
        sizeComboBox.addItem("Medium");
        sizeComboBox.addItem("Large");


        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sizeSelected = (String) sizeComboBox.getSelectedItem();
                selectedSizeLabel.setText("Thanks you chose " + sizeSelected);
            }
        });

        String[] color = {"Red", "Blue", "Green"};

        // new combobox model with array of colors
        DefaultComboBoxModel<String> colorModel = new DefaultComboBoxModel(color);
        colorComboBox.setModel(colorModel);

        sizeComboBox.setSelectedIndex(-1); // unselected
        colorComboBox.setSelectedIndex(-1); // unselected

        System.out.println(colorModel.getIndexOf("Red"));
        if (colorModel.getIndexOf("White") == -1) {
            colorModel.addElement("White");
        }

        colorComboBox.addActionListener(event -> {  //Lambda Function
            String colorSelected = (String) colorComboBox.getSelectedItem();
            selectedColorLabel.setText("Thanks you chose " + colorSelected);
        });

//        colorComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String colorSelected = (String) colorComboBox.getSelectedItem();
//                selectedColorLabel.setText("Thanks you chose " + colorSelected);
//            }
//
//        });

        }

    }
