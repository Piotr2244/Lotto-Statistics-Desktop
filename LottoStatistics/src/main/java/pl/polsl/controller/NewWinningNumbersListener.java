/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import pl.polsl.view.FrequencyTableView;

/**
 * Listener class responsible for making new winning numbers
 *
 * @author Piotr Benio
 * @version 3.0
 */
public class NewWinningNumbersListener implements ActionListener {

    /**
     * Frequency table to update
     */
    FrequencyTableView frequencyTable;

    /**
     * Constructor
     *
     * @param frequencyTable is an instance of table to put an updated data in
     */
    public NewWinningNumbersListener(FrequencyTableView frequencyTable) {
        this.frequencyTable = frequencyTable;

    }

    /**
     * creates new winning sets and places data in table model. Then prints an
     * updated data
     *
     * @param e incoming action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        frequencyTable.controller.newRandomWinningSetProcedure();
        DefaultTableModel dm = (DefaultTableModel) frequencyTable.table.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }

        frequencyTable.printFrequency();
    }

}
