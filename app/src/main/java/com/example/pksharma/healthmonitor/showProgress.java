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
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView;
    EditText et1,et2,et3;
    Button button;
    String Disease="";
    int y1,y2,y3,extra,val;
    LinearLayout linearLayout;
    GraphView graphView1,graphView2,graphView3;
    Disease d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_progress);
        Intent i=getIntent();
        Bundle bundle=i.getExtras();
        Disease=bundle.getString("Disease");
        val=bundle.getInt("val");
        Log.d("val","value:"+val);

        Toast.makeText(this, "we are here "+val, Toast.LENGTH_SHORT).show();


        textView=findViewById(R.id.text);


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



//        try {
//
//
//           Disease=d.getDis();
//        }catch (NullPointerException exp){
//            Toast.makeText(this, "oops2!", Toast.LENGTH_SHORT).show();
//        }


       // Disease=dum;
        textView.setText("Disease="+Disease);

        extra=0;

       if(val == 1){
            Toast.makeText(this, "oh beteeee", Toast.LENGTH_LONG).show();
            textView1.setText("Blood Pressure");
            textView2.setText("Heart Rate");
            textView3.setText("Blood Pressure");
            textView4.setText("Heart Rate");
        }
        else if(val==2) {
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




        }else if(val==3){
            textView1.setText("Insulin");
            textView2.setText(" Blood Pressure");

            textView3.setText("Insulin");
            textView4.setText(" Blood Pressure");



        }else{
       //     Toast.makeText(this, "well"+Disease, Toast.LENGTH_SHORT).show();
            textView1.setText("Cough(Severe-3,Mild-2,Moderate-1)");
            textView2.setText("Breathing Issue(Severe-3,Mild-2,Moderate-1)");
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
                int g11=0,g12=0,g13=0,g14=0;
                int g21=0,g22=0,g23=0,g24=0;
                if(val==1)
                {
                    g11=120;
                    g12=122;
                    g13=130;
                    g14=139;
                    g21=60;
                    g22=70;
                    g23=100;
                    g24=80;

                    if(y1>140)
                        Toast.makeText(showProgress.this, "ALERT! High Blood Pressure", Toast.LENGTH_SHORT).show();
                    if(y1<90)
                        Toast.makeText(showProgress.this, "ALERT! Low Blood Pressure", Toast.LENGTH_SHORT).show();


                    if(y2>120)
                        Toast.makeText(showProgress.this, "ALERT! High Heart Rate", Toast.LENGTH_SHORT).show();
                    if(y2<60)
                        Toast.makeText(showProgress.this, "ALERT! Low Heart Rate", Toast.LENGTH_SHORT).show();

                }else if(val==2)
                {
                    g11=1;
                    g12=1;
                    g13=2;
                    g14=3;
                    g21=2;
                    g22=2;
                    g23=1;
                    g24=2;
                    
                }else if(val==3){
                    g21=120;
                    g22=122;
                    g23=130;
                    g24=139;
                    g11=60;
                    g12=70;
                    g13=100;
                    g14=80;

                    if(y2>140)
                        Toast.makeText(showProgress.this, "ALERT! High Blood Pressure", Toast.LENGTH_SHORT).show();
                    if(y2<90)
                        Toast.makeText(showProgress.this, "ALERT! Low Blood Pressure", Toast.LENGTH_SHORT).show();


                    if(y1>100)
                        Toast.makeText(showProgress.this, "ALERT! High Insulin Level", Toast.LENGTH_SHORT).show();
                    if(y1<70)
                        Toast.makeText(showProgress.this, "ALERT! Low Insulin Level", Toast.LENGTH_SHORT).show();


                }else{
                    g11=1;
                    g12=1;
                    g13=2;
                    g14=3;
                    g21=2;
                    g22=2;
                    g23=1;
                    g24=2;

                }


                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
               // String val1=et1.getText().toString();
                //String val2=et2.getText().toString();



            //    int y3=6;


                graphView1.setVisibility(View.VISIBLE);
                LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                        new DataPoint(0, g11),
                        new DataPoint(1, g12),
                        new DataPoint(2, g13),
                        new DataPoint(3, g14),
                        new DataPoint(4, y1)
                });
                graphView1.addSeries(series);

                graphView2.setVisibility(View.VISIBLE);
                LineGraphSeries<DataPoint> serie = new LineGraphSeries<DataPoint>(new DataPoint[] {
                        new DataPoint(0, g21),
                        new DataPoint(1, g22),
                        new DataPoint(2, g23),
                        new DataPoint(3, g24),
                        new DataPoint(4, y2)
                });
                graphView2.addSeries(serie);

        if(extra>0) {
            String val3=et3.getText().toString();
            textView5.setVisibility(View.VISIBLE);
            int g31=0,g32=0,g33=0,g34=0;
            g31=2;
            g32=3;
            g33=1;
            g34=1;

            try{
                y3=Integer.parseInt(val3);
            }catch(NumberFormatException ex){ // handle your exception
                Toast.makeText(showProgress.this, "val3 error", Toast.LENGTH_SHORT).show();

            }

            graphView3.setVisibility(View.VISIBLE);
            LineGraphSeries<DataPoint> seri = new LineGraphSeries<DataPoint>(new DataPoint[]{
                    new DataPoint(0, g31),
                    new DataPoint(1, g32),
                    new DataPoint(2, g33),
                    new DataPoint(3, g34),
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
