/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import pl.polsl.view.SingleNumberView;
import pl.polsl.model.SingleNumber;

/**
 * Controller for operating on single number values
 *
 * @author Piotr Benio
 * @version 1.0
 */
public class SingleNumberController {

    /**
     * Interface for managing lotto numbers data
     */
    private SingleNumber model;

    /**
     * Interface to communicate with user
     */
    private SingleNumberView view;

    /**
     * Constructor, creates default controller with model and view
     *
     * @param model is a single number model
     * @param view is a single model view
     */
    public SingleNumberController(SingleNumber model, SingleNumberView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Sets the value od the single lotto number
     *
     * @param value is a new value of the lotto number
     */
    public void setNumber(int value) {
        model.setNumber(value);
    }

    /**
     * Gets the value of the single lotto number
     *
     * @return the value of lotto number
     */
    public int getNumer() {
        return model.getNumber();
    }

    /**
     * Prints the value of the single lotto number
     */
    public void PrintValue() {
        view.printNumber(model.getNumber());
    }

}
