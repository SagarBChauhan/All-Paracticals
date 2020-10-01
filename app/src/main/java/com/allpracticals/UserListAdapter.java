package com.allpracticals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private User[] userListData;

    public UserListAdapter(User[] userListData) {
        this.userListData = userListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View list_item = layoutInflater.inflate(R.layout.list_user, parent, false);
        ViewHolder vh = new ViewHolder(list_item);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = userListData[position];
        holder.tv_user_name.setText(userListData[position].getName());
        holder.tv_user_email.setText(userListData[position].getEmail());
        holder.layout_list_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You clicked: " + user.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userListData.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tv_user_name;
        public AppCompatTextView tv_user_email;
        public RelativeLayout layout_list_user;

        public ViewHolder(View v) {
            super(v);
            this.tv_user_name = v.findViewById(R.id.tv_user_name);
            this.tv_user_email = v.findViewById(R.id.tv_user_email);
            layout_list_user = v.findViewById(R.id.layout_list_user);
        }
    }
}
