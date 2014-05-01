import javax.swing.*;
import java.awt.*;

/**
 * @author Naimdjon Takhirov
 */
public class Example {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showGUI();
            }
        });
    }

    private static void showGUI() {
        final JFrame frame = createFrame();
        final JLabel label = new JLabel("Mouse over me!") {
            @Override
            public JToolTip createToolTip() {
                return new MultiLineToolTip();
            }
        };
        label.setToolTipText("This is a tooltip \n " +
                "spanning multiple lines! \n" +
                "More text here\n" +
                "and more...");
        frame.getContentPane().add(label);
        displayFrame(frame);
    }

    private static JFrame createFrame() {
        final JFrame frame = new JFrame("Tooltip");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static void displayFrame(JFrame frame) {
        frame.setPreferredSize(new Dimension(600, 400));
        frame.pack();
        frame.setVisible(true);
    }

    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
