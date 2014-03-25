package javax.swing;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class MultilineTooltipTest {
    JLabel label;
    final MultiLineToolTip multiLineToolTip = new MultiLineToolTip();
    @Before
    public void setUp(){
        label=new JLabel(){
            @Override
            public JToolTip createToolTip() {
                return multiLineToolTip;
            }
        };
        label.setToolTipText("test1\n" +
                "test2\n" +
                "test3\n");
    }

    @Test
    public void testMultilineTooltipGenerates3Lines() throws Exception{
        Dimension preferredSize =multiLineToolTip.getPreferredSize();
        assertEquals(preferredSize,new Dimension(6,4));
    }

}
