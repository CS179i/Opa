package com.example.opa.friends_list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.opa.R;
import com.example.opa.models.Friend;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    public List<Friend> friends;

    public FriendAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView userImage;
        private TextView userName;

        public ViewHolder(View itemView){
            super(itemView);

            userImage = itemView.findViewById(R.id.iv_user_image);
            userName = itemView.findViewById(R.id.tv_username);
        }
    }
}
