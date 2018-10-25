package com.example.osos.deathnote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Entity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.osos.deathnote.DataBase.AppDataBase;
import com.example.osos.deathnote.DataBase.NoteEntity;

public class AddNoteActivity extends AppCompatActivity {


    // Extra for the task ID to be received in the intent
    public static final String EXTRA_TASK_ID = "extraTaskId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";

    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_NOTE_ID = -1;
    // Constant for logging
    private static final String TAG = AddNoteActivity.class.getSimpleName();
    // Fields for views
    EditText mEditTextTitle;
    EditText mEditTextContent;
    Button mButton;

    private int mNoteId = DEFAULT_NOTE_ID;

    // TODO (3) Create AppDatabase member variable for the Database
    private AppDataBase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //instance for database
        mDb = AppDataBase.getsInstance(this);

        initViews();

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            mNoteId = savedInstanceState.getInt(INSTANCE_TASK_ID, DEFAULT_NOTE_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_TASK_ID)) {
            mButton.setText("update");
            if (mNoteId == DEFAULT_NOTE_ID) {
                // populate the UI
                    mNoteId=intent.getIntExtra(EXTRA_TASK_ID,DEFAULT_NOTE_ID);
                    AddTaskViewModelFactory factory=new AddTaskViewModelFactory(mDb,mNoteId);
                    final AddTaskViewModel viewModel=ViewModelProviders.of(this,factory)
                            .get(AddTaskViewModel.class);


                 viewModel.getNote().observe(this, new Observer<NoteEntity>() {
                     @Override
                     public void onChanged(@Nullable NoteEntity noteEntity) {
                         viewModel.getNote().removeObserver(this);
                         populateUI(noteEntity);

                     }
                 });


            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_TASK_ID, mNoteId);
        super.onSaveInstanceState(outState);
    }

    private void initViews() {

        mEditTextTitle = findViewById(R.id.title);
        mEditTextContent = findViewById(R.id.content);
        mButton = findViewById(R.id.save);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    private void populateUI(NoteEntity note) {
        if (note==null){
            return;
        }
        mEditTextTitle.setText(note.getTitle());
        mEditTextContent.setText(note.getContant());

    }

    public void onSaveButtonClicked() {
        String title=mEditTextTitle.getText().toString();
        String content=mEditTextContent.getText().toString();
       final NoteEntity noteEntity =new NoteEntity(title,content,true);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (mNoteId==DEFAULT_NOTE_ID){
                    mDb.noteDao().insertNote(noteEntity);
                }else {
                    noteEntity.setId(mNoteId);
                    mDb.noteDao().updateNote(noteEntity);
                }

                finish();
            }
        });




    }

}
