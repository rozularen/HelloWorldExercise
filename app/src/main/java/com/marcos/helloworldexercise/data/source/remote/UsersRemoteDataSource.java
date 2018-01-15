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
    public void createUser(User user) {
        String name = user.getName();
        String birthdate = user.getBirthdate().toString();

        Call<User> call = apiService.createUser(name, birthdate);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //TODO: update UI
                Log.d(TAG, "onResponse: STATUS CODE: " + response.code());
                Log.d(TAG, "onResponse: User created succesfully." + response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //TODO: show error
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
    public void getUser(Integer userId, final LoadUserCallback callback) {
        Call<User> call = apiService.getUser(userId);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                callback.onUserLoaded(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void updateUser(User user) {
        Integer userId = user.getId();
        String name = user.getName();
        String birthdate = user.getBirthdate().toString();

        Call<User> call = apiService.updateUser(userId, name, birthdate);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //TODO: Update UI
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //TODO: Show error while updating user
            }
        });
    }

    @Override
    public void removeUser(Integer userId) {
        //TODO: Try to remove later
//        Call<Void> call = apiService.removeUser(userId);
//
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//                //TODO: Update UI
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                //TODO: Show remove user error
//            }
//        });
    }

}
