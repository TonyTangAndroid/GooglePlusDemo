package io.anycopy.googleplusdemo;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class SideNavItemSelectedListener
    implements NavigationView.OnNavigationItemSelectedListener {

  private final DrawerLayout drawer;
  private final BottomNavigationView navigation;

  public SideNavItemSelectedListener(DrawerLayout drawer, BottomNavigationView navigation) {
    this.drawer = drawer;
    this.navigation = navigation;
  }

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
    drawer.closeDrawer(GravityCompat.START);
    return true;
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
}
