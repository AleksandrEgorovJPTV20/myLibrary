package gui.components.renderers;

import entity.Author;
import entity.Book;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;

public class ListBooksCellRenderer extends DefaultListCellRenderer{
    private final Color background = new Color(0, 100, 255, 15);
    private final Color defaultBackground = (Color) UIManager.get("List.background");
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,int index,
                                                    boolean isSelected, boolean cellHasFocus){
        Component component = super.getListCellRendererComponent(list, value, index, 
                isSelected, cellHasFocus);
            if(component instanceof JLabel){
                JLabel label = (JLabel) component;
                Book book = (Book) value;
                StringBuilder sb = new StringBuilder();
                for (Author author : book.getAuthor()) {
                    sb.append(author.getFirstName())
                      .append(" ")
                      .append(author.getSurname())
                      .append(". ");
                }
                label.setText(String.format("%d. %s. %s %d."
                        ,book.getId()
                        ,book.getBookName()
                        ,sb.toString()
                        ,book.getPublishedYear()
                ));
                if(!isSelected){
                    label.setBackground(index % 2 == 0 ? background : defaultBackground);
                }
            }
            return component;                                            
        }

}