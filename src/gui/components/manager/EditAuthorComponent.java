package gui.components.manager;

import gui.components.*;
import entity.Author;
import facade.AuthorFacade;
import gui.GuiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class EditAuthorComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameComponent;
    private EditComponent surNameComponent;
    private EditComponent birthYearComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxAuthorsComponent comboBoxAuthorsComponent;
    private Author editAuthor;
    private AuthorFacade authorFacade;
    
    public EditAuthorComponent() {
        authorFacade = new AuthorFacade(Author.class);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Изменить автора", GuiApp.WIDTH_WINDOW, 31);
        this.add(captionComponent); 
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW, 30);
        this.add(infoComponent);
        comboBoxAuthorsComponent = new ComboBoxAuthorsComponent("Авторы", 230, 30, 300);
        this.add(comboBoxAuthorsComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameComponent = new EditComponent("Имя автора:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(nameComponent);
        surNameComponent = new EditComponent("Фамилия автора:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(surNameComponent);
        birthYearComponent = new EditComponent("Год рождения автора:", GuiApp.WIDTH_WINDOW, 30, 200);
        this.add(birthYearComponent);
        buttonComponent = new ButtonComponent("Добавить автора", 30, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Author author = authorFacade.find(editAuthor.getId());
                if(nameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите имя автора");
                    return;
                }
                author.setFirstName(nameComponent.getEditor().getText());
                if(surNameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите фамилию автора");
                    return;
                }
                author.setSurname(surNameComponent.getEditor().getText());
                
                try {
                    author.setBirthYear(Integer.parseInt(birthYearComponent.getEditor().getText()));
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите год рождения автора (цифрами)");
                    return;
                }
                
                AuthorFacade authorFacade = new AuthorFacade(Author.class);
                
                try {
                    authorFacade.edit(author);
                    infoComponent.getInfo().setText("Автор успешно изменён");
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    comboBoxAuthorsComponent.getComboBox().setModel(comboBoxAuthorsComponent.getComboBoxModel());
                    comboBoxAuthorsComponent.getComboBox().setSelectedIndex(-1);
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Автора изменить не удалось");
                }
            }
        });
    comboBoxAuthorsComponent.getComboBox().addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
               if(comboBox.getSelectedIndex() == -1){
                    nameComponent.getEditor().setText("");
                    surNameComponent.getEditor().setText("");
                    birthYearComponent.getEditor().setText("");
               }else{
                    editAuthor = (Author) e.getItem();
                    nameComponent.getEditor().setText(editAuthor.getFirstName());
                    surNameComponent.getEditor().setText(editAuthor.getSurname());
                    birthYearComponent.getEditor().setText(((Integer)editAuthor.getBirthYear()).toString());
                    
               }  
            }
        });
    }
         
}