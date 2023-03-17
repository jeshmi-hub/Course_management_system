package GUIApplication;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherHome extends JFrame {
    TeacherHome(){
        setSize(1540,850);
        ImageIcon icon = new ImageIcon("./image/teacher.jpeg");
        ImageIcon icon1 = new ImageIcon("image/index.png");
        Image i2 =  icon.getImage().getScaledInstance(1500,750, Image.SCALE_DEFAULT);
        setTitle("Teacher Panel");
        this.setIconImage(icon1.getImage());
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        JMenuBar mb = new JMenuBar();
        JMenu course  = new JMenu("Student");
        mb.add(course);

        JMenuItem addCourse = new JMenuItem("View Student");
        course.add(addCourse);
        addCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStudent();
            }
        });

        JMenu module = new JMenu("Teacher");
        mb.add(module);


        JMenuItem addModule = new JMenuItem("Assigned Module");
        module.add(addModule);
        addModule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewInstructorPanel();
            }
        });
        JMenu details = new JMenu("Marks");
        mb.add(details);

        JMenuItem addDetails = new JMenuItem("Add Marks");
        details.add(addDetails);
        addDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InstructorPanel();
            }
        });






        setJMenuBar(mb);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

    }


}
