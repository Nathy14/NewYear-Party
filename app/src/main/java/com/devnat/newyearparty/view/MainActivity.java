package com.devnat.newyearparty.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devnat.newyearparty.R;
import com.devnat.newyearparty.constant.LastYearsConstant;
import com.devnat.newyearparty.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.text_today = findViewById(R.id.text_today);
        this.mViewHolder.text_label_days_left = findViewById(R.id.text_label_days_left);
        this.mViewHolder.button_confirm = findViewById(R.id.button_confirm);

        this.mViewHolder.button_confirm.setOnClickListener(this);
        //Date
        this.mViewHolder.text_today.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.days));
        this.mViewHolder.text_label_days_left.setText(daysLeft);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }


    public void onClick(View view) {
        if (view.getId() == R.id.button_confirm) {
            //change the activity
            String presence = this.mSecurityPreferences.getStoredString(LastYearsConstant.PRESENCE_KEY);

            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(LastYearsConstant.PRESENCE_KEY, presence);
            //start the intent =  open the Details Activity
            startActivity(intent);
        }
    }

    private void verifyPresence() {
        //no confirmed presence, confirmed, no presence
        String presence = this.mSecurityPreferences.getStoredString(LastYearsConstant.PRESENCE_KEY);
        if (presence.equals("")) {
            this.mViewHolder.button_confirm.setText(getString(R.string.no_confirmed));
        } else if (presence.equals(LastYearsConstant.CONFIRMATION_YES)) {
            this.mViewHolder.button_confirm.setText(getString(R.string.yes));
        } else {
            this.mViewHolder.button_confirm.setText(getString(R.string.no));
        }

    }


    private int getDaysLeft() {
        //today's Date
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);
        //Last dat of the year
        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);
        return dayMax - today;
    }

    private static class ViewHolder {
        TextView text_today;
        TextView text_label_days_left;
        Button button_confirm;
    }
}
