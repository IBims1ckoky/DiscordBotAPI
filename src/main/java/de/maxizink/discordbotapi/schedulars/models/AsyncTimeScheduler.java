package de.maxizink.discordbotapi.schedulars.models;

import de.maxizink.discordbotapi.schedulars.TimeScheduler;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public abstract class AsyncTimeScheduler extends TimeScheduler {

  private static final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

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
    scheduledExecutorService.scheduleAtFixedRate(this::action, 0, period, timeUnit);
  }

  public void stopRun() {
    scheduledExecutorService.shutdown();
  }

}