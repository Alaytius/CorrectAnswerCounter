

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.text.DecimalFormat;




public class gui extends JFrame {
    private JPanel mainPanel;
    private JButton Correct;
    private JButton Wrong;
    private JButton Reset;
    private JLabel Needed;
    private JLabel Current;
    private JSlider slider;
    private JLabel thresh;
    private JSpinner thresholdspinner;
    public static double cscore = 0;
    public static int currentcorrect = 0;
    public static int needed = 0;
    public static int total = 0;
    public double threshold = 0.85;
    public gui(String title){
        super(title);
        DecimalFormat dec = new DecimalFormat("#0.00");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        Correct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                threshold = (double)slider.getValue()/100;
                thresh.setText("Threshold: " + threshold*100 + "%");
                currentcorrect++;
                total++;
                cscore = ((double)currentcorrect/(double)total)*100;
                needed = (int)Math.ceil((threshold*total - currentcorrect)/(1 - threshold));
                if (needed < 0) needed = 0;
                Current.setText("Current Percent: " + dec.format(cscore) + "% (" + currentcorrect + "/" + total + ")");
                Needed.setText("Correct Answers Needed: " + needed);
            }
        });
        Wrong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                threshold = (double)slider.getValue()/100;
                thresh.setText("Threshold: " + threshold*100 + "%");
                total++;
                cscore = ((double)currentcorrect/(double)total)*100;
                needed = (int)Math.ceil((threshold*total - currentcorrect)/(1 - threshold));
                if (needed < 0) needed = 0;
                Current.setText("Current Percent: " + dec.format(cscore) + "% (" + currentcorrect + "/" + total + ")");
                Needed.setText("Correct Answers Needed: " + needed);
            }
        });
        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cscore = 0;
                currentcorrect = 0;
                total = 0;
                needed = 0;
                Current.setText("Current Percent: " + cscore + "% (" + currentcorrect + "/" + total + ")");
                Needed.setText("Correct Answers Needed: " + needed);
            }
        });
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                threshold = (double)slider.getValue()/100;
                thresh.setText("Threshold: " + (int)(threshold*100) + "%");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new gui("Correct Answer Counter");
        frame.setVisible(true);
    }


}
