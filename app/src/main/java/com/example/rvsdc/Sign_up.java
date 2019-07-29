package com.example.rvsdc;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity
{
    String id,name,email,password,confirmPass;
    EditText idEt,nameEt,emailEt,passwordEt,confirmPassEt;
    Button signup,view;
    MyDatabase obj;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        idEt=findViewById(R.id.a);
        nameEt=findViewById(R.id.b);
        emailEt=findViewById(R.id.c);
        passwordEt=findViewById(R.id.d);
        confirmPassEt=findViewById(R.id.e);
        view=findViewById(R.id.button);

        obj=new MyDatabase(this);

        signup=findViewById(R.id.Submit);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=obj.getAllData();
                Toast.makeText(Sign_up.this, "success", Toast.LENGTH_SHORT).show();
                if (res.getCount()==0) {
                    showMessage("Error!!!","No Data Found");
                    //Toast.makeText(Main2Activity.this, "No Data Found..", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuilder sb=new StringBuilder();
                while (res.moveToNext()){
                    sb.append("ID:").append(res.getString(0)).append("\n");
                    sb.append("Name:").append(res.getString(1)).append("\n");
                    sb.append("Phone:").append(res.getString(2)).append("\n");
                    sb.append("Password:").append(res.getString(3)).append("\n");

                }
                //show Message
                showMessage("DataBase",sb.toString());
            }
        });

        signup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                convetToString();
                if(TextUtils.isEmpty(id))
                {
                    idEt.setError("User ID is empty!");
                    return;
                }
                if(TextUtils.isEmpty(name))
                {
                    nameEt.setError("Name is empty!");
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    emailEt.setError("Email is empty!");
                    return;
                }
                if(TextUtils.isEmpty(password))
                {
                    passwordEt.setError("password is empty");
                    return;
                }
                boolean isInserted=obj.Insert(id,name,email,password);
                if(isInserted) {
                    Toast.makeText(Sign_up.this, "Your detail was saved successfully...", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Sign_up.this,MainActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(Sign_up.this, "Please enter all details.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void convetToString()
    {
        id=idEt.getText().toString();
        name=nameEt.getText().toString();
        email=emailEt.getText().toString();
        password=passwordEt.getText().toString();
        confirmPass=confirmPassEt.getText().toString();
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
