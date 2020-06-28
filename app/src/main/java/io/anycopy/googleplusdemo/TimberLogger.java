package io.anycopy.googleplusdemo;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import timber.log.Timber;

public class TimberLogger implements LifecycleObserver {

  private final LifecycleOwner lifecycleOwner;

  public TimberLogger(LifecycleOwner lifecycleOwner) {
    this.lifecycleOwner = lifecycleOwner;
  }

  @OnLifecycleEvent(Event.ON_RESUME)
  public void onResume() {
    Timber.i("onResume:%s", lifecycleOwner.getClass().getSimpleName());
  }

  @OnLifecycleEvent(Event.ON_PAUSE)
  public void onPause() {
    Timber.i("onPause:%s", lifecycleOwner.getClass().getSimpleName());
  }

  @OnLifecycleEvent(Event.ON_START)
  public void onStart() {
    Timber.i("onStart:%s", lifecycleOwner.getClass().getSimpleName());
  }

  @OnLifecycleEvent(Event.ON_STOP)
  public void onStop() {
    Timber.i("onStop:%s", lifecycleOwner.getClass().getSimpleName());
  }
}
