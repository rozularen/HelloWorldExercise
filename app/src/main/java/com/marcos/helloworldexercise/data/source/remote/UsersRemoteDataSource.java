package com.marcos.helloworldexercise.data.source.remote;

import android.util.Log;

import com.marcos.helloworldexercise.data.User;
import com.marcos.helloworldexercise.data.source.UsersDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by markc on 13/01/2018.
 */

public class UsersRemoteDataSource implements UsersDataSource {

    private static final String TAG = "UsersRemoteDS";

    private static UsersRemoteDataSource INSTANCE;
    private final APIService apiService;

    // Prevent direct instantiation.
    private UsersRemoteDataSource() {
        Retrofit client = APIClient.getClient();

        apiService = client.create(APIService.class);
    }

    public static UsersRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsersRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void createUser(User user, final CreateUserCallback callback) {
        //TODO: Validate User object before POST request
        Call<User> call = apiService.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    User user = response.body();
                    callback.onUserCreated(user);

                } else {
                    callback.onDataNotAvailable();
                }
                Log.d(TAG, "onResponse: STATUS CODE: " + response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onDataNotAvailable();
                Log.d(TAG, "onFailure: Couldn't create new user.");
            }
        });
    }

    @Override
    public void getUsers(final LoadUsersCallback callback) {
        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    List<User> users = response.body();
                    callback.onUsersLoaded(users);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUser(Integer userId, final LoadUserCallback callback) {
        Call<User> call = apiService.getUser(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    User user = response.body();
                    callback.onUserLoaded(user);
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void updateUser(User user, final UpdateUserCallback callback) {
        //TODO: Validate user object before POST request
        Call<User> call = apiService.updateUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    User user = response.body();
                    callback.onUserUpdated(user);
                } else {
                    callback.onDataNotAvailable();
                }
                Log.d(TAG, "onResponse: STATUS CODE: " + response.code());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onDataNotAvailable();
                Log.e(TAG, "onFailure: Error while updating user.", t);
            }
        });
    }

    @Override
    public void removeUser(Integer userId, final RemoveUserCallback callback) {
        //TODO: Try to remove later
        Call<Void> call = apiService.removeUser(userId);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    callback.onUserRemoved();
                } else {
                    callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

}
