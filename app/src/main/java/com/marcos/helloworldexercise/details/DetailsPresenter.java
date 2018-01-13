package com.marcos.helloworldexercise.details;

/**
 * Created by markc on 14/01/2018.
 */

public class DetailsPresenter implements DetailsContract.Presenter {
    private DetailsContract.View view;

    public DetailsPresenter(DetailsContract.View view) {
        if (view != null) {
            this.view = view;
            view.setPresenter(this);
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        view = null;
    }
}
