package datamodel;

import java.time.LocalDate;

public class TodoListItem implements Comparable<TodoListItem>{
    private String title ;
    private String desc;
    private LocalDate deadline;

    public TodoListItem(String title, String desc, LocalDate deadline) {
        this.title = title;
        this.desc = desc;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String dateFormatter(){
        LocalDate date = getDeadline();
        String work = date.toString();
        String split[] = work.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        String Month = "";
        switch(month){
            case 1 :
                Month = "January";
            case 2 :
                Month = "February";
            case 3 :
                Month = "March";
            case 4 :
                Month = "April";
            case 5 :
                Month = "May";
            case 6 :
                Month = "June";
            case 7 :
                Month = "July";
            case 8 :
                Month = "August";
            case 9 :
                Month = "September";
            case 10 :
                Month = "October";
            case 11 :
                Month = "November";
            case 12 :
                Month = "December";
        }

        return  day + " " + Month + " , " + year;

    }

    // overriden the toString() to show the title in list view except for object references
    // In controller we are populating our listview with a list of objects i.e. the TodoListItemClass not a list of strings
    // an object is not a string so what should fxml listview infer on display , it display object references( weird codes like @2334datamodel.class)
    // TO overcome this in List<TodoListItem> in TodoListItem Class we override toString() to give a single string as title
    @Override
    public String toString() {
        return title;
    }

    @Override
    public int compareTo(TodoListItem o) {

        if(this.deadline.isEqual(LocalDate.now())){
            return -1;
        }else{
            return (this.deadline.isAfter(o.deadline)) ? 1 : this.deadline.isBefore(o.deadline) ? -1 : 0;
        }

    }
}
