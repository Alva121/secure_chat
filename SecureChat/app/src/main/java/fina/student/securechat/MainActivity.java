package fina.student.securechat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.GeneralSecurityException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    EditText msg;
    Button send;
     String CUSER = utils.getInstance().CUSER;
     String FUSER = utils.getInstance().FUSER;
    ArrayList<chat> mChat = new ArrayList<>();
    private DatabaseReference mDatabase, ref2;



    void sendMSG(String msg) {
        if(msg.isEmpty())
            return;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        chat chat = new chat();
        chat.setSender(CUSER);
        chat.setReceiver(FUSER);
        chat.setMsg(msg);
        String key=utils.getInstance().generateKey();
        //String key="hai";
        chat.encrypt(key);
        String enckey=utils.getInstance().encKey(key);
        chat.setKey(enckey);
       // Log.d("enckey",enckey);
        mDatabase.child("chats").push().setValue(chat);
        this.msg.setText("");
        rv.scrollToPosition(mChat.size() - 1);
        chat chat1 = new chat();
        chat1.setSender(CUSER);
        chat1.setReceiver(FUSER);
        chat1.setMsg(msg);
        chat1.setKey(enckey);
        utils.getInstance().chatme.add(chat1);

            utils.getInstance().writechat(getApplicationContext());



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // Toast.makeText(this, CUSER+FUSER, Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            try {
                new testAlg();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }

        rv = findViewById(R.id.rv);
        msg = findViewById(R.id.msg);
        send = findViewById(R.id.send);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

//        utils.getInstance().init();

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(linearLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMSG(msg.getText().toString());
            }
        });


        ref2 = FirebaseDatabase.getInstance().getReference("chats");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mChat.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    chat chat = snapshot.getValue(fina.student.securechat.chat.class);

                    if (chat.getReceiver().equals(FUSER) && chat.getSender().equals(CUSER) ||
                            chat.getReceiver().equals(CUSER) && chat.getSender().equals(FUSER)

                    ) {

                        //      return null;
                        mChat.add(chat);
                        RecyclerAdpter recyclerAdpter = new RecyclerAdpter(mChat);
                        rv.setAdapter(recyclerAdpter);
                        recyclerAdpter.notifyDataSetChanged();
                        rv.scrollToPosition(mChat.size() - 1);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
