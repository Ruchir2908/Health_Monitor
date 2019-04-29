package com.example.pksharma.healthmonitor;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Diagnosis {

    Questions question;
    ArrayList<Conditions> conditions = new ArrayList<>();
    Extras extras;
    @SerializedName("should_stop")
    String shouldStop;

}
