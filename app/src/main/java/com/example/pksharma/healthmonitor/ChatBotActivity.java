package com.example.pksharma.healthmonitor;

//import android.support.v7.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class ChatBotActivity extends AppCompatActivity{

    RecyclerView messagesRecyclerView;
    Button sendButton;
    EditText typingEditText;
    MessagesAdapter adapter;
    Retrofit retrofit;
    ChatbotService service;

    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> botMessages = new ArrayList<>();
    ArrayList<String> userMessages = new ArrayList<>();
    ArrayList<Mentions> mentions = new ArrayList<>();
    ArrayList<String> conditionsMentionedId = new ArrayList<>();
    ArrayList<Items> items = new ArrayList<>();
    ArrayList<Conditions> conditions = new ArrayList<>();
    ArrayList<Float> diseaseProbabilities = new ArrayList<>();

    Diagnosis diagnosis = new Diagnosis();
    Questions questions = new Questions();
    Parse parsedData = new Parse();

    String myMessage, commonNameDisease, nameDisease;
    Boolean symptomEnterring = true, diagnos = false;
    boolean questionsAsking = false;
    int questionCounter = 5;
    float probabilityDisease = (float)0.0;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatbot_layout);

        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        sendButton = findViewById(R.id.sendButton);
        typingEditText = findViewById(R.id.typingEditText);
        sendButton = findViewById(R.id.sendButton);
        adapter = new MessagesAdapter(messages,this);
        messagesRecyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        messagesRecyclerView.setLayoutManager(manager);

        retrofit = ApiClient.getRetrofit();
        service = ApiClient.getService();

        messages.add("Hi !!!");
        botMessages.add("Hi !!!");
        messagesRecyclerView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        adapter.notifyDataSetChanged();

        messages.add("Please enter your symptoms");
        botMessages.add("Please enter your symptoms");
        messagesRecyclerView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        adapter.notifyDataSetChanged();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myMessage = typingEditText.getText().toString();
                Log.i("msg","r"+ myMessage);
                messages.add(myMessage);
                userMessages.add(myMessage);
                adapter.notifyDataSetChanged();

                if(myMessage.equals("No") || myMessage.equals("no") && symptomEnterring){
                    Log.i("msg","1");
                    symptomEnterring = false;
                    questionsAsking = true;
                    myMessage = "";
                    messages.add("Ok!");
                    botMessages.add("Ok!");
                    adapter.notifyDataSetChanged();



                    Diagnos(conditionsMentionedId);

                }else if(symptomEnterring){
                    Log.i("msg","2");
                    String parseJson = "{ \"text\" : \"" + myMessage + "\"}";
                    final RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), parseJson);
                    final Call<Parse> parseCall = service.postMessageForReply(body);
                    parseCall.enqueue(new Callback<Parse>() {
                        @Override
                        public void onResponse(Call<Parse> call, Response<Parse> response) {
                            if(response.body()!=null){
                                parsedData = response.body();
                                messages.add(parsedData.mentions.get(0).commonName + " noted. Anything else ?");
                                Log.i("Parse",parsedData.mentions.get(0).commonName);
                                botMessages.add(parsedData.mentions.get(0).commonName + " noted. Anything else ?");
                                adapter.notifyDataSetChanged();

                                mentions.addAll(parsedData.mentions);
                                for(int i=0;i<mentions.size();i++){
                                    conditionsMentionedId.add(mentions.get(i).id);
                                }

                            }else{
                                messages.add("Smjh ni aya");
                                botMessages.add("Smjh ni aya");
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<Parse> call, Throwable t) {
                            Toast.makeText(ChatBotActivity.this, "Parse Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Log.i("msg","3"+items.size());

                    for(int i=0;i<items.size();i++){
                        if(myMessage.equalsIgnoreCase(items.get(i).name)){
                            conditionsMentionedId.add(items.get(i).id);
                            Mentions mention = new Mentions();
                            mention.id = items.get(i).id;
                            mention.choiceId = "present";
                            mentions.add(mention);
                            Log.i("New",conditionsMentionedId.get(conditionsMentionedId.size()-1));
                        }
                    }
                    for(int i=0;i<conditionsMentionedId.size();i++) {
                        Log.i("CMID", i+" "+conditionsMentionedId.get(i));
                    }
                    Diagnos(conditionsMentionedId);
                }
            }
        });
    }

    public void Diagnos(ArrayList<String> conditionsMentionedId){

        if(questionCounter>0){

            StringBuilder builder = new StringBuilder();
            for(int i=0;i<mentions.size();i++){
                Log.i("IDS", mentions.get(i).id + " " + mentions.get(i).commonName);
                builder.append(mentions.get(i).id + " ");
            }
            messages.add(builder.toString());
            adapter.notifyDataSetChanged();

            StringBuilder diagnosisJson = new StringBuilder();
            diagnosisJson.append("{  \"sex\": \"male\",  \"age\": 30,  \"evidence\": [");
            for(int i=0;i<mentions.size();i++){
                diagnosisJson.append("{ \"id\" : \""+mentions.get(i).id+"\", \"choice_id\" : \"present\" } ");
                if(i!=mentions.size()-1){
                    diagnosisJson.append(",");
                }
            }
            diagnosisJson.append("]}");
            Log.i("JSON", diagnosisJson.toString());

            final RequestBody diagnosisBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),diagnosisJson.toString());
            Call<Diagnosis> diagnosisCall = service.postDiagnosis(diagnosisBody);
            diagnosisCall.enqueue(new Callback<Diagnosis>() {
                @Override
                public void onResponse(Call<Diagnosis> call, Response<Diagnosis> response) {
                    if(response.body()!=null){
                        diagnosis = response.body();
                        messages.add(diagnosis.question.text);
                        questionCounter-=1;
                        conditions.addAll(diagnosis.conditions);
                        botMessages.add(diagnosis.question.text);
                        items.clear();
                        items.addAll(diagnosis.question.items);
                        StringBuilder options = new StringBuilder();
                        for(int i=0;i<items.size();i++){
                            options.append((i+1) + items.get(i).name);
                        }
                        messages.add(options.toString());
                        botMessages.add(options.toString());
                        adapter.notifyDataSetChanged();
                    }
                }
                @Override
                public void onFailure(Call<Diagnosis> call, Throwable t) {

                }
            });

        }else{
            for(int i=0;i<conditions.size();i++){
                if(conditions.get(i).probability>probabilityDisease){
                    probabilityDisease = conditions.get(i).probability;
                    commonNameDisease = conditions.get(i).commonName;
                    nameDisease = conditions.get(i).name;
                }
            }
            messages.add(commonNameDisease + " " + nameDisease + " " + probabilityDisease);
            botMessages.add(commonNameDisease + " " + nameDisease + " " + probabilityDisease);
        }


    }

}
