package com.marcos.helloworldexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.marcos.helloworldexercise.data.source.UsersDataSource;
import com.marcos.helloworldexercise.data.source.UsersRepository;
import com.marcos.helloworldexercise.data.source.remote.UsersRemoteDataSource;
import com.marcos.helloworldexercise.main.MainFragment;
import com.marcos.helloworldexercise.main.MainPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(MainFragment.TAG);

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, mainFragment)
                    .commit();
        }

        UsersRemoteDataSource usersRemoteDataSource = UsersRemoteDataSource.getInstance();

        UsersRepository usersRepository = UsersRepository.getInstance(usersRemoteDataSource);

        MainPresenter mainPresenter = new MainPresenter(usersRepository, mainFragment);

    }

}
