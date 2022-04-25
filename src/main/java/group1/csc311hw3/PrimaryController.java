package group1.csc311hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private TextArea textArea;

    private Stack<String> s = new Stack<String>();//stack used for undo and redo 

    private File file;//file that the user clicks on to open into the textarea
    private int stringLength = 0;

    /**
     * this method is used to open a file and read it into the text area for
     * editing, filechooser will open to the project directory showing only .txt
     * files
     *
     * @param event
     */
    @FXML
    private void openFile(ActionEvent event) throws FileNotFoundException {

        textArea.setText("");//when opening a file, the textArea needs to be wipped
        
        FileChooser fc = new FileChooser();//create a filechooser object

        File initDirect = new File(System.getProperty("user.dir"));//get the path to the working directory, its different for everybody

        fc.setInitialDirectory(initDirect);//sets the filechooser starting point to the current working directory 
        fc.setTitle("File Chooser");//set the title of the filechooser
        FileChooser.ExtensionFilter exFilter = new FileChooser.ExtensionFilter("text file", "*.txt");//filters all the files so only text files appear 
        fc.getExtensionFilters().add(exFilter);//adds and applys the extension
        file = fc.showOpenDialog(null);//sets the chosen file to the global variable called file, showOpenDialog accepts a window as a parameter, since no window is passed I pass null instead

        Scanner fileScan = new Scanner(file);//creates a scanner to read data into the text area

        String str = "";//the string needs to be concatenated to be added all at once 

        while (fileScan.hasNext()) {
            
            
            str += fileScan.nextLine();//concatenates the string line by line, once complete...
            str += "\n";//a newline character is added to keep the formating of the file, say theres indentation this helps preserve that
            
        }
        textArea.setText(str);//add the string to the textArea

    }

    /**
     * this method is used to save the file that is being used
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void saveFile(ActionEvent event) throws IOException {

        FileWriter fw = new FileWriter(file);//create a file writer that will right to the file 
        fw.write(textArea.getText());//write whats in the textarea 
        fw.flush();//empty the filewriter
        fw.close();//close the filewriter and save the file

    }

    @FXML
    private void closeProgram(ActionEvent event) {
        System.exit(0);

    }

    /**
     * method that will undo a change made to the file or textarea, it pushes a
     * character into the stack so it can be redone if need be
     *
     * @param event
     */
    @FXML
    private void undoType(ActionEvent event) {

        try {

            String getString = textArea.getText();//gets the string that is currently in the textarea

            String removeMe = getString.substring(getString.length() - 1, getString.length());//removes the last character

            s.push(removeMe);//pushes the character into the stack for possible redo

            String newString = getString.substring(0, getString.length() - 1);//creates a new string by chopping off the end that was removed
            stringLength--;

            textArea.setText(newString);//puts the string back into the text area
        } catch (Exception e) {//this fires if there is nothing left to undo, IE if the textArea is empty 
            makeUndoAlert();
        }

    }

    /**
     * method that redos a character, it pops the stack and places the character
     * back into the text area
     *
     * @param event
     */
    @FXML
    private void redoType(ActionEvent event) {

        try {
            String str = s.pop();//pops the stack to get the character

            String curr = textArea.getText();// gets the current text from the textarea

            curr += str;//adds what was popped to the current string

            textArea.setText(curr);//sets the text of the textarea to the new string 
            stringLength++;

        } catch (Exception e) {
            makeRedoAlert();//generates an alert if there is nothing to redo IE the stack is empty
        }
    }

    /**
     *
     * @param event
     */
    @FXML
    private void testMe(KeyEvent event) {

        stringLength++;

    }

    /**
     * this method will create an alert saying there is nothing to undo, if the
     * text area is empty
     */
    public void makeUndoAlert() {
        
        Alert a = new Alert(AlertType.INFORMATION);//create an alert
        a.setTitle("Undo Error");//title
        a.setHeaderText("There is nothing to Undo at this time");
        a.showAndWait();//keeps alert up until the user deals with it

    }

    /**
     * this method will create an alert saying there is nothing to redo, meaning
     * the stack is empty
     */
    public void makeRedoAlert() {
         Alert a = new Alert(AlertType.INFORMATION);//create an alert
        a.setTitle("Redo Error");//title
        a.setHeaderText("There is nothing to Redo at this time");
        a.showAndWait();//keeps alert up until the user deals with it
    }
}
