package io.anycopy.googleplusdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class DashboardFragment extends Fragment {

  private static final String ARG_NAME = "arg_name";

  public static DashboardFragment newInstance(String name) {
    Bundle bundle = new Bundle();
    bundle.putString(ARG_NAME, name);
    DashboardFragment dashboardFragment = new DashboardFragment();
    dashboardFragment.setArguments(bundle);
    return dashboardFragment;
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_dashboard, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ViewPager viewPager = view.findViewById(R.id.view_pager);
    GooglePlusFragmentPageAdapter adapter =
        new GooglePlusFragmentPageAdapter(
            getChildFragmentManager(), requireArguments().getString(ARG_NAME));
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(adapter.getCount() - 1);
    TabLayout tabLayout = view.findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    getLifecycle().addObserver(new TimberLogger(this));
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    inflater.inflate(R.menu.send, menu);
  }

  private static class GooglePlusFragmentPageAdapter extends FragmentPagerAdapter {

    private final String name;

    public GooglePlusFragmentPageAdapter(FragmentManager fm, String name) {
      super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
      this.name = name;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
      return DashboardChildFragment.newInstance(position, name);
    }

    @Override
    public int getCount() {
      return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
      return "Dashboard " + position;
    }
  }
}
