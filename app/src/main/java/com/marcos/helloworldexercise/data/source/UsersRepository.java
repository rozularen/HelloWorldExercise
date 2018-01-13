package com.marcos.helloworldexercise.data.source;

import com.marcos.helloworldexercise.data.User;

import java.util.List;

/**
 * Created by markc on 13/01/2018.
 */

public class UsersRepository implements UsersDataSource {

    private static UsersRepository INSTANCE = null;

    private UsersDataSource localDataSource;

    private UsersDataSource remoteDataSource;

    // Prevent direct instantiation.
    private UsersRepository(UsersDataSource remoteDataSource) {
        if (remoteDataSource != null) {
            this.remoteDataSource = remoteDataSource;
        }
    }

    public static UsersRepository getInstance(UsersDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UsersRepository(remoteDataSource);
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getUsers(LoadUsersCallback callback) {
        if (callback != null) {
            getUsersFromRemoteDataSource(callback);
        }
    }

    private void getUsersFromRemoteDataSource(final LoadUsersCallback callback) {
        remoteDataSource.getUsers(new LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                callback.onUsersLoaded(users);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUser(Integer userId, LoadUserCallback callback) {
        if(userId != null){

        }
    }

    @Override
    public void editUser(Integer userId) {

    }

    @Override
    public void removeUser(Integer userId) {

    }

}
