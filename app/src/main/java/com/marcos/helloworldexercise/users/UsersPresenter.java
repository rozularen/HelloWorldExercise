package com.marcos.helloworldexercise.users;

import android.util.Log;

import com.marcos.helloworldexercise.data.User;
import com.marcos.helloworldexercise.data.source.UsersDataSource;
import com.marcos.helloworldexercise.data.source.UsersRepository;

import java.util.List;

/**
 * Created by markc on 13/01/2018.
 */

public class UsersPresenter implements UsersContract.Presenter {

    private static final String TAG = "UsersPresenter";

    private UsersRepository usersRepository;
    private UsersContract.View view;

    public UsersPresenter(UsersRepository usersRepository, UsersContract.View view) {
        if (usersRepository != null) {
            this.usersRepository = usersRepository;
            if (view != null) {
                this.view = view;
                view.setPresenter(this);
            } else {
                Log.d(TAG, "UsersPresenter: View can't be null");
            }
        } else {
            Log.d(TAG, "UsersPresenter: Users Repository can't be null");
        }
    }

    @Override
    public void start() {
        //load whatever
        usersRepository.getUsers(new UsersDataSource.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                view.showUsers(users);
            }

            @Override
            public void onDataNotAvailable() {
                //showerror
            }
        });
    }

    @Override
    public void stop() {
        view = null;
    }


    @Override
    public void openUserDetails(User clickedUser) {

    }
}
