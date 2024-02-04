module cpsc219p3.energytracker {
    requires javafx.controls;
    requires javafx.fxml;


    opens cpsc219p3.energytracker to javafx.fxml;
    exports cpsc219p3.energytracker;
    exports cpsc219p3.energytracker.p2;
    opens cpsc219p3.energytracker.p2 to javafx.fxml;
    opens cpsc219p3.energytracker.data to javafx.fxml;
    exports cpsc219p3.energytracker.data;
    opens cpsc219p3.energytracker.power to javafx.fxml;
    exports cpsc219p3.energytracker.power;
    opens cpsc219p3.energytracker.writer to javafx.fxml;
    exports cpsc219p3.energytracker.writer;
    exports cpsc219p3.energytracker.util;
    opens cpsc219p3.energytracker.util to javafx.fxml;
    exports cpsc219p3.energytracker.cost;
    opens cpsc219p3.energytracker.cost to javafx.fxml;
}