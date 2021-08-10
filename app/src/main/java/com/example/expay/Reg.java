package com.example.expay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Reg extends AppCompatActivity {
    EditText txt_name,txt_username;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        txt_name=findViewById(R.id.com_name);
        txt_username=findViewById(R.id.num_plate);
        btn_next=findViewById(R.id.btn);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Intent intent = new Intent(Reg.this,Payment .class);
                startActivity(intent);
            }
        });


    }



    private boolean save()
    {

        String Com_name=txt_username.getText().toString();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Users").child(Com_name);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.hasChildren())
                {
                    Toast.makeText(getApplicationContext(),"Data Already Exits",Toast.LENGTH_LONG).show();
                }
                else
                {String Com_name=txt_name.getText().toString();
                    String num_plate=txt_username.getText().toString();
                    FirebaseDatabase rootNode;
                    DatabaseReference reference;
                    rootNode=FirebaseDatabase.getInstance();
                    reference=rootNode.getReference("Users");
                    UserHelperClass helperClass=new UserHelperClass(num_plate,Com_name);
                    reference.child(Com_name).setValue(helperClass);
                    Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return  true;


    }

}