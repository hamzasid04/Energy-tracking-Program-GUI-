package cpsc219p3.energytracker;

import cpsc219p3.energytracker.cost.Cost;
import cpsc219p3.energytracker.data.Data;

import cpsc219p3.energytracker.power.Power;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import cpsc219p3.energytracker.PowerController;

import static cpsc219p3.energytracker.PowerController.*;


/**
 * This class manages the user interface for inputting boolean values related to the presence of
 * certain utilities like air conditioning, electric heating, and solar panels.
 * It allows users to specify whether they have these utilities and stores their responses.
 */
public class BooleanController {
    private Label status_display;
    public static boolean hasAc;
    public static boolean hasHeater;
    public static boolean hasSolar;

    public static double acPower;
    public static double heaterPower;
    public static double solarPower;
    /**
     * Sets the status of air conditioning to 'yes' when the corresponding button is clicked.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    void yesAC(ActionEvent event) {
        hasAc = true;
    }
    /**
     * Sets the status of air conditioning to 'no' when the corresponding button is clicked.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    void noAc(ActionEvent event) {
        hasAc = false;
    }

    @FXML
    void yesElectric(ActionEvent event) {//If user has electric
        hasHeater = true;
    }

    @FXML
    void noElectric(ActionEvent event) {//If user does not have electric
        hasHeater = false;
    }

    @FXML
    void yesSolar(ActionEvent event) {//If user has solar
        hasSolar = true;
    }

    @FXML
    void noSolar(ActionEvent event) {//If user does not have solar
        hasSolar = false;
    }
    public static Cost costBoolean;
    private double heaterCost = 0;//Set the cost of heater to 0
    private double solarCost = 0;//Set the cost of solar to 0
    private double acCost = 0;//Set the cost of ac to 0
    /**
     * Submits the responses for AC, heater, and solar panel usage.
     * Stores the responses and updates the user interface accordingly.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    void submitAnswers(ActionEvent event) {
        boolean[] allResponses = {hasAc, hasHeater, hasSolar};

        Data.storeAllBooleanResponseGathered(allResponses);//Store all boolean responses

        if (hasAc) {
            acCost = 72.00; //https://www.perchenergy.com/energy-calculators/cost-to-run-air-conditioner
        }
        if (hasHeater) {
            heaterCost = 36.00;//https://www.perchenergy.com/energy-calculators/space-heater-electricity-use-cost
        }
        if (hasSolar) {
            solarCost = -240; //https://uk.renogy.com/blog/average-solar-panel-output-per-day-uk-guide/#:~:text=The%20average%20monthly%20solar%20panel,to%20400%20kWh%20per%20month.
        }
        costBoolean = new Cost(acCost, heaterCost, solarCost);//Make a new instance of Cost

        status_display.setText("AC, Heater and Solar Usage Submitted");
        status_display.setTextFill(Color.BLACK);

    }

    /**
     * Sets the label for displaying status messages.
     *
     * @param status_display the label to be used for status display
     */
    public void setStatus_display(Label status_display) {
        this.status_display = status_display;
    }
}
