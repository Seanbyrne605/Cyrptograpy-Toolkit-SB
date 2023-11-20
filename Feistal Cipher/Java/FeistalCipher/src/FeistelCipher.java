import java.util.ArrayList;

public class FeistelCipher {

    private int initialKey;
    private String functionOperator;
    private int totalRound;
    private ArrayList<String> keys = new ArrayList<>();

    FeistelCipher(int round) {
        this.totalRound = round;

        keys.add("1110");
        keys.add("0100");
        keys.add("1101");
        keys.add("0001");
        keys.add("0010");
        keys.add("1111");
        keys.add("1011");
        keys.add("1000");
        keys.add("0011");
        keys.add("1010");
        keys.add("0110");
        keys.add("1100");
        keys.add("0101");
        keys.add("1001");
        keys.add("0000");
        keys.add("0111");
    }

    public void setInitialKey(int initialKey) {
        this.initialKey = initialKey;
    }

    public void setFunctionOperator(String functionOperator) {
        this.functionOperator = functionOperator;
    }

    public String encrypt(String message) {
        int messageMid = message.length() / 2;
        String left = message.substring(0, messageMid);
        String right = message.substring(messageMid);
    
        // Ensure that both left and right have the same length
        int maxLength = Math.max(left.length(), right.length());
        left = zeroPad(left, maxLength);
        right = zeroPad(right, maxLength);
    
        for (int roundIndex = 0; roundIndex < totalRound; roundIndex++) {
            String temp = right;
            String functionText = function(right, roundIndex);
            right = XOR(left, functionText);
            left = temp;
        }
        return left + right;
    }
    
    public String decrypt(String encryptedMessage) {
        int messageMid = encryptedMessage.length() / 2;
        String left = encryptedMessage.substring(0, messageMid);
        String right = encryptedMessage.substring(messageMid);
    
        // Ensure that both left and right have the same length
        int maxLength = Math.max(left.length(), right.length());
        left = zeroPad(left, maxLength);
        right = zeroPad(right, maxLength);
    
        for (int roundIndex = 0; roundIndex < totalRound; roundIndex++) {
            String temp = left;
            String functionText = function(left, roundIndex);
            left = XOR(right, functionText);
            right = temp;
        }
        return left + right;
    }
    
  
    // Helper method to zero-pad a string to a specified length
    private String zeroPad(String str, int length) {
        while (str.length() < length) {
            str = "0" + str;
        }
        return str;
    }
    

    private String function(String right, int roundIndex) {
        String currentKey = getSubKey(roundIndex);
        String encryptedText = "";

        switch (functionOperator) {
            case "AND":
                encryptedText = AND(right, currentKey);
                break;
            case "OR":
                encryptedText = OR(right, currentKey);
                break;
            case "XOR":
                encryptedText = XOR(right, currentKey);
                break;
        }
        return encryptedText;
    }

    private String getSubKey(int roundIndex) {
        int x = (initialKey + roundIndex) % 16;
        return keys.get(x);
    }

    private String AND(String left, String right) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < left.length(); i++) {
            stringBuilder.append((left.charAt(i) - '0') & (right.charAt(i) - '0'));
        }
        return stringBuilder.toString();
    }

    private String OR(String left, String right) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < left.length(); i++) {
            stringBuilder.append((left.charAt(i) - '0') | (right.charAt(i) - '0'));
        }
        return stringBuilder.toString();
    }

    private String XOR(String left, String right) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < left.length(); i++) {
            stringBuilder.append((left.charAt(i) - '0') ^ (right.charAt(i) - '0'));
        }
        return stringBuilder.toString();
    }
}
