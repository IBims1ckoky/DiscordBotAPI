package de.maxizink.discordbotapi.utils.embedbuilders;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NumberEmojis {

  ONE(1, "1\u20E3", ":one:"),
  TWO(2, "2\u20E3", ":two:"),
  THREE(3, "3\u20E3", ":three:"),
  FOUR(4, "4\u20E3", ":four:"),
  FIVE(5, "5\u20E3", ":five:"),
  SIX(6, "6\u20E3", ":six:"),
  SEVEN(7, "7\u20E3", ":seven:"),
  EIGHT(8, "8\u20E3", ":eight:"),
  NINE(9, "9\u20E3", ":nine:");

  private final int number;
  private final String uniCode;
  private final String name;

  public static NumberEmojis getEmojiByNumber(final int number) {
    for (NumberEmojis numberEmojis : values()) {
      if (numberEmojis.getNumber() == number) {
        return numberEmojis;
      }
    }
    throw new IllegalStateException("No Number found!");
  }

  public static NumberEmojis getEmojiByUniCode(final String uniCode) {
    for (NumberEmojis numberEmojis : values()) {
      if (numberEmojis.getUniCode().equals(uniCode)) {
        return numberEmojis;
      }
    }
    throw new IllegalStateException("No Number found!");
  }

}
