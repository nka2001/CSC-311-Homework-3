module group1.csc311hw3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens group1.csc311hw3 to javafx.fxml;
    exports group1.csc311hw3;
}
