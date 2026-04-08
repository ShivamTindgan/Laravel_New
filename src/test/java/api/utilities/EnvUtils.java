package api.utilities;

public class EnvUtils {

    public static String get(String key) {
        String value = System.getenv(key);

        if (value == null) {
            throw new RuntimeException("Environment variable not set: " + key);
        }

        return value;
    }

    public static String getOptional(String key) {
        return System.getenv(key);
    }

    public static String getFirstSet(String... keys) {
        if (keys == null || keys.length == 0) {
            throw new IllegalArgumentException("No keys provided");
        }

        for (String k : keys) {
            if (k == null) continue;
            String v = System.getenv(k);
            if (v != null && !v.trim().isEmpty()) {
                return v;
            }
        }

        StringBuilder sb = new StringBuilder("None of these environment variables are set: ");
        for (int i = 0; i < keys.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(keys[i]);
        }
        throw new RuntimeException(sb.toString());
    }

    public static String requireNonBlank(String value, String name) {
        if (value == null || value.trim().isEmpty()) {
            throw new RuntimeException("Required value is blank: " + name);
        }
        return value;
    }
}
