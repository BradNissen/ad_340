package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();
    private TextView messageView;
    private Button loginBtn;
    private EditText nameIn;
    private EditText userNameIn;
    private EditText emailIn;
    private TextView dobIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        messageView = findViewById(R.id.messageTextView);

        StringBuilder msg = new StringBuilder("Thanks for signing up, ");

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Log.i(TAG, "intent");

        assert b != null;
        if (b.containsKey(Constants.KEY_NAME)){
            String name = b.getString(Constants.KEY_NAME);
            String lowerCaseName = name.toLowerCase();
            String properName = lowerCaseName.substring(0, 1).toUpperCase() + lowerCaseName.substring(1);
            msg.append(properName).append("!");
        }
//        if (b.containsKey(Constants.KEY_USER_NAME)){
//            String userName = b.getString(Constants.KEY_USER_NAME);
//            msg.append("username: "+ userName).append("\n");
//        }
//        if (b.containsKey(Constants.KEY_EMAIL)){
//            String email = b.getString(Constants.KEY_EMAIL);
//            msg.append("email: " +email).append("\n");
//        }

//        if (b.containsKey(Constants.KEY_DOB)){
//            String dob = b.getString(Constants.KEY_DOB);
//            msg.append("D.O.B.: "+ dob).append("\n");
//        }

        if (b.containsKey(Constants.KEY_AGE)){
            String age = b.getString(Constants.KEY_AGE);
            msg.append("\nForm\nif you didnt know... You're "+ age + " years old.");
        }


            messageView.setText(msg);
    }

    public void goToMainActivity(View view){
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
        //this.finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }
}
