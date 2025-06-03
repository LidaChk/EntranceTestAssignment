package assessment.cipher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CipherBreaker extends Cipher {

  // source https://en.wikipedia.org/wiki/Letter_frequency
  private static final Map<Character, Double> ENGLISH_FREQ = initEnglishFreq();
  // source
  // https://ru.wikipedia.org/wiki/%D0%A7%D0%B0%D1%81%D1%82%D0%BE%D1%82%D0%BD%D0%BE%D1%81%D1%82%D1%8C
  private static final Map<Character, Double> RUSSIAN_FREQ = initRussianFreq();

  private record CandidateScore(String candidate, double score) {
  }

  private List<CandidateScore> candidates;

  public String breakCaesar(String cipherText) {

    candidates = new ArrayList<>();

    if (isRussianDominatedInText(cipherText)) {
      generateCandidatesWithScore(cipherText, RUSSIAN_ALPHABET_SIZE, RUSSIAN_FREQ);
    } else {
      generateCandidatesWithScore(cipherText, ENGLISH_ALPHABET_SIZE, ENGLISH_FREQ);
    }

    return findBestCandidate(cipherText);
  }

  private void generateCandidatesWithScore(String cipherText, int alphabetSize, Map<Character, Double> freq) {
    for (int shift = 0; shift < alphabetSize; shift++) {
      String decrypted = decrypt(cipherText, shift);
      candidates.add(new CandidateScore(decrypted, calculateFrequencyScore(decrypted, freq)));
    }
  }

  private String findBestCandidate(String cipherText) {
    return candidates.stream()
        .max(Comparator.comparingDouble(candidateScore -> candidateScore.score))
        .map(bestCandidateScore -> bestCandidateScore.candidate)
        .orElse(cipherText);
  }

  private boolean isRussianDominatedInText(String text) {
    int russianChars = 0;
    int englishChars = 0;

    for (char c : text.toLowerCase().toCharArray()) {
      if (isRussian(c)) {
        russianChars++;
      } else if (isEnglish(c)) {
        englishChars++;
      }
    }

    return russianChars > englishChars;
  }

  private record CountResult(Map<Character, Integer> countMap, int totalLetters) {
  }

  private double calculateFrequencyScore(String text, Map<Character, Double> expectedFreq) {

    CountResult countResult = getTotalLetterCount(text, expectedFreq);

    Map<Character, Integer> actualLettersCount = countResult.countMap;
    int totalLetters = countResult.totalLetters;

    if (actualLettersCount.isEmpty())
      return Double.NEGATIVE_INFINITY;

    Map<Character, Double> actualFreq = normalizeFrequencies(actualLettersCount, totalLetters);

    return getScore(actualFreq, expectedFreq);
  }

  private CountResult getTotalLetterCount(String text, Map<Character, Double> expectedFreq) {
    Map<Character, Integer> actualLettersCount = new HashMap<>();
    int totalLetters = 0;

    for (char c : text.toLowerCase().toCharArray()) {
      if (expectedFreq.containsKey(c)) {
        actualLettersCount.put(c, actualLettersCount.getOrDefault(c, 0) + 1);
        totalLetters++;
      }
    }

    return new CountResult(actualLettersCount, totalLetters);
  }

  private Map<Character, Double> normalizeFrequencies(Map<Character, Integer> actualLettersCount, int totalLetters) {
    Map<Character, Double> normalized = new HashMap<>();

    for (Map.Entry<Character, Integer> entry : actualLettersCount.entrySet()) {
      double frequencyPercent = (entry.getValue() / (double) totalLetters) * 100;
      normalized.put(entry.getKey(), frequencyPercent);
    }

    return normalized;
  }

  private double getScore(Map<Character, Double> actualFreq, Map<Character, Double> expectedFreq) {
    double score = 0.0;
    for (Map.Entry<Character, Double> entry : expectedFreq.entrySet()) {
      char letter = entry.getKey();
      double expected = entry.getValue();
      double actual = actualFreq.getOrDefault(letter, 0.0);
      score += Math.pow(expected - actual, 2) / expected;
    }

    return score;
  }

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
