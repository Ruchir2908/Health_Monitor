package com.example.pksharma.healthmonitor;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

public class showProgress extends AppCompatActivity {

    DatabaseReference myRef,demoRef;
    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    EditText et1,et2,et3;
    Button button;
    String Disease;
    int y1,y2,y3,extra;
    LinearLayout linearLayout;
    GraphView graphView1,graphView2,graphView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_progress);
        Intent i=getIntent();
        Toast.makeText(this, "we are here", Toast.LENGTH_SHORT).show();

        button=findViewById(R.id.btn1);
        textView1=findViewById(R.id.text1);
        textView2=findViewById(R.id.text2);
        textView3=findViewById(R.id.text3);
        textView4=findViewById(R.id.text4);
        textView5=findViewById(R.id.text5);
        textView6=findViewById(R.id.text6);
        linearLayout=findViewById(R.id.linear);
        graphView1=findViewById(R.id.graph1);
        graphView2=findViewById(R.id.graph2);
        graphView3=findViewById(R.id.graph3);
        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        Disease="Depression";
        extra=0;
        if(Disease=="Heart") {
            textView1.setText("Blood Pressure");
            textView2.setText("Heart Rate");
            textView3.setText("Blood Pressure");
            textView4.setText("Heart Rate");
        }
        else if(Disease=="Depression") {
            textView1.setText("Mood (Good-3,Avg-2,Bad-1)" );

            textView2.setText("Anxiety(Good-3,Avg-2,Bad-1) ");
            textView6.setVisibility(View.VISIBLE);
            textView6.setText("Sleep Cycle (Good-3,Avg-2,Bad-1) ");
            et3.setVisibility(View.VISIBLE);

            extra++;
            textView3.setText("Mood (Good-3,Avg-2,Bad-1)" );
            textView4.setText("Anxiety(Good-3,Avg-2,Bad-1) ");
            textView5.setText("Sleep Cycle (Good-3,Avg-2,Bad-1) ");


//            TextView textView=new TextView(this);
//            textView.setText("Sleep Cycle (Good-3,Avg-2,Bad-1) ");
//            linearLayout.addView(textView);
//
//            EditText editText=new EditText(this);
//            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//             editText.setId(0);
//            linearLayout.addView(editText);
//            extra++;
//            Button b=new Button(this);
//            b.setText("Add");
//            linearLayout.addView(b);




        }else if(Disease=="Diabities"){
            textView1.setText("Insulin");
            textView2.setText(" Blood Pressure");

            textView3.setText("Insulin");
            textView4.setText(" Blood Pressure");



        }else{
            textView1.setText("Cough");
            textView2.setText("Breathing Issue");
            textView3.setText("Cough");
            textView4.setText("Breathing Issue");
        }






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String val1=et1.getText().toString();
                String val2=et2.getText().toString();
                try{
                    y1= Integer.parseInt(val1);
                }catch(NumberFormatException ex){ // handle your exception
                    Toast.makeText(showProgress.this, "val1 error", Toast.LENGTH_SHORT).show();
                }
                try{
                    y2=Integer.parseInt(val2);
                }catch(NumberFormatException ex){ // handle your exception
                    Toast.makeText(showProgress.this, "val2 error", Toast.LENGTH_SHORT).show();

                }


                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
               // String val1=et1.getText().toString();
                //String val2=et2.getText().toString();



            //    int y3=6;

                int x=3;
                graphView1.setVisibility(View.VISIBLE);
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                        new DataPoint(0, x),
                        new DataPoint(1, 5),
                        new DataPoint(2, 3),
                        new DataPoint(3, 2),
                        new DataPoint(4, y1)
                });
                graphView1.addSeries(series);

                graphView2.setVisibility(View.VISIBLE);
                LineGraphSeries<DataPoint> serie = new LineGraphSeries<DataPoint>(new DataPoint[] {
                        new DataPoint(0, 50),
                        new DataPoint(1, 70),
                        new DataPoint(2, 60),
                        new DataPoint(3, 80),
                        new DataPoint(4, y2)
                });
                graphView2.addSeries(serie);

        if(extra>0) {
            String val3=et3.getText().toString();
            textView5.setVisibility(View.VISIBLE);
            try{
                y3=Integer.parseInt(val3);
            }catch(NumberFormatException ex){ // handle your exception
                Toast.makeText(showProgress.this, "val3 error", Toast.LENGTH_SHORT).show();

            }

            graphView3.setVisibility(View.VISIBLE);
            LineGraphSeries<DataPoint> seri = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(0, 5),
                    new DataPoint(1, 7),
                    new DataPoint(2, 6),
                    new DataPoint(3, 8),
                    new DataPoint(4, y3)
            });
            graphView3.addSeries(seri);

        }
            }
        });






//        Patients patients = n
      /*    FirebaseDatabase database = FirebaseDatabase.getInstance();
          myRef = database.getReference("patients");
          // demoRef=myRef.child("patients");

        Toast.makeText(this, "before data", Toast.LENGTH_SHORT).show();

        button=findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name =myRef.push().getKey();
                ArrayList<Symptom> s=new ArrayList<>();
                Symptom symp=new Symptom();
                symp.sname="headache";
                symp.values.add(10);
                symp.values.add(20);
                s.add(symp);

                Patient patient=new Patient("xyz",20,"M","Diabeties", s );
                myRef.child("patients").push().setValue(patient);

                Toast.makeText(showProgress.this, "Added", Toast.LENGTH_SHORT).show();

            }
        });

/*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Patients patients=new Patients();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()) {

                     patients = dataSnapshot.getValue(Patients.class);
                }
                Log.d("tag", "Value is: " + patients.patients.get(0).name);
                Toast.makeText(showProgress.this,patients.patients.get(0).name, Toast.LENGTH_SHORT).show();




            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.d("tag", "Failed to read value.", error.toException());
            }
        });


*/

    }
}
