package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomeScreen {
    public static void main(String args[]) {
      JFrame a = new JFrame("Stock Analyzer 3000");
      JLabel ta = new JLabel(Options.START.getString());
      JButton b = new JButton("New Portfolio");
      JButton c = new JButton("Open Portfolio");
      c.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          JOptionPane.showMessageDialog(null, "You clicked Open Portfolio");
        }
      });
      ta.setBounds(80, 40, 300, 20);
      b.setBounds(40, 90, 100, 20);
      c.setBounds(160, 90, 100, 20);
      a.add(ta);
      a.add(b);
      a.add(c);
      a.setSize(300,300);
      a.setLayout(null);
      a.setVisible(true);
    }
}
