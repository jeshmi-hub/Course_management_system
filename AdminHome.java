package GUIApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHome extends  JFrame{
    private int id;

    AdminHome(int id){
        this.id = id;
        setSize(1540,850);
        ImageIcon icon = new ImageIcon("./image/herald.jpg");
        ImageIcon icon1 = new ImageIcon("image/index.png");
        Image i2 =  icon.getImage().getScaledInstance(1500,750, Image.SCALE_DEFAULT);
        setTitle("Course Management System");
        this.setIconImage(icon1.getImage());
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        JMenuBar mb = new JMenuBar();
        JMenu course  = new JMenu("Course");
        mb.add(course);

        JMenuItem addCourse = new JMenuItem("Add Course");
        course.add(addCourse);
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModulePanel(id);
            }
        });

        JMenu module = new JMenu("Module");
        mb.add(module);


        JMenuItem addModule = new JMenuItem("Add Module");
        module.add(addModule);
        addModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModulePanel(id);
            }
        });
        JMenu details = new JMenu("Instructor");
        mb.add(details);

        JMenuItem addDetails = new JMenuItem("Add Instructor");
        details.add(addDetails);
        addDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddInstructorPanel();
            }
        });

        JMenuItem viewDetails = new JMenuItem("View Instructor");
        details.add(viewDetails);
        viewDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewInstructorPanel();
            }
        });

        JMenu student = new JMenu("Student");
        mb.add(student);

        JMenuItem addStudent = new JMenuItem("Enter Remarks");
        student.add(addStudent);
        addStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Remarks();
            }
        });


        JMenuItem viewMarks = new JMenuItem("View Marks");
        student.add(viewMarks);
        viewMarks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewRemarks();
            }
        });





        setJMenuBar(mb);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

}
