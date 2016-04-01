package com.example.risbo.simpsongrades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import stanford.androidlib.SimpleActivity;

public class SimpsonGradesActivity extends SimpleActivity {
    private static final String EMAIL = "bpo@gmail.com";
    private static final String PASSWORD = "bpo@gmail.com";
    private static final String[] DATABASE_NAMES = {"simpsons", "world"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpson_grades);

        Firebase.setAndroidContext(this);

    }

    public void queryClick(View view) {
        Firebase fb = new Firebase("https://popping-inferno-233.firebaseIO.com");

        //We want to build the database like this :
        // simpsons/students
        //          123
        //              name = "Bart"
        //              email = "bart@fox.com"

        // that is equivalent to the students table
        Firebase table = fb.child("simpsons/students");

        Firebase bart = table.child("123");
        bart.child("name").setValue("Bart");
        bart.child("email").setValue("bart@fox.com");

        //watching bart email
        bart.child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                log("bart's email changed to: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        //And if we want to add Lisa too :
        Firebase lisa = table.child("888");
        lisa.child("name").setValue("Lisa");
        lisa.child("email").setValue("lisa@fox.com");

    }


}
