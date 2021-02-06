package de.maxizink.discordbotapi.schedulars;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SchedulerManager {

  private final List<TimeScheduler> timeSchedulers = new ArrayList<>();

  public void registerTimeSchedular(final TimeScheduler timeScheduler) {
    timeSchedulers.add(timeScheduler);
  }

  public void startAll() {
    timeSchedulers.forEach(TimeScheduler::start);
  }

  public void stopAll() {
    timeSchedulers.forEach(TimeScheduler::stop);
  }

}
