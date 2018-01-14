package com.marcos.helloworldexercise.users;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marcos.helloworldexercise.R;
import com.marcos.helloworldexercise.data.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by markc on 13/01/2018.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private final ItemClickListener itemClickListener;
    private List<User> users = new ArrayList<>();

    public UsersAdapter(List<User> users, ItemClickListener itemClickListener) {
        this.users = users;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);

        holder.user = user;
        holder.tvName.setText(user.getName());
        holder.tvBirthdate.setText(user.getBirthdate().toString());

        //get random pic and link it to each user
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public interface ItemClickListener {
        void onClick(View view, int itemId);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener itemClickListener;
        private User user;

        @BindView(R.id.iv_profile_image)
        ImageView ivProfileImage;

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_birthdate)
        TextView tvBirthdate;

        ViewHolder(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, user.getId());
        }
    }
}
