package com.example.expay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;


public class home extends AppCompatActivity {

    private FirebaseAuth mAuth;

    //private TextView mReading;

    private DatabaseReference mDatabase;

    private FirebaseUser user;

    //private DatabaseReference reference;

    private String userID;
    private TextView creditTextView;
    private TextView greetingTextView;
    private TextView rideStatusTextView;
    private CardView cardviewFirst;
    private CardView cardviewSecond;

    public home() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = FirebaseAuth.getInstance().getCurrentUser();
        // reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        //Log.e(null, "case  " + userID);

        creditTextView = findViewById(R.id.credit_Label);
        greetingTextView = findViewById(R.id.first_status);
        rideStatusTextView = findViewById(R.id.ride_status);
        cardviewFirst = findViewById(R.id.cardview_credit);
        cardviewSecond = findViewById(R.id.cardview_Ride);

        getData();
        changeCard();
        

    }

    private void changeCard() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                String ride_status = String.valueOf(snapshot.child("state").getValue());
                Log.e(null, "case finish  " + ride_status);

                if (ride_status.toString().equals("1")) {
                    rideStatusTextView.setText("Ride started. Have a safe journey");
                    cardviewSecond.setCardBackgroundColor(Color.parseColor("#4F7942"));
                }else{
                    rideStatusTextView.setText("You have completed your ride!");
                    cardviewSecond.setCardBackgroundColor(Color.parseColor("#f88379"));
                }



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    private void getData() {

       // mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

               // Log.e(null, "case  " + snapshot);
                String credit_value = String.valueOf(snapshot.child("credit").getValue());
               // Log.e(null, "case finish  " + credit_value);
                creditTextView.setText(credit_value);



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

      /* mDatabase.child("Users").child(userID).orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)  {

                //Log.e(null, "case  " + dataSnapshot);


                //String credit_value = String.valueOf(dataSnapshot.child("credit").getValue());
                //String credit_value = dataSnapshot.child("credit").getValue().toString();
                //creditTextView.setText(credit_value);

              // Log.e(null, "case na  " + credit_value);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }


            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {

            }


            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                Toast.makeText(home.this, "Something went wrong!", Toast.LENGTH_LONG).show();

            }
        });



*/

    }
}