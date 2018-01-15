package com.marcos.helloworldexercise.create;

import com.marcos.helloworldexercise.BasePresenter;
import com.marcos.helloworldexercise.BaseView;

/**
 * Created by markc on 14/01/2018.
 */

public interface CreateContract {
    interface View extends BaseView<Presenter> {

        void showNameError();

        void showBirthdateError();
    }

    interface Presenter extends BasePresenter {

        void onSaveClicked(String name, String birthdate);

    }
}
