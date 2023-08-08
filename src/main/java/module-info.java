module com.example.xx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.xx to javafx.fxml;
    exports com.example.xx;
}