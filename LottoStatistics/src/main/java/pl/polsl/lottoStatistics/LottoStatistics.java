/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package pl.polsl.lottoStatistics;

import pl.polsl.view.LottoBaseView;
import pl.polsl.model.LottoBase;
import pl.polsl.controller.LottoBaseController;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import pl.polsl.view.FrequencyTableView;

/**
 * Main class of the application
 *
 * @author Piotr Benio
 * @version 3.0
 */
public class LottoStatistics {

    /**
     * Main method of the application, creates database and uses controller
     * methods Method tries to get lotto numbers from database, then asks user
     * for additional random data, finally prints complete data. Program works
     * with full functional GUI, but starting results are also visible in
     * console to check if everything is correct.
     *
     * @param args first arg is the name of database file
     * @throws FileNotFoundException will be thrown when file can't be found
     */
    public static void main(String[] args) throws FileNotFoundException, InputMismatchException {

        LottoBase mainbasemodel = new LottoBase();
        LottoBaseView mainbaseview = new LottoBaseView();
        LottoBaseController mainbaseController = new LottoBaseController(mainbasemodel, mainbaseview);

        if (args.length == 0) {
            mainbaseController.askUserForFilename();
        } else {
            mainbaseController.setFilenameFromArguments(args);
        }

        try {
            mainbaseController.getDataFromDatabase();
        } catch (FileNotFoundException exception) {
            System.err.println("File does not exist");
        }

        mainbaseController.newRandomWinningSetProcedure();
        mainbaseController.showFrequency();

        mainbaseController.printWholeDatabase();
        mainbaseController.printLastWinningNumbers();

        FrequencyTableView lottoInterface = new FrequencyTableView(mainbasemodel, mainbaseController);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                lottoInterface.createAndShowGUI();
            }
        });

    }

}
