module com.example.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens org to javafx.fxml;
    opens org.controller to javafx.fxml;
    exports org;
}