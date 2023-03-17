package GUIApplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyApp extends JFrame {
    JMenuBar menuBar;
    LoginPanel loginPanel;
    RegisterPanel registerPanel;
    DataPanel dataPanel;
    MyApp self = this;
    UserRegistration reg;


    public MyApp(){
        setVisible(true);
        setResizable(true);
        setTitle("Course Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuBar = new JMenuBar();
        loginPanel = new LoginPanel();
        registerPanel = new RegisterPanel();
        dataPanel = new DataPanel();
        reg = new UserRegistration();
        ImageIcon icon = new ImageIcon("image/index.png");
        this.setIconImage(icon.getImage());
        setJMenuBar(getMenu());
        add(appLayout());
        refreshTable();
        pack();
        setLocationRelativeTo(null);
        loginEventHandlers();
        addTableData();
        clearField();
        updateField();
        deleteField();
    }

    private JPanel appLayout(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(loginPanel.panelUI(), gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(registerPanel.panelUI(), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        mainPanel.add(dataPanel.panelUI(), gbc);

        return mainPanel;
    }
    private  JMenuBar getMenu(){
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu view = new JMenu("View");


        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(view);
        file.setMnemonic(KeyEvent.VK_F);

        JMenu fileItem1 = new JMenu("New");
        JMenuItem fileItem2 = new JMenuItem("Print");
        JMenuItem fileItem3 = new JMenuItem("Close");

        file.add(fileItem1);
        file.add(fileItem2);
        file.addSeparator();
        file.add(fileItem3);
        fileItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        fileItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

        fileItem1.add(new JMenuItem("New Project"));
        fileItem1.add(new JMenuItem("New GUI Project"));

        return menuBar;
    }

    private void loginEventHandlers(){
        JButton btnLogin = loginPanel.getBtnLogin();
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginPanel.getUsername().getText();
                char[] password = loginPanel.getPassword().getPassword();
                try{
                    reg.login(username,new String(password));

                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });


    }

    private void addTableData(){
        JButton btnRegister = registerPanel.getBtnRegister();
        DefaultTableModel model = dataPanel.getModel();
        JButton btnClear = registerPanel.getBtnClear();
        JButton btnDelete = registerPanel.getBtnDelete();
        JComboBox chooseRole = registerPanel.getRole();
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    String username = registerPanel.getUsername().getText().trim();
                    String firstName  = registerPanel.getFirstName().getText().trim();
                    String lastName = registerPanel.getLastName().getText().trim();
                    int age = Integer.parseInt(registerPanel.getAge().getText().trim());
                    String date = registerPanel.getDate().getText().trim();
                    String course = registerPanel.getCourse().getSelectedItem().toString();
                    char[] password = registerPanel.getPassword().getPassword();
                    String gender = registerPanel.getGender().getSelectedItem().toString();
                    String role = registerPanel.getRole().getSelectedItem().toString();

                    if(username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.length==0){
                        JOptionPane.showMessageDialog(self, "All of the fields are required","Warning",JOptionPane.WARNING_MESSAGE);
                    }else if(password.length<9){
                        JOptionPane.showMessageDialog(self,"Password must of 8 characters long","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        if(role.equals("Admin")){
                            reg.insert( firstName,lastName,username,new String(password),age,date,gender,course, role);
                            refreshTable();
                            JOptionPane.showMessageDialog(self,"You've been registered!! Please login","Success",JOptionPane.INFORMATION_MESSAGE);
                            btnClear.doClick();

                        }else if(role.equals("Instructor")){
                            btnDelete.setVisible(false);
                            reg.insert( firstName,lastName,username,new String(password),age,date,gender,course, role);
                            refreshTable();
                            JOptionPane.showMessageDialog(self,"You've been registered!! Please login","Success",JOptionPane.INFORMATION_MESSAGE);
                            btnClear.doClick();

                        }else{
                            btnDelete.setVisible(false);
                            reg.insert( firstName,lastName,username,new String(password),age,date,gender,course, role);
                            refreshTable();
                            JOptionPane.showMessageDialog(self,"You've been registered!! Please login","Success",JOptionPane.INFORMATION_MESSAGE);
                            btnClear.doClick();

                        }

                    }

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(self, "You must enter age in numerical format", "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });



    }

    private void clearField(){
       JButton clearBtn = registerPanel.getBtnClear();
       JTable dataTable = dataPanel.getTable();
       JButton btnUpdate = registerPanel.getBtnUpdate();
       JButton btnRegister = registerPanel.getBtnRegister();
       clearBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               btnUpdate.setVisible(false);
               btnRegister.setVisible(true);
               registerPanel.getUsername().setText("");
               registerPanel.getFirstName().setText("");
               registerPanel.getLastName().setText("");
               registerPanel.getAge().setText("");
               registerPanel.getDate().setText("");
               registerPanel.getPassword().setText("");
               registerPanel.getGender().setSelectedIndex(0);
               registerPanel.getCourse().setSelectedIndex(0);
               dataTable.clearSelection();
           }
       });
    }

    private void updateField(){
        JTable dataTable = dataPanel.getTable();
        DefaultTableModel model = dataPanel.getModel();
        JButton btnUpdate = registerPanel.getBtnUpdate();
        JButton btnClear = registerPanel.getBtnClear();
        JButton btnRegister = registerPanel.getBtnRegister();
        JButton btnDelete = registerPanel.getBtnDelete();
        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnUpdate.setVisible(true);
                btnRegister.setVisible(false);
                int selectedRow = dataTable.getSelectedRow();
                registerPanel.getFirstName().setText(model.getValueAt(selectedRow,1).toString());
                registerPanel.getLastName().setText(model.getValueAt(selectedRow, 2).toString());
                registerPanel.getUsername().setText(model.getValueAt(selectedRow,3).toString());
                registerPanel.getPassword().setText(model.getValueAt(selectedRow,4).toString());
                registerPanel.getAge().setText(model.getValueAt(selectedRow,5).toString());
                registerPanel.getDate().setText(model.getValueAt(selectedRow,6).toString());
                registerPanel.getGender().setSelectedItem(model.getValueAt(selectedRow,7).toString());
                registerPanel.getCourse().setSelectedItem(model.getValueAt(selectedRow,8).toString());


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDelete.setVisible(true);

                int selectedRow = dataTable.getSelectedRow();
                if(selectedRow==-1){
                    JOptionPane.showMessageDialog(self, "You must select a row to update a field","Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    String username = registerPanel.getUsername().getText().trim();
                    String firstName  = registerPanel.getFirstName().getText().trim();
                    String lastName = registerPanel.getLastName().getText().trim();
                    int age = Integer.parseInt(registerPanel.getAge().getText().trim());
                    String date = registerPanel.getDate().getText().trim();
                    String course = registerPanel.getCourse().getSelectedItem().toString();
                    char[] password = registerPanel.getPassword().getPassword();
                    String gender = registerPanel.getGender().getSelectedItem().toString();

                    if(username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.length==0){
                        JOptionPane.showMessageDialog(self, "All of the fields are required","Warning",JOptionPane.WARNING_MESSAGE);
                    }else if(password.length<9){
                        JOptionPane.showMessageDialog(self,"Password must of 8 characters long","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                    else {
                        int id =Integer.parseInt(model.getValueAt(selectedRow, 0).toString()) ;
                        reg.update(id,firstName,lastName,username,new String(password),age,date,gender,course);
                        refreshTable();

                        JOptionPane.showMessageDialog(self,"Data of " + username +" is updated successfully","Success",JOptionPane.INFORMATION_MESSAGE);
                        btnClear.doClick();
                    }

                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(self, "You must enter age in numerical format", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });



    }

   private void deleteField(){
        JButton btnDelete = registerPanel.getBtnDelete();
        DefaultTableModel model = dataPanel.getModel();

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String row = JOptionPane.showInputDialog(dataPanel, "Please enter row number to delete?", "Queries", JOptionPane.QUESTION_MESSAGE);
                int confirm = JOptionPane.showConfirmDialog(dataPanel, "Are you sure want to delete row?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        int rowDelete = Integer.parseInt(row);
                        reg.delete(rowDelete);
                        refreshTable();
                    } catch (NumberFormatException exception) {
                        JOptionPane.showMessageDialog(dataPanel,"You must enter valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(dataPanel, "Provided row doesn't exist. Please enter valid row number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
   }

   private void refreshTable(){
        //removes all data from JTable
        dataPanel.getModel().setRowCount(0);
        try{
            ResultSet resultSet = reg.get();
            while ((resultSet.next())) {
                dataPanel.getModel().addRow(new Object[]{
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getInt("age"),
                        resultSet.getString("date_of_birth"),
                        resultSet.getString("gender"),
                        resultSet.getString("course"),
                        resultSet.getString("role")

                });

            }

        }catch(SQLException ex){
            ex.printStackTrace();

        }

   }



    public static void main(String[] args) {
        new MyApp();
    }
}
