package com.example.ing.myapplication;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    private EditText e;
    private EditText e2;
    private Button b;
    private long id;
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
                    //final List<Forms> Fs = new ArrayList<Forms>();
                    for (int i = 0; i < array.length(); i++) {
                        final List<Questions> Qs = new ArrayList<Questions>();
                        final JSONObject entry = array.getJSONObject(i);
                        final Forms f = new Forms();
                        String name = entry.getString("name");
                        String date = entry.getString("created_at");
                        String cate = entry.getString("name");
                        String comm = entry.getString("name");
                        f.setFormName(name);
                        f.setFormDate(date);
                        f.setFormCategory(cate);
                        f.setFormComment(comm);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                id = formDatabase.daoAccess().insertOnlySingleForm(f);
                                System.out.println(id);
                                try {
                                    JSONArray array2 = entry.getJSONArray("fieldsets");
                                    for (int j = 0; j < array2.length(); j++) {
                                        System.out.println(j);
                                        JSONObject entry2 = array2.getJSONObject(j);
                                        Questions q = new Questions();
                                        String qtext = entry2.getString("description");
                                        String qtype = entry2.getString("name");
                                        q.setFormId((int) (long) id);
                                        q.setQuestionText(qtext);
                                        q.setQuestionType(qtype);
                                        Qs.add(q);
                                    }
                                    if (Qs != null) {
                                        formDatabase.daoAccess().insertMultipleQuestions(Qs);
                                    }
                                }catch (Exception e){

                                }
                            }
                        }) .start();
                    }


                } catch (Exception e) {

                }
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
