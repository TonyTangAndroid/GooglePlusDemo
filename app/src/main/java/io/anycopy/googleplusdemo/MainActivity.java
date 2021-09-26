package io.anycopy.googleplusdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

  private BottomNavigationView navigation;
  private Toolbar toolbar;

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.search, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_search) {
      showSearch();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void showSearch() {
    Snackbar.make(navigation, "Go to Search", Snackbar.LENGTH_LONG).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ViewPager2 viewPager = findViewById(R.id.view_pager);
    // https://stackoverflow.com/a/55193815/4068957
    viewPager.setUserInputEnabled(false);
    AppFragmentPageAdapter adapter = new AppFragmentPageAdapter(this);
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(adapter.getItemCount() - 1);
    navigation = findViewById(R.id.navigation);
    BottomNavItemSelectedListener listener = new BottomNavItemSelectedListener(viewPager, toolbar);
    navigation.setOnItemSelectedListener(listener);
    bindNavigationDrawer();
    initTitle();
  }

  private void initTitle() {
    toolbar.post(() -> toolbar.setTitle(navigation.getMenu().getItem(0).getTitle()));
  }

  private void bindNavigationDrawer() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    NavigationView navigationView = findViewById(R.id.nav_view);
    SideNavItemSelectedListener listener = new SideNavItemSelectedListener(drawer, navigation);
    navigationView.setNavigationItemSelectedListener(listener);
  }

  public void onFabClicked(View view) {
    Snackbar.make(navigation, "Fab Share", Snackbar.LENGTH_SHORT).show();
  }
}
