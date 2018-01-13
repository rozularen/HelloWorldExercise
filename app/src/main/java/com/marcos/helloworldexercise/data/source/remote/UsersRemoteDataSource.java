package com.marcos.helloworldexercise.data.source.remote;

import com.marcos.helloworldexercise.data.source.UsersDataSource;

/**
 * Created by markc on 13/01/2018.
 */

public class UsersRemoteDataSource implements UsersDataSource {

    private static UsersRemoteDataSource INSTANCE;

    // Prevent direct instantiation.
    private UsersRemoteDataSource() {
    }

    public static UsersRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsersRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getUsers(LoadUsersCallback callback) {

    }

    @Override
    public void getUser(int userId, LoadUserCallback callback) {

    }

    @Override
    public void editUser(int userId) {

    }

    @Override
    public void removeUser(int userId) {

    }
}
