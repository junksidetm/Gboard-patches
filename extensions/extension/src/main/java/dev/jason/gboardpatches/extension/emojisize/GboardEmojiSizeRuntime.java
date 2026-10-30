package dev.jason.gboardpatches.extension.emojisize;

import android.content.SharedPreferences;

import dev.jason.gboardpatches.extension.flagsettings.GboardFlagRuntimeContext;

public final class GboardEmojiSizeRuntime {
    private GboardEmojiSizeRuntime() {
    }

    public static Object applyOverriddenFlagValue(String flagName, Object stockResult) {
        try {
            if (!GboardEmojiSizePolicy.EMOJI_SCALE_SUPPORTED_FLAG.equals(flagName)
                    || !Boolean.FALSE.equals(stockResult)) {
                return stockResult;
            }
            SharedPreferences preferences = GboardFlagRuntimeContext.preferencesOrNull();
            boolean enabled = GboardCursorTrackpadSettings.readEnabled(preferences);
            return GboardCursorTrackpad1803Policy.maybeForceFlag(flagName, stockResult, enabled);
        } catch (Throwable ignored) {
            if (GboardCursorTrackpad1803Policy.isCursorTrackpadFlag(flagName)
                    && GboardCursorTrackpadSettings.isCachedEnabled()) {
                return Boolean.TRUE;
            }
            return stockResult;
        }
    }

    static Object applyOverriddenFlagValue(
            String flagName,
            Object stockResult,
            SharedPreferences preferences) {
        return GboardEmojiSizePolicy.maybeForce(
                flagName,
                stockResult,
                GboardEmojiSizeSettings.readEnabled(preferences));
    }
}
