package gui.components;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class InfoComponent extends JPanel{

    private JLabel info;

    public InfoComponent(String text,int widthPanel,int heightPanel) {
        initComponents(text,widthPanel,heightPanel);

    }

    private void initComponents(String text,int widthPanel,int heightPanel) {
        this.setPreferredSize(new Dimension(widthPanel,heightPanel));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        info = new JLabel(text);
        info.setPreferredSize(new Dimension(widthPanel,heightPanel));
        info.setMinimumSize(info.getPreferredSize());
        info.setMaximumSize(info.getPreferredSize());
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setFont(new Font("Tahoma",0,12));
        this.add(info);
    }

    public JLabel getCaption() {
        return info;
    }

}