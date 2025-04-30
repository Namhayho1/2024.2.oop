package hust.soict.hespi.swing;

import javax.swing.*;        
import java.awt.*;          
import java.awt.event.*;     

public class SwingAccumulator extends JFrame {
    private JTextField tfInput;
    private JTextField tfOutput;
    private int sum = 0; 
  
    public SwingAccumulator() {
                Container cp = getContentPane();
        cp.setLayout(new GridLayout(2, 2, 5, 5)); 

        cp.add(new JLabel("Enter an Integer: "));

        tfInput = new JTextField(10);
        cp.add(tfInput);

        tfInput.addActionListener(new TFInputListener());

        cp.add(new JLabel("The Accumulated Sum is: "));

        tfOutput = new JTextField(10);
        tfOutput.setEditable(false);
        cp.add(tfOutput);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setTitle("Swing Accumulator");
        setSize(350, 120);
        setLocationRelativeTo(null); 
        setVisible(true); 
    }

    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingAccumulator(); 
            }
        });
    }

    private class TFInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                int numberIn = Integer.parseInt(tfInput.getText());
                sum += numberIn;                 tfInput.setText(""); 
                tfOutput.setText(sum + ""); 
            } catch (NumberFormatException ex) {
                tfInput.setText(""); 
                System.err.println("Invalid input: " + tfInput.getText()); 
            }
        }
    }
}