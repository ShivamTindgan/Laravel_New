package api.utilities;

public class EnvUtils {

    public static String get(String key) {
        String value = System.getenv(key);

        if (value == null) {
            throw new RuntimeException("Environment variable not set: " + key);
        }

        return value;
    }
}
