package com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.R;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.model.User;
import com.snipex.shantu.androidarchitecturecomponentsmvvmretrofitwithjava.view.UserDetails;


import java.util.ArrayList;

public class userAdapter extends RecyclerView.Adapter<userAdapter.ViewHolder> {

    private Context context;
    ArrayList<User> userArrayList;

    public userAdapter(Context context, ArrayList<User> UserArrayList) {
        this.context = context;
        this.userArrayList = UserArrayList;
    }

    @NonNull
    @Override
    public userAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_row_movie_article,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userAdapter.ViewHolder viewHolder, int i) {
        User user=userArrayList.get(i);
        viewHolder.name.setText(user.getName());
        viewHolder.email.setText(user.getEmail());

        Glide.with(context)
                .load(user.getUrlToImage())
                .into(viewHolder.imgViewCover);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView imgViewCover;
        private final TextView name;
        private final TextView email;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgViewCover=(ImageView) itemView.findViewById(R.id.profileImage);
            name=(TextView) itemView.findViewById(R.id.name);
            email=(TextView) itemView.findViewById(R.id.email);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            clicked(position,v.getContext());

        }

        public void clicked(int pos,Context ctx)
        {
            Bundle b = new Bundle();
            b.putParcelable("user", userArrayList.get(pos));
           ctx.startActivity(new Intent(ctx, UserDetails.class).putExtras(b));
        }


    }
}
