package gui.components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditComponent extends JPanel{
    private JLabel title;
    private JTextField editor;
    
    public EditComponent(String text,int widthPanel,int heightPanel, int widthEdit) {
        initComponents(text,widthPanel,heightPanel,widthEdit);
    }
    private void initComponents(String text,int widthPanel,int heightPanel,int widthEdit) {
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.setPreferredSize(new Dimension(widthPanel,heightPanel));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        title = new JLabel(text);
        title.setPreferredSize(new Dimension(widthPanel/3,27));
        title.setMinimumSize(this.title.getPreferredSize());
        title.setMaximumSize(this.title.getPreferredSize());
        title.setHorizontalAlignment(JLabel.RIGHT);
        this.add(Box.createRigidArea(new Dimension(5,0)));
        editor = new JTextField();
        if(widthEdit == 0){
            editor.setPreferredSize(new Dimension(((widthPanel-widthPanel/3)-40),27));
        }else{
            editor.setPreferredSize(new Dimension(widthEdit,27));
        }
        editor.setMaximumSize(editor.getPreferredSize());
        editor.setMinimumSize(editor.getPreferredSize());
        this.add(title);
        this.add(editor);
    }

    public JLabel getTitle() {
        return title;
    }

    public JTextField getEditor() {
        return editor;
    }

}