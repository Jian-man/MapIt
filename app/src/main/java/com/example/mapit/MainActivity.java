package com.example.mapit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        FirebaseUser user = auth.getCurrentUser();

      // if(user != null){
      //     finish();
      //     startActivity(new Intent(this, TrackingActivity.class));
      // }

    }


    public void login (View view)
    {
        view.setEnabled(false);
        Toast.makeText(this,"Going to Log In",Toast.LENGTH_LONG).show();
        Intent i = (new Intent(this, LogInActivity.class));
        startActivity(i);

    }
    public void register (View view)
    {
        view.setEnabled(false);
        Toast.makeText(this,"Going to Register",Toast.LENGTH_LONG).show();
        Intent i = (new Intent(this,RegisterActivity.class));
        startActivity(i);
    }
}