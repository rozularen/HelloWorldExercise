package com.marcos.helloworldexercise.users;

import com.marcos.helloworldexercise.BasePresenter;
import com.marcos.helloworldexercise.BaseView;
import com.marcos.helloworldexercise.data.User;

import java.util.List;

/**
 * Created by markc on 13/01/2018.
 */

public interface UsersContract {
    interface View extends BaseView<UsersContract.Presenter> {

        void showUsers(List<User> users);

        void setLoadingIndicator(boolean isLoading);

        void showUserLoadingError();
    }

    interface Presenter extends BasePresenter {

    }
}
