package com.example.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextView helloText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //opens up the activity
        setContentView(R.layout.activity_main);

        //add the changeText
        this.helloText = findViewById(R.id.changeText);

        //add the button to the button object.
        this.button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get the value from the TextView and
                String str = helloText.getText().toString();

                //how to get the String not and int!
                if (str.equals(getString(R.string.hello_world))) {
                    //set "Hello,World!" to new string.
                    helloText.setText(R.string.goodbye_world);
                }
                else{
                    helloText.setText(R.string.hello_world);
                }
            }
        });

    }

}
