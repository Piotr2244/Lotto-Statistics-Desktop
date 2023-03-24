/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import pl.polsl.model.SingleNumber;
import pl.polsl.model.LottoBase;

/**
 * Listener class printing last winning numbers in JOptionPane
 *
 * @author Piotr Benio
 * @version 3.0
 */
public class LastWinningNumbersListener implements ActionListener {

    /**
     * gets last winning numbers from model and shows it in new pane
     *
     * @param e incoming action
     */
    LottoBase model;

    /**
     * constructor
     *
     * @param base is main base model
     */
    public LastWinningNumbersListener(LottoBase base) {
        this.model = base;
    }

    /**
     * method showing last winning numbers as JOptionPane after detecting that a
     * proper button was clicked
     *
     * @param e is incoming action
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList<SingleNumber> numbers = model.getWinningNumbers();
        String numberString = "";
        for (int i = 0; i < 6; i++) {
            numberString += ((numbers.get(i)).getNumber()) + " ";
        }

        JOptionPane.showMessageDialog(null, numberString, "Last winning numbers",
                JOptionPane.INFORMATION_MESSAGE);

    }
}
