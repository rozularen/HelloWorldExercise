package com.marcos.helloworldexercise.data.source;

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
