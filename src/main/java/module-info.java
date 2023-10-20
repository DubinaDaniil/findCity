module com.find_city.final_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.find_city.final_project to javafx.fxml;
    exports com.find_city.final_project;
}