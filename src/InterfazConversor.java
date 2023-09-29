import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

public class InterfazConversor extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6500040516728661020L;
	private JTextField inputField;
    private JComboBox<String> monedaDeComboBox;
    private JComboBox<String> monedaAComboBox;
    private JButton convertirButton;

    public InterfazConversor() {
        setTitle("Conversor de Monedas Alura");
        setSize(400, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel labelFrom = new JLabel("Convertir de:");
        labelFrom.setBounds(46, 27, 100, 25);
        getContentPane().add(labelFrom);

        monedaDeComboBox = new JComboBox<>(new String[]{"MXN", "EUR", "USD", "KRW", "JPY", "GBP"});
        monedaDeComboBox.setBounds(137, 27, 150, 25);
        getContentPane().add(monedaDeComboBox);

        JLabel labelTo = new JLabel("a:");
        labelTo.setBounds(97, 62, 50, 25);
        getContentPane().add(labelTo);

        monedaAComboBox = new JComboBox<>(new String[]{"MXN", "EUR", "USD", "KRW", "JPY", "GBP"});
        monedaAComboBox.setBounds(137, 62, 150, 25);
        getContentPane().add(monedaAComboBox);

        JLabel amountLabel = new JLabel("Cantidad:");
        amountLabel.setBounds(63, 97, 100, 25);
        getContentPane().add(amountLabel);

        inputField = new JTextField();
        inputField.setBounds(137, 97, 150, 25);
        getContentPane().add(inputField);

        convertirButton = new JButton("Convertir");
        convertirButton.setBounds(160, 132, 100, 30);
        getContentPane().add(convertirButton);
        
        JLabel lblNewLabel = new JLabel("Juan Sinuhe Vázquez Merlos");
        lblNewLabel.setBounds(217, 184, 169, 9);
        getContentPane().add(lblNewLabel);
        convertirButton.addActionListener(this);

    
        revalidate();
        repaint();
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertirButton) {
            String fromCurrency = (String) monedaDeComboBox.getSelectedItem();
            String toCurrency = (String) monedaAComboBox.getSelectedItem();
            double amount = Double.parseDouble(inputField.getText());

            try {
                double fromRate = ConversorMonedas.obtenerTasaCambio(fromCurrency);
                double toRate = ConversorMonedas.obtenerTasaCambio(toCurrency);

                double convertedAmount = amount * (toRate / fromRate);
                
                DecimalFormat df = new DecimalFormat("#.##"); 

                JOptionPane.showMessageDialog(this, amount + " " + fromCurrency + " equivale a " + df.format(convertedAmount) + " " + toCurrency, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al obtener la tasa de cambio", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        InterfazConversor interfaz = new InterfazConversor();
        interfaz.setVisible(true);
    }
}


