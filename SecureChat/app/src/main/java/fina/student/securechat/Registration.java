package fina.student.securechat;

import android.net.Uri;
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
import com.google.android.material.snackbar.Snackbar;

public class Registration extends AppCompatActivity {
Button reg;
EditText name,email,phone,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        reg=findViewById(R.id.reg);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.password);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()||
                    name.getText().toString().isEmpty()||
                    phone.getText().toString().isEmpty()||
                    email.getText().toString().isEmpty()||
                    pass.getText().toString().isEmpty()
                )
                {
                    Snackbar.make(view,"All fields are mandatory !!!!",Snackbar.LENGTH_LONG).show();
                    return;
                }

                utils.getInstance().init();
                Toast.makeText(Registration.this, "init", Toast.LENGTH_SHORT).show();


                StringRequest stringRequest = new StringRequest(Request.Method.GET, utils.IP+utils.TYPE1+"&name="+name.getText().toString()
                        +"&phone="+phone.getText().toString()+"&email="+email.getText().toString()+"&password="+pass.getText().toString()+
                        "&public_key="+ Uri.encode(utils.getInstance().publicKey),
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                             if(response.equals("0"))
                             {


                                 utils.getInstance().savePrivateKey(utils.getInstance().privateKey,getApplicationContext());


                                 Toast.makeText(Registration.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                                 finish();

                             }else {
                                 Toast.makeText(Registration.this, "Bad inputs", Toast.LENGTH_SHORT).show();
                             }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registration.this, "error", Toast.LENGTH_SHORT).show();
                      //  textView.setText("That didn't work!");
                    }
                });
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
// Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });
    }
}
