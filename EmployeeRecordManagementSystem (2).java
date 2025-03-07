/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.employeerecordmanagementsystem;

/**
 *
 * @author heba
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

class Employee {

    String name;
    int id;
    String firstDayOfWork;
    String phoneNumber;
    String address;
    int workHours;
    double salary;

    Employee(String name, int id, String firstDayOfWork, String phoneNumber, String address, int workHours, double salary) {
        this.name = name;
        this.id = id;
        this.firstDayOfWork = firstDayOfWork;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.workHours = workHours;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", ID: " + id + ", First Day of Work: " + firstDayOfWork + ", Phone Number: " + phoneNumber
                + ", Address: " + address + ", Work Hours: " + workHours + ", Salary: " + salary;
    }
}

class Node {

    Employee emp;
    Node next;

    Node(Employee emp) {
        this.emp = emp;
        next = null;
    }
}

class EmployeeRecordManagementSystem {

    static Node head;
    int id;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        head = null;

        JFrame frame = new JFrame("Employee Record Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(7, 1));

        JButton insertButton = new JButton("Insert Record");
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame insertFrame = new JFrame("Insert Record");
                insertFrame.setSize(400, 300);
                insertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                insertFrame.setLayout(new GridLayout(7, 2));

                JTextField nameField = new JTextField();
                JTextField idField = new JTextField();
                JTextField firstDayField = new JTextField();
                JTextField phoneField = new JTextField();
                JTextField addressField = new JTextField();
                JTextField salaryField = new JTextField();

                insertFrame.add(new JLabel("Name:"));
                insertFrame.add(nameField);
                insertFrame.add(new JLabel("ID:"));
                insertFrame.add(idField);
                insertFrame.add(new JLabel("First Day of Work:"));
                insertFrame.add(firstDayField);
                insertFrame.add(new JLabel("Phone Number:"));
                insertFrame.add(phoneField);
                insertFrame.add(new JLabel("Address:"));
                insertFrame.add(addressField);
                insertFrame.add(new JLabel("Salary:"));
                insertFrame.add(salaryField);

                JButton submitButton = new JButton("Submit");
                insertFrame.add(submitButton);
                insertFrame.setVisible(true);
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String id = idField.getText();
                        String firstDayOfWork = firstDayField.getText();
                        String phoneNumber = phoneField.getText();
                        String address = addressField.getText();
                        String salary = salaryField.getText();
                        String workHours = "32";
                        createRecord(name, id, firstDayOfWork, phoneNumber, address, salary, workHours);

                    }
                });
            }
        });

        JButton deleteButton = new JButton("Delete Record");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame deleteFrame = new JFrame("Delete Record");
                deleteFrame.setSize(300, 200);
                deleteFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                deleteFrame.setLayout(new FlowLayout());

                JTextField idField = new JTextField(10);
                deleteFrame.add(new JLabel("Enter employee ID to delete:"));
                deleteFrame.add(idField);

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int id2 = 0;
                        try {
                            id2 = Integer.parseInt(idField.getText());

                        } catch (NumberFormatException x) {

                            JOptionPane.showMessageDialog(null, "Error: ID must be a valid number.");
                            return;
                        }

                        if (deleteRecord(id2) == 0) {
                            JOptionPane.showMessageDialog(null, "Record deleted successfully!");

                        } else {
                            JOptionPane.showMessageDialog(null, "ID does not exist.");

                        }
                        deleteFrame.dispose();
                    }
                });

                deleteFrame.add(deleteButton);
                deleteFrame.setVisible(true);
            }
        });

        JButton updateButton = new JButton("Update Record");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame updateFrame = new JFrame("Update Record");
                updateFrame.setSize(400, 300);
                updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateFrame.setLayout(new GridLayout(8, 2));

                JTextField idField = new JTextField();
                JTextField nameField = new JTextField();
                JTextField firstDayField = new JTextField();
                JTextField phoneField = new JTextField();
                JTextField addressField = new JTextField();
                JTextField salaryField = new JTextField();

                updateFrame.add(new JLabel("Enter employee ID to update:"));
                updateFrame.add(idField);
                updateFrame.add(new JLabel("Name:"));
                updateFrame.add(nameField);
                updateFrame.add(new JLabel("First Day of Work:"));
                updateFrame.add(firstDayField);
                updateFrame.add(new JLabel("Phone Number:"));
                updateFrame.add(phoneField);
                updateFrame.add(new JLabel("Address:"));
                updateFrame.add(addressField);
                updateFrame.add(new JLabel("Salary:"));
                updateFrame.add(salaryField);

                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int idupdate = Integer.parseInt(idField.getText());
                        String nameupdate = nameField.getText();
                        String firstDayupdate = firstDayField.getText();
                        String phoneupdate = phoneField.getText();
                        String addressupdate = addressField.getText();
                        String salaryupdate = salaryField.getText();

                        updateRecord(idupdate, nameupdate, firstDayupdate, phoneupdate, addressupdate, salaryupdate);

                        updateFrame.dispose();
                    }
                });

                updateFrame.add(updateButton);
                updateFrame.setVisible(true);
            }
        });

        JButton showButton = new JButton("Show Record");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame showFrame = new JFrame("Show Record");
                showFrame.setSize(300, 200);
                showFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                showFrame.setLayout(new FlowLayout());

                JTextField idField = new JTextField(10);
                showFrame.add(new JLabel("Enter employee ID:"));
                showFrame.add(idField);

                JButton showButton = new JButton("Show");
                showButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        showRecord(idField.getText());
                        showFrame.dispose();
                    }
                });

                showFrame.add(showButton);
                showFrame.setVisible(true);
            }
        });

        JButton searchButton = new JButton("Search Employee");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame searchFrame = new JFrame("Search Employee");
                searchFrame.setSize(300, 200);
                searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                searchFrame.setLayout(new FlowLayout());

                JTextField idField = new JTextField(8);
                searchFrame.add(new JLabel("Enter employee ID to search:"));
                searchFrame.add(idField);

                JButton searchButton = new JButton("Search");
                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id2 = 0;
                        try {
                            id2 = Integer.parseInt(idField.getText());

                        } catch (NumberFormatException x) {

                            JOptionPane.showMessageDialog(null, "Error: ID must be a valid number.");
                            return;
                        }

                        smartSearchRecord(id2);
                        searchFrame.dispose();
                    }
                });

                searchFrame.add(searchButton);
                searchFrame.setVisible(true);
            }
        });

        JButton updateSalaryButton = new JButton("Update Salary");
        updateSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame updateSalaryFrame = new JFrame("Update Salary");
                updateSalaryFrame.setSize(400, 200);
                updateSalaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                updateSalaryFrame.setLayout(new FlowLayout());

                JTextField idField = new JTextField(8);
                JTextField hoursField = new JTextField(8);
                updateSalaryFrame.add(new JLabel("Enter employee ID to update salary:"));
                updateSalaryFrame.add(idField);
                updateSalaryFrame.add(new JLabel("Enter new work hours:"));
                updateSalaryFrame.add(hoursField);

                JButton updateButton = new JButton("Update");
                updateButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int id = 0;
                        int newHours = 0;
                        try {
                            id = Integer.parseInt(idField.getText());
                            newHours = Integer.parseInt(hoursField.getText());
                        } catch (NumberFormatException x) {

                            JOptionPane.showMessageDialog(null, "Error: ID must be a valid number.");
                            return;
                        }

                        updateSalary(id, newHours);
                        updateSalaryFrame.dispose();
                    }
                });

                updateSalaryFrame.add(updateButton);
                updateSalaryFrame.setVisible(true);
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(insertButton);
        frame.add(deleteButton);
        frame.add(updateButton);
        frame.add(showButton);
        frame.add(searchButton);
        frame.add(updateSalaryButton);
        frame.add(exitButton);

        frame.setVisible(true);
    }

    static boolean checkRecord(int id) {
        Node temp = head;
        while (temp != null) {
            if (temp.emp.id == id) {
                return true; // Record already exists
            }
            temp = temp.next;
        }
        return false; // Record does not exist
    }

    static void createRecord(String name, String id, String firstDayOfWork, String phoneNumber, String address, String salary, String workHours) {
        if ("".equals(firstDayOfWork) || "".equals(name) || "".equals(phoneNumber) || "".equals(address)) {
            JOptionPane.showMessageDialog(null, "Error: Please fill in all required fields.");
            return;
        }

        try {
            int id3 = Integer.parseInt(id);
            double salary3 = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: ID and Salary must be valid numbers.");
            return;
        }

        int id2 = Integer.parseInt(id);
        Double salary2 = Double.parseDouble(salary);
        int workHours2 = Integer.parseInt(workHours);

        if (checkRecord(id2)) {
            JOptionPane.showMessageDialog(null, "Error: Employee with ID " + id2 + " already exists.");
            return;
        }

        Node newNode = new Node(new Employee(name, id2, firstDayOfWork, phoneNumber, address, workHours2, salary2));

        if (head == null || head.emp.id >= newNode.emp.id) {
            newNode.next = head;
            head = newNode;
            JOptionPane.showMessageDialog(null, "Record inserted successfully!");
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.emp.id < newNode.emp.id) {
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;

        JOptionPane.showMessageDialog(null, "Record inserted successfully!");
    }

    static int deleteRecord(int id) {

        Node temp = head;
        Node prev = null;

        if (temp != null && temp.emp.id == id) {
            head = temp.next;
            return 0;
        }

        while (temp != null && temp.emp.id != id) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            return -1;
        }

        prev.next = temp.next;
        return 0;
    }

    static void updateRecord(int idupdate, String nameupdate, String firstDayupdate, String phoneupdate, String addressupdate, String salaryupdate) {
        Node temp = head;
        boolean recordFound = false;
        boolean updateSuccess = false;

        while (temp != null) {
            if (temp.emp.id == idupdate) {
                recordFound = true;
                if (!"".equals(nameupdate)) {
                    temp.emp.name = nameupdate;
                    updateSuccess = true;
                }

                if (!"".equals(firstDayupdate)) {
                    temp.emp.firstDayOfWork = firstDayupdate;
                    updateSuccess = true;
                }

                if (!"".equals(phoneupdate)) {
                    temp.emp.phoneNumber = phoneupdate;
                    updateSuccess = true;
                }

                if (!"".equals(addressupdate)) {
                    temp.emp.address = addressupdate;
                    updateSuccess = true;
                }

                if (!"".equals(salaryupdate)) {
                    try {
                        double salary = Double.parseDouble(salaryupdate);
                        temp.emp.salary = salary;
                        updateSuccess = true;
                    } catch (NumberFormatException ex) {

                        JOptionPane.showMessageDialog(null, "Error: Salary must be a valid number.");
                    }
                }

            }

            temp = temp.next;
        }

        if (!recordFound) {
            JOptionPane.showMessageDialog(null, "Error: Employee with ID " + idupdate + " does not exist.");
        } else if (updateSuccess) {
            JOptionPane.showMessageDialog(null, "Record updated successfully!");
        } else if (!updateSuccess) {
            JOptionPane.showMessageDialog(null, "Nothing updated");

        }
    }

    static void showRecord(String id) {

        Node temp = head;
        boolean tag = false;
        int id2 = 0;
        try {
            id2 = Integer.parseInt(id);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(null, "Error: ID must be a valid number.");
            return;
        }

        if (temp == null) {
            JOptionPane.showMessageDialog(null, "ID " + id2 + " does not exist.");

            return;
        }

        while (temp != null) {
            if (temp.emp.id == id2) {
                tag = true;
                JOptionPane.showMessageDialog(null, temp.emp.toString());

            }
            temp = temp.next;
        }

        if (!tag) {
            JOptionPane.showMessageDialog(null, "Record with id " + id2 + " does not exist.");
        }
    }

    static void smartSearchRecord(int id) {

        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.emp.id == id) {
                found = true;
                JOptionPane.showMessageDialog(null, "Employee " + id + " exist.");

                break;
            }
            temp = temp.next;
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "ID " + id + " dose not exist.");

        }
    }

    static void updateSalary(int id, int newHours) {

        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.emp.id == id) {
                found = true;

                if (newHours <= 32) {
                    JOptionPane.showMessageDialog(null, "Work hours changed, salary remains unchanged.");
                } else if (newHours > temp.emp.workHours) {
                    double additionalHours = newHours - temp.emp.workHours;
                    temp.emp.salary += (additionalHours * temp.emp.salary * 0.02);
                    temp.emp.workHours = newHours;
                    JOptionPane.showMessageDialog(null, "Salary updated successfully!");

                } else {
                    double reducedHours = temp.emp.workHours - newHours;
                    temp.emp.salary -= (reducedHours * temp.emp.salary * 0.02);
                    temp.emp.workHours = newHours;

                    JOptionPane.showMessageDialog(null, "Salary updated successfully!");
                }
                break;
            }
            temp = temp.next;
        }

        if (!found) {
            JOptionPane.showMessageDialog(null, "ID does not exist.");

        }
    }

}
