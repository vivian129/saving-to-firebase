package com.vivian.savingtofirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.inputNames)
    EditText inputnames;

@BindView(R.id.inputAge)
EditText inputAge;

@BindView(R.id.inputGender)
EditText inputgender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       // String sentence="the quick fox jumped over a lazy dog";

       // HashMap<String,String> data=new HashMap<>();
       // data.put("sentence",sentence);
        //data.put("country","kenya");
        //data.put("phone","huawei");
        //data.put("bus station","station");

       // FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("mydata");

        //myRef.setValue(data);


    }

    @OnClick(R.id.buttonSave)
    public void save(){


        String names= inputnames.getText().toString().trim();
        String age= inputAge.getText().toString().trim();
        String gender= inputgender.getText().toString().trim();



        if (names.isEmpty()||age.isEmpty()||gender.isEmpty()){
            return;
        }

         HashMap<String,String> data=new HashMap<>();
         data.put("names",names);
        data.put("age",age);
        data.put("gender",gender);


         FirebaseDatabase database = FirebaseDatabase.getInstance();
         DatabaseReference myRef = database.getReference("students");

        myRef.push().setValue(data) .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                inputAge.setText("");
                inputgender.setText("");
                inputnames.setText("");
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
