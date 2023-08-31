package com.onrender.themba.discovercareers.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class SlugUtil {
    public static String createSlug(String title){
        String normalizer = Normalizer.normalize(title, Normalizer.Form.NFC);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        normalizer = pattern.matcher(normalizer).replaceAll("");

        normalizer = normalizer.toLowerCase();
        normalizer = normalizer.replaceAll(" ", "-");

        normalizer = normalizer.replaceAll("[^a-z0-9-]", "");
        return normalizer;
    }
}
