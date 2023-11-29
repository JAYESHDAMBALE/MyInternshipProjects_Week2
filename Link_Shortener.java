package motion_cut;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Link_Shortener {
    private static final String BASE_URL = "http://short.link/";
    private static final int SHORT_LINK_LENGTH = 6;
    
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;

    public Link_Shortener() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
    }

    public String shortenLink(String longUrl) {
        if (longToShortMap.containsKey(longUrl)) {
            return BASE_URL + longToShortMap.get(longUrl);
        }

        String shortKey = generateShortKey();
        shortToLongMap.put(shortKey, longUrl);
        longToShortMap.put(longUrl, shortKey);

        return BASE_URL + shortKey;
    }

    public String expandLink(String shortUrl) {
        String shortKey = shortUrl.substring(BASE_URL.length());
        return shortToLongMap.getOrDefault(shortKey, "Link not found");
    }

    private String generateShortKey() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortKey = new StringBuilder();

        for (int i = 0; i < SHORT_LINK_LENGTH; i++) {
            int index = new Random().nextInt(characters.length());
            shortKey.append(characters.charAt(index));
        }

        return shortKey.toString();
    }

    public static void main(String[] args) {
    	Link_Shortener linkShortener = new Link_Shortener();

        // Test the link shortener
        String longUrl = "https://www.example.com";
        String shortUrl = linkShortener.shortenLink(longUrl);

        System.out.println("Shortened URL: " + shortUrl);
        System.out.println("Expanded URL: " + linkShortener.expandLink(shortUrl));
    }
}
