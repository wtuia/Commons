package java_lang.system;

import java.util.Map;

public class Env {

    public static void main(String[] args) {
        Map<String, String> envs = System.getenv();
        for (Map.Entry<String, String> entry: envs.entrySet()) {
            System.out.printf("%s = %s%n", entry.getKey(), entry.getValue());
        }
    }
}
