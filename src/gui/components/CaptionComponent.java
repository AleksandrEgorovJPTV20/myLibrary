package gui.components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class CaptionComponent extends JPanel{

    private JLabel caption;

    public CaptionComponent(String text,int widthPanel,int heightPanel) {
        initComponents(text,widthPanel,heightPanel);

    }

    private void initComponents(String text,int widthPanel,int heightPanel) {
        this.setPreferredSize(new Dimension(widthPanel,heightPanel));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        caption = new JLabel(text);
        caption.setPreferredSize(new Dimension(widthPanel,heightPanel));
        caption.setMinimumSize(caption.getPreferredSize());
        caption.setMaximumSize(caption.getPreferredSize());
        caption.setHorizontalAlignment(JLabel.CENTER);
        caption.setFont(new Font("Tahoma",1,16));
        this.add(caption);
    }

    public JLabel getCaption() {
        return caption;
    }

}