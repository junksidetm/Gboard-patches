package dev.jason.gboardpatches.extension.cursortrackpad;

import org.junit.Assert;
import org.junit.Test;

public final class GboardCursorTrackpad1803PolicyTest {
    @Test
    public void enabledForcesBothFlags() {
        Assert.assertEquals(Boolean.TRUE, GboardCursorTrackpad1803Policy.maybeForceFlag(
                "free_cursor", Boolean.FALSE, true));
        Assert.assertEquals(Boolean.TRUE, GboardCursorTrackpad1803Policy.maybeForceFlag(
                "free_cursor_lock_mode", Boolean.FALSE, true));
    }

    @Test
    public void disabledOrUnrelatedPreservesStock() {
        Assert.assertSame(Boolean.FALSE, GboardCursorTrackpad1803Policy.maybeForceFlag(
                "free_cursor", Boolean.FALSE, false));
        Assert.assertNull(GboardCursorTrackpad1803Policy.maybeForceFlag(
                "free_cursor", null, false));
        Assert.assertSame(Boolean.FALSE, GboardCursorTrackpad1803Policy.maybeForceFlag(
                "unrelated_flag", Boolean.FALSE, true));
        Assert.assertNull(GboardCursorTrackpad1803Policy.maybeForceFlag(
                "unrelated_flag", null, true));
    }

    @Test
    public void enabledForcesFlagsPermanentlyEvenWhenStockResultIsNull() {
        Assert.assertEquals(Boolean.TRUE, GboardCursorTrackpad1803Policy.maybeForceFlag(
                "free_cursor", null, true));
        Assert.assertEquals(Boolean.TRUE, GboardCursorTrackpad1803Policy.maybeForceFlag(
                "free_cursor_lock_mode", null, true));
    }

    @Test
    public void recognizesCursorTrackpadFlags() {
        Assert.assertTrue(GboardCursorTrackpad1803Policy.isCursorTrackpadFlag("free_cursor"));
        Assert.assertTrue(GboardCursorTrackpad1803Policy.isCursorTrackpadFlag("free_cursor_lock_mode"));
        Assert.assertFalse(GboardCursorTrackpad1803Policy.isCursorTrackpadFlag("unrelated_flag"));
    }
}
