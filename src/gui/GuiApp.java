/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.components.AuthorsComponent;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gui.components.ButtonComponent;
import gui.components.CaptionComponent;
import gui.components.ComboBoxComponent;
import gui.components.EditComponent;
import gui.components.InfoComponent;
import javax.swing.Box;

public class GuiApp extends JFrame{

    public GuiApp() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(600, 400));
        this.setMinimumSize(getPreferredSize());
        this.setMaximumSize(getPreferredSize());
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,25)));
        JPanel captionPanel = new CaptionComponent("Добавление новой книги в библиотеку", this.getWidth(), 30);
        getContentPane().add(captionPanel);
        JPanel infoComponent = new InfoComponent("Информация о добавлении книги в библиотеку", this.getWidth(), 30);
        getContentPane().add(infoComponent);
        this.add(Box.createRigidArea(new Dimension(0,10)));
        JPanel titlePanel = new EditComponent("Название книги: ",this.getWidth(), 31,250);
        getContentPane().add(titlePanel);
        JPanel listAuthorsComponent = new AuthorsComponent("Авторы книги: ", this.getWidth(),31,250);
        getContentPane().add(listAuthorsComponent);
        JPanel publishedYearPanel = new EditComponent("Год издания книги: ",this.getWidth(),31,70);
        getContentPane().add(publishedYearPanel);
        JPanel quantityPanel = new EditComponent("Количество экземпляров: ",this.getWidth(),31,50);
        getContentPane().add(quantityPanel);
        JPanel buttonAddBook = new ButtonComponent("Добавить книгу", this.getWidth(),40,150,160);
        getContentPane().add(buttonAddBook);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuiApp().setVisible(true);
            }
        });
    }

} 
