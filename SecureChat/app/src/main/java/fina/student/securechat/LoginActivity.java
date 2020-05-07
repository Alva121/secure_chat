package fina.student.securechat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
Button login;
Button reg;
EditText user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=findViewById(R.id.login);
        user=findViewById(R.id.user);
        pass=findViewById(R.id.pass);
        utils.getInstance().getPrivateKey(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.GET, utils.IP+utils.TYPE2+"&email="+user.getText().toString()
                        +"&password="+pass.getText().toString(),
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                                if(!response.equals("-1"))
                                {

                                    try {
                                        JSONArray jsonArray=new JSONArray(response);
 utils.getInstance().friends.clear();
                                        for(int i=0;i<jsonArray.length();i++)
                                        {
                                            JSONObject object=jsonArray.getJSONObject(i);
                                            Friend friend=new Friend(object.getString("name"),"",object.getString("email"),object.getString("phone"),object.getString("public_key"));
                                            utils.getInstance().friends.add(friend);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                   utils.getInstance().CUSER=user.getText().toString();
                                    Intent intent=new Intent(view.getContext(),friendActivity.class);
                                    startActivity(intent);


                                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();




                                    finish();

                                }else {
                                    Toast.makeText(getApplicationContext(), "Login unsuccess", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //  textView.setText("That didn't work!");
                    }
                });
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
// Add the request to the RequestQueue.
                queue.add(stringRequest);

            }
        });
        reg=findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),Registration.class);
                startActivity(intent);
            }
        });
    }
}
