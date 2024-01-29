import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentMessManagementSystem extends JFrame implements ActionListener {

    private List<Student> students;
    private DefaultTableModel tableModel;
    private JTable table;
    private JLabel nameLabel, rollNumberLabel, branchLabel, phoneNumberLabel, feePaymentLabel, validityLabel;
    private JTextField nameField, rollNumberField, branchField, phoneNumberField, feePaymentField, validityField;
    private JButton addButton, viewButton, searchButton, updateButton, deleteButton;

    public StudentMessManagementSystem() {
        // Initialize the list of students
        students = new ArrayList<>();

        // Set frame properties
        setTitle("Student Mess Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create table model
        String[] columnNames = {"Name", "Roll Number", "Branch", "Phone Number", "Fee Payment", "Validity"};
        tableModel = new DefaultTableModel(columnNames, 0);

        // Create table
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Create labels
        nameLabel = new JLabel("Name:");
        rollNumberLabel = new JLabel("Roll Number:");
        branchLabel = new JLabel("Branch:");
        phoneNumberLabel = new JLabel("Phone Number:");
        feePaymentLabel = new JLabel("Fee Payment:");
        validityLabel = new JLabel("Validity:");

        // Create text fields
        nameField = new JTextField();
        rollNumberField = new JTextField();
        branchField = new JTextField();
        phoneNumberField = new JTextField();
        feePaymentField = new JTextField();
        validityField = new JTextField();

        // Create buttons
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        viewButton = new JButton("View Details");
        viewButton.addActionListener(this);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);

        // Create panel for input fields and add components
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(rollNumberLabel);
        inputPanel.add(rollNumberField);
        inputPanel.add(branchLabel);
        inputPanel.add(branchField);
        inputPanel.add(phoneNumberLabel);
        inputPanel.add(phoneNumberField);
        inputPanel.add(feePaymentLabel);
        inputPanel.add(feePaymentField);
        inputPanel.add(validityLabel);
        inputPanel.add(validityField);

        // Create panel for buttons and add components
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Add components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            String rollNumber = rollNumberField.getText();
            String branch = branchField.getText();
            String phoneNumber = phoneNumberField.getText();
            String feePayment = feePaymentField.getText();
            String validity = validityField.getText();

            // Validate input fields
            if (name.isEmpty() || rollNumber.isEmpty() || branch.isEmpty() ||
                    phoneNumber.isEmpty() || feePayment.isEmpty() || validity.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a new student object
            Student student = new Student(name, rollNumber, branch, phoneNumber, feePayment, validity);

            // Add the student to the list
            students.add(student);

            // Add the student to the table
            tableModel.addRow(student.toTableRow());

            // Clear the input fields
            clearFields();

            JOptionPane.showMessageDialog(this, "Student added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == viewButton) {
            // No action required for viewButton
        } else if (e.getSource() == searchButton) {
            searchStudent();
        } else if (e.getSource() == updateButton) {
            updateStudent();
        } else if (e.getSource() == deleteButton) {
            deleteStudent();
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollNumberField.setText("");
        branchField.setText("");
        phoneNumberField.setText("");
        feePaymentField.setText("");
        validityField.setText("");
    }

    private void searchStudent() {
        String searchTerm = JOptionPane.showInputDialog(this, "Enter roll number or name of the student to search:");

        if (searchTerm != null) {
            for (int rowIndex = 0; rowIndex < tableModel.getRowCount(); rowIndex++) {
                String rollNumber = (String) tableModel.getValueAt(rowIndex, 1);
                String name = (String) tableModel.getValueAt(rowIndex, 0);

                if (Objects.equals(rollNumber, searchTerm) || Objects.equals(name, searchTerm)) {
                    table.setRowSelectionInterval(rowIndex, rowIndex);
                    table.scrollRectToVisible(table.getCellRect(rowIndex, 0, true));
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Student not found.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateStudent() {
        int rowIndex = table.getSelectedRow();

        if (rowIndex != -1) {
            String name = nameField.getText();
            String rollNumber = rollNumberField.getText();
            String branch = branchField.getText();
            String phoneNumber = phoneNumberField.getText();
            String feePayment = feePaymentField.getText();
            String validity = validityField.getText();

            // Validate input fields
            if (name.isEmpty() || rollNumber.isEmpty() || branch.isEmpty() ||
                    phoneNumber.isEmpty() || feePayment.isEmpty() || validity.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the student in the list
            Student student = students.get(rowIndex);
            student.setName(name);
            student.setRollNumber(rollNumber);
            student.setBranch(branch);
            student.setPhoneNumber(phoneNumber);
            student.setFeePayment(feePayment);
            student.setValidity(validity);

            // Update the student in the table
            tableModel.setValueAt(name, rowIndex, 0);
            tableModel.setValueAt(rollNumber, rowIndex, 1);
            tableModel.setValueAt(branch, rowIndex, 2);
            tableModel.setValueAt(phoneNumber, rowIndex, 3);
            tableModel.setValueAt(feePayment, rowIndex, 4);
            tableModel.setValueAt(validity, rowIndex, 5);

            JOptionPane.showMessageDialog(this, "Student details updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteStudent() {
        int rowIndex = table.getSelectedRow();

        if (rowIndex != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Remove student from the list
                students.remove(rowIndex);

                // Remove student from the table
                tableModel.removeRow(rowIndex);

                // Clear the input fields
                clearFields();

                JOptionPane.showMessageDialog(this, "Student deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        StudentMessManagementSystem system = new StudentMessManagementSystem();
        system.setVisible(true);
    }
}

class Student {
    private String name;
    private String rollNumber;
    private String branch;
    private String phoneNumber;
    private String feePayment;
    private String validity;

    public Student(String name, String rollNumber, String branch, String phoneNumber, String feePayment, String validity) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.phoneNumber = phoneNumber;
        this.feePayment = feePayment;
        this.validity = validity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFeePayment() {
        return feePayment;
    }

    public void setFeePayment(String feePayment) {
        this.feePayment = feePayment;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public Object[] toTableRow() {
        return new Object[]{name, rollNumber, branch, phoneNumber, feePayment, validity};
    }
}
