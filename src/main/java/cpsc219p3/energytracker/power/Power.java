package cpsc219p3.energytracker.power;

public class Power {

    private double fridgePower;
    private double microwavePower;
    private double tvPower;
    private double dishwasherPower;
    private double lightsPower;
    private double washerPower;
    private double dryerPower;
    private double acPower;
    private double heaterPower;
    private double solarPower;

    private Object[] data;
    //private ArrayList <Object> individualdata;

    /**
     * Constructs a new cpsc219p3.energytracker.power.Power object with specified power consumption values for various appliances.
     *
     * @param fridgeKWh      cpsc219p3.energytracker.power.Power consumption of the fridge in kWh.
     * @param microwaveKWh   cpsc219p3.energytracker.power.Power consumption of the microwave in kWh.
     * @param tvKWh          cpsc219p3.energytracker.power.Power consumption of the TV in kWh.
     * @param dishwasherKWh  cpsc219p3.energytracker.power.Power consumption of the dishwasher in kWh.
     * @param lightKWh       cpsc219p3.energytracker.power.Power consumption of the lights in kWh.
     * @param washerKWh      cpsc219p3.energytracker.power.Power consumption of the washer in kWh.
     * @param dryerKWh       cpsc219p3.energytracker.power.Power consumption of the dryer in kWh.
     *
     */

// this is the contructor where we have all the variables that we need to access for other classes to use
    public Power (double fridgeKWh,double microwaveKWh,double tvKWh,double dishwasherKWh,double lightKWh,double washerKWh,double dryerKWh){
        this.fridgePower= fridgeKWh;
        this.microwavePower = microwaveKWh;
        this.tvPower = tvKWh;
        this.dishwasherPower = dishwasherKWh;
        this.lightsPower = lightKWh;
        this.washerPower = washerKWh;
        this.dryerPower = dryerKWh;
        //this.acPower = acPower;
        //@param acPower        cpsc219p3.energytracker.power.Power consumption of the air conditioner in kWh.
    }
    public Power (double fridgeKWh,double microwaveKWh,double tvKWh,double dishwasherKWh,double lightKWh,double washerKWh,double dryerKWh, double acPower,
                  double heaterPower, double solarPower){
        this.fridgePower= fridgeKWh;
        this.microwavePower = microwaveKWh;
        this.tvPower = tvKWh;
        this.dishwasherPower = dishwasherKWh;
        this.lightsPower = lightKWh;
        this.washerPower = washerKWh;
        this.dryerPower = dryerKWh;
        this.acPower = acPower;
        this.heaterPower = heaterPower;
        this.solarPower = solarPower;
    }


    // Getters for each power consumption value

    public double getFridgePower() {
        return this.fridgePower;
    }

    public double getMicrowavePower() {
        return this.microwavePower;
    }

    public double getTVPower() {
        return this.tvPower;

    }

    public double getDishwasherPower() {
        return this.dishwasherPower;

    }

    public double getLightPower() {
        return this.lightsPower;

    }

    public double getWasherPower() {
        return this.washerPower;

    }

    public double getDryerPower() {
        return this.dryerPower;

    }

    public  double getAcPower() {
        return this.acPower;

    }
    /**
     * Calculates the total power consumption from all appliances.
     *
     * @return The sum of power consumption values of all appliances.
     */
    public double getTotalPower(){

        double totalPower = this.acPower + this.fridgePower +this.microwavePower+this.tvPower+this.lightsPower+this.dishwasherPower+this.washerPower+this.dryerPower;
        return totalPower;
    }

    public Object[] getData() {
        return data;
    }

    // Overrides the toString method to display power consumption information

    @Override
    public String toString() {
        return "Power: " + '\n' +
                "Fridge Power=" + String.format("%.2f", fridgePower) + " kWh, " + '\n' +
                "Microwave Power=" + String.format("%.2f", microwavePower) + " kWh, " + '\n' +
                "TV Power=" + String.format("%.2f", tvPower) + " kWh, " + '\n' +
                "Dishwasher Power=" + String.format("%.2f", dishwasherPower) + " kWh, " + '\n' +
                "Lights Power=" + String.format("%.2f", lightsPower) + " kWh, " + '\n' +
                "Washer Power=" + String.format("%.2f", washerPower) + " kWh, " + '\n' +
                "Dryer Power=" + String.format("%.2f", dryerPower) + " kWh, " + '\n' +
                "AC Power=" + String.format("%.2f", acPower) + " kWh, " + '\n' +
                "Total Power=" + String.format("%.2f", getTotalPower()) + " kWh" + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Power power = (Power) o;

        return Double.compare(fridgePower, power.fridgePower) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(fridgePower);
        return (int) (temp ^ (temp >>> 32));
    }
}
