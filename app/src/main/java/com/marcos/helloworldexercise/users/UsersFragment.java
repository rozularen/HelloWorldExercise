package com.marcos.helloworldexercise.users;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.marcos.helloworldexercise.MainActivity;
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
    @BindView(R.id.rv_users)
    RecyclerView rvUsers;
    @BindView(R.id.pb_loading_indicator)
    ProgressBar pbLoadingIndicator;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private UsersAdapter usersAdapter;
    private UsersContract.Presenter presenter;
    private MainActivity mainActivity;
    UsersAdapter.ItemClickListener itemClickListener = new UsersAdapter.ItemClickListener() {

        @Override
        public void onClick(View view, int itemId) {
            mainActivity.navigateToDetails(itemId);
        }
    };

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
        usersAdapter = new UsersAdapter(new ArrayList<User>(0), getContext(), itemClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        ButterKnife.bind(this, view);
        mainActivity = (MainActivity) getActivity();

        ActionBar supportActionBar = mainActivity.getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setTitle("Hello World Exercise");


        //set up FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.navigateToCreate();
            }
        });

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

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        if (isLoading) {
            pbLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            pbLoadingIndicator.setVisibility(View.GONE);
        }
    }

    @Override
    public void showUserLoadingError() {
        Toast.makeText(mainActivity, "Couldn't retrieve Users", Toast.LENGTH_SHORT).show();
    }

    public interface UserItemListener {
        void onUserClick(User clickedUser);
    }
}
