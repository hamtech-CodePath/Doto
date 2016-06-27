package io.hamtech.doto;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by hmiles on 6/24/16.
 */
@Table(name = "Task")
public class Task extends Model {
    @Column(name = "name")
    public String name;
    public Task() {
        super();
    }
    public Task(String name){
        super();
        this.name = name;
    }

    //accessor method that returns all ToDoItem objects from List
    public static List<Task> getAllItems() {
        return new Select().from(Task.class).execute();
    }
}
