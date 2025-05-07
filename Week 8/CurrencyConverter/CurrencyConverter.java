package week8.currency_converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class CurrencyConverter extends JFrame {

    private JTextField dollarsTextField;
    private JButton convertButton;
    private JLabel conversionLabel;
    private JPanel mainPanel;
    private JComboBox <String> currencyComboBox;



    private final String  PESOS = "Pesos"; //final means ur not going to change them
    private final String EUROS = "Euros";

    private Map<String, Double> exchangeRates = Map.of(EUROS, 0.84, PESOS, 19.9);

    CurrencyConverter() {
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(500, 450));
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getRootPane().setDefaultButton(convertButton);

        currencyComboBox.addItem(PESOS);
        currencyComboBox.addItem(EUROS);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // what was typed in the JTextField dolllarsetField?
                String dollarsString = dollarsTextField.getText();

                try {
                    // convert to a number
                    double dollars = Double.parseDouble(dollarsString);
                    String toCurrency = (String) currencyComboBox.getSelectedItem();

                    double exchangeRate = exchangeRates.get(toCurrency);


                    // convert to target currency
                    double convertedValue = dollars * exchangeRate;
                    // display result in conversionLabel JLabel
                    String resultString = String.format("%.2f dollars is equivalent to %.2f %s",
                            dollars, convertedValue, toCurrency);
                    conversionLabel.setText(resultString);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(CurrencyConverter.this,
                            "Please enter a number without any $ or other characters. ");
                }
            }
        });
    }
}
