package com.example.restaurantapp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LocaleHelper {

    private static final String SELECTED_LANG = "app_lang";

    public static Context setLocale(Context context, String language) {
        persistLanguage(context, language);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        } else {
            return updateResourcesLegacy(context, language);
        }
    }

    private static void persistLanguage(Context context, String language) {
        PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext())
                .edit()
                .putString(SELECTED_LANG, language)
                .apply();
    }

    public static String getSavedLanguage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext())
                .getString(SELECTED_LANG, "ar");
    }

    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        config.setLayoutDirection(locale);
        return context.createConfigurationContext(config);
    }

    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources res = context.getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(locale);
        }
        res.updateConfiguration(config, res.getDisplayMetrics());
        return context;
    }
}
