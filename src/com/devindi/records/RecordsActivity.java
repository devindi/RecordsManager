package com.devindi.records;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

public class RecordsActivity extends FragmentActivity {
    private ViewPager viewPager;
    View firstTitle, secondTitle;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setUpView();
        setTab();
        firstTitle = findViewById(R.id.in_tab);
        firstTitle.setOnClickListener(tabSwitchListener);
        secondTitle = findViewById(R.id.out_tab);
        secondTitle.setOnClickListener(tabSwitchListener);
    }

    private void setUpView(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    private void setTab(){
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        findViewById(R.id.first_tab).setVisibility(View.VISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.INVISIBLE);
                        break;

                    case 1:
                        findViewById(R.id.first_tab).setVisibility(View.INVISIBLE);
                        findViewById(R.id.second_tab).setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    View.OnClickListener tabSwitchListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.in_tab:
                    viewPager.setCurrentItem(0, true);
                    break;
                case R.id.out_tab:
                    viewPager.setCurrentItem(1, true);
                    break;
            }
        }
    };
}