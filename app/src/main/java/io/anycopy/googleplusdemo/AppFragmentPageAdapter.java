package io.anycopy.googleplusdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

class AppFragmentPageAdapter extends FragmentStateAdapter {

  public AppFragmentPageAdapter(AppCompatActivity appCompatActivity) {
    super(appCompatActivity);
  }

  @NonNull
  @Override
  public Fragment createFragment(int position) {
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
  public int getItemCount() {
    return 3;
  }
}
