package com.sandip.myword;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        final TextView txtEmail=(TextView)findViewById(R.id.txt_email);
        txtEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{txtEmail.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Reg:MyWord App");
                startActivity(Intent.createChooser(intent, "Select Email Sending App :"));
                finish();
            }
        });
    }
}
