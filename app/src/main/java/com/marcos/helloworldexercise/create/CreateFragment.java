package com.marcos.helloworldexercise.create;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.marcos.helloworldexercise.MainActivity;
import com.marcos.helloworldexercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment implements CreateContract.View {

    public static final String TAG = "CreateFragment";
    @BindView(R.id.et_name)
    TextInputEditText etName;
    @BindView(R.id.et_birthdate)
    TextInputEditText etBirthdate;
    private CreateContract.Presenter presenter;
    private MainActivity mainActivity;

    public CreateFragment() {
        // Required empty public constructor
    }

    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        ButterKnife.bind(this, view);
        mainActivity = (MainActivity) getActivity();

        ActionBar supportActionBar = mainActivity.getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void setPresenter(CreateContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick(R.id.et_birthdate)
    void onBirthdateClick(View view) {
        showDatePickerDialog();
    }

    @OnClick(R.id.tv_save)
    void onSaveClick(View view) {
        String name = etName.getText().toString();
        String birthdate = etBirthdate.getText().toString();
        presenter.onSaveClicked(name, birthdate);
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = twoDigits(day) + "/" + twoDigits(month + 1) + "/" + year;

                etBirthdate.setText(selectedDate);
            }
        });
        newFragment.show(mainActivity.getFragmentManager(), "DatePicker");
    }

    @Override
    public void showNameError() {
        etName.setError("Name can't be empty");
    }

    @Override
    public void showBirthdateError() {
        etBirthdate.setError("Birthdate can't be empty");
    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mainActivity.navigateToUsers();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
