package fina.student.securechat;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.viewholder> {
    public FriendAdapter(int which,ArrayList<Friend>friends1) {
        this.which = which;
        friends=friends1;
    }

    int which;
    ArrayList<Friend>friends;
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        switch (which){
            case 1:view= LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list,parent,false);
            break;
            case 2:view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_friend,parent,false);
        }


        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
       int count =1;
        holder.name.setText(friends.get(position).name);
        holder.f.setText(friends.get(position).name.toUpperCase().charAt(0)+"");
        if(which==1)
        {
          //  utils.getInstance().chatme
            for (chat chat : utils.getInstance().chatALL)
            {
              if(chat.getSender().contentEquals( friends.get(position).getEmail()))
              {
                 if(! chat.isIsread())
                 {
                     holder.newcard.setVisibility(View.VISIBLE);
                     holder.count.setText(""+count++);

                 }
              }
                //chat.getReceiver()
                if(count==1)
                    holder.newcard.setVisibility(View.INVISIBLE);
            }
        }


    }

    @Override
    public int getItemCount() {
  return friends.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView name,f,count;
        Button add;
        CardView newcard;

        public viewholder(@NonNull final View itemView) {
            super(itemView);
            switch (which)
            {

                case 1:
                    name=itemView.findViewById(R.id.name);
                    count=itemView.findViewById(R.id.new1);
                    f=itemView.findViewById(R.id.f);
                    newcard=itemView.findViewById(R.id.newcard);
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            utils.getInstance().FUSER=friends.get(getPosition()).email;
                            utils.getInstance().publicKey=friends.get(getPosition()).getPublic_key();
                            Intent i =new Intent(itemView.getContext(),MainActivity.class);
                            newcard.setVisibility(View.INVISIBLE);
                            itemView.getContext().startActivity(i);
                        }
                    });

                    break;
                case 2:
                    name=itemView.findViewById(R.id.name);
                    f=itemView.findViewById(R.id.f);
                    add=itemView.findViewById(R.id.add);
                    add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, utils.IP+utils.TYPE4+"&cuser="+utils.getInstance().CUSER+
                                    "&fuser="+friends.get(getPosition()).email,
                                    new Response.Listener<String>() {

                                        @Override
                                        public void onResponse(String response) {
                                            if(response.equals("0"))
                                            {


                                                utils.getInstance().savePrivateKey(utils.getInstance().privateKey,itemView.getContext());


                                                Toast.makeText(itemView.getContext(), "friend added", Toast.LENGTH_SHORT).show();



                                            }else {
                                                Toast.makeText(itemView.getContext(), response, Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(itemView.getContext(), "error", Toast.LENGTH_SHORT).show();
                                    //  textView.setText("That didn't work!");
                                }
                            });
                            RequestQueue queue = Volley.newRequestQueue(itemView.getContext());
// Add the request to the RequestQueue.
                            queue.add(stringRequest);


                        }
                    });

            }

        }
    }
}
