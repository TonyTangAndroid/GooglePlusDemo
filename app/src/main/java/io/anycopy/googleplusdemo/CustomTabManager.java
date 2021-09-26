package io.anycopy.googleplusdemo;

import androidx.appcompat.app.ActionBar;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.Tab;
import com.google.android.material.tabs.TabLayoutMediator;

public class CustomTabManager {

  private final ActionBar actionBar;

  public CustomTabManager(ActionBar actionBar) {
    this.actionBar = actionBar;
  }

  public void bind(ViewPager2 viewPager2) {
    actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    actionBar.setCustomView(R.layout.custom_toolbar);
    TabLayout tabLayout = actionBar.getCustomView().findViewById(R.id.tabs);
    new TabLayoutMediator(tabLayout, viewPager2, this::setTabItem).attach();
  }

  private void setTabItem(Tab tab, int position) {
    tab.setText(getPageTitle(position));
  }

  private CharSequence getPageTitle(int position) {
    return "Dashboard " + position;
  }

  public void unbind() {
    actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
  }
}
