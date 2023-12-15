import java.util.HashMap;
import java.util.Map;

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

        // Decrypt the ciphertext
        String decryptedText = decryptSuperencryption(ciphertext, codebook);

        System.out.println("Decrypted Text: " + decryptedText);
    }

    private static String decryptSuperencryption(String ciphertext, Map<String, String> codebook) {
        // Initialize a StringBuilder to store the decrypted text
        StringBuilder decryptedText = new StringBuilder();

        // Iterate through the ciphertext in steps of the length of the letter sequence
        for (int i = 0; i < ciphertext.length(); i += 3) {
            // Extract a three-letter sequence from the ciphertext
            String letterSequence = ciphertext.substring(i, i + 3);

            // Check if the letter sequence is in the codebook
            if (codebook.containsKey(letterSequence)) {
                // If yes, append the corresponding word from the codebook to the decrypted text
                decryptedText.append(codebook.get(letterSequence));
            } else {
                // If not, leave the letter sequence unchanged
                decryptedText.append(letterSequence);
            }
        }

        // Convert StringBuilder to String and return the decrypted text
        return decryptedText.toString();
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
}
