package GUIApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHome extends JFrame {
    private int id;
    StudentHome(){
        setSize(1540,850);
        ImageIcon icon = new ImageIcon("./image/student.jpeg");
        ImageIcon icon1 = new ImageIcon("image/index.png");
        Image i2 =  icon.getImage().getScaledInstance(1500,750, Image.SCALE_DEFAULT);
        setTitle("Teacher Panel");
        this.setIconImage(icon1.getImage());
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Student");
        JMenu editMenu = new JMenu("Teacher");
        JMenu viewMenu = new JMenu("View");

        JMenuItem enroll = new JMenuItem("Enroll");
        fileMenu.add(enroll);
        enroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentPanel(id);
            }
        });


        // creating sub menu
        JMenuItem newItem = new JMenuItem("View Instructor");
        JMenuItem exitItem = new JMenuItem("View Result");

        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewInstructor();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewRemarks();
            }
        });



        editMenu.add(newItem);
        viewMenu.add(exitItem);

        // top level menu added inside menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);


        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentHome();
    }
}
