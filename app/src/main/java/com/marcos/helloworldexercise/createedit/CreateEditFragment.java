package com.marcos.helloworldexercise.createedit;


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
import android.widget.TextView;
import android.widget.Toast;

import com.marcos.helloworldexercise.MainActivity;
import com.marcos.helloworldexercise.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEditFragment extends Fragment implements CreateEditContract.View {

    public static final String TAG = "CreateEditFragment";
    private static boolean isEditing = false;
    @BindView(R.id.et_name)
    TextInputEditText etName;
    @BindView(R.id.et_birthdate)
    TextInputEditText etBirthdate;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private CreateEditContract.Presenter presenter;
    private MainActivity mainActivity;

    public CreateEditFragment() {
        // Required empty public constructor
    }

    public static CreateEditFragment newInstance() {
        return new CreateEditFragment();
    }

    public static CreateEditFragment newEditInstance(Integer userId) {
        isEditing = true;
        Bundle args = new Bundle();
        args.putInt("id", userId);
        CreateEditFragment createEditFragment = new CreateEditFragment();
        createEditFragment.setArguments(args);
        return createEditFragment;
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
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        ButterKnife.bind(this, view);
        mainActivity = (MainActivity) getActivity();

        ActionBar supportActionBar = mainActivity.getSupportActionBar();

        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);

        setHasOptionsMenu(true);

        //TODO: Should move away
        if (isEditing) {
            supportActionBar.setTitle("Update User");
            tvSave.setText("UPDATE");
        } else {
            supportActionBar.setTitle("Create New User");
            tvSave.setText("SAVE");
        }

        return view;
    }

    @Override
    public void onDestroy() {
        presenter.stop();
        isEditing = false;
        super.onDestroy();
    }

    @Override
    public void setPresenter(CreateEditContract.Presenter presenter) {
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
        if (isEditing) {
            presenter.onSaveClicked(name, birthdate);
        } else {
            presenter.onUpdateClicked(name, birthdate);
        }
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
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

    @Override
    public void showMissingUser() {
        //TODO: show missing user erro
        Toast.makeText(mainActivity, "User is missing", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMissingName() {
        etName.setError("User Name is missing");
    }

    @Override
    public void showName(String name) {
        etName.setText(name);
    }

    @Override
    public void showMissingBirthdate() {
        etBirthdate.setError("User Birthday is missing");
    }

    @Override
    public void showBirthdate(String birthdate) {
        etBirthdate.setText(birthdate);
    }

    @Override
    public void showCreateUserError() {

    }

    @Override
    public void showUserCreated() {

    }

    @Override
    public void showUserUpdated() {

    }

    @Override
    public void showUpdateUserError() {

    }

    private String twoDigits(int n) {
        return (n <= 9) ? ("0" + n) : String.valueOf(n);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!isEditing) {
                    mainActivity.navigateToUsers();
                } else {
                    mainActivity.onBackPressed();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
