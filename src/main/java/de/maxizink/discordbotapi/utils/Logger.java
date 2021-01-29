package de.maxizink.discordbotapi.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

  public static void ERROR(final String message) {
    System.out.println("[ERROR | ] " + getCurrentTime() + " ] " + message);
  }

  public static void WARN(final String message) {
    System.out.println("[WARN | ] " + getCurrentTime() + " ] " + message);
  }

  public static void INFO(final String message) {
    System.out.println("[INFO | ] " + getCurrentTime() + " ] " + message);
  }

  public static String getCurrentTime() {
    return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date(System.currentTimeMillis()));
  }

}
