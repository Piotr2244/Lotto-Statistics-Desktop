/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.view;

import java.util.ArrayList;
import pl.polsl.model.SingleNumber;
import java.util.HashMap;
import java.util.stream.Stream;
import pl.polsl.controller.SingleNumberController;

/**
 * View allows to see the lotto numbers in database structures.
 *
 * @author Piotr Benio
 * @version 2.0
 */
public class LottoBaseView {

    /**
     * prints all winning numbers stored in database
     *
     * @param base is main base with all winning numbers
     */
    public void printWholeBase(ArrayList<SingleNumber> base) {
        SingleNumberView view = new SingleNumberView();
        Stream<SingleNumber> numberStream = base.stream();
        var wrapper = new Object() {
            int counter = 0;
        };
        numberStream.forEach(n -> {
            new SingleNumberController(n, view).PrintValue();
            wrapper.counter++;
            if (wrapper.counter == 6) {
                wrapper.counter = 0;
                System.out.println();
            }
        });
    }

    /**
     * prints latest winning numbers
     *
     * @param base is a base with last wining numbers
     */
    public void printWinningNumbers(ArrayList<SingleNumber> base) {

        SingleNumberView view = new SingleNumberView();
        Stream<SingleNumber> numberStream = base.stream();
        numberStream.forEach(n -> new SingleNumberController(n, view).PrintValue());
        System.out.println();
    }

    /**
     * Main printing method. Shows numbers from 1 to 40, frequency of them in
     * database and percent of this numbers in whole base.
     *
     * @param frequency is a base with values and its frequencies
     * @param percents is a base with values and its percentages in database
     */
    public void showFrequency(HashMap<Integer, Integer> frequency, HashMap<Integer, Integer> percents) {
        for (Integer i : frequency.keySet()) {
            System.out.println("Number: " + i + " Frequency: " + frequency.get(i) + " percent: " + percents.get(i) + "%");
        }
    }

    /**
     * Method prints message asking for amount of winning sets to generate
     */
    public void askForSetAmount() {
        System.out.println("How many new winning sets would You like to generate?");
    }

    /**
     * Method informs about error that may occure while getting info from user
     */
    public void valueErrorMessage() {
        System.out.println("Value should be a number");
    }

    /**
     * Method prints a message about database issue and then asks if user want
     * to generste random winning numbers
     */
    public void databaseIssueMessage() {
        System.out.println("Database has some issues. Type '1' To fill it randomly.");
    }

    /**
     * Method asks user for daabase name
     */
    public void askForDatabaseName() {
        System.out.println("No database detected. Type the name of the file");
    }

    public void askForMoreData() {
        System.out.println("Type '1' if you want to add even more sets.Type any other number to skip.");
    }

}
