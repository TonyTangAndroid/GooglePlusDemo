package io.anycopy.googleplusdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class DashboardFragment extends Fragment {

  private static final String ARG_NAME = "arg_name";
  private ViewPager2 viewPager;
  private CustomTabManager customTabManager;

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
    viewPager = view.findViewById(R.id.view_pager2);
    GooglePlusFragmentPageAdapter adapter =
        new GooglePlusFragmentPageAdapter(this, requireArguments().getString(ARG_NAME));
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(adapter.getItemCount() - 1);
    initCustomTabManager();
  }

  @Override
  public void onResume() {
    super.onResume();
    bindTab();
  }

  @Override
  public void onPause() {
    super.onPause();
    unbindTab();
  }

  private void unbindTab() {
    customTabManager.unbind();
  }

  private void bindTab() {
    customTabManager.bind(viewPager);
  }

  private void initCustomTabManager() {
    customTabManager =
        new CustomTabManager(((AppCompatActivity) requireActivity()).getSupportActionBar());
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
  }
}
