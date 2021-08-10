package com.example.expay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.email_type);
        EditText pwd = findViewById(R.id.pwd_type);
        Button btn = findViewById(R.id.btn_login);
        firebaseAuth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(!email.getText().toString().isEmpty() && !pwd.getText().toString().isEmpty()){
                        firebaseAuth.signInWithEmailAndPassword(email.getText().toString().trim(),pwd.getText().toString().trim())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(Login.this, "Successfully Sign in this App!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login.this,User_interface_1.class));
                                    }
                                });
                    }else{
                        Toast.makeText(Login.this, "Fill the Fields!", Toast.LENGTH_SHORT).show();
                    }

                }catch(Exception e){
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
//        Email email1= new Email();
        /*btn.setOnClickListener(v ->
        {
            Data emails = new Data(email.getText().toString(),pwd.getText().toString());
            email1.add(emails).addOnSuccessListener(suc->
            {
                Toast.makeText(this, "Log in Success", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->
            {
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });


        });*/
    }
}