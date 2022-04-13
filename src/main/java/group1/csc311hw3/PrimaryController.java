package group1.csc311hw3;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class PrimaryController {

    @FXML
    private TextArea textArea;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void openFile(ActionEvent event) {
    }

    @FXML
    private void saveFile(ActionEvent event) {
    }

    @FXML
    private void closeProgram(ActionEvent event) {
    }

    @FXML
    private void undoType(ActionEvent event) {
    }

    @FXML
    private void redoType(ActionEvent event) {
    }

    @FXML
    private void testMe(KeyEvent event) {
        
        System.out.println(textArea.getText());
        
    }
}
