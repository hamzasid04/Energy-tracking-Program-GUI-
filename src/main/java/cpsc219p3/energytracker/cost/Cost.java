package cpsc219p3.energytracker.cost;
import cpsc219p3.energytracker.data.*;


public class Cost {
    private Object[] data;
    private double fridgeCost;
    private double microwaveCost;
    private double tvCost;
    private double dishwasherCost;
    private double lightsCost;
    private double washerCost;
    private double dryerCost;
    //private double ACCost;

    private double acCost;
    private double heaterCost;
    private double solarCost;
    /**
     * Constructs a cpsc219p3.energytracker.cost.Cost object with specified power consumption values for various appliances.
     * The cost for each appliance is calculated based on the given power consumption value and a fixed rate.
     *
     * @param fridgeKWh      cpsc219p3.energytracker.power.Power consumption of the fridge in kWh.
     * @param microwaveKWh   cpsc219p3.energytracker.power.Power consumption of the microwave in kWh.
     * @param tvKWh          cpsc219p3.energytracker.power.Power consumption of the TV in kWh.
     * @param dishwasherKWh  cpsc219p3.energytracker.power.Power consumption of the dishwasher in kWh.
     * @param lightKWh       cpsc219p3.energytracker.power.Power consumption of the lights in kWh.
     * @param washerKWh      cpsc219p3.energytracker.power.Power consumption of the washer in kWh.
     * @param dryerKWh       cpsc219p3.energytracker.power.Power consumption of the dryer in kWh.
     */

    // this is the contructor where we have all the variables that we need to access for other classes to use
    public Cost (double fridgeKWh,double microwaveKWh,double tvKWh,double dishwasherKWh,double lightKWh,double washerKWh,double dryerKWh){
        this.fridgeCost= ((fridgeKWh*12.79)/100);
        this.microwaveCost = ((microwaveKWh*12.79)/100);
        this.tvCost = ((tvKWh*12.79)/100);
        this.dishwasherCost = ((dishwasherKWh*12.79)/100);
        this.lightsCost = ((lightKWh*12.79)/100);
        this.washerCost = ((washerKWh*12.79)/100);
        this.dryerCost = ((dryerKWh*12.79)/100);

        //this.ACCost = acCost;
        //* @param acCost cpsc219p3.energytracker.cost.Cost of the air conditioner usage.

    }
    public Cost (double acCost, double heaterCost, double solarCost) {
        this.acCost = acCost;
        this.heaterCost = heaterCost;
        this.solarCost = solarCost;
    }

    public Cost (double fridgeKWh,double microwaveKWh,double tvKWh,double dishwasherKWh,double lightKWh,double washerKWh,double dryerKWh,double acCost, double heaterCost, double solarCost ){
        this.fridgeCost= ((fridgeKWh*12.79)/100);
        this.microwaveCost = ((microwaveKWh*12.79)/100);
        this.tvCost = ((tvKWh*12.79)/100);
        this.dishwasherCost = ((dishwasherKWh*12.79)/100);
        this.lightsCost = ((lightKWh*12.79)/100);
        this.washerCost = ((washerKWh*12.79)/100);
        this.dryerCost = ((dryerKWh*12.79)/100);
        this.acCost = acCost;
        this.heaterCost = heaterCost;
        this.solarCost = solarCost;
    }

    public Object[] getData() {
        return data;
    }
    // Getters for each cost value

    public double getFridgeCost() {
        return this.fridgeCost;
    }

    public double getMicrowaveCost() {
        return this.microwaveCost;
    }

    public double getTvCost() {
        return this.tvCost;
    }

    public double getDishwasherCost() {
        return this.dishwasherCost;
    }

    public double getLightsCost() {
        return this.lightsCost;
    }

    public double getWasherCost() {
        return this.washerCost;
    }

    public double getDryerCost() {
        return this.dryerCost;
    }

    public double getAcCost() {
        return this.acCost;
    }


    public double getHeaterCost(){
        return this.heaterCost;
    }

    public double getSolarCost(){
        return this.solarCost;
    }
    /**
     * Calculates the total cost of power consumption for all appliances.
     *
     * @return The sum of the costs of all appliances.
     */
    public double getTotalCost(){
        double totalCost = this.acCost + this.fridgeCost +this.microwaveCost+this.tvCost+this.lightsCost+this.dishwasherCost+this.washerCost+this.dryerCost
                + this.acCost + this.heaterCost + this.solarCost;
        return totalCost;
    }
    // Overrides the toString method to display cost information

    @Override
    public String toString() {
        return "Cost:" + '\n' +
                "Fridge Cost= $" + String.format("%.2f", fridgeCost) + '\n' +
                "Microwave Cost= $" + String.format("%.2f", microwaveCost) + '\n' +
                "TV Cost= $" + String.format("%.2f", tvCost) + '\n' +
                "Dishwasher Cost= $" + String.format("%.2f", dishwasherCost) + '\n' +
                "Lights Cost= $" + String.format("%.2f", lightsCost) + '\n' +
                "Washer Cost= $" + String.format("%.2f", washerCost) + '\n' +
                "Dryer Cost= $" + String.format("%.2f", dryerCost) + '\n' +
                "AC Cost= $" + String.format("%.2f", acCost) + '\n' +
                "Heater Cost= $" + String.format("%.2f", heaterCost) + '\n' +
                "Solar Cost= $" + String.format("%.2f", solarCost) + '\n' +
                "Total Cost= $" + String.format("%.2f", getTotalCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cost cost = (Cost) o;

        return Double.compare(fridgeCost, cost.fridgeCost) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(fridgeCost);
        return (int) (temp ^ (temp >>> 32));
    }
}
