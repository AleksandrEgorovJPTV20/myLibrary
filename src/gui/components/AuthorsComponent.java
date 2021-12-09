package gui.components;

import entity.Author;
import facade.AuthorFacade;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class AuthorsComponent extends JPanel{
    private JLabel title;
    private JList<Author> list;
    
    public AuthorsComponent(String text,int widthPanel,int heightPanel, int widthEdit) {
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
        title.setAlignmentY(TOP_ALIGNMENT);
        title.setFont(new Font("Tahoma",1,12));
        this.add(Box.createRigidArea(new Dimension(5,0)));
        list = new JList<>();
        list.setModel(getListModel());
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HEIGHT);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(widthPanel,heightPanel));
        scrollPane.setMaximumSize(list.getPreferredSize());
        scrollPane.setMinimumSize(list.getPreferredSize());
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentY(TOP_ALIGNMENT);
        this.add(title);
        this.add(scrollPane);
    }
    private ListModel<Author> getListModel() {
        AuthorFacade authorFacade = new AuthorFacade(Author.class);
        List<Author> authors = authorFacade.findAll();
        DefaultListModel<Author> defaultListModel = new DefaultListModel<>();
        for(Author author: authors){
            defaultListModel.addElement(author);
        }
        return defaultListModel;
    }

}