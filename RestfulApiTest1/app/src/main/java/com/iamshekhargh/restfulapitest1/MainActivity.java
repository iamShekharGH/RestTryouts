package com.iamshekhargh.restfulapitest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    TextView name,email,mobile,result;
    Button sendTOServer;
    String displayInputData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://handi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

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

    public interface HandiServices {
        @GET("users/{user}/repos -> patients")
        Call<List<Patient>> listRepos();   //@Path("user") String user
    }

    private static class Patient {
        String name;
        String phone;

        public Patient(String nameVal, String phoneVal) {
            name = nameVal;
            phone = phoneVal;
        }
    }

}
