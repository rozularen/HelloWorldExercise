package com.marcos.helloworldexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.marcos.helloworldexercise.create.CreateFragment;
import com.marcos.helloworldexercise.create.CreatePresenter;
import com.marcos.helloworldexercise.data.source.UsersRepository;
import com.marcos.helloworldexercise.data.source.remote.UsersRemoteDataSource;
import com.marcos.helloworldexercise.details.DetailsFragment;
import com.marcos.helloworldexercise.details.DetailsPresenter;
import com.marcos.helloworldexercise.users.UsersFragment;
import com.marcos.helloworldexercise.users.UsersPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        navigateToUsers();
    }

    public void navigateToDetails(int id) {
        getSupportActionBar().setTitle("Details");

        DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentByTag(DetailsFragment.TAG);

        if (detailsFragment == null) {
            detailsFragment = DetailsFragment.newInstance(id);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, detailsFragment)
                    .addToBackStack(DetailsFragment.TAG)
                    .commit();
        }

        UsersRemoteDataSource usersRemoteDataSource = UsersRemoteDataSource.getInstance();

        UsersRepository usersRepository = UsersRepository.getInstance(usersRemoteDataSource);

        DetailsPresenter detailsPresenter = new DetailsPresenter(id, usersRepository, detailsFragment);
    }

    public void navigateToCreate() {
        getSupportActionBar().setTitle("Create New User");

        CreateFragment createFragment = (CreateFragment) getSupportFragmentManager().findFragmentByTag(CreateFragment.TAG);

        if (createFragment == null) {
            createFragment = CreateFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, createFragment)
                    .addToBackStack(CreateFragment.TAG)
                    .commit();
        }

        UsersRemoteDataSource usersRemoteDataSource = UsersRemoteDataSource.getInstance();

        UsersRepository usersRepository = UsersRepository.getInstance(usersRemoteDataSource);

        CreatePresenter createPresenter = new CreatePresenter(usersRepository, createFragment);
    }

    public void navigateToUsers() {
        getSupportActionBar().setTitle("Hello World Exercise");

        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag(UsersFragment.TAG);

        if (usersFragment == null) {
            usersFragment = UsersFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, usersFragment)
                    .commit();
        }

        UsersRemoteDataSource usersRemoteDataSource = UsersRemoteDataSource.getInstance();

        UsersRepository usersRepository = UsersRepository.getInstance(usersRemoteDataSource);

        UsersPresenter usersPresenter = new UsersPresenter(usersRepository, usersFragment);
    }

    public void navigateToEdit(Integer userId) {
        //TODO: Navigate to edit view
    }
}
