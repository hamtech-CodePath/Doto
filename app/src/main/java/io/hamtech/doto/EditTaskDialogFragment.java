package io.hamtech.doto;

import android.app.Activity;
import android.support.v4.app.DialogFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditTaskDialogFragment extends android.support.v4.app.DialogFragment {
    private EditText mEditText;
    private int currentPos;
    private Button cancelEditBtn;
    private Button changeTaskBtn;
    OnDataPass dataPasser;

    public EditTaskDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below

    }

    public static EditTaskDialogFragment newInstance(String title, Task toEdit, int tPos) {
        EditTaskDialogFragment frag = new EditTaskDialogFragment();
        Bundle args = new Bundle();
        int currentPos = tPos;
        args.putString("title", title);
        args.putString("taskName", toEdit.name);
        frag.setArguments(args);
        return frag;
    }

    private void setText(Task toEdit){
        mEditText.setText(toEdit.name);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_edit_task, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cancelEditBtn = (Button) view.findViewById(R.id.cancel_action);
        //setup Btns
        cancelEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //remove fragment >> goto MainActivity
                dismiss();
            }
        });

        changeTaskBtn = (Button) view.findViewById(R.id.change_action);
        changeTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send changed task to actvity
                passData(mEditText.getText().toString());
            }
        });


        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.txt_your_task);
        String cTask = getArguments().getString("taskName", "Task Name");
        final int tPos = getArguments().getInt("tPos");
        mEditText.setText(cTask);
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            passData(mEditText.toString()); //on enter send data back to activity
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Edit Task");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request fs to field
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public interface OnDataPass {
        public void onDataPass(String data);
    }

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
        dataPasser = (OnDataPass) a;
    }

    public void passData(String task) {
        dataPasser.onDataPass(task);
    }
}
