import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class Customer {
    String customerId;
    String name;
    String hostelName;
    String course;
    String mobileNumber;
    boolean enrolled;
    boolean messCut;
    int totalCost;

    public Customer(String customerId, String name, String hostelName, String course, String mobileNumber) {
        this.customerId = customerId;
        this.name = name;
        this.hostelName = hostelName;
        this.course = course;
        this.mobileNumber = mobileNumber;
        this.enrolled = false;
        this.messCut = false;
        this.totalCost = 0;
    }
}

public class MessManagementSystemGUI extends JFrame implements ActionListener {
    private Map<String, Customer> customers;
    private JTextField customerIdField, nameField, hostelNameField, courseField, mobileNumberField, miscelleneousField;
    private JButton enrollButton, logEntryButton, messCutButton, markAbsentButton, generateBillButton, miscellaneousNotesButton, displayAllBillsButton;
    private JTextArea outputArea;

    public MessManagementSystemGUI() {
        this.customers = new HashMap<>();
        setTitle("Mess Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create a panel to contain all components
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JScrollPane scrollPane = new JScrollPane(panel);
        setContentPane(scrollPane);

        // Set size of the panel to be larger than the frame
        panel.setPreferredSize(new Dimension(1200, 800));

        // Place components on the panel
        JLabel customerIdLabel = new JLabel("Customer ID:");
        customerIdLabel.setBounds(20, 20, 100, 20);
        panel.add(customerIdLabel);
        customerIdField = new JTextField();
        customerIdField.setBounds(120, 20, 200, 20);
        panel.add(customerIdField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 50, 100, 20);
        panel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(120, 50, 200, 20);
        panel.add(nameField);

        JLabel hostelNameLabel = new JLabel("Hostel Name:");
        hostelNameLabel.setBounds(20, 80, 100, 20);
        panel.add(hostelNameLabel);
        hostelNameField = new JTextField();
        hostelNameField.setBounds(120, 80, 200, 20);
        panel.add(hostelNameField);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(20, 110, 100, 20);
        panel.add(courseLabel);
        courseField = new JTextField();
        courseField.setBounds(120, 110, 200, 20);
        panel.add(courseField);

        JLabel mobileNumberLabel = new JLabel("Mobile Number:");
        mobileNumberLabel.setBounds(20, 140, 100, 20);
        panel.add(mobileNumberLabel);
        mobileNumberField = new JTextField();
        mobileNumberField.setBounds(120, 140, 200, 20);
        panel.add(mobileNumberField);

        JLabel miscelleneousLabel = new JLabel("Miscelleneous :");
        miscelleneousLabel.setBounds(550, 10, 100, 20);
        panel.add(miscelleneousLabel);
        miscelleneousField = new JTextField();
        miscelleneousField.setBounds(550, 30, 350, 150);
        panel.add(miscelleneousField);

        enrollButton = new JButton("Enroll Customer");
        enrollButton.setBounds(350, 20, 150, 20);
        enrollButton.addActionListener(this);
        panel.add(enrollButton);

        logEntryButton = new JButton("Log Entry");
        logEntryButton.setBounds(350, 50, 150, 20);
        logEntryButton.addActionListener(this);
        panel.add(logEntryButton);

        messCutButton = new JButton("Mark Mess Cut");
        messCutButton.setBounds(350, 80, 150, 20);
        messCutButton.addActionListener(this);
        panel.add(messCutButton);

        markAbsentButton = new JButton("Mark Absent");
        markAbsentButton.setBounds(350, 110, 150, 20);
        markAbsentButton.addActionListener(this);
        panel.add(markAbsentButton);

        miscellaneousNotesButton = new JButton("Miscelleneous ");
        miscellaneousNotesButton.setBounds(350, 140, 150, 20);
        miscellaneousNotesButton.addActionListener(this);
        panel.add(miscellaneousNotesButton);

        generateBillButton = new JButton("Generate Bill");
        generateBillButton.setBounds(350, 170, 150, 20);
        generateBillButton.addActionListener(this);
        panel.add(generateBillButton);

        displayAllBillsButton = new JButton("Display All Bills");
        displayAllBillsButton.setBounds(150, 170, 150, 20);
        displayAllBillsButton.addActionListener(this);
        panel.add(displayAllBillsButton);

        outputArea = new JTextArea();
        outputArea.setBounds(20, 200, 950, 50000000);
        panel.add(outputArea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enrollButton) {
            String customerId = customerIdField.getText();
            String name = nameField.getText();
            String hostelName = hostelNameField.getText();
            String course = courseField.getText();
            String mobileNumber = mobileNumberField.getText();
            String miscelleneous = miscelleneousField.getText();
            enrollCustomer(customerId, name, hostelName, course, mobileNumber, miscelleneous);
        } else if (e.getSource() == logEntryButton) {
            String customerId = customerIdField.getText();
            logEntry(customerId);
        } else if (e.getSource() == messCutButton) {
            String customerId = customerIdField.getText();
            markMessCut(customerId);
        } else if (e.getSource() == markAbsentButton) {
            String customerId = customerIdField.getText();
            markAbsent(customerId);
        } else if (e.getSource() == generateBillButton) {
            String customerId = customerIdField.getText();
            generateBill(customerId);
        } else if (e.getSource() == displayAllBillsButton) {
            displayAllBills();
        }
    }

    public void enrollCustomer(String customerId, String name, String hostelName, String course, String mobileNumber, String miscelleneous) {
        Customer customer = new Customer(customerId, name, hostelName, course, mobileNumber);
        customers.put(customerId, customer);
        outputArea.append("Customer enrolled: " + name + "\n");
    }

    public void logEntry(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            if (!customer.enrolled) {
                customer.enrolled = true;
            }
            customer.totalCost += 98; // Daily meal fee
        }
    }

    public void markMessCut(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.messCut = true;
            customer.totalCost += 25; // Mess cut cost
            outputArea.append("Mess cut marked for: " + customer.name + "\n");
        }
    }

    public void markAbsent(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            customer.totalCost += 50; // Fine for missing
            outputArea.append("Absent marked for: " + customer.name + "\n");
        }
    }

    public void generateBill(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            outputArea.append("Bill for Customer: " + customer.name + "\n");
            outputArea.append("Customer ID\tName\tHostel\tCourse\tMobile\tTotal Cost\n");
            outputArea.append(customer.customerId + "\t" + customer.name + "\t" +
                    customer.hostelName + "\t" + customer.course + "\t" +
                    customer.mobileNumber + "\t" + customer.totalCost + "\n");
        } else {
            outputArea.append("Customer not found!\n");
        }
    }

    public void displayAllBills() {
        outputArea.append("All Customer Bills:\n");
        outputArea.append("Customer ID\tName\tHostel\tCourse\tMobile\tTotal Cost\n");
        for (Customer customer : customers.values()) {
            outputArea.append(customer.customerId + "\t" + customer.name + "\t" +
                    customer.hostelName + "\t" + customer.course + "\t" +
                    customer.mobileNumber + "\t" + customer.totalCost + "\n");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MessManagementSystemGUI gui = new MessManagementSystemGUI();
            gui.setVisible(true);
        });
    }
}
