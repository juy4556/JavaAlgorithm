import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Solution {
    public static void main(String[] args) {
        String value = "31eGpg6xdhzm8AZC0f3nfLNz1znYBsaMIr_bwzwOgtc7UEZY";
        String algorithm = "SHA-256";
        String codeChallenge = convertToCodeChallenge(value, algorithm);
        System.out.println(codeChallenge);
    }

    public static String convertToCodeChallenge(String codeVerifier, String codeChallengeMethod) {
        byte[] digest = getDigest(codeVerifier.getBytes(StandardCharsets.UTF_8), codeChallengeMethod);
        System.out.println(digest.toString());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
    }

    private static byte[] getDigest(byte[] input, String algorithm) {
        System.out.println("input: " + input);
        System.out.println("algorithm: " + algorithm);
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            return md.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}