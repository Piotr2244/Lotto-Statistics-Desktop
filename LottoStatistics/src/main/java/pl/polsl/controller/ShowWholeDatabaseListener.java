/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pl.polsl.model.LottoBase;
import pl.polsl.view.AllNumbersBaseView;

/**
 * Listener class printing the whole database as a list in the new window
 *
 * @author Piotr Benio
 * @version 3.0
 */
public class ShowWholeDatabaseListener implements ActionListener {

    /**
     * lotto base model with winning numbers data inside
     */
    LottoBase model;
    /**
     * lotto base controller with usefull methds
     */
    LottoBaseController controller;

    /**
     * constructor
     *
     * @param base main base model
     * @param controller main base controller
     */
    public ShowWholeDatabaseListener(LottoBase base, LottoBaseController controller) {
        this.model = base;
        this.controller = controller;
    }

    /**
     * gets data from model and controller and creates new window with all
     * winning numbers shown as sets
     *
     * @param e incoming action
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        AllNumbersBaseView allNumbers = new AllNumbersBaseView(model, controller);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                allNumbers.createAndShowGUI();
            }
        });
    }
}
