package com.iamshekhargh.restfulapitest1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import retrofit2.converter.gson.GsonConverterFactory;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Retention;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

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
            mHandler.postDelayed(this,5000);
            get("patients/");

        }
    };





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

        mHandler.post(runnable);

    }

    protected String get(String path){
        String dataUrl = "http://handi.herokuapp.com/";
        //String dataUrlParameters = "email="+"pp@gmail.com"+"&name="+"priyabrat";
        URL url;
        HttpsURLConnection connection = null;
        String line = "empty";
        try {
// Create connection
            url = new URL(dataUrl+path);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type","application/json");
            //connection.setRequestProperty("Content-Length","" + 0);
            //connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            //connection.setDoOutput(true);
// Send request
//            DataOutputStream wr = new DataOutputStream(
//                    connection.getOutputStream());
//            //wr.writeBytes(dataUrlParameters);
//            wr.flush();
//            wr.close();
// Get Response

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            String responseStr = response.toString();
            Log.d("Server response",responseStr);
            Log.d("After","Earth");
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        Log.d("line get",line);
        return line;
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



//    Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("http://handi.herokuapp.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build();


//@Retention(RUNTIME)
//@interface Json {
//}



//
//public interface HandiServices {
//    @GET("patients/current") @Json
//    Call<CurrentResponse> decodeRes();
//    //Call<List<Patient>> Patients();   //@Path("user") String user
//
//}