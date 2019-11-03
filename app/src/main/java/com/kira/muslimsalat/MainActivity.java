package com.kira.muslimsalat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    //you can get api key from   https://muslimsalat.com  after creating account
    private String apiKey="";
    final String URL = "https://muslimsalat.com/qena.json?key=" + apiKey + "";

    String tag_json_req = "json_obj_req";

    TextView mFajrTv, mDhuhrTv, mAsrTv, mMaghribTv, mIshaTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intializeField();

    }

    private void getSalatTimes() {
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String mFajr = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String mDhuhr = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String mAsr = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String mMaghrib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String mIsha = response.getJSONArray("items").getJSONObject(0).get("isha").toString();
                            mFajrTv.setText(mFajr);
                            mDhuhrTv.setText(mDhuhr);
                            mAsrTv.setText(mAsr);
                            mMaghribTv.setText(mMaghrib);
                            mIshaTv.setText(mIsha);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }


                });

        AppController.getInstance().addToRequestQueue(jsonObjRequest, tag_json_req);


    }

    private void intializeField() {
        mFajrTv = findViewById(R.id.tv_fajr);
        mDhuhrTv = findViewById(R.id.tv_dhuhr);
        mAsrTv = findViewById(R.id.tv_asr);
        mMaghribTv = findViewById(R.id.tv_maghrib);
        mIshaTv = findViewById(R.id.tv_isha);

    }

}
