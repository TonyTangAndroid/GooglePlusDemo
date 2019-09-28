package io.anycopy.googleplusdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private BottomNavigationView navigation;
    private Toolbar toolbar;

    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.snack_bar, menu);
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View rootView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(rootView, savedInstanceState);
        toolbar = rootView.findViewById(R.id.toolbar);
        viewPager = rootView.findViewById(R.id.view_pager);
        drawer = rootView.findViewById(R.id.drawer_layout);
        navigationView = rootView.findViewById(R.id.nav_view);
        drawer = rootView.findViewById(R.id.drawer_layout);
        rootView.findViewById(R.id.floating_action_button).setOnClickListener(view -> onFabClicked());

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        GooglePlusFragmentPageAdapter adapter = new GooglePlusFragmentPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
        navigation = rootView.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        bindNavigationDrawer();
        initTitle();
    }


    private void initTitle() {
        toolbar.post(() -> toolbar.setTitle(navigation.getMenu().getItem(0).getTitle()));
    }

    private void bindNavigationDrawer() {


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                requireActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            // Handle navigation rootView item clicks here.
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
            drawer.closeDrawer(GravityCompat.START);
            return true;
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

    public void onFabClicked() {
        showShareSnackBar();
    }


    private static class GooglePlusFragmentPageAdapter extends FragmentPagerAdapter {


        public GooglePlusFragmentPageAdapter(FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return HomeFragment.newInstance(1);
                case 1:
                    return DashboardFragment.newInstance("Tony");
                case 2:
                    return NotificationFragment.newInstance();
                default:
                    throw new RuntimeException("Not supported");
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

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
}
