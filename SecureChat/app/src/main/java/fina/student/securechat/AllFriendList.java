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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllFriendList extends AppCompatActivity {
    RecyclerView rv;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_friend_list);
        this.setTitle("Friend List");
        rv=findViewById(R.id.rv);
        bottomNavigationView=findViewById(R.id.nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.chat:
                        startActivity(
                                new Intent(getApplicationContext(),friendActivity.class)
                        );
                        finish();
                        break;
                    case  R.id.add_list:
                        startActivity(
                                new Intent(getApplicationContext(),AllFriendList.class)
                        );
                        finish();
                }
                return true;
            }
        });
       // Toast.makeText(this, "AAA", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, utils.IP+utils.TYPE3+"&user="+utils.getInstance().CUSER,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                     // Toast.makeText(AllFriendList.this, response, Toast.LENGTH_SHORT).show();
                        ArrayList<Friend>f=new ArrayList<>();
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

                        FriendAdapter friendAdapter=new FriendAdapter(2,f);
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
