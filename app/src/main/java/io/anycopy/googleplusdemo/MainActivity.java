package io.anycopy.googleplusdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private Toolbar toolbar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            toolbar.setTitle(item.getTitle());
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.snack_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_show_snack_bar:
                showSnackBar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void showSnackBar() {
        Snackbar.make(navigation, "Some text", Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.view_pager);
        GooglePlusFragmentPageAdapter adapter = new GooglePlusFragmentPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bindNavigationDrawer();
        initTitle();
    }

    private void initTitle() {
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(navigation.getMenu().getItem(0).getTitle());
            }
        });
    }

    private void bindNavigationDrawer() {


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();
                if (id == R.id.nav_tool) {
                    showToolSnackBar();
                } else if (id == R.id.nav_share) {
                    showShareSnackBar();
                } else if (id == R.id.nav_gallery) {
                    showGallerySnackBar();
                } else if (id == R.id.nav_send) {
                    showSendSnackBar();
                }
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }


        });
    }

    private void showSendSnackBar() {
        Snackbar.make(navigation, "Send", Snackbar.LENGTH_SHORT).show();

    }

    private void showGallerySnackBar() {
        Snackbar.make(navigation, "Gallery", Snackbar.LENGTH_SHORT).show();

    }

    private void showToolSnackBar() {
        Snackbar.make(navigation, "Tool", Snackbar.LENGTH_SHORT).show();
    }

    private void showShareSnackBar() {
        Snackbar.make(navigation, "Share", Snackbar.LENGTH_SHORT).show();
    }


    private static class GooglePlusFragmentPageAdapter extends FragmentPagerAdapter {


        public GooglePlusFragmentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance(1);
                case 1:
                    return DashboardFragment.newInstance("Tony");
                case 2:
                    return NotificationFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
