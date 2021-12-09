package gui.components;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ButtonComponent extends JPanel{
  private JButton jButtonGo;
  private JLabel jLabel;

  public ButtonComponent(String text,int widthPanel,int heightPanel, int left, int widthEdit) {
    initComponents(text,widthPanel,heightPanel, left, widthEdit);
  }

  private void initComponents(String text,int widthPanel,int heightPanel, int left, int widthEdit) {
    this.setPreferredSize(new Dimension(widthPanel,heightPanel));
    this.setMinimumSize(this.getPreferredSize());
    this.setMaximumSize(this.getPreferredSize());
    jLabel = new JLabel("");
    jLabel.setPreferredSize(new Dimension(left,heightPanel));
    jLabel.setMinimumSize(jLabel.getPreferredSize());
    jLabel.setMaximumSize(jLabel.getPreferredSize());
    //jLabelTitle.setHorizontalAlignment(JLabel.RIGHT);
    this.add(jLabel);
    jButtonGo = new JButton(text);
    if(widthEdit == 0){
        jButtonGo.setPreferredSize(new Dimension(((widthPanel-widthPanel/3)-40),27));
    }else{
        jButtonGo.setPreferredSize(new Dimension(widthEdit,27));
    }
    jButtonGo.setMaximumSize(jButtonGo.getPreferredSize());
    jButtonGo.setMinimumSize(jButtonGo.getPreferredSize());
    this.add(jButtonGo);
  }


}
