import javax.swing.*;
import java.awt.*;

public class _frame extends JFrame {

    ImageIcon beePlanLogoWithoutText = new ImageIcon("src/images/BeePlan logo without logo.png");
    ImageIcon beePlanLogoWithText = new ImageIcon("src/images/BeePlan logo with text.png");

    _frame() {
        this.setVisible(true);
        this.setTitle("BeePlan - The Course Schedule Automation");
        this.setSize(1060,596);
        this.setLayout(null);

        this.setIconImage(beePlanLogoWithoutText.getImage());
        this.getContentPane().setBackground(new Color(0x1b1815));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
