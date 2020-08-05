package com.devnat.newyearparty.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.devnat.newyearparty.R;
import com.devnat.newyearparty.constant.LastYearsConstant;
import com.devnat.newyearparty.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkbox_participate = findViewById(R.id.checkbox_participate);
        this.mViewHolder.checkbox_participate.setOnClickListener(this);

        this.loadDataFromActivity();

    }

    public void onClick(View view) {
        if (view.getId() == R.id.checkbox_participate) {

            if (this.mViewHolder.checkbox_participate.isChecked()) {
                //Participate
                this.mSecurityPreferences.storeString(LastYearsConstant.PRESENCE_KEY, LastYearsConstant.CONFIRMATION_YES);

            } else {
                //Not participate
                this.mSecurityPreferences.storeString(LastYearsConstant.PRESENCE_KEY, LastYearsConstant.CONFIRMATION_NO);
            }
        }
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(LastYearsConstant.PRESENCE_KEY);
            if (presence != null && presence.equals(LastYearsConstant.CONFIRMATION_YES)) {
                this.mViewHolder.checkbox_participate.setChecked(true);
            } else {
                this.mViewHolder.checkbox_participate.setChecked(false);
            }
        }
    }


    private static class ViewHolder {
        CheckBox checkbox_participate;
    }
}
