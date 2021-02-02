package de.maxizink.discordbotapi.schedulars;

import de.maxizink.discordbotapi.schedulars.core.models.SyncTimeScheduler;

import java.util.concurrent.TimeUnit;

public class ExampleSyncTimeScheduler extends SyncTimeScheduler {

  public ExampleSyncTimeScheduler() {
    super(5, TimeUnit.SECONDS);
  }

  @Override
  public void beforeStart() {
    System.out.println("Ich passiere vor dem starten");
  }

  @Override
  public void action() {
    System.out.println("Ich werde alle 5 Sekunden ausgeführt");
  }

  @Override
  public void afterStop() {
    System.out.println("Ich passiere nach dem stopen");
  }
}
