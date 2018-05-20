package com.example.helloworld.datamodels;


import com.example.helloworld.MatchesItemFragment;
import com.example.helloworld.models.MatchItem;
//import com.example.mcnutt.inclassdemo.models.TodoItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.function.Consumer;

public class FirebaseMatchModel {

    private DatabaseReference mDatabase;
    private HashMap<DatabaseReference, ValueEventListener> listeners;

    public FirebaseMatchModel() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listeners = new HashMap<>();
    }

    public void addMatchItem(MatchItem item) {
        DatabaseReference todoItemsRef = mDatabase.child("matchItems");
        todoItemsRef.push().setValue(item);
    }

    public void getMatchItems(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
        DatabaseReference matchItemsRef = mDatabase.child("matchItems");
        ValueEventListener matchItemsListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataChangedCallback.accept(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dataErrorCallback.accept(databaseError);
            }
        };
        matchItemsRef.addValueEventListener(matchItemsListener);
        listeners.put(matchItemsRef, matchItemsListener);
    }

    public void updateTodoItemById(MatchItem item) {
        DatabaseReference todoItemsRef = mDatabase.child("matchItems");
        todoItemsRef.child(item.uid).setValue(item);
    }

    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(Query::removeEventListener);
    }
}