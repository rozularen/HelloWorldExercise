package com.marcos.helloworldexercise.data.source;

import com.marcos.helloworldexercise.data.User;

import java.util.List;

/**
 * Created by markc on 13/01/2018.
 */

public interface UsersDataSource {

    void createUser(User user, CreateUserCallback callback);

    void getUsers(LoadUsersCallback callback);

    void getUser(Integer userId, LoadUserCallback callback);

    void updateUser(User user, UpdateUserCallback callback);

    void removeUser(Integer userId, RemoveUserCallback callback);

    interface CreateUserCallback {
        void onUserCreated(User user);

        void onDataNotAvailable();
    }

    interface LoadUsersCallback {
        void onUsersLoaded(List<User> users);

        void onDataNotAvailable();
    }

    interface LoadUserCallback {
        void onUserLoaded(User user);

        void onDataNotAvailable();
    }

    interface UpdateUserCallback {
        void onUserUpdated(User user);

        void onDataNotAvailable();
    }

    interface RemoveUserCallback {
        void onUserRemoved();

        void onDataNotAvailable();
    }

}
