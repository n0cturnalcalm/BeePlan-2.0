import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class _button  extends JButton implements ActionListener {
    public String name;
    public int buttonType = -1;
    public String selectedFilePath;

    _button(String name, String text, int buttonType) { //buttonType: 0 -> start generating, 1 -> select a file
        this.name = name;
        this.setText(text);
        this.buttonType = buttonType;
        this.addActionListener(this);
        this.setFont(new Font("IM Fell English SC", Font.PLAIN, 14));
        this.setForeground(new Color(0x1b1815));
        this.setBackground(new Color(0xF5f2ea));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this) {
            if (this.buttonType == 1) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
                int response = fileChooser.showOpenDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    System.out.println("> " + this.name + ": " + filePath);
                    selectedFilePath = filePath.getAbsolutePath();
                    switch (this.name) {
                        case "selectCommonCoursesDocButton":
                            Main.selectedCommonCoursesDocPATH = selectedFilePath;
                            Main.comCoursesPath.setText("Common Courses file: " + selectedFilePath);
                            Main.comCoursesPath.revalidate();
                            Main.comCoursesPath.repaint();
                            break;
                        case "selectInstructorsDocButton":
                            Main.selectedInstructorsDocPATH = selectedFilePath;
                            Main.instructorsPath.setText("Instructors file: " + selectedFilePath);
                            Main.instructorsPath.revalidate();
                            Main.instructorsPath.repaint();
                            break;
                        case "selectClassroomsDocButton":
                            Main.selectedClassroomsDocPATH = selectedFilePath;
                            Main.classroomsPath.setText("Classrooms file: " + selectedFilePath);
                            Main.classroomsPath.revalidate();
                            Main.classroomsPath.repaint();
                            break;
                        case "selectCoursesDocButton":
                            Main.selectedCoursesDocPATH = selectedFilePath;
                            Main.coursesPath.setText("Courses file: " + selectedFilePath);
                            Main.coursesPath.revalidate();
                            Main.coursesPath.repaint();
                            break;
                    }
                }
            }else if(this.buttonType == 0) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
                int response = fileChooser.showSaveDialog(null);

                if (response == JFileChooser.APPROVE_OPTION) {
                    File filePath = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    System.out.println("> " + this.name + ": " + filePath);
                    selectedFilePath = filePath.getAbsolutePath();
                    Main.selectedSavePath = selectedFilePath;
                    Main.savePath.setText("Save file: " + selectedFilePath);
                    Main.savePath.revalidate();
                    Main.savePath.repaint();
                    Main.generateCourseScedulePROGRESS();
                }
            }else {
                System.out.print("Error");
            }
        }
    }
}