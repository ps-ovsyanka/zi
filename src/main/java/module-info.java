module com.example.zilab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.zilab2 to javafx.fxml;
    exports com.example.zilab2;
}