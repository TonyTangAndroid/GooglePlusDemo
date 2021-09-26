package io.anycopy.googleplusdemo;

import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavItemSelectedListener
    implements BottomNavigationView.OnNavigationItemSelectedListener {

  private final Toolbar toolbar;
  private final ViewPager viewPager;
  private final ActionBar actionBar;

  public BottomNavItemSelectedListener(ViewPager viewPager, Toolbar toolbar, ActionBar actionBar) {
    this.toolbar = toolbar;
    this.viewPager = viewPager;
    this.actionBar = actionBar;
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    int itemId = item.getItemId();
    if (itemId == R.id.navigation_home) {
      viewPager.setCurrentItem(0);
      actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
      toolbar.setTitle(item.getTitle());
      return true;
    } else if (itemId == R.id.navigation_dashboard) {
      actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
      actionBar.setCustomView(R.layout.custom_toolbar);
      TextView textView = actionBar.getCustomView().findViewById(R.id.toolbar_title);
      textView.setText("Custom Title");
      viewPager.setCurrentItem(1);
      return true;
    } else if (itemId == R.id.navigation_notifications) {
      actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
      viewPager.setCurrentItem(2);
      toolbar.setTitle(item.getTitle());
      return true;
    }
    return false;
  }
}
