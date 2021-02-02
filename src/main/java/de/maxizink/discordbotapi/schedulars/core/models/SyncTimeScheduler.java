package de.maxizink.discordbotapi.schedulars.core.models;

import de.maxizink.discordbotapi.schedulars.core.TimeScheduler;
import lombok.RequiredArgsConstructor;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public abstract class SyncTimeScheduler extends TimeScheduler {

  private static final Timer timer = new Timer();

  private final int period;
  private final TimeUnit timeUnit;

  @Override
  public void start() {
    beforeStart();
    run();
  }

  @Override
  public void stop() {
    stopRun();
    afterStop();
  }

  public abstract void beforeStart();

  public abstract void action();

  public abstract void afterStop();

  public void run() {
    timer.scheduleAtFixedRate(getTimerTask(), 0, TimeUnit.MILLISECONDS.convert(period, timeUnit));
  }

  public void stopRun() {
    timer.cancel();
  }

  public TimerTask getTimerTask() {
    return new TimerTask() {
      @Override
      public void run() {
        action();
      }
    };
  }

}
