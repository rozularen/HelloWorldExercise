package com.marcos.helloworldexercise.details;

import com.marcos.helloworldexercise.BasePresenter;
import com.marcos.helloworldexercise.BaseView;

/**
 * Created by markc on 14/01/2018.
 */

public interface DetailsContract {
    interface View extends BaseView<DetailsContract.Presenter> {

        void showMissingUser();

        void showMissingBirthdate();

        void showMissingName();

        void showName(String name);

        void showBirthdate(String birthdate);

        void setLoadingIndicator(boolean isLoading);

        void showNullIdError();

        void showEditView(Integer userId);

        void showUserRemoved();

        void showRemoveUserError();

        void showRemoveConfirmDialog();
    }

    interface Presenter extends BasePresenter {

        void onRemoveItemClicked();

        void onEditItemClicked();

        void onConfirmRemoveUserClicked();

    }
}
