package com.thesoftparrot.classlecture.test;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.thesoftparrot.classlecture.R;
import com.thesoftparrot.classlecture.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding mBinding;

    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private MyAccountFragment myAccountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        setSupportActionBar(mBinding.mytoolbar);
        
        initRef();
        addHomeFragment();
        click();
        initDrawer();
    }

    private void initDrawer() {

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mBinding.drawerLayout,
                mBinding.mytoolbar,
                R.string.open_drawer,
                R.string.close_drawer);

        toggle.setDrawerSlideAnimationEnabled(true);
        mBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initRef() {
        homeFragment = new HomeFragment();
        chatFragment = new ChatFragment();
        myAccountFragment = new MyAccountFragment();
    }

    private void click() {

        mBinding.sideNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean isItemClicked = false;

                int id = item.getItemId();

                switch (id){

                    case R.id.action_home:{
                        isItemClicked = true;
                        replaceFragment(homeFragment);
                        closeDrawer();
                        break;
                    }
                    case R.id.action_chat:{
                        isItemClicked = true;
                        replaceFragment(chatFragment);
                        closeDrawer();
                        break;
                    }
                    case R.id.action_my_account:{
                        isItemClicked = true;
                        replaceFragment(myAccountFragment);
                        closeDrawer();
                        break;
                    }
                    default:
                        break;
                }

                return isItemClicked;
            }
        });

        mBinding.bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean isItemClicked = false;

                if(item.getItemId() == R.id.action_home){
                    replaceFragment(homeFragment);
                    isItemClicked = true;
                }else if(item.getItemId() == R.id.action_chat){
                    replaceFragment(chatFragment);
                    isItemClicked = true;
                }else if(item.getItemId() == R.id.action_my_account){
                    replaceFragment(myAccountFragment);
                    isItemClicked = true;
                }

                return isItemClicked;
            }
        });
    }

    private void closeDrawer() {
        if(mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            mBinding.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, fragment, fragment.getTag());
        ft.commit();
    }

    private void addHomeFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, homeFragment, homeFragment.getTag());
        ft.commit();
    }

}