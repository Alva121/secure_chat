package fina.student.securechat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ListIterator;


public class RecyclerAdpter extends RecyclerView.Adapter {
    final static int LEFT=0;
    final static int RIGHT=1;
    ListIterator iterator =utils.getInstance().chatme.listIterator();
    ArrayList<chat>mChat;
    int i=0;
    public RecyclerAdpter(ArrayList<chat> mChat) {
        this.mChat = mChat;i=0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==LEFT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left, parent, false);
            return new Left(view);
        }
        view =LayoutInflater.from(parent.getContext()).inflate(R.layout.right,parent,false);
        return new Right(view);



    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       int type= getItemViewType(position);

        String key ="";
        if(type==LEFT) {

          key =utils.getInstance().decKey( mChat.get(position).getKey());
            Log.d("enckey", key);
            mChat.get(position).decrypt(key);

            ((Left) holder).msg.setText(mChat.get(position).getMsg());

        }
        else if(type==RIGHT) {
             utils.getInstance().readchat(holder.itemView.getContext());
             for(chat chat1:utils.getInstance().chatme)
             {
                if(mChat.get(position).getKey().compareTo(chat1.getKey())==0)
                 {
                     ((Right) holder).msg.setText(chat1.getMsg());
                 }
             }



        }

    }



    @Override
    public int getItemViewType(int position) {

        if(mChat.get(position).getSender().equals(utils.getInstance().CUSER))
        {
            return RIGHT;


        }else
            return LEFT;
    }

    @Override
    public int getItemCount() {
        return mChat.size();

    }

    public class Left extends RecyclerView.ViewHolder {
TextView msg;
        public Left(@NonNull View itemView) {
            super(itemView);
msg=itemView.findViewById(R.id.msg);

        }
    }

    public class Right extends RecyclerView.ViewHolder {
        TextView msg;
        public Right(@NonNull View itemView) {
            super(itemView);
            msg=itemView.findViewById(R.id.msg);

        }
    }
}
