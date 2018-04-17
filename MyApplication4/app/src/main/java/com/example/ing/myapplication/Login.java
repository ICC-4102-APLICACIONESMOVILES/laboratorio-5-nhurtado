package com.example.ing.myapplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private EditText e;
    private EditText e2;
    private Button b;
    private NetworkManager networkManager;
    private static final String DATABASE_NAME = "forms_db";
    private FormDatabase formDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e= findViewById(R.id.editText);
        e2= findViewById(R.id.editText2);
        b= findViewById(R.id.button);

        formDatabase = Room.databaseBuilder(getApplicationContext(),
                FormDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();

        networkManager = NetworkManager.getInstance(this);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String u = e.getText().toString();
                    String p = e2.getText().toString();
                    networkManager.login("ignacio@magnet.cl", "usuarioprueba", new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            getForms();
                            JSONObject headers = response.optJSONObject("headers");
                            String token = headers.optString("Authorization", "");
                            Intent intent=new Intent();
                            intent.putExtra("T",token);
                            setResult(1,intent);
                            finish();
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error con usuario o contraseña",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getForms(){
        networkManager.getForms(new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                            JSONArray array = response.getJSONArray("0");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject entry = array.getJSONObject(i);
                                Forms f = new Forms(entry);
                                formDatabase.daoAccess().insertOnlySingleForm(f);
                            }
                } catch (Exception e) {

                }
                System.out.println(response);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error
                System.out.println(error);
            }
        });
    }
}
