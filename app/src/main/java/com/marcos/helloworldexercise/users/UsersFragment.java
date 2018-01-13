package com.marcos.helloworldexercise.users;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marcos.helloworldexercise.R;
import com.marcos.helloworldexercise.data.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class UsersFragment extends Fragment implements UsersContract.View {

    public static final String TAG = "UsersFragment";
    private UsersAdapter usersAdapter;
    private UsersContract.Presenter presenter;

    @BindView(R.id.rv_users)
    RecyclerView rvUsers;

    public UsersFragment() {
        // Required empty public constructor
    }


    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usersAdapter = new UsersAdapter(new ArrayList<User>(0), itemListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        ButterKnife.bind(this, view);

        //set up recycler view
        rvUsers.setAdapter(usersAdapter);

        rvUsers.setHasFixedSize(true);
        rvUsers.setItemAnimator(new DefaultItemAnimator());
        rvUsers.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void setPresenter(UsersContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroy() {
        presenter.stop();
        super.onDestroy();
    }

    @Override
    public void showUsers(List<User> users) {
        usersAdapter.setUsers(users);
        usersAdapter.notifyDataSetChanged();
    }

    UserItemListener itemListener = new UserItemListener() {
        @Override
        public void onUserClick(User clickedUser) {
            presenter.openUserDetails(clickedUser);
        }
    };

    public interface UserItemListener {
        void onUserClick(User clickedUser);
    }
}
