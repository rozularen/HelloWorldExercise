package com.marcos.helloworldexercise.data.source;

import android.util.Log;

import com.marcos.helloworldexercise.data.User;

import java.util.List;

/**
 * Created by markc on 13/01/2018.
 */

public class UsersRepository implements UsersDataSource {

    private static final String TAG = "UsersRepository";
    private static UsersRepository INSTANCE = null;
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
    public void createUser(User user) {
        if(user != null) {
            remoteDataSource.createUser(user);
        }
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
    public void getUser(Integer userId, final LoadUserCallback callback) {
        if (userId != null) {
            remoteDataSource.getUser(userId, new LoadUserCallback() {
                @Override
                public void onUserLoaded(User user) {
                    Log.d(TAG, "onUserLoaded: ");
                    callback.onUserLoaded(user);
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            });
        }
    }

    @Override
    public void updateUser(User user) {
        if(user != null){
            remoteDataSource.updateUser(user);
        } else {
            Log.e(TAG, "updateUser: User can't be null.");
        }
    }

    @Override
    public void removeUser(Integer userId) {
        if(userId != null) {
            remoteDataSource.removeUser(userId);
        } else {
            Log.e(TAG, "removeUser: User ID can't be null.");
        }
    }

}
