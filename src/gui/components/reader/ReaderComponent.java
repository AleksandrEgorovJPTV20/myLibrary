package gui.components.reader;

import gui.GuiApp;
import gui.components.*;
import static gui.GuiApp.HEIGHT_WINDOW;
import static gui.GuiApp.WIDTH_WINDOW;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class ReaderComponent extends JPanel{
    private CaptionComponent captionComponent;
    private InfoComponent infoComponent;
    private EditComponent nameBookComponent;
    private EditComponent publishedYearComponent;
    private EditComponent quantityComponent;
    private ButtonComponent buttonComponent;
    private ListAuthorsComponent listAuthorsComponent;
    
    public ReaderComponent() {
        initComponents();
    }    

    private void initComponents() {
        JTabbedPane readerTabbed = new JTabbedPane();
        readerTabbed.setPreferredSize(new Dimension(GuiApp.WIDTH_WINDOW,GuiApp.HEIGHT_WINDOW));
        readerTabbed.setMinimumSize(readerTabbed.getPreferredSize());
        readerTabbed.setMaximumSize(readerTabbed.getPreferredSize());
        this.add(readerTabbed);
        readerTabbed.addTab("Взять книгу", new TakeOnBookComponent());
        readerTabbed.addTab("Вернуть книгу", new ReturnBookComponent());        
    }

    public InfoComponent getInfoComponent() {
        return infoComponent;
    }
    
}
