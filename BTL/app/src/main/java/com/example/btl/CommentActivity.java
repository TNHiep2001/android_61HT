package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        CommentApiCaller apiCaller = new CommentApiCaller();
        apiCaller.callApi();
    }

    private class CommentApiCaller {

        private final String API_URL = "http://localhost:3000/comment/1";

        public void callApi() {
            try {
                URL url = new URL(API_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Replace the JSON string with your actual request body
                String requestBody = "{ \"comment\": \"This is my comment.\" }";

                // Write the request body to the connection's output stream
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(requestBody);
                outputStream.flush();
                outputStream.close();

                int responseCode = connection.getResponseCode();
                Log.d("API Response Code", String.valueOf(responseCode));

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                Log.d("API Response Body", response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
