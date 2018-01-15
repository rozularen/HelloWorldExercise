package com.marcos.helloworldexercise.createedit;

import com.marcos.helloworldexercise.BasePresenter;
import com.marcos.helloworldexercise.BaseView;

/**
 * Created by markc on 14/01/2018.
 */

public interface CreateEditContract {
    interface View extends BaseView<Presenter> {

        void showNameError();

        void showBirthdateError();

        void showMissingUser();

        void showMissingName();

        void showName(String name);

        void showMissingBirthdate();

        void showBirthdate(String birthdate);

        void showCreateUserError();

        void showUserCreated();

        void showUserUpdated(Integer userId);

        void showUpdateUserError();
    }

    interface Presenter extends BasePresenter {

        void onSaveClicked(String name, String birthdate);

        void onUpdateClicked(String name, String birthdate);

    }
}
