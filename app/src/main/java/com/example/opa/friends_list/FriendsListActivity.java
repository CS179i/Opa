package com.example.opa.friends_list;

import android.os.Bundle;

import com.example.opa.R;
import com.example.opa.models.Friend;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class FriendsListActivity extends AppCompatActivity {

    List<Friend> friends;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        RecyclerView rvFriends = findViewById(R.id.rv_questions);
        FriendAdapter adapter = new FriendAdapter(friends);
        // Todo add items to the recycler view
    }
}
