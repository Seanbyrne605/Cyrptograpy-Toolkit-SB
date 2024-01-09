import java.util.*;

public class SuperencryptionDecryption {

    public static void main(String[] args) {
        // Your ciphertext
        String ciphertext = "NZEIYVKYFPGJCHESYUWUOPGJBTABRPRYYJNS";

        // Your codebook (substitution map)
        Map<String, String> codebook = new HashMap<>();
        // Populate the codebook based on your specific superencryption cipher
        codebook.put("AIT", "LIKE");
        codebook.put("BTJ", "POLICE");
        codebook.put("CXP", "I");
        codebook.put("EJW", "IN");
        codebook.put("HZJ", "SUFFERING");
        codebook.put("ICV", "HAVE");
        codebook.put("INB", "AND");
        codebook.put("INI", "CRYING");
        codebook.put("JPT", "REPUBLIC");
        codebook.put("KHJ", "THE");
        codebook.put("KLJ", "GO");
        codebook.put("LDA", "SEA");
        codebook.put("LER", "EVERYWHERE");
        codebook.put("LXD", "ISLE");
        codebook.put("MED", "BLUE");
        codebook.put("NCF", "FOUND");
        codebook.put("NZD", "SCREAMING");
        codebook.put("PBO", "YOU");
        codebook.put("QCC", "BANANA");
        codebook.put("QGW", "PRIESTS");
        codebook.put("QOH", "SEE");
        codebook.put("TCA", "HIDDEN");
        codebook.put("UJP", "IT");
        codebook.put("VVL", "UNIFORMS");
        codebook.put("WJZ", "BLACK");
        codebook.put("WQK", "SONG");
        codebook.put("WRU", "RATS");
        codebook.put("YMZ", "SOUNDS");
        codebook.put("YRM", "SEPTIC");
        codebook.put("YXO", "SECRET");
        codebook.put("ZAE", "WITHIN");

        // Your frequent words and their corresponding mappings
        Map<String, String> frequentWordsMap = new HashMap<>();
        frequentWordsMap.put("NZE", "YOU");
        frequentWordsMap.put("PGJ", "THE");
        frequentWordsMap.put("WUO", "WITHIN");
        frequentWordsMap.put("IYV", "HAVE");

        // Update the codebook with frequent words
        updateCodebookWithFrequentWords(codebook, frequentWordsMap);

        // Decrypt the ciphertext and get decipherments with confidence scores
        List<Decipherment> decipherments = decryptSuperencryption(ciphertext, codebook);

        // Sort decipherments by confidence (in descending order)
        decipherments.sort(Comparator.comparing(Decipherment::getConfidence).reversed());

        // Print the sorted decipherments
        for (Decipherment decipherment : decipherments) {
            if( decipherment.getConfidence() == 100)
            System.out.println("Decrypted Text: " + decipherment.getText() + ", Confidence: " + decipherment.getConfidence());
        }
    }

    private static List<Decipherment> decryptSuperencryption(String ciphertext, Map<String, String> codebook) {
        List<Decipherment> decipherments = new ArrayList<>();
        generatePermutations(ciphertext, codebook, "", decipherments);
        return decipherments;
    }

    private static void generatePermutations(String remaining, Map<String, String> codebook, String currentPermutation, List<Decipherment> decipherments) {
        if (remaining.isEmpty()) {
            // Calculate confidence based on the frequency of words in the codebook
            int confidence = calculateConfidence(currentPermutation, codebook);
            decipherments.add(new Decipherment(currentPermutation, confidence));
            return;
        }

        for (int i = 3; i <= remaining.length(); i += 3) {
            String letterSequence = remaining.substring(0, i);
            String partialSequence = remaining.substring(0, 3);

            if (codebook.containsKey(letterSequence)) {
                // If the letter sequence is in the codebook, append the corresponding word
                generatePermutations(remaining.substring(i), codebook, currentPermutation + codebook.get(letterSequence) + ":", decipherments);
            } else {
                // If not, try each word that starts or ends with the same letter
                List<String> alternativeWords = findAlternativeWords(codebook, partialSequence);
                for (String alternativeWord : alternativeWords) {
                    generatePermutations(remaining.substring(i), codebook, currentPermutation + alternativeWord + ":", decipherments);
                }
            }
        }
    }

    private static List<String> findAlternativeWords(Map<String, String> codebook, String partialSequence) {
        List<String> alternatives = new ArrayList<>();
        for (Map.Entry<String, String> entry : codebook.entrySet()) {
            String codebookWord = entry.getKey();
            if (codebookWord.startsWith(partialSequence.charAt(0) + "") || codebookWord.endsWith(partialSequence.charAt(2) + "")) {
                alternatives.add(codebook.get(codebookWord));
            }
        }
        return alternatives;
    }

    private static void updateCodebookWithFrequentWords(Map<String, String> codebook, Map<String, String> frequentWordsMap) {
        // Iterate through the frequent words map and update the codebook
        for (Map.Entry<String, String> entry : frequentWordsMap.entrySet()) {
            String letterSequence = entry.getKey();
            String correspondingWord = entry.getValue();

            // Update the codebook with the frequent word mapping
            codebook.put(letterSequence, correspondingWord);
        }
    }

    private static int calculateConfidence(String text, Map<String, String> codebook) {
        // Calculate confidence based on the frequency of words in the codebook
        int totalWords = 0;
        int matchingWords = 0;
        String[] words = text.split(":");
        for (String word : words) {
            if (codebook.containsValue(word)) {
                matchingWords++;
            }
            totalWords++;
        }
        return (totalWords == 0) ? 0 : (matchingWords * 100) / totalWords;
    }

    // Class to store decipherment text and confidence
    private static class Decipherment {
        private String text;
        private int confidence;

        public Decipherment(String text, int confidence) {
            this.text = text;
            this.confidence = confidence;
        }

        public String getText() {
            return text;
        }

        public int getConfidence() {
            return confidence;
        }
    }
}
