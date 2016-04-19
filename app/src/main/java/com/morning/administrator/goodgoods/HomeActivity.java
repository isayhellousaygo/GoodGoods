package com.morning.administrator.goodgoods;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.morning.administrator.goodgoods.fragments.FragmentHero;
import com.morning.administrator.goodgoods.fragments.FragmentMagazine;
import com.morning.administrator.goodgoods.fragments.FragmentMine;
import com.morning.administrator.goodgoods.fragments.FragmentShare;
import com.morning.administrator.goodgoods.fragments.FragmentShop;


public class HomeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup navigation;
    private Fragment cacheFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        navigation = ((RadioGroup) findViewById(R.id.navigation));
        navigation.setOnCheckedChangeListener(this);

        cacheFragment = new FragmentShop();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.home_frame, cacheFragment, FragmentShop.TAG).commit();




    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.navigation_shop:
                changeFragment(FragmentShop.TAG, FragmentShop.class);
                break;

            case R.id.navigation_magazine:
                changeFragment(FragmentMagazine.TAG, FragmentMagazine.class);
                break;

            case R.id.navigation_share:
                changeFragment(FragmentShare.TAG, FragmentShare.class);
                break;

            case R.id.navigation_hero:
                changeFragment(FragmentHero.TAG, FragmentHero.class);
                break;

            case R.id.navigation_mine:
                changeFragment(FragmentMine.TAG, FragmentMine.class);
                break;
        }
    }

    private void changeFragment(String tag, Class<? extends Fragment> cls) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(cacheFragment);

        cacheFragment = fragmentManager.findFragmentByTag(tag);
        if (cacheFragment == null) {
            try {
                cacheFragment = cls.getConstructor().newInstance();
                fragmentTransaction.add(R.id.home_frame, cacheFragment, tag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        fragmentTransaction.show(cacheFragment);
        fragmentTransaction.commit();
    }
}
