package fina.student.securechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class friendActivity extends AppCompatActivity {
RecyclerView rv;
    DatabaseReference  ref2;
    FriendAdapter friendAdapter;
    ArrayList<Friend> f;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        this.setTitle("Chat List");
        utils.getInstance().privateKey = utils.getInstance().getPrivateKey(getApplicationContext());

        bottomNavigationView = findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.chat:
                        startActivity(
                            new Intent(getApplicationContext(), friendActivity.class)
                        );
                        finish();
                        break;
                    case R.id.add_list:
                        startActivity(
                            new Intent(getApplicationContext(), AllFriendList.class)
                        );
                        finish();
                }
                return true;
            }
        });
        rv = findViewById(R.id.rv);
       f=new ArrayList<>();
        friendAdapter=new FriendAdapter(1,f);
init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    void init(){

        ref2 = FirebaseDatabase.getInstance().getReference("chats");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                utils.getInstance().chatALL.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chat chat = snapshot.getValue(fina.student.securechat.chat.class);


                    if (chat.getReceiver().equals(utils.getInstance().CUSER)) {


                        //ref2.updateChildren()
                        //      return null;
                        utils.getInstance().chatALL.add(chat);

                    }
                }
                friendAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        StringRequest stringRequest = new StringRequest(Request.Method.GET, utils.IP+utils.TYPE5+"&email="+utils.getInstance().CUSER,
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    //    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                  f.clear();
                    try {
                        JSONArray jsonArray=new JSONArray(response);

                        for(int i=0;i<jsonArray.length();i++)
                        {
                            JSONObject object=jsonArray.getJSONObject(i);
                            Friend friend=new Friend(object.getString("name"),"",object.getString("email"),object.getString("phone"),object.getString("public_key"));
                            f.add(friend);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    rv.setLayoutManager(linearLayoutManager);


                    rv.setAdapter(friendAdapter);
                    friendAdapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                //  textView.setText("That didn't work!");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.getCache().clear();
// Add the request to the RequestQueue.
        queue.add(stringRequest);








    }
}
