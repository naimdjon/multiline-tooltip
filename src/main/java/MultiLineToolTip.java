import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Collection;

/**                              !
 * @author Naimdjon Takhirov
 */
public class MultiLineToolTip extends JToolTip {

    public MultiLineToolTip() {
        setUI(new MultiLineToolTipUI());
    }

    class MultiLineToolTipUI extends javax.swing.plaf.metal.MetalToolTipUI/*CROSS PLATFORM UI*/ {
        private String[] toolTipLines;

        public void paint(Graphics g, JComponent c) {
            FontMetrics metrics = getFontMetrics(c.getFont());
            Dimension size = c.getSize();
            g.setColor(c.getBackground());
            g.fillRect(0, 0, size.width, size.height);
            g.setColor(c.getForeground());
            for (int i = 0; toolTipLines != null && i < toolTipLines.length; i++) {
                g.drawString(toolTipLines[i], 3, (metrics.getHeight()) * (i + 1));
            }
        }

        public Dimension getPreferredSize(JComponent component) {
            final FontMetrics metrics = getFontMetrics(component.getFont());
            final String tipText = getToolTipText((JToolTip) component);
            final BufferedReader br = new BufferedReader(new StringReader(tipText));
            String line;
            int maxWidth = 0;
            final Collection<String> lines = new java.util.ArrayList<String>();
            try {
                while ((line = br.readLine()) != null) {
                    maxWidth = Math.max(maxWidth, SwingUtilities.computeStringWidth(metrics, line));
                    lines.add(line);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (lines.size() > 1)
                toolTipLines = lines.toArray(new String[lines.size()]);
            int height = metrics.getHeight() * lines.size();
            return new Dimension(maxWidth + 6, height + 4);
        }

        private String getToolTipText(final JToolTip c) {
            String tipText = (c).getTipText();
            if (tipText == null)
                tipText = "";
            return tipText;
        }
    }
}