package assessment.cipher;

import java.util.HashMap;
import java.util.Map;

public class CipherBreaker {
  // source https://en.wikipedia.org/wiki/Letter_frequency
  private static final Map<Character, Double> ENGLISH_FREQ = initEnglishFreq();
  // source
  // https://ru.wikipedia.org/wiki/%D0%A7%D0%B0%D1%81%D1%82%D0%BE%D1%82%D0%BD%D0%BE%D1%81%D1%82%D1%8C
  private static final Map<Character, Double> RUSSIAN_FREQ = initRussianFreq();

  private static Map<Character, Double> initEnglishFreq() {
    Map<Character, Double> map = new HashMap<>();
    map.put('a', 8.2);
    map.put('b', 1.5);
    map.put('c', 2.8);
    map.put('d', 4.3);
    map.put('e', 12.7);
    map.put('f', 2.2);
    map.put('g', 2.0);
    map.put('h', 6.1);
    map.put('i', 7.0);
    map.put('j', 0.15);
    map.put('k', 0.77);
    map.put('l', 4.0);
    map.put('m', 2.4);
    map.put('n', 6.7);
    map.put('o', 7.5);
    map.put('p', 1.9);
    map.put('q', 0.095);
    map.put('r', 6.0);
    map.put('s', 6.3);
    map.put('t', 9.1);
    map.put('u', 2.8);
    map.put('v', 0.98);
    map.put('w', 2.4);
    map.put('x', 0.15);
    map.put('y', 2.0);
    map.put('z', 0.074);
    return map;
  }

  private static Map<Character, Double> initRussianFreq() {
    Map<Character, Double> map = new HashMap<>();
    map.put('а', 8.01);
    map.put('б', 1.59);
    map.put('в', 4.54);
    map.put('г', 1.70);
    map.put('д', 2.98);
    map.put('е', 8.45);
    map.put('ё', 0.04);
    map.put('ж', 0.94);
    map.put('з', 1.65);
    map.put('и', 7.35);
    map.put('й', 1.21);
    map.put('к', 3.49);
    map.put('л', 4.40);
    map.put('м', 3.21);
    map.put('н', 6.70);
    map.put('о', 10.97);
    map.put('п', 2.81);
    map.put('р', 4.73);
    map.put('с', 5.47);
    map.put('т', 6.26);
    map.put('у', 2.62);
    map.put('ф', 0.26);
    map.put('х', 0.97);
    map.put('ц', 0.48);
    map.put('ч', 1.44);
    map.put('ш', 0.73);
    map.put('щ', 0.36);
    map.put('ъ', 0.04);
    map.put('ы', 1.90);
    map.put('ь', 1.74);
    map.put('э', 0.32);
    map.put('ю', 0.64);
    map.put('я', 2.01);
    return map;
  }
}
