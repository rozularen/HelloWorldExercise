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
        loadUsers();
    }

    private void loadUsers() {
        view.setLoadingIndicator(true);
        usersRepository.getUsers(new UsersDataSource.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                view.setLoadingIndicator(false);
                view.showUsers(users);
            }

            @Override
            public void onDataNotAvailable() {
                view.showUserLoadingError();
            }
        });
    }

    @Override
    public void stop() {
        view = null;
    }

}
