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
    
    
    private Stack<String> s = new Stack<String>();

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
        
        try{
        
        String getString = textArea.getText();//gets the string that is currently in the textarea
        
        String removeMe = getString.substring(getString.length() -1, getString.length());//removes the last character
        
        s.push(removeMe);//pushes the character into the stack for possible redo
        
        
        String newString = getString.substring(0, getString.length() - 1);//creates a new string by chopping off the end that was removed
        
        textArea.setText(newString);//puts the string back into the text area
        } catch (Exception e){//this fires if there is nothing left to undo, IE if the textArea is empty 
            makeUndoAlert();
        }
     
        
    }

    @FXML
    private void redoType(ActionEvent event) {
        
        try{
        String str = s.pop();
        
        String curr = textArea.getText();
        
        curr += str;
        
        textArea.setText(curr);
        
        }catch (Exception e){
            makeRedoAlert();
        }
    }

    @FXML
    private void testMe(KeyEvent event) {
        
        System.out.println(textArea.getText());
        
    }
    /**
     * this method will create an alert saying there is nothing to undo, if the text area is empty
     */
    public void makeUndoAlert(){
        
    }
    
    /**
     * this method will create an alert saying there is nothing to redo, meaning the stack is empty 
     */
    public void makeRedoAlert(){
        
    }
}
