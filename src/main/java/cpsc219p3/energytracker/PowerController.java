package cpsc219p3.energytracker;

import cpsc219p3.energytracker.cost.Cost;
import cpsc219p3.energytracker.data.Data;
import cpsc219p3.energytracker.power.Power;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class is responsible for controlling the power consumption input interface.
 * It allows users to enter details about their utility power usage and computes the corresponding
 * consumption in kilowatt-hours (KWH). It also communicates with the EnergyController to update
 * power and cost details based on user input.
 */

public class PowerController {
    //Added a field in PowerController to hold a reference to EnergyController.
    public static EnergyController energyController;

    Data data;
    public static Power power;
    public static Cost cost;
    @FXML
    private Button return_button;
    private Label status_display;
    private TextArea display_field;

    //@FXML
    //private TextField ac_power;

    @FXML
    private TextField dishwasher_power;


    @FXML
    private TextField dryer_power;

    @FXML
    private TextField fridge_power;

    @FXML
    private TextField light_power;

    @FXML
    private TextField microwave_power;

    @FXML
    private TextField tv_power;

    @FXML
    private TextField washer_power;

    /**
     * Handles the action of entering utility power details.
     * Computes and displays the power consumption in KWH for each utility.
     *
     * @param event the action event triggered by the user
     */
    @FXML
        void enter_utility_button(ActionEvent event) {
        try {
            data = new Data();
            double fridgePower = Double.parseDouble(fridge_power.getText());
            double fridgeKWH = (fridgePower * 240) / 1000.0;

            double microwavePower = Double.parseDouble(microwave_power.getText());
            double microwaveKWH = (microwavePower * 240) / 1000.0;

            double tvPower = Double.parseDouble(tv_power.getText());
            double tvKWH = (tvPower * 240) / 1000.0;

            double dishwasherPower = Double.parseDouble(dishwasher_power.getText());
            double dishwasherKWH = (dishwasherPower * 240) / 1000.0;

            double lightsPower = Double.parseDouble(light_power.getText());
            double lightsKWH = (lightsPower * 240) / 1000.0;

            double washerPower = Double.parseDouble(washer_power.getText());
            double washerKWH = (washerPower * 240) / 1000.0;

            double dryerPower = Double.parseDouble(dryer_power.getText());
            double dryerKWH = (dryerPower * 240) / 1000.0;

            double acCost = (120*(12.79/100));
            double heaterCost = (84*(12.79/100));
            double solarCost = (-45*(12.79/100));

            double heaterKWH = 84;
            //total kwh from solar produced  in a month subtracted from total cost and power
            double solarKWH = -45;


            data.storeUtilityConsumptionPower(fridgeKWH, microwaveKWH, tvKWH, dishwasherKWH, lightsKWH, washerKWH, dryerKWH);
            power = new Power(fridgeKWH, microwaveKWH, tvKWH, dishwasherKWH, lightsKWH, washerKWH, dryerKWH);
            data.storeAllutilityConsumptionCosts(fridgeKWH, microwaveKWH, tvKWH, dishwasherKWH, lightsKWH, washerKWH, dryerKWH);
            cost = new Cost(fridgeKWH, microwaveKWH, tvKWH, dishwasherKWH, lightsKWH, washerKWH, dryerKWH, acCost, heaterCost, solarCost);
            status_display.setText("Successfully entered utility power");

            // Update EnergyController with the new power and cost
            if (energyController != null) {
//                energyController.updatePowerDetails(power);
//                energyController.updateCostDetails(cost);
            }
            display_field.setText(
                    "Utility Consumptions in KWH:\n" +
                            "Fridge: " + String.format("%.2f", power.getFridgePower()) + " KWH \n" +
                            "Microwave: " + String.format("%.2f", power.getMicrowavePower()) + " KWH \n" +
                            "TV: " + String.format("%.2f", power.getTVPower()) + " KWH \n" +
                            "Dishwasher: " + String.format("%.2f", power.getDishwasherPower()) + " KWH\n" +
                            "Lights: " + String.format("%.2f", power.getLightPower()) + " KWH \n" +
                            "Washer: " + String.format("%.2f", power.getWasherPower()) + " KWH\n" +
                            "Dryer: " + String.format("%.2f", power.getDryerPower()) + " KWH\n " +
                            "Total Power: " + String.format("%.2f", power.getTotalPower()) + " KWH"
            );
        } catch (NumberFormatException | NullPointerException e) {
            status_display.setText("Error: Please enter numeric values for all fields. " + e.getMessage());
            status_display.setTextFill(Color.RED);
            e.printStackTrace(); // This will print the stack trace to your console
        }

}



    /**
     * Closes the current window when the return button is clicked.
     *
     * @param event the action event triggered by the user
     */
    @FXML
    void return_button(ActionEvent event) {
        Stage stage = (Stage) return_button.getScene().getWindow();
        stage.close(); // This will close only the current window
    }

    /**
     * Initializes the controller with the given display and status labels.
     *
     * @param statusDisplay the label for displaying status messages
     * @param displayField  the text area for displaying detailed information
     */
    public void setup(Label statusDisplay, TextArea displayField) {

        this.status_display = statusDisplay;
        this.display_field = displayField;

    }


}
