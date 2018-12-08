package sample;

import datamodel.TodoListItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Controller {

    private List<TodoListItem> todoList;

    @FXML
    private ListView leftListView;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label dueDateLabel;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button focusget;

    // initialize() in a special method used to set the initial things if any in the application , it is self invoking
    public void initialize(){

        todoList = new ArrayList<>();

        // Sample items
        TodoListItem item = new TodoListItem("TodoList Application","Complete the JavFX sample Todo list applicaton as " +
                "soon as possible",LocalDate.of(2018, Month.DECEMBER,10));

        // Adding items to the List
        todoList.add(item);

        item = new TodoListItem("today","today",LocalDate.of(2018, Month.DECEMBER,5));

        todoList.add(item);



        item = new TodoListItem("Origin","Download and watch Origin youtube series as " +
                "soon as possible",LocalDate.of(2018, Month.DECEMBER,25));

        todoList.add(item);


        item = new TodoListItem("InternShips","Start work from Home Web Development Internship as " +
                "soon as possible",LocalDate.of(2018, Month.DECEMBER,15));

        todoList.add(item);



        item = new TodoListItem("End Sems","Study for Endsems",LocalDate.of(2018, Month.NOVEMBER,30));

        todoList.add(item);


        List<TodoListItem> expiredList = new ArrayList<>();
        List<TodoListItem> copy = new ArrayList<>();


        for(int i=0 ; i<todoList.size() ; i++){
            if(todoList.get(i).getDeadline().isBefore(LocalDate.now())){

                expiredList.add(todoList.get(i));

            }else{

                copy.add(todoList.get(i));

            }
        }


        todoListDateSort(copy);


        // Cell factory is used to set color of individual cell's text and other things
        leftListView.setCellFactory(new javafx.util.Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell<TodoListItem> cell = new ListCell<>(){
                    @Override
                    protected void updateItem(TodoListItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty){
                            setText(null);
                        }else{
                            setText(item.getTitle());
                            if(item.getDeadline().isEqual(LocalDate.now()) || item.getDeadline().isBefore(LocalDate.now())){
                                setTextFill(Color.RED);
                            }else{
                                setTextFill(Color.BLUE);
                            }
                        }
                    }
                };
                return cell;
            }
        });

        // Populating the ListView
        leftListView.getItems().addAll(copy);
        leftListView.getItems().addAll(expiredList);

        // setting the selection mode to single selection in ListView
        leftListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        descriptionArea.setEditable(false);

    }

    @FXML
    public void showDescription(){

        // storing selected item
        descriptionArea.clear();
       TodoListItem item = (TodoListItem) leftListView.getSelectionModel().getSelectedItem();
       descriptionArea.setText(item.getDesc());
       if(item.getDeadline().isBefore(LocalDate.now())){
           dueDateLabel.setText("Due Date : Expired");
       }else {
           dueDateLabel.setText("Due Date : " + item.dateFormatter());
       }

       }



       @FXML
    public void Home(){

        leftListView.getItems().clear();
           dueDateLabel.setText("");
           descriptionArea.setText("");

           List<TodoListItem> expiredList = new ArrayList<>();
           List<TodoListItem> copy = new ArrayList<>();


           for(int i=0 ; i<todoList.size() ; i++){
               if(todoList.get(i).getDeadline().isBefore(LocalDate.now())){

                   expiredList.add(todoList.get(i));

               }else{

                   copy.add(todoList.get(i));

               }
           }

            todoListDateSort(copy);

           copy.addAll(expiredList);
           leftListView.getItems().addAll(copy);




       }

    @FXML
    public void Today(){


        ArrayList<TodoListItem>  temp = new ArrayList<>() ;
        // clearing the current list view
        leftListView.getItems().clear();
        dueDateLabel.setText("");
        descriptionArea.setText("");

        for(int i=0 ; i < todoList.size() ; i++){
            if(todoList.get(i).getDeadline().isEqual(LocalDate.now())){
                temp.add(todoList.get(i));
            }
        }

        leftListView.getItems().addAll(temp);
    }

    @FXML
    public void Expired(){


        ArrayList<TodoListItem>  temp = new ArrayList<>() ;
        // clearing the current list view
        leftListView.getItems().clear();
        dueDateLabel.setText("");
        descriptionArea.setText("");

        for(int i=0 ; i < todoList.size() ; i++){
            if(todoList.get(i).getDeadline().isBefore(LocalDate.now())){
                temp.add(todoList.get(i));
            }
        }

        leftListView.getItems().addAll(temp);

    }

    public void todoListDateSort(List<TodoListItem> todoList){
        Collections.sort(todoList);
        }

        @FXML
        public void openDialog(){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dialog.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            // to make the dialog modal , Application Modal is the key
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Add new TODO");
            stage.showAndWait();
            focusget.requestFocus();
            addNewTodo();

        }catch (IOException ex){
            System.out.println("error");
            return;
        }


        }

public void addNewTodo(){
        TodoListItem item = DialogController.getItem();

        // Null Value Handling
        if(item != null) {
            todoList.add(item);
            }

        DialogController.setItem(null);

        Home();

    }

}
