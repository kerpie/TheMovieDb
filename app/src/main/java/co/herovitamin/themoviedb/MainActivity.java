package co.herovitamin.themoviedb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import co.herovitamin.themoviedb.fragment.MovieListFragment;

public class MainActivity extends AppCompatActivity{

    public FragmentManager mFragmentManager;
    public FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        replaceFragment(MovieListFragment.newInstance(), false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack){

        mFragmentTransaction = mFragmentManager.beginTransaction();

        mFragmentTransaction
                .replace(
                        R.id.content_container,
                        fragment);
        if(addToBackStack)
            mFragmentTransaction.addToBackStack("");

        mFragmentTransaction.commit();
    }
}