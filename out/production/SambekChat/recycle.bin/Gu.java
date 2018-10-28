package recycle.bin;

import javax.swing.*;
import javax.swing.text.rtf.RTFEditorKit;

public class Gu extends JFrame {
    Gu(){
        getContentPane().add(panel1);
        setVisible(true);
    }
    private JPanel panel1;
    public JEditorPane textArea1;
    public JEditorPane textArea2;
    public JTextPane textArea3;
    public JButton b1;

    public JScrollPane jScroll1;
}
