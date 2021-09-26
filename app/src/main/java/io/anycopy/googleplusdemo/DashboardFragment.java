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
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

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
    ViewPager2 viewPager = view.findViewById(R.id.view_pager);
    GooglePlusFragmentPageAdapter adapter =
        new GooglePlusFragmentPageAdapter(this, requireArguments().getString(ARG_NAME));
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(adapter.getItemCount() - 1);
    TabLayout tabLayout = view.findViewById(R.id.tabs);

    new TabLayoutMediator(
            tabLayout, viewPager, (tab, position) -> tab.setText(adapter.getPageTitle(position)))
        .attach();
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

  private static class GooglePlusFragmentPageAdapter extends FragmentStateAdapter {

    private final String name;

    public GooglePlusFragmentPageAdapter(Fragment fragment, String name) {
      super(fragment);
      this.name = name;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
      return DashboardChildFragment.newInstance(position, name);
    }

    @Override
    public int getItemCount() {
      return 2;
    }

    public CharSequence getPageTitle(int position) {
      return "Dashboard " + position;
    }
  }
}
