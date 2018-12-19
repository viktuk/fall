package xyz.viktuk.fall;

import java.util.HashMap;
import java.util.Map;

public class Config {
    private final static Map<String, String> settings = new HashMap<>();
    static {
        settings.put("HttpVersion", "HTTP/1.1");
        settings.put("ServerName", "Fall/0.1");
    }

    public static Map<String, String> getSettings() {
        return settings;
    }

    public static String getHttpVersion(){
        return settings.get("HttpVersion");
    }

    public static String getServerName(){
        return settings.get("ServerName");
    }
}
