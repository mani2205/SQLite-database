package com.example.rvsdc;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Intent intent;
    TextView errorMessage;
    EditText useridEt,passEt;
    Button loginBtn,signUpBtn;
    String username,password;
    MyDatabase obj;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        obj=new MyDatabase(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn=findViewById(R.id.loginBtn);
        signUpBtn=findViewById(R.id.signup);
        useridEt=findViewById(R.id.aa);
        passEt=findViewById(R.id.bb);
        errorMessage=findViewById(R.id.errorMsg);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Cursor res=obj.getAllData();
                username=useridEt.getText().toString();
                password=passEt.getText().toString();
                while (res.moveToNext())
                {

                    if(username.contentEquals(res.getString(0))&&password.contentEquals(res.getString(3)))
                    {
                        errorMessage.setText("");
                        loginSucess("Welcome","Your are authorized to access this page");
                        return;
                    }

                }
                errorMessage.setText("! Incorrect username or password");
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this,Sign_up.class);
                startActivity(intent);
            }
        });
    }

    public void loginSucess(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
