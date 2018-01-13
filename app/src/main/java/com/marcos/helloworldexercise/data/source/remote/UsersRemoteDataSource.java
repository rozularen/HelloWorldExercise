package com.marcos.helloworldexercise.data.source.remote;

import android.util.Log;
import android.widget.Toast;

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
    public void getUsers(final LoadUsersCallback callback) {
        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                callback.onUsersLoaded(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void getUser(Integer userId, LoadUserCallback callback) {

    }

    @Override
    public void editUser(Integer userId) {

    }

    @Override
    public void removeUser(Integer userId) {

    }

}
