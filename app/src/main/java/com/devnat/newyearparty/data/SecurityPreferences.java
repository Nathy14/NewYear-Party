package com.devnat.newyearparty.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {
    /*storage of the data of the application
    save the data if the application is closed
    recommended to small and simple data*/
    private SharedPreferences mSharedPreferences;

    //Constructor
    public SecurityPreferences(Context mContext){
        this.mSharedPreferences = mContext.getSharedPreferences("NewYearsParty", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value){
        this.mSharedPreferences.edit().putString(key,value).apply();
    }

    public String getStoredString(String key){
        return this.mSharedPreferences.getString(key, "");
    }
}
