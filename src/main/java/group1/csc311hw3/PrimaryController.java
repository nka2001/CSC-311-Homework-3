package group1.csc311hw3;

import java.io.File;
import java.io.IOException;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PrimaryController {

    @FXML
    private TextArea textArea;
    
    
    private Stack<String> s;

    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void openFile(ActionEvent event) {
        
        FileChooser fc = new FileChooser();
        fc.setTitle("Open File");
        fc.setInitialDirectory(new File("Data"));
        fc.getExtensionFilters().addAll(
        new ExtensionFilter("Text Files", "*.txt"));
        
        
        System.out.println("open file");
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
        
        String str = s.pop();
        
        String curr = textArea.getText();
        
        curr += str;
        
        textArea.setText(curr);
        
        
    }

    @FXML
    private void testMe(KeyEvent event) {
        
        System.out.println(textArea.getText());
        
    }
}
