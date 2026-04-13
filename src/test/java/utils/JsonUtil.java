package utils;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {

    public static String get(String key) {
        try {
            String content = new String(
                    Files.readAllBytes(Paths.get("src/test/resources/testdata/login.json"))
            );
            JSONObject obj = new JSONObject(content);
            return obj.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}