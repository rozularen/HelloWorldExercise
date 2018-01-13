package com.marcos.helloworldexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marcos.helloworldexercise.data.source.UsersRepository;
import com.marcos.helloworldexercise.data.source.remote.UsersRemoteDataSource;
import com.marcos.helloworldexercise.details.DetailsFragment;
import com.marcos.helloworldexercise.details.DetailsPresenter;
import com.marcos.helloworldexercise.users.UsersFragment;
import com.marcos.helloworldexercise.users.UsersPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsersFragment usersFragment = (UsersFragment) getSupportFragmentManager().findFragmentByTag(UsersFragment.TAG);

        if (usersFragment == null) {
            usersFragment = UsersFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, usersFragment)
                    .commit();
        }

        UsersRemoteDataSource usersRemoteDataSource = UsersRemoteDataSource.getInstance();

        UsersRepository usersRepository = UsersRepository.getInstance(usersRemoteDataSource);

        UsersPresenter usersPresenter = new UsersPresenter(usersRepository, usersFragment);

    }

    public void navigateToDetails(){
        DetailsFragment detailsFragment = DetailsFragment.newInstance();
        DetailsPresenter detailsPresenter = new DetailsPresenter(detailsFragment);
    }

}
