package com.example.expay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.CountDownTimer;
import android.os.Handler;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;


public class home extends AppCompatActivity {

   // private static final String CHANNEL_ID = "channel1";
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

       // createNotificationChannel();

        getData();
        changeCard();
       // notification();
        

    }

   /* private void createNotificationChannel() {

// Create the NotificationChannel, but only on API 26+ because
// the NotificationChannel class is new and not in the support library
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CharSequence name = getString(R.string.channel_name);
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name,
                        importance);
                channel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviours after this
                NotificationManager notificationManager =
                        getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);
            }
    }


    private void notification() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                String notification_status = String.valueOf(snapshot.child("state").getValue());
                Log.e(null, "case notification  " + notification_status);
                if (notification_status.toString().equals("1")){






                    NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.ic_person_24)
                            .setContentTitle("ExPay")
                            .setContentText("You have successfully started your ride");

                    NotificationManager notificationManager = (NotificationManager)
                            getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(1,mBuilder.build());

                }else{



                        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder)
                                new NotificationCompat.Builder(getApplicationContext())
                                        .setSmallIcon(R.drawable.ic_person_24)

                                        .setContentTitle("ExPay")
                                        .setContentText("You have successfully completed your ride");

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);   */

// notificationId is a unique int for each notification that you must define
                //    notificationManager.notify(1, mBuilder.build());


                     /*   NotificationManager notificationManager = (NotificationManager)
                                getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(1, mBuilder.build()); */



             //   }
         //   }

          //  @Override
         //   public void onCancelled(@NonNull @NotNull DatabaseError error) {

        //    }
       // });
   // }

    private void changeCard() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                String ride_status = String.valueOf(snapshot.child("state").getValue());
                Log.e(null, "case finish  " + ride_status);

                if (ride_status.toString().equals("1")) {
                    rideStatusTextView.setText("Ride started. Have a safe journey");
                    cardviewSecond.setCardBackgroundColor(Color.parseColor("#39ff14"));

                   /* new CountDownTimer(7000, 2000) {

                        public void onTick(long millisUntilFinished) {
                            rideStatusTextView.setText("100 rupees deducted from your account");
                           // mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            rideStatusTextView.setText("200 rupees deducted from your account");
                        }
                    }.start(); */

                   final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms

                            rideStatusTextView.setText("100 rupees deducted from your account");
                            cardviewSecond.setCardBackgroundColor(Color.parseColor("#f4ca16"));

                        }
                    }, 5000);

                    final Handler handler2 = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms

                            rideStatusTextView.setText("200 rupees deducted from your account");
                            cardviewSecond.setCardBackgroundColor(Color.parseColor("#f4ca16"));

                        }
                    }, 7000);


                }else{
                    rideStatusTextView.setText("You have completed your ride!");
                    cardviewSecond.setCardBackgroundColor(Color.parseColor("#f88379"));
                }



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(home.this, "Database error identified!", Toast.LENGTH_LONG).show();

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
                Toast.makeText(home.this, "Database error identified!", Toast.LENGTH_LONG).show();

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