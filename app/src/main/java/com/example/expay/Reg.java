package com.example.expay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Reg extends AppCompatActivity {
    EditText txt_name,txt_username,text_rfid,regEmail,regPassword;
    Button btn_next;

    private ProgressBar progressBar;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        txt_name=findViewById(R.id.com_name);
        txt_username=findViewById(R.id.num_plate);
        text_rfid = findViewById(R.id.rfid_text);


        btn_next=findViewById(R.id.btn);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn:
                        btn();
                        break;

                }

            }

            private void btn() {
               // final String email = regEmail.getText().toString().trim();
               // final String accNumber = regAccNumber.getText().toString().trim();
               // final String password = regPassword.getText().toString().trim();
               // final  String meterReading = "0";

               final String Com_name=txt_name.getText().toString();
               final String num_plate=txt_username.getText().toString();
               final String rfid_text = text_rfid.getText().toString();
               final String email = regEmail.getText().toString().trim();
               final String password = regPassword.getText().toString().trim();

                final int credit = 100;
                final int state = 1;

                //add validation here
                if(email.isEmpty()){
                    regEmail.setError("E-mail is required");
                    regEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    regEmail.setError("please provide valid email");
                    regEmail.requestFocus();
                    return;
                }


                if (password.isEmpty()){
                    regPassword.setError("Type a password");
                }
                if (password.length()<6){
                    regPassword.setError("Minimum 6 letters required");
                    regPassword.requestFocus();
                    return;

                }


                progressBar.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                   // int meterReading = 0 ;
                                   // boolean power = true ;
                                   // User user = new User(accNumber, email, meterReading, power);

                                    UserHelperClass helperClass=new UserHelperClass(num_plate,Com_name,rfid_text,credit,state);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()) {
                                                Toast.makeText(Reg.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }else{
                                                Toast.makeText(Reg.this, "Failed to register User", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);


                                            }
                                        }
                                    });
                                }
                                //else{
////                                        Toast.makeText(SignUp.this, "Failed to connect", Toast.LENGTH_SHORT).show();
////                                        progressBar.setVisibility(View.GONE);
////                                }
                            }
                        });




            }
        });


    }


/*
    private boolean save()
    {

        String Com_name=txt_username.getText().toString();
       // DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child(Com_name);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChildren())
                {
                    Toast.makeText(getApplicationContext(),"Data Already Exits",Toast.LENGTH_LONG).show();
                }
                else
                {
                    String Com_name=txt_name.getText().toString();
                    String num_plate=txt_username.getText().toString();
                    String rfid_text = text_rfid.getText().toString();
                    int credit = 100;
                    int state = 1;

                    FirebaseDatabase rootNode;
                    DatabaseReference reference;
                    rootNode=FirebaseDatabase.getInstance();
                    reference=rootNode.getReference();

                    UserHelperClass helperClass=new UserHelperClass(num_plate,Com_name,rfid_text,credit,state);

                    FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(helperClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(Reg.this, "User has been registered successfully", Toast.LENGTH_SHORT).show();
                                //progressBar.setVisibility(View.GONE);
                            }else{
                                Toast.makeText(Reg.this, "Failed to register User", Toast.LENGTH_SHORT).show();
                                //progressBar.setVisibility(View.GONE);


                            }
                        }
                    });
                    //reference.child(Com_name).setValue(helperClass);

                    Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return  true;


    } */

}