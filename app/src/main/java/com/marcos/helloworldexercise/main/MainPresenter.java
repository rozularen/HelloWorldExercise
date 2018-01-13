package com.marcos.helloworldexercise.main;

import android.util.Log;

import com.marcos.helloworldexercise.data.source.UsersRepository;

/**
 * Created by markc on 13/01/2018.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";

    private UsersRepository usersRepository;
    private MainContract.View view;

    public MainPresenter(UsersRepository usersRepository, MainContract.View view) {
        if (usersRepository != null) {
            this.usersRepository = usersRepository;
            if (view != null) {
                this.view = view;
                view.setPresenter(this);
            } else {
                Log.d(TAG, "MainPresenter: View can't be null");
            }
        } else {
            Log.d(TAG, "MainPresenter: Users Repository can't be null");
        }
    }

    @Override
    public void start() {
        //load whatever
    }

    @Override
    public void stop() {
        view = null;
    }
}
