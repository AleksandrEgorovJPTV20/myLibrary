package gui.components.director;

import gui.components.reader.*;
import gui.components.*;
import entity.Reader;
import facade.ReaderFacade;
import gui.GuiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class EditReaderComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameComponent;
    private EditComponent lastNameComponent;
    private EditComponent phoneComponent;
    private ButtonComponent buttonComponent;
    private ComboBoxReadersComponent comboBoxReadersComponent;
    private Reader editReader;
    private ReaderFacade readerFacade;
    
    public EditReaderComponent() {
        readerFacade = new ReaderFacade(Reader.class);
        initComponents();
    }    

    private void initComponents() {
       this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Изменение читателя", GuiApp.WIDTH_WINDOW, 31);
        this.add(captionComponent); 
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW, 30);
        this.add(infoComponent);
        comboBoxReadersComponent = new ComboBoxReadersComponent("Читатели", 200, 30, 300);
        this.add(comboBoxReadersComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        nameComponent = new EditComponent("Имя:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(nameComponent);
        lastNameComponent = new EditComponent("Фамилия:", GuiApp.WIDTH_WINDOW, 30, 300);
        this.add(lastNameComponent);
        phoneComponent = new EditComponent("Телефон:", GuiApp.WIDTH_WINDOW, 30, 200);
        this.add(phoneComponent);
        buttonComponent = new ButtonComponent("Изменить читателя", 30, 350, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                Reader reader = readerFacade.find(editReader.getId());
                if(nameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите имя");
                    return;
                }
                reader.setFirstName(nameComponent.getEditor().getText());
                if(lastNameComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите фамилию");
                    return;
                }
                reader.setSurname(lastNameComponent.getEditor().getText());
                
                if(phoneComponent.getEditor().getText().isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Введите телефон");
                    return;
                } 
                reader.setPhone(phoneComponent.getEditor().getText());
                
                ReaderFacade readerFacade = new ReaderFacade(Reader.class);
                
                try {
                    readerFacade.edit(reader);
                    infoComponent.getInfo().setText("Читатель успешно изменён");
                    infoComponent.getInfo().setForeground(Color.BLUE);
                    comboBoxReadersComponent.getComboBox().setModel(comboBoxReadersComponent.getComboBoxModel());
                    comboBoxReadersComponent.getComboBox().setSelectedIndex(-1);                    
                } catch (Exception ex) {
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Читателя изменить не удалось");
                }               
           }
       });
      comboBoxReadersComponent.getComboBox().addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                if(comboBox.getSelectedIndex() == -1){
                    nameComponent.getEditor().setText("");
                    lastNameComponent.getEditor().setText("");
                    phoneComponent.getEditor().setText("");
                }else{
                    editReader = (Reader) e.getItem();
                    nameComponent.getEditor().setText(editReader.getFirstName().toString());
                    lastNameComponent.getEditor().setText(editReader.getSurname().toString());
                    phoneComponent.getEditor().setText(editReader.getPhone().toString());
                }                
           }
       });
    }
}
