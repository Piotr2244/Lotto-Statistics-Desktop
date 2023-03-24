/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import pl.polsl.controller.LottoBaseController;
import pl.polsl.model.LottoBase;
import pl.polsl.model.SingleNumber;

/**
 * This class is a list with all numbers in database. Winning numbers will be
 * printed in rows, six numbers in each to be visible as winning sets.
 *
 * @author Piotr Benio
 * @version 3.0
 */
public class AllNumbersBaseView extends JPanel {

    /**
     * List with all numbers
     */
    private JList list;

    /**
     * Interface for managing main base data and using it in GUI
     */
    private LottoBase model;

    /**
     * Interface for using LottoBase controller methods for easier operations on
     * Data and for using JOptionPane windows in this controller
     */
    private LottoBaseController controller;

    /**
     * declaring default list model
     */
    private DefaultListModel listModel;

    /**
     * constructor, creates list and gets main base model and controller as
     * arguments Created list is being prepaired to be visible. Data is placed
     * inside as rows of winning numbers. All remaining parts of window are
     * being constructed and filled with list elements. As a result the window
     * is ready to be shown.
     *
     * @param model is database model with winning numbers
     * @param controller is database constructor with proper methods
     */
    public AllNumbersBaseView(LottoBase model, LottoBaseController controller) {
        super(new BorderLayout());

        this.model = model;
        this.controller = controller;

        listModel = new DefaultListModel();

        ArrayList<SingleNumber> numbers = model.getAllDatabaseElements();
        String displayNumber;
        String displaySet = "";
        int counter = 0;
        for (int i = 0; i < numbers.size(); i++) {
            displayNumber = Integer.toString(numbers.get(i).getNumber());

            displaySet += displayNumber + "      ";
            if (numbers.get(i).getNumber() < 10) {
                displaySet += "  ";
            }
            counter++;
            if (counter == 6) {
                counter = 0;
                listModel.addElement(displaySet);
                displaySet = "";
            }

        }

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(30);
        JScrollPane listScrollPane = new JScrollPane(list);

        add(listScrollPane, BorderLayout.CENTER);
    }

    /**
     * After invoking this method, whole basewill be created and shown as
     * readable panel. After closing this window, main table will still be
     * visible and program will keep working
     */
    public void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("All lotto numbers");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JComponent newContentPane = new AllNumbersBaseView(model, controller);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

}
