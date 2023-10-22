module com.find_city.final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;


    opens com.find_city.final_project to javafx.fxml;
    exports com.find_city.final_project;
    exports com.find_city.bd;
}