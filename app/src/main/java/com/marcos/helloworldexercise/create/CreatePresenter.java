package com.marcos.helloworldexercise.create;

import android.util.Log;

import com.google.common.base.Strings;
import com.marcos.helloworldexercise.data.User;
import com.marcos.helloworldexercise.data.source.UsersRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by markc on 14/01/2018.
 */

public class CreatePresenter implements CreateContract.Presenter {

    private static final String TAG = "CreatePresenter";
    private UsersRepository usersRepository;
    private CreateContract.View view;


    public CreatePresenter(UsersRepository usersRepository, CreateContract.View view) {
        if (usersRepository != null) {
            this.usersRepository = usersRepository;
            if (view != null) {
                this.view = view;
                view.setPresenter(this);
            }
        }
    }

    @Override
    public void start() {
        //load previous info
    }

    @Override
    public void stop() {
        view = null;
    }

    @Override
    public void onSaveClicked(String name, String birthdate) {
        if (Strings.isNullOrEmpty(name)) {
            view.showNameError();
        } else if (Strings.isNullOrEmpty(birthdate)) {
            view.showBirthdateError();
        } else {
            SimpleDateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date outputDate = null;

            try {
                outputDate = originalFormat.parse(birthdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            User newUser = new User(name, outputDate);
            usersRepository.createUser(newUser);
        }
    }
}
