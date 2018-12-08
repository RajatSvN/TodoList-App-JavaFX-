package sample;

import datamodel.TodoListItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;



public class DialogController {
    @FXML
    private TextField title;

    @FXML
    private TextArea description;


    @FXML
    private DatePicker deadline;

    @FXML
    private Button closer ;


    private static TodoListItem item;


    public static TodoListItem getItem() {
        return item;
    }

    public static void setItem(TodoListItem item) {
        DialogController.item = item;
    }

    public String getTitle() {
        return title.getText();
    }

    public String getDescription() {
        return description.getText();
    }

    public LocalDate getDeadline() {
        return deadline.getValue();
    }

    public void passValue(){

        if(!(this.getTitle().isEmpty()) && !(this.getDescription().isEmpty()) && this.getDeadline() != null){
          item = new TodoListItem(this.getTitle(),this.getDescription(),this.getDeadline());
          // closing the current window when Add button is clicked
          Stage stage = (Stage)closer.getScene().getWindow();
          stage.close();
          }

          }
}
