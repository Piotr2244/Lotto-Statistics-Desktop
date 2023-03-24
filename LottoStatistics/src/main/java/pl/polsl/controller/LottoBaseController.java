/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.controller;

import pl.polsl.model.LottoBase;
import pl.polsl.view.LottoBaseView;
import pl.polsl.model.LottoBaseException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;

/**
 * Controller for operating on main lotto database
 *
 * @author Piotr Benio
 * @version 3.0
 *
 */
public class LottoBaseController {

    /**
     * Interface for managing main base data
     */
    private LottoBase model;

    /**
     * Interface to communicate with user
     */
    private LottoBaseView view;

    /**
     * Constructor, creates default controller with model and view
     *
     * @param model is lotto base model
     * @param view is lotto base view
     */
    public LottoBaseController(LottoBase model, LottoBaseView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Method prints all lotto numbers from database
     */
    public void printWholeDatabase() {
        view.printWholeBase(model.getAllDatabaseElements());
    }

    /**
     * Invokes model method to print last winning numbers
     */
    public void printLastWinningNumbers() {
        view.printWinningNumbers(model.getWinningNumbers());
    }

    /**
     * Invokes main printing method, shows numbers from 1 to 40, their frequency
     * and percents
     */
    public void showFrequency() {
        view.showFrequency(model.getFrequency(), model.getPercents());
    }

    /**
     * invokes model method to generate new winning numbers sets. User is being
     * twice asked how many winning sets should be generated.
     */
    public void newRandomWinningSetProcedure() {
        // Scanner scanner = new Scanner(System.in);
        // view.askForSetAmount();
        String textToConvert;
        int firstValue;
        int secondValue;
        try {
            //int firstValue = Integer.parseInt(scanner.nextLine());
            textToConvert = (JOptionPane.showInputDialog("How many new winning sets would You like to generate?"));
            if (textToConvert != null) {
                firstValue = Integer.parseInt(textToConvert);
            } else {
                firstValue = 0;
            }

            String[] options = {"yes", "no"};

            int permission = JOptionPane.showOptionDialog(null, "Do You want to add even more sets?",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (permission == 0) {
                textToConvert = (JOptionPane.showInputDialog("How many new winning sets would You like to generate?"));
                if (textToConvert != null) {
                    secondValue = Integer.parseInt(textToConvert);
                } else {
                    secondValue = 0;
                }
                try {
                    model.DoNewRandomWinningSetsProcedure(firstValue, secondValue);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(null, "Value should be a number", "Error",
                            JOptionPane.ERROR_MESSAGE);

                }
            } else
          try {
                model.DoNewRandomWinningSetsProcedure(firstValue);
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null, "Value should be a number", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null, "Value should be a number", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * method invokes database reading procedures. If parameter exception will
     * occure, Database can be filled randomly after user's permission.
     *
     * @throws FileNotFoundException will be thrown when file can't be found
     */
    public void getDataFromDatabase() throws FileNotFoundException, InputMismatchException {
        try {
            model.getDataFromDatabase();
        } catch (LottoBaseException exception) {
            System.err.println(exception.getMessage());
            String[] options = {"yes", "no"};
            int permission = JOptionPane.showOptionDialog(null, "Database has some issues. Do you want to fill it randomly?",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (permission == 0) {
                int amount;
                String textToConvert = (JOptionPane.showInputDialog("How many new winning sets would You like to generate?"));
                if (textToConvert != null) {
                    amount = Integer.parseInt(textToConvert);
                } else {
                    amount = 0;
                }
                model.DoNewRandomWinningSetsProcedure(amount);
            }
        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(null, "File not Found", "No such file!",
                    JOptionPane.ERROR_MESSAGE);
        } catch (InputMismatchException exception) {
            String[] options = {"yes", "no"};
            int permission = JOptionPane.showOptionDialog(null, "Database has some issues. Do you want to fill it randomly?",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (permission == 0) {
                int amount;
                String textToConvert = (JOptionPane.showInputDialog("How many new winning sets would You like to generate?"));
                if (textToConvert != null) {
                    amount = Integer.parseInt(textToConvert);
                } else {
                    amount = 0;
                }
                model.DoNewRandomWinningSetsProcedure(amount);
            }
        }

    }

    /**
     * Sets filename
     *
     * @param args takes filename directly from application arguments
     */
    public void setFilenameFromArguments(String[] args) {

        model.setFilename(args[0]);

    }

    /**
     * Asks user for database file name. After getting one, it will be set as
     * filename and later used during file opening in other method
     */
    public void askUserForFilename() {
        String filename = JOptionPane.showInputDialog("No database detected. Type the name of the file");
        if (filename != null) {
            model.setFilename(filename);
        } else {
            model.setFilename("no data");
        }

    }

}
