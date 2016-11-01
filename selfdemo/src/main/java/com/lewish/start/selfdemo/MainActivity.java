package com.lewish.start.selfdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.lewish.start.selfdemo.R.id.nv_navigation;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CoordinatorLayout mainContent;
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewpager;

    private android.widget.ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
    }

    private void initViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(nv_navigation);
        mainContent = (CoordinatorLayout)findViewById(R.id.main_content);
        appbar = (AppBarLayout)findViewById(R.id.appbar);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        toolbar.setTitle("Toolbar");
        setSupportActionBar(toolbar);

        List<String> titles = new ArrayList<>();
        titles.add("Title one");
        titles.add("Title two");
        titles.add("Title three");

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecFragment());
        fragments.add(new RecFragment());
        fragments.add(new RecFragment());

        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments,titles);
        viewpager.setAdapter(myFragmentAdapter);
        tabLayout.setupWithViewPager(viewpager);
    }

    private void initListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String str = "";
                switch (item.getItemId()) {
                    case  R.id.nv_talk:
                        str = "CC Talk";
                        break;
                    case  R.id.nv_class:
                        str = "HJ CLASS";
                        break;
                    case  R.id.nv_words:
                        str = "Words";
                        break;
                    case  R.id.nv_bighj:
                        str = "Big HJ";
                        break;
                    case  R.id.ver_android:
                        str = "Android";
                        break;
                    case  R.id.ver_ios:
                        str = "IOS";
                        break;
                }
                drawerLayout.closeDrawers();
                final String finalStr = str;
                Snackbar.make(drawerLayout,str,Snackbar.LENGTH_SHORT)
                        .setAction(str, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, finalStr, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toobarmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings :
                Snackbar.make(mainContent,"Settings",Snackbar.LENGTH_SHORT)
                        .setAction("Settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
                break;
        }
        return true;
    }
}
