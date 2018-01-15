package com.marcos.helloworldexercise.details;

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

public class DetailsPresenter implements DetailsContract.Presenter {

    private final static String TAG = "DetailsPresenter";

    private UsersRepository usersRepository;
    private DetailsContract.View view;
    private Integer userId;

    public DetailsPresenter(Integer userId,
                            UsersRepository usersRepository,
                            DetailsContract.View view) {
        this.userId = userId;

        if (usersRepository != null) {
            this.usersRepository = usersRepository;
            if (view != null) {
                this.view = view;
                view.setPresenter(this);
            } else {
                Log.d(TAG, "DetailsPresenter: View can't be null.");
            }
        } else {
            Log.d(TAG, "DetailsPresenter: Users Repository can't null.");
        }
    }

    @Override
    public void start() {
        //load
        openUser();
    }

    private void openUser() {
        if (userId == null) {
            view.showNullIdError();
            return;
        }
        view.setLoadingIndicator(true);
        usersRepository.getUser(userId, new UsersDataSource.LoadUserCallback() {
            @Override
            public void onUserLoaded(User user) {
                view.setLoadingIndicator(false);
                if (user == null) {
                    view.showMissingUser();
                } else {
                    showUser(user);
                }
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
    public void onRemoveItemClicked() {
        view.showRemoveConfirmDialog();

    }

    @Override
    public void onEditItemClicked() {
        view.showEditView(userId);
    }

    @Override
    public void onConfirmRemoveUserClicked() {
        usersRepository.removeUser(userId, new UsersDataSource.RemoveUserCallback() {
            @Override
            public void onUserRemoved() {
                view.showUserRemoved();
            }

            @Override
            public void onDataNotAvailable() {
                view.showRemoveUserError();
            }
        });
    }
}
