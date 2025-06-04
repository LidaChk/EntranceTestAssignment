package assessment.cipher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import assessment.utils.FileIO;

public class CipherBreaker extends Cipher {

    // Penalty coefficient for vowel deviation in frequency score
    private static final int VOWEL_PENALTY = 5;

    // Minimum number of letters required for adequate frequency analysis
    private static final int ADEQUATE_MIN_LETTER_COUNT = 15;

    private static final LanguageProfile ENGLISH_PROFILE = LanguageProfile.createEnglishProfile();
    private static final LanguageProfile RUSSIAN_PROFILE = LanguageProfile.createRussianProfile();

    private record CandidateScore(String candidate, double score) {
    }

    private record CountResult(Map<Character, Integer> countMap, int totalLetters) {
    }

    private List<CandidateScore> candidates;

    public void processFile(String inputFilePath, String outputFilePath) {

        try {
            String cipherText = FileIO.readFromFile(inputFilePath);
            String decryptedText = breakCaesar(cipherText);
            FileIO.writeToFile(decryptedText, outputFilePath);
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    public String breakCaesar(String cipherText) {
        if (!isInputAdequate(cipherText)) {
            System.out.println("[Warning] The input is too short or contains too few letters.");
            System.out.println("Frequency analysis may not work correctly and result could be incorrect.");
        }

        if (!mightBeMeaningfulText(cipherText)) {
            System.out.println("[Note] The input seems to be random or meaningless text.");
            System.out.println("The result of breaking the cipher might also be incorrect.");
        }
        candidates = new ArrayList<>();

        LanguageProfile profile = selectLanguageProfile(cipherText);
        generateCandidatesWithScore(cipherText, profile);

        return findBestCandidate(cipherText);
    }

    private LanguageProfile selectLanguageProfile(String text) {
        int russianChars = 0;
        int englishChars = 0;

        for (char c : text.toLowerCase().toCharArray()) {
            if (isRussian(c)) {
                russianChars++;
            } else if (isEnglish(c)) {
                englishChars++;
            }
        }

        return russianChars > englishChars ? RUSSIAN_PROFILE : ENGLISH_PROFILE;
    }

    private void generateCandidatesWithScore(String cipherText, LanguageProfile profile) {
        int alphabetSize = profile.getAlphabetSize();

        for (int shift = 0; shift < alphabetSize; shift++) {
            String decrypted = decrypt(cipherText, shift);
            double score = calculateFrequencyScore(decrypted, profile);
            candidates.add(new CandidateScore(decrypted, score));
        }
    }

    private double calculateFrequencyScore(String text, LanguageProfile profile) {
        Map<Character, Double> expectedFreq = profile.getFrequencyMap();
        Set<Character> vowels = profile.getVowels();
        double expectedVowelPercent = profile.getExpectedVowelPercent();

        CountResult countResult = getTotalLetterCount(text, expectedFreq);
        Map<Character, Integer> actualLettersCount = countResult.countMap;
        int totalLetters = countResult.totalLetters;

        if (actualLettersCount.isEmpty())
            return Double.NEGATIVE_INFINITY;

        Map<Character, Double> actualFreq = normalizeFrequencies(actualLettersCount, totalLetters);
        double score = getScore(actualFreq, expectedFreq);

        double vowelPercent = calculateVowelPercentage(text, vowels);
        double vowelDiff = Math.abs(vowelPercent - expectedVowelPercent);
        double vowelScore = -vowelDiff * VOWEL_PENALTY;

        return score + vowelScore;
    }

    private String findBestCandidate(String cipherText) {
        return candidates.stream()
                .max(Comparator.comparingDouble(CandidateScore::score))
                .map(CandidateScore::candidate)
                .orElse(cipherText);
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

        return -score;
    }

    private double calculateVowelPercentage(String text, Set<Character> vowelsSet) {
        int total = 0;
        int vowels = 0;

        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                total++;
                if (vowelsSet.contains(c)) {
                    vowels++;
                }
            }
        }

        return total > 0 ? ((double) vowels / total) * 100 : 0;
    }

    private boolean isInputAdequate(String text) {
        if (text == null || text.trim().isEmpty())
            return false;

        int letterCount = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                letterCount++;
            }
        }
        return letterCount >= ADEQUATE_MIN_LETTER_COUNT;
    }

    private boolean mightBeMeaningfulText(String text) {

        LanguageProfile profile = selectLanguageProfile(text);
        Set<Character> vowels = profile.getVowels();
        double expectedVowelPercent = profile.getExpectedVowelPercent();

        if (text.trim().isEmpty())
            return false;

        int spaceCount = 0;
        int totalLetters = 0;
        int vowelCount = 0;

        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c)) {
                totalLetters++;
                if (vowels.contains(c)) {
                    vowelCount++;
                }
            } else if (c == ' ') {
                spaceCount++;
            }
        }

        if (totalLetters == 0)
            return false;

        double vowelRatio = ((double) vowelCount / totalLetters) * 100;
        boolean hasEnoughVowels = vowelRatio >= expectedVowelPercent - 10 &&
                vowelRatio <= expectedVowelPercent + 10;
        boolean hasStructure = spaceCount > 0 || totalLetters > 20;

        return hasEnoughVowels && hasStructure;
    }

}
