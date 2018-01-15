package com.marcos.helloworldexercise.data.source;

import com.marcos.helloworldexercise.data.User;

import java.util.List;

/**
 * Created by markc on 13/01/2018.
 */

public interface UsersDataSource {

    void createUser(User user);

    void getUsers(LoadUsersCallback callback);

    void getUser(Integer userId, LoadUserCallback callback);

    void updateUser(User user);

    void removeUser(Integer userId);

    interface LoadUsersCallback {
        void onUsersLoaded(List<User> users);

        void onDataNotAvailable();
    }

    interface LoadUserCallback {
        void onUserLoaded(User user);

        void onDataNotAvailable();
    }

}
