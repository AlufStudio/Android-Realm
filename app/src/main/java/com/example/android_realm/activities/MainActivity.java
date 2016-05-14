package com.example.android_realm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.android_realm.R;
import com.example.android_realm.adapter.NoteListAdapter;
import com.example.android_realm.db.RealmDB;
import com.example.android_realm.listener.RecyclerItemClickListener;
import com.example.android_realm.model.Note;
import com.example.android_realm.util.DividerItemDecoration;

import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener{

    private RecyclerView lvNote;
    private Button btnAdd;

    private NoteListAdapter noteListAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvNote = (RecyclerView) findViewById(R.id.lvNote);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        noteListAdapter = new NoteListAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);

        lvNote.setLayoutManager(linearLayoutManager);
        lvNote.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        lvNote.setAdapter(noteListAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveActivity.start(MainActivity.this, 0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData(){
        if(retrieve() != null)
            noteListAdapter.setNoteList(retrieve());
    }

    public RealmResults<Note> retrieve() {
        RealmResults<Note> result = (RealmResults<Note>) new RealmDB(this).getAllData(Note.class);
        result.sort("dateModified", Sort.DESCENDING);
        return result;
    }

    @Override
    public void onItemClick(int position, View view) {
        SaveActivity.start(this, noteListAdapter.getItem(position).getId());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
