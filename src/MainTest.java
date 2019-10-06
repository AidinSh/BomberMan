import javax.swing.*;

public class MainTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ImageIcon icon = new ImageIcon("C:\\Users\\aidin.shahmoradi\\IdeaProjects\\BomberMan\\Resources\\BlocksImage.jpg");
        JLabel label = new JLabel(icon);
        frame.add(label);
        frame.setDefaultCloseOperation
                (JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
