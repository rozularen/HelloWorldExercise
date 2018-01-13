package com.marcos.helloworldexercise.details;

import com.marcos.helloworldexercise.BasePresenter;
import com.marcos.helloworldexercise.BaseView;
import com.marcos.helloworldexercise.users.UsersContract;

/**
 * Created by markc on 14/01/2018.
 */

public interface DetailsContract {
    interface View extends BaseView<DetailsContract.Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
