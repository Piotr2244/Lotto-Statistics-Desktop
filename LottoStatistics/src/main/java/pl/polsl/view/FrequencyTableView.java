/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import pl.polsl.controller.ShowWholeDatabaseListener;
import pl.polsl.controller.NewWinningNumbersListener;
import pl.polsl.controller.LastWinningNumbersListener;
import java.awt.*;
import javax.swing.*;
import pl.polsl.controller.LottoBaseController;
import pl.polsl.model.LottoBase;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 * This class is is used to create and manage frequency GUI interface. Listener
 * methods of proper controller classes will execute chosen functions after
 * pressing buttons. Data is presented as Table filled with lotto numbers, their
 * frequency and percents.
 *
 * @author Piotr Benio
 * @version 3.0
 */
public class FrequencyTableView extends JPanel {

    /**
     * Interface for managing main base data and using it in GUI
     */
    public LottoBase model;

    /**
     * Interface for using LottoBase controller methods for easier operations on
     * Data and for using JOptionPane windows in this controller
     */
    public LottoBaseController controller;

    /**
     * Column names in frequency table
     */
    private String[] columnNames = {"Number", "Frequency", "Percent"};

    /**
     * Button to add new winning numbers sets
     */
    private JButton addNumbersButton;

    /**
     * Button to show last winning numbers
     */
    private JButton lastWinningNumbersButton;

    /**
     * Button to show all numbers from database in new window
     */
    private JButton showWholeDatabaseButton;

    /**
     * New Default Table model defined
     */
    private DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    /**
     * New Jpanel to put all elements inside it
     */
    private JPanel panel = new JPanel();

    /**
     * New Table to put data inside
     */
    public JTable table = new JTable();

    /**
     * New scroll pane to keep data in table easy to read
     */
    private JScrollPane scrollPane = new JScrollPane(table);

    /**
     * New frame with project title
     */
    private JFrame frame = new JFrame("Lotto Statistics");

    /**
     * Constructor, gets model and controller as arguments and saves their data
     * inside GUI class. Frame is being prepaired and filled with table and
     * buttons. Table options are set to make it visible and easy to read.
     * Buttons have their options signed to them to make sure a proper action
     * will appear after clicking them. Buttons are placed in pane located at
     * the bottom of the window.
     *
     * @param model is a Lotto Base model taken as a parameter
     * @param controller is a Lotto Base controller taken as parameter
     */
    public FrequencyTableView(LottoBase model, LottoBaseController controller) {

        super(new BorderLayout());

        this.model = model;
        this.controller = controller;

        table.setPreferredScrollableViewportSize(new Dimension(1200, 700));
        table.setModel(tableModel);
        frame.setSize(1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.add(scrollPane);
        frame.setVisible(false);

        table.setPreferredScrollableViewportSize(new Dimension(200, 500));

        add(scrollPane);

        printFrequency();

        addNumbersButton = new JButton("Add numbers");
        addNumbersButton.setActionCommand("Add numbers");
        addNumbersButton.addActionListener(new NewWinningNumbersListener(this));

        showWholeDatabaseButton = new JButton("Show all numbers");
        showWholeDatabaseButton.setActionCommand("Show all numbers");
        showWholeDatabaseButton.addActionListener(new ShowWholeDatabaseListener(model, controller));

        lastWinningNumbersButton = new JButton("Last winning numbers");
        lastWinningNumbersButton.setActionCommand("Last winning numbers");
        lastWinningNumbersButton.addActionListener(new LastWinningNumbersListener(model));

        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(addNumbersButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(lastWinningNumbersButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(showWholeDatabaseButton);

        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);

    }

    /**
     * Method to get data from model and fill table with it. After this, whole
     * frequency data and percentage will be placed inside the table.
     */
    public void printFrequency() {
        HashMap<Integer, Integer> frequency = model.getFrequency();
        HashMap<Integer, Integer> percents = model.getPercents();
        for (Integer i : frequency.keySet()) {
            Object[] data = {i, frequency.get(i), percents.get(i) + "%"};
            tableModel.addRow(data);
        }

    }

    /**
     * After invoking this method, whole GUI will be created and shown as
     * readable panel.
     */
    public void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FrequencyTableView newContentPane = new FrequencyTableView(model, controller);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);

    }

}
