package com.marcos.helloworldexercise.main;

import com.marcos.helloworldexercise.BasePresenter;
import com.marcos.helloworldexercise.BaseView;

/**
 * Created by markc on 13/01/2018.
 */

public interface MainContract {
    interface View extends BaseView<MainContract.Presenter> {


    }

    interface Presenter extends BasePresenter {

    }
}
