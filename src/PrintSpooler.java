import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ExceptionListener;
import java.util.LinkedList;
import java.util.Queue;

class PrintSpooler extends JFrame {
    // Queue queue = new Queue();


    // Create a queue to hold print jobs

    // Create UI components
    private Queue<String> printQueue = new LinkedList<>();
    private JLabel jobLabel = new JLabel("Enter print job: ");
    private JTextField jobField = new JTextField(20);
    private JButton addButton = new JButton("Add Job");
    private JButton printButton = new JButton("Print Next Job");
    private JButton clearButton = new JButton("Clear Queue");
    private JLabel statusLabel = new JLabel("Status: ");
    private JTextArea queueArea = new JTextArea(10, 30);
    private JScrollPane scrollPane = new JScrollPane(queueArea);
    private TitledBorder border = new TitledBorder("Print Queue");

    public PrintSpooler() {

        //Creating a queue to hold the print jobs


        // Set up JFrame
        setTitle("Print Spooler");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JPanel to hold the UI components
        JPanel panel = new JPanel();

        // Add components to the panel
        panel.add(jobLabel);
        panel.add(jobField);
        panel.add(addButton);
        panel.add(printButton);
        panel.add(clearButton);
        panel.add(statusLabel);

        // Add scrollPane to the panel
        scrollPane.setBorder(border);
        panel.add(scrollPane);
        queueArea.setEditable(false);

        // Add action listeners to the buttons
        addButton.addActionListener(new AddButtonListener());
        printButton.addActionListener(new PrintButtonListener());
        clearButton.addActionListener(new ClearButtonListener());

        // Add the panel to the JFrame
        add(panel);

        // Show the JFrame
        setVisible(true);
    }

    // Inner class for the Add Job button listener
    private class AddButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
// try & catch statement to check for empty fields
            try {
                String job = jobField.getText();
                if (job.isEmpty()) {
                    throw new IllegalArgumentException("Add a job");
                }
                printQueue.add(job);
                queueArea.append(job + "\n");
                statusLabel.setText("Status: Job has been added");
                jobField.setText(""); //clearing the textfield


            } catch (IllegalArgumentException exception) {
                statusLabel.setText("error." + exception.getMessage());
            }
        }
            }


        private class PrintButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {

                if (printQueue.isEmpty()) {
                    statusLabel.setText("Status: No jobs in queue");
                } else {
                    statusLabel.setText("Status: Printing job");
                    String job = printQueue.remove();
                    statusLabel.setText("Status: Printed Job:" + job);
                    queueArea.setText(queueArea.getText().replace(job + "\n", ""));
                    jobField.setText(""); //clearing the textfield

                }
            }
//
        }


        private class ClearButtonListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                if (printQueue.isEmpty()) {
                    statusLabel.setText("Status: No Job in Queue");
                } else {
                    printQueue.clear();
                    queueArea.setText("");
                    statusLabel.setText("Status: Jobs cleared"); //updating the status
                }

            }
        }

    }


