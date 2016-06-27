package io.hamtech.doto;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;

import java.util.ArrayList;

import io.hamtech.doto.db.TaskContract;
import io.hamtech.doto.db.TaskDbHelper;
import io.hamtech.doto.db.TasksAdapter;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivity";
    public TaskDbHelper mHelper;
    public ListView mTaskListView;
    public TasksAdapter mAdapter;
    public ArrayList<Task> taskArray = new ArrayList<>();
    public boolean longTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new TasksAdapter(this, taskArray); // make adapter
        mTaskListView = (ListView) findViewById(R.id.list_todo); //connect listView
        mTaskListView.setAdapter(mAdapter);

        mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task t = taskArray.get(position); //get selected task
                Toast.makeText(getApplicationContext(),t.name, Toast.LENGTH_LONG).show();
                if(!longTouch) {
                    showEditDialog(t);
                }

                longTouch = false;
            }
        });

        mTaskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longTouch = true;
                showDeleteDialog(view, position);
                Toast.makeText(getApplicationContext(),"delete", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        //Dummy Data
        Task newTask = new Task("This is test");

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

    private void addTaskToDB(Task toSave) {
        taskArray.add(toSave); //add array object
        //save to SQL
        mAdapter.notifyDataSetChanged();
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

    public void editTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        Task t = new Task(task);
        showEditDialog(t);
    }

    private void showEditDialog(Task task) {
        FragmentManager fm = getSupportFragmentManager();
        EditTaskDialogFragment editNameDialogFragment = EditTaskDialogFragment.newInstance("Some Title");
        //editNameDialogFragment.setText(task);
        editNameDialogFragment.show(fm, "fragment_edit_task");
    }

    public void showDeleteDialog(View itemView ,final int position){
        final AlertDialog removalDialog = new AlertDialog.Builder(itemView.getContext())
                .setTitle("Remove from your list?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //todoItems.get(position).delete(); //SQL C.R.U.Delete.
                        taskArray.remove(position);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_menu_delete)
                .create();
        removalDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                //removalDialog.getWindow().setBackgroundDrawableResource(R.color.activityBackground);
                //removalDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#89613D"));
                //removalDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#89613D"));
            }
        });
        removalDialog.show();
    }

}
