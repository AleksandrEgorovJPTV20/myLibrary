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
import javax.swing.JPanel;


public class EditAuthorComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameComponent;
    private EditComponent surNameComponent;
    private EditComponent birthYearComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxAuthorsComponent comboBoxAuthorsComponent;
    
    public EditAuthorComponent() {
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Добавление нового автора", GuiApp.WIDTH_WINDOW, 31);
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
        buttonComponent.getButton().addActionListener(ButtonAddReader());
    }
    private ActionListener ButtonAddReader(){
        return new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Author author = new Author();
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
                    authorFacade.create(author);
                    infoComponent.getInfo().setText("Читатель успешно добавлен");
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    birthYearComponent.getEditor().setText("");
                    surNameComponent.getEditor().setText("");
                    nameComponent.getEditor().setText("");
                } catch (Exception e) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Читателя добавить не удалось");
                }
            }
        };
        comboBoxAuthorsComponent.getComboBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            }
        });
    }
}
