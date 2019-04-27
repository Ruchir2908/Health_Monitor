package com.example.pksharma.healthmonitor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button enterBtn,botBtn;
    LinearLayout linearLayout;
    String Disease="";
    Disease d=new Disease();
    int val=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linearLayout=findViewById(R.id.main_linear);
        botBtn=findViewById(R.id.botBtn);
        enterBtn=findViewById(R.id.enterBtn);


       final Button b=new Button(MainActivity.this);
        final EditText editText=new EditText(MainActivity.this);
        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // namebar = view.findViewById(R.id.namebar);

                if (linearLayout != null) {
                    linearLayout.removeView(enterBtn);
                }


                LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                editText.setId(0);
                linearLayout.addView(editText);

                b.setText("Enter");
                b.setBackgroundColor(Color.parseColor("#3aa8c1"));
               // b.setId(1);
                linearLayout.addView(b);



            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Disease= editText.getText().toString();

                String check="Heart";
                if(Disease.trim().equals(check.trim()))
                {
                    val=1;
                    //    Toast.makeText(this, "value", Toast.LENGTH_SHORT).show();
                }else if(Disease.trim().equals("Depression"))
                    val=2;
                else if(Disease.trim().equals("Diabities"))
                    val=3;
                else if(Disease.trim().equals("Asthama"))
                    val=4;
                else
                    val=0;

                Toast.makeText(MainActivity.this, "value: "+val, Toast.LENGTH_SHORT).show();
               // Toast.makeText(MainActivity.this, "Disease:"+Disease, Toast.LENGTH_SHORT).show();
//                try {
//                    d.Dis="Heart";
//                }catch (NullPointerException ex){
//                    Toast.makeText(MainActivity.this, "dhinchuk", Toast.LENGTH_SHORT).show();
//                }
//
//                try {
//                    Toast.makeText(getApplicationContext(), "Disease: "+d.Dis, Toast.LENGTH_SHORT).show();
//                }catch (NullPointerException ex){
//                    Toast.makeText(MainActivity.this, "Ooops", Toast.LENGTH_SHORT).show();
//                }
//
             }
        });




        Log.d("MainActivity","value:"+val);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent intent=new Intent(MainActivity.this,showProgress.class);
                Bundle bundle=new Bundle();
                bundle.putString("Disease",Disease);
                bundle.putInt("val",val);
                intent.putExtras(bundle);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "yayyy", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
