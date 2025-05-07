package week8.todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ToDOList extends JFrame{
    private JPanel rootPanel;
    private JTextField newToDoTextField;
    private JButton addToDobutton;
    private JList<String> toDoList;
    private JButton deleteSelectedButton;
    private DefaultListModel<String> listModel;
    private JPopupMenu rightClickMenu;
    private JMenuItem deleteSelectedMenuItem;

    ToDOList(){
        setTitle("To do list");
        setContentPane(rootPanel);
        setPreferredSize(new Dimension(550,250));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        toDoList.setModel(listModel);

        toDoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        getRootPane().setDefaultButton(addToDobutton); // enter = add


        JPopupMenu rightClickMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        rightClickMenu.add(deleteItem);

        toDoList.setComponentPopupMenu(rightClickMenu);

        addListeners();


    }

    private void addListeners(){

        addToDobutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newTodo = newToDoTextField.getText();
                newTodo = newTodo.trim(); // remove whitespace
                 if (newTodo.isEmpty()) {
                    JOptionPane.showMessageDialog(ToDOList.this, "Enter a todo Item ");
                } else {
                    listModel.addElement(newTodo);
                    newToDoTextField.setText(""); // empty String
                }
            }
        });

        deleteSelectedMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = toDoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showConfirmDialog(ToDOList.this, "Select an item to delete");
                }
            }
        });

        toDoList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selection= toDoList.locationToIndex(e.getPoint()); // which JList item ud er mouse click>
                toDoList.setSelectedIndex(selection);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                toDoList.setBackground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                toDoList.setBackground(Color.GRAY);
            }
        });
//      TODO AAAAAAAAAAAAAAAAAAAAAA

        toDoList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selection = toDoList.locationToIndex(e.getPoint());
                toDoList.setSelectedIndex(selection);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // add this code - a copy of the code in mouseClicked
                // if mouseClicked doesn't seem to have an effect.
                int selection = toDoList.locationToIndex(e.getPoint());
                toDoList.setSelectedIndex(selection);
            }

            // Even though we don't need these events to do anything, still
            // required to provide the methods.
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {
                toDoList.setBackground(Color.CYAN);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                toDoList.setBackground(Color.GRAY);
            }
        });



        deleteSelectedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // what was selected?
                int selectedIndex = toDoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String itemToDelete = toDoList.getSelectedValue();
                    if (JOptionPane.showConfirmDialog(ToDOList.this, "Delete" + itemToDelete + "?", "Delete", JOptionPane.OK_CANCEL_OPTION)
                            == JOptionPane.OK_OPTION) {
                    listModel.remove(selectedIndex);
                    }
                } else {
                    JOptionPane.showConfirmDialog(ToDOList.this, "Select an item to delete");
                }
            }
        });
//        toDoList.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                // what was selected?
//                int selectedIndex = toDoList.getSelectedIndex(); // -1 if nothing selected
//                if (selectedIndex != -1) {
//                    String itemToDelete = toDoList.getSelectedValue();
//
//                    if (JOptionPane.showConfirmDialog(ToDOList.this, "Delete" + itemToDelete + "?", "Delete", JOptionPane.OK_CANCEL_OPTION)
//                            == JOptionPane.OK_OPTION) {
//                        listModel.remove(selectedIndex);
//                    }
//                }
//            }
//        });
    }

}
