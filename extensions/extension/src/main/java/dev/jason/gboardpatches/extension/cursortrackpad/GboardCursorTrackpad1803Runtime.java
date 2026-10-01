package dev.jason.gboardpatches.extension.cursortrackpad;

import android.content.SharedPreferences;

import dev.jason.gboardpatches.extension.flagsettings.GboardFlagRuntimeContext;

public final class GboardCursorTrackpad1803Runtime {
    private GboardCursorTrackpad1803Runtime() {
    }

    public static Object applyOverriddenFlagValue(String flagName, Object stockResult) {
        try {
            if (!GboardCursorTrackpad1803Policy.isCursorTrackpadFlag(flagName)) {
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
        return GboardCursorTrackpad1803Policy.maybeForceFlag(
                flagName,
                stockResult,
                GboardCursorTrackpadSettings.readEnabled(preferences));
    }

}
