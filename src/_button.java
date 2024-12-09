import javax.swing.*;
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
                            break;
                        case "selectInstructorsDocButton":
                            Main.selectedInstructorsDocPATH = selectedFilePath;
                            break;
                        case "selectClassroomsDocButton":
                            Main.selectedClassroomsDocPATH = selectedFilePath;
                            break;
                        case "selectCoursesDocButton":
                            Main.selectedCoursesDocPATH = selectedFilePath;
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
                    Main.generateCourseScedulePROGRESS();
                }
            }else {
                System.out.print("Error");
            }
        }
    }
}