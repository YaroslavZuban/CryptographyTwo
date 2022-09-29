module com.example.cryptographytwo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cryptographytwo to javafx.fxml;
    exports com.example.cryptographytwo;
}