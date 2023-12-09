module com.example.ap_final {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;

    opens com.example.ap_final to javafx.fxml;
    exports com.example.ap_final;
}