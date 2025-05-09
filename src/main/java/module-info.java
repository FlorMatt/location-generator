module com.floormatt.locationgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.floormatt.locationgenerator to javafx.fxml;
    exports com.floormatt.locationgenerator;
}