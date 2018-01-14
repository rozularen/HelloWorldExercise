package com.marcos.helloworldexercise.details;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.marcos.helloworldexercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements DetailsContract.View {

    public static final String TAG = "DetailsFragment";

    DetailsContract.Presenter presenter;

    @BindView(R.id.pb_loading_indicator)
    ProgressBar pbLoadingIndicator;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_birthdate)
    TextView tvBirthdate;


    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt("id", id);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroy() {
        presenter.stop();
        super.onDestroy();
    }

    @Override
    public void setPresenter(DetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showMissingUser() {
        tvName.setText("Missing User!");
        tvBirthdate.setVisibility(View.GONE);
    }

    @Override
    public void showMissingBirthdate() {
        tvBirthdate.setText("Missing User Birthdate!");
    }

    @Override
    public void showMissingName() {
        tvName.setText("Missing User Name!");
    }

    @Override
    public void showName(String name) {
        tvName.setVisibility(View.VISIBLE);
        tvName.setText(name);
    }

    @Override
    public void showBirthdate(String birthdate) {
        tvBirthdate.setVisibility(View.VISIBLE);
        tvBirthdate.setText(birthdate);
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        if (isLoading) {
            pbLoadingIndicator.setVisibility(View.VISIBLE);
        } else {
            pbLoadingIndicator.setVisibility(View.GONE);
        }
    }
}
