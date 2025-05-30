package assessment.cipher;

public class Cipher {

  private static final String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String RUSSIAN_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
  private static final int ENGLISH_ALPHABET_SIZE = 26;
  private static final int RUSSIAN_ALPHABET_SIZE = 33;

  public String encrypt(String plainText, int shift) {
    return shiftText(plainText, shift);
  }

  public String decrypt(String cipherText, int shift) {
    return shiftText(cipherText, -shift);
  }

  private String shiftText(String text, int shift) {
    StringBuilder result = new StringBuilder();

    for (char c : text.toCharArray()) {
      boolean isRussian = isRussian(c);
      boolean isEnglish = isEnglish(c);

      if (!isEnglish && !isRussian) {
        result.append(c);
      } else {
        result.append(getShiftedChar(c, shift, isRussian));
      }
    }

    return result.toString();
  }

  private char getShiftedChar(char c, int shift, boolean isRussian) {
    int alphabetSize = isRussian ? RUSSIAN_ALPHABET_SIZE : ENGLISH_ALPHABET_SIZE;
    String alphabet = isRussian ? RUSSIAN_ALPHABET : ENGLISH_ALPHABET;

    int pos = alphabet.indexOf(Character.toUpperCase(c));

    int positiveShift = shift < 0 ? (shift % alphabetSize) + alphabetSize : shift;

    int newPos = (pos + positiveShift) % alphabetSize;

    return Character.isUpperCase(c) ? alphabet.charAt(newPos) : Character.toLowerCase(alphabet.charAt(newPos));
  }

  private boolean isRussian(char c) {
    return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я') || c == 'ё' || c == 'Ё';
    //can't resolve decoding problem - в некоторых консолях возникает проблема с кодировками

  }

  private boolean isEnglish(char c) {
    return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }
}
