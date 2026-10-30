package dev.jason.gboardpatches.extension.swipedismiss;

import android.content.Context;
import android.content.SharedPreferences;

import dev.jason.gboardpatches.extension.flagsettings.GboardBooleanFlagSettings;
import dev.jason.gboardpatches.extension.settings.GboardPatchesSettings;

public final class GboardSwipeDismissSettings {
    public static final String PREF_KEY_ENABLED = "pref_swipe_dismiss_enabled";
    public static final boolean DEFAULT_ENABLED = false;

    private GboardSwipeDismissSettings() {
    }

    public static boolean readEnabled(Context context) {
        return GboardPatchesSettings.readRuntimeSetting(context,
                GboardSwipeDismissSettings::readEnabled, DEFAULT_ENABLED);
    }

    public static Boolean readEnabledOrNull(SharedPreferences preferences) {
        return GboardBooleanFlagSettings.readEnabledOrNull(preferences, PREF_KEY_ENABLED);
    }

    public static boolean readEnabled(SharedPreferences preferences) {
        return GboardBooleanFlagSettings.readEnabled(
                preferences, PREF_KEY_ENABLED, DEFAULT_ENABLED);
    }

    public static void ensureDefault(SharedPreferences preferences) {
        GboardBooleanFlagSettings.ensureDefault(
                preferences, PREF_KEY_ENABLED, DEFAULT_ENABLED);
    }

    public static boolean writeEnabled(SharedPreferences preferences, boolean enabled) {
        return GboardBooleanFlagSettings.writeEnabled(
                preferences, PREF_KEY_ENABLED, enabled);
    }

    public static boolean writeEnabled(Context context, boolean enabled) {
        SharedPreferences preferences = GboardPatchesSettings.preferencesOrNull(context);
        if (preferences == null) return false;
        try {
            return preferences.edit().putBoolean(PREF_KEY_ENABLED, enabled).commit();
        } catch (RuntimeException unavailable) {
            return false;
        }
    }
}
