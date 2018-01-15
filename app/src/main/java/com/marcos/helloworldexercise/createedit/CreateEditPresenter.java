package com.marcos.helloworldexercise.createedit;

import android.util.Log;

import com.google.common.base.Strings;
import com.marcos.helloworldexercise.data.User;
import com.marcos.helloworldexercise.data.source.UsersDataSource;
import com.marcos.helloworldexercise.data.source.UsersRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by markc on 14/01/2018.
 */

public class CreateEditPresenter implements CreateEditContract.Presenter {

    private static final String TAG = "CreateEditPresenter";
    private UsersRepository usersRepository;
    private CreateEditContract.View view;
    private Integer userId;

    public CreateEditPresenter(UsersRepository usersRepository, CreateEditContract.View view) {
       this(null, usersRepository, view);
    }

    public CreateEditPresenter(Integer userId, UsersRepository usersRepository, CreateEditContract.View view) {
        this.userId = userId;
        if (usersRepository != null) {
            this.usersRepository = usersRepository;
            if (view != null) {
                this.view = view;
                view.setPresenter(this);
            } else {
                Log.d(TAG, "CreateEditPresenter: View can't be null.");
            }
        } else {
            Log.d(TAG, "CreateEditPresenter: Users Repository can't be null.");
        }

    }

    @Override
    public void start() {
        //load previous info
        openUser();
    }

    private void openUser() {
        usersRepository.getUser(userId, new UsersDataSource.LoadUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                showUser(user);
            }

            @Override
            public void onDataNotAvailable() {
                view.showMissingUser();
            }
        });
    }

    private void showUser(User user) {
        String name = user.getName();

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String birthdate = formatter.format(user.getBirthdate());
        Log.d(TAG, "showUser: " + birthdate);

        if (Strings.isNullOrEmpty(name)) {
            view.showMissingName();
        } else {
            view.showName(name);
        }

        if (Strings.isNullOrEmpty(birthdate)) {
            view.showMissingBirthdate();
        } else {
            view.showBirthdate(birthdate);
        }
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
            usersRepository.createUser(newUser, new UsersDataSource.CreateUserCallback() {
                @Override
                public void onUserCreated(User user) {
                    view.showUserCreated();
                }

                @Override
                public void onDataNotAvailable() {
                    view.showCreateUserError();
                }
            });
        }
    }

    @Override
    public void onUpdateClicked(String name, String birthdate) {
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

            User newUser = new User(userId, name, outputDate);
            usersRepository.updateUser(newUser, new UsersDataSource.UpdateUserCallback() {
                @Override
                public void onUserUpdated(User user) {
                    view.showUserUpdated();
                }

                @Override
                public void onDataNotAvailable() {
                    view.showUpdateUserError();
                }
            });
        }
    }
}
