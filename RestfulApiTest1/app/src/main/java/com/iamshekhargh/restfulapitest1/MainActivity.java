package com.iamshekhargh.restfulapitest1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    int counter =0;
    private final Handler mHandler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            counter+=5;
            Log.d("counter  ::",""+counter);
            //mHandler.removeCallbacks(runnable);
            mHandler.postDelayed(runnable,5000);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://handi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HandiServices service = retrofit.create(HandiServices.class);

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
        @GET("patients/current")
        Call<CurrentResponse> decodeRes();
        //Call<List<Patient>> Patients();   //@Path("user") String user

    }


    public static class Patient {
        String name;
        String phone;

//        public Patient(String nameVal, String phoneVal) {
//            name = nameVal;
//            phone = phoneVal;
//        }
    }
    public static class CurrentResponse {
        Patient patient;
        String action;
        Integer token;
    }

}
