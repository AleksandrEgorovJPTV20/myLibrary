package gui.components.reader;

import gui.components.*;
import entity.Author;
import entity.Book;
import entity.History;
import facade.BookFacade;
import facade.HistoryFacade;
import gui.GuiApp;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class ReturnBookComponent extends JPanel{
    private HistoryFacade historyFacade;
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameBookComponent;
    private EditComponent publishedYearComponent;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    private ListAuthorsComponent listAuthorsComponent;
    private ListHistoriesComponent listHistoriesComponent;
    
    public ReturnBookComponent() {
        historyFacade = new HistoryFacade(History.class);
        initComponents();
    }    

    private void initComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOW, GuiApp.HEIGHT_WINDOW));
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.add(Box.createRigidArea(new Dimension(0,25)));
        captionComponent = new CaptionComponent("Возврат книги в библиотеку", GuiApp.WIDTH_WINDOW, 30);
        this.add(captionComponent);
        infoComponent = new InfoComponent("", GuiApp.WIDTH_WINDOW,30);
        this.add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        listHistoriesComponent = new ListHistoriesComponent(false, "Читаемые книги", 10, GuiApp.HEIGHT_WINDOW - 50, GuiApp.WIDTH_WINDOW);
        this.add(listHistoriesComponent);
//        nameBookComponent = new EditComponent("Название книги:",GuiApp.WIDTH_WINDOW, 30, 300);
//        this.add(nameBookComponent);
//        listAuthorsComponent = new ListAuthorsComponent("Авторы:", GuiApp.WIDTH_WINDOW, 120, 300);
//        this.add(listAuthorsComponent);
//        publishedYearComponent = new EditComponent("Год издания книги:", GuiApp.WIDTH_WINDOW, 30, 100);
//        this.add(publishedYearComponent);
//        quantityComponent = new EditComponent("Количество экземпляров:", GuiApp.WIDTH_WINDOW, 30, 50);
//        this.add(quantityComponent);
        buttonComponent = new ButtonComponent("Вернуть книгу", GuiApp.WIDTH_WINDOW,30, 200, 150);
        this.add(buttonComponent);
        buttonComponent.getButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<History> historyReturnBooks = listHistoriesComponent.getList().getSelectedValuesList();
                if(historyReturnBooks.isEmpty()){
                    infoComponent.getInfo().setForeground(Color.red);
                    infoComponent.getInfo().setText("Выберите название книги");
                    return;
                }
                for (int i = 0; i < historyReturnBooks.size(); i++) {
                    historyReturnBooks.get(i).setReturnedDate(Calendar.getInstance().getTime());
                    historyFacade.edit(historyReturnBooks.get(i));
                }
            }
        });        
    }
}
