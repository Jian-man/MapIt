package com.example.mapit;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowSavedLocationsList extends AppCompatActivity {

    ListView lv_savedLocations;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_locations_list);


        lv_savedLocations = findViewById(R.id.lv_wayPoints);


        MyApplication myApplication = (MyApplication)getApplicationContext();
        List<Location> savedLocations = myApplication.getMyLocations();
        for(Location location: savedLocations){
            Geocoder geocoder = new Geocoder(ShowSavedLocationsList.this);
        try{
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            List<String> temp = new ArrayList<>();

            temp.add(addresses.get(0).getAddressLine(0));
            //Populating the ListView with the temp addresses as the wayPoints
            lv_savedLocations.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , temp));
           //readAddress();

        }
        catch (Exception e){
            Toast.makeText(this, "Could not track location", Toast.LENGTH_SHORT).show();
        }
        }

    }
   //private void readAddress() {
   //   FirebaseDatabase database = FirebaseDatabase.getInstance();
   //   DatabaseReference myRef = database.getReference("Users");


   //   mAuth = FirebaseAuth.getInstance();
   //   final FirebaseUser users = mAuth.getCurrentUser();
   //   String finaluser = users.getEmail();
   //   String resultemail = finaluser.replace(".", "");


   //   myRef.child("Users").child(resultemail).child("Addresses").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
   //       @Override
   //       public void onComplete(@NonNull Task<DataSnapshot> task) {
   //           if (!task.isSuccessful()) {
   //               Log.e("firebase", "Error getting data", task.getException());
   //           }
   //           else {
   //               Log.d("firebase", String.valueOf(task.getResult().getValue()));
   //              // lv_savedLocations.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , temp));
   //           }
   //       }
   //   });








       // Read from the database
     //  myRef.addValueEventListener(new ValueEventListener() {
     //      @Override
     //      public void onDataChange(DataSnapshot dataSnapshot) {
     //          // This method is called once with the initial value and again
     //          // whenever data at this location is updated.
     //          String value = dataSnapshot.getValue(String.class);
     //          Log.d(TAG, "Value is: " + value);
     //          lv_savedLocations.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , temp));
     //      }
//
     //      @Override
     //      public void onCancelled(DatabaseError error) {
     //          // Failed to read value
     //          Log.w(TAG, "Failed to read value.", error.toException());
     //      }
     //  });

      //myRef.child("Users").child(resultemail).child("Addresses").setValue(temp);
}