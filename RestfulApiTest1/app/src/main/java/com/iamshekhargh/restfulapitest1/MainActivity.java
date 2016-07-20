package com.iamshekhargh.restfulapitest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView name,email,mobile,result;
    Button sendTOServer;
    String displayInputData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextView)findViewById(R.id.editText_Name);
        email = (TextView)findViewById(R.id.editText_Email);
        mobile = (TextView)findViewById(R.id.editText_MobNo);
        result = (TextView)findViewById(R.id.showResult);
        sendTOServer = (Button)findViewById(R.id.button_send);

        sendTOServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputData =
                        "Name ::"+ name.getText().toString() +"\n"
                        +"Email ::"+ email.getText().toString() +"\n"
                        +"Mobile ::"+ mobile.getText().toString();
                result.setText(displayInputData);

            }
        });



    }
}
