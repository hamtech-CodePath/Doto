package io.hamtech.doto;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.util.ArrayList;

import io.hamtech.doto.db.TaskContract;
import io.hamtech.doto.db.TaskDbHelper;
import io.hamtech.doto.db.TasksAdapter;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    TaskDbHelper mHelper;
    ListView mTaskListView;
    TasksAdapter mAdapter;
    ArrayList<Task> taskArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new TasksAdapter(this, taskArray); // make adapter
        mTaskListView = (ListView) findViewById(R.id.list_todo); //connect listView
        mTaskListView.setAdapter(mAdapter);


        //Dummy Data
        Task newTask = new Task("This is test");
        mAdapter.add(newTask);


//        mHelper = new TaskDbHelper(this);

//        SQLiteDatabase db = mHelper.getReadableDatabase();
//        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
//                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE},
//                null, null, null, null, null);
//        while(cursor.moveToNext()) {
//            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
//            Log.d(TAG, "Task: " + cursor.getString(idx));
//        }
//        cursor.close();
//        db.close();

       // updateUI(); //load values into view

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Task task = new Task(String.valueOf(taskEditText.getText()));
                                addTaskToDB(task); //save task in db
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addTaskToDB(Task toSave){
          taskArray.add(toSave); //add array object
          //save to SQL
          mAdapter.notifyDataSetChanged();

//          mAdapter.notifyItemInserted(todoItems.indexOf(tdi));
//        todoItems.add(toSave);
//        tdi.save(); //SQL Create.R.U.D.
//        todoAdapter.notifyItemInserted(todoItems.indexOf(tdi));
//        displayCompletionToast();
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(TaskContract.TaskEntry.COL_TASK_TITLE, task);
//        db.insertWithOnConflict(TaskContract.TaskEntry.TABLE,
//                null,
//                values,
//                SQLiteDatabase.CONFLICT_REPLACE);
//        db.close();

    }

    private void updateUI() {
//        ArrayList<String> taskList = new ArrayList<>();
//        SQLiteDatabase db = mHelper.getReadableDatabase();
//        Cursor cursor = db.query(TaskContract.TaskEntry.TABLE,
//                new String[]{TaskContract.TaskEntry._ID, TaskContract.TaskEntry.COL_TASK_TITLE},
//                null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            int idx = cursor.getColumnIndex(TaskContract.TaskEntry.COL_TASK_TITLE);
//            taskList.add(cursor.getString(idx));
//        }
//
//        if (mAdapter == null) {
//            mAdapter = new ArrayAdapter<>(this,
//                    R.layout.item_todo,
//                    R.id.task_title,
//                    taskList);
//            mTaskListView.setAdapter(mAdapter);
//        } else {
//            mAdapter.clear();
//            mAdapter.addAll(taskList);
//            mAdapter.notifyDataSetChanged();
//        }
//
//        cursor.close();
//        db.close();
    }

    public void deleteTask(View view) {
//        View parent = (View) view.getParent();
//        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
//        String task = String.valueOf(taskTextView.getText());
//        SQLiteDatabase db = mHelper.getWritableDatabase();
//        db.delete(TaskContract.TaskEntry.TABLE,
//                TaskContract.TaskEntry.COL_TASK_TITLE + " = ?",
//                new String[]{task});
//        db.close();
//        updateUI();
    }

    public void editTask(View view){
//        View parent = (View) view.getParent();
//        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
//        String task = String.valueOf(taskTextView.getText());
//        showEditDialog();
    }

    private void showEditDialog() {
//        FragmentManager fm = getSupportFragmentManager();
//        EditTaskDialogFragment editNameDialogFragment = EditTaskDialogFragment.newInstance("Some Title");
//        editNameDialogFragment.show(fm, "fragment_edit_task");
    }

}
