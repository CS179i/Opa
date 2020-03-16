package com.example.opa.results;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.opa.R;
import com.example.opa.friends_list.FriendAdapter;
import com.example.opa.login_register.OrganizerLoginActivity;
import com.example.opa.models.Friend;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserResultsActivity extends AppCompatActivity {

    private List<Friend> matches;
    private ImageView teamColor;
    private TextView hexCode;
    private RecyclerView rvMatches;
    private Context context;
    private FirebaseUser mUser;
    private String tokenID = "";
    private String eventID;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_results);
        eventID = getIntent().getStringExtra("EVENT_ID");

        final FriendAdapter individual = new FriendAdapter(matches);

        context = this;
        teamColor = findViewById(R.id.ivTeamColor);
        hexCode = findViewById(R.id.tvHexColor);
        rvMatches = findViewById(R.id.rvUserResults);
        mUser = mAuth.getCurrentUser();
        mUser.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if(task.isSuccessful()){
                    tokenID = task.getResult().getToken();
                    Log.d("AUTH_SUCCESS", tokenID);

                    String uri = "http://api.opa.social/event/" + eventID + "/matches";
                    RequestQueue queue = Volley.newRequestQueue(context);
                    final JsonObjectRequest jReq = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("RESPONSE", "true");
                            Toast.makeText(context,
                                    "Results found!",
                                    Toast.LENGTH_SHORT).show();
                            try {
                                String hex = "";
                                hex = response.getString("color");
                                Log.d("HEXSTRING", hex);
                                hexCode.setText("Hex #" + hex);
                                teamColor.setColorFilter(Color.parseColor("#" + hex));
                                Log.d("TEST", "this should work?");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                JSONArray jArr = response.getJSONArray("matches");
                                for (int i = 0; i < jArr.length(); i++){
                                    JSONObject match = jArr.getJSONObject(i);
                                    String name = match.getString("name");
                                    // I have no idea how to recycler view.
                                    //Friend f = new Friend(name, "@drawable/app_logo");
                                    //individual.friends.add(f);
                                }
                                rvMatches.setAdapter(individual);
                                rvMatches.setLayoutManager(new LinearLayoutManager(context));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error){
                                    Log.d("RESPONSE", "false");
                                    Toast.makeText(context,
                                            "Could not get result from API.",
                                            Toast.LENGTH_SHORT).show();
                                    Log.d("VOLLEYERROR", error.toString());
                                }
                            }){

                        @Override
                        public Map getHeaders() throws AuthFailureError{
                            String hKey = "Authorization";
                            // change this back to tokenID
                            String hValue = "Bearer " + tokenID;
                            HashMap headers = new HashMap();
                            headers.put(hKey, hValue);
                            return headers;
                        }
                    };
                    queue.start();
                    queue.add(jReq);
                }
                else{
                    Toast.makeText(context,
                            "Could not contact API.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
