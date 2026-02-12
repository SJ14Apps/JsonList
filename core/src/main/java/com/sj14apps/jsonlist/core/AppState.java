package com.sj14apps.jsonlist.core;

public class AppState {
    public int settingsVersion;
    boolean hasNewCrash;
    boolean hasCrashLogs;
    boolean MIMEFilterDisabled;
    boolean syntaxHighlighting = true;
    boolean scrollAnimation = true;
    boolean autoCheckForUpdate = true;
    private int theme;
    long lastCheckForUpdate;
    boolean hasNewVersion;
    int newVersionCode = 0;
    String newVersionName = "1.0";

    public boolean hasNewCrash() {
        return hasNewCrash;
    }

    public void setHasNewCrash(boolean hasNewCrash) {
        this.hasNewCrash = hasNewCrash;
    }

    public boolean hasCrashLogs() {
        return hasCrashLogs;
    }

    public void setHasCrashLogs(boolean hasCrashLogs) {
        this.hasCrashLogs = hasCrashLogs;
    }

    public boolean isMIMEFilterDisabled() {
        return MIMEFilterDisabled;
    }

    public void setMIMEFilterDisabled(boolean MIMEFilterDisabled) {
        this.MIMEFilterDisabled = MIMEFilterDisabled;
    }

    public boolean isSyntaxHighlighting() {
        return syntaxHighlighting;
    }

    public void setSyntaxHighlighting(boolean syntaxHighlighting) {
        this.syntaxHighlighting = syntaxHighlighting;
    }

    public boolean isScrollAnimation() {
        return scrollAnimation;
    }

    public void setScrollAnimation(boolean scrollAnimation) {
        this.scrollAnimation = scrollAnimation;
    }

    public boolean isAutoCheckForUpdate() {
        return autoCheckForUpdate;
    }

    public void setAutoCheckForUpdate(boolean autoCheckForUpdate) {
        this.autoCheckForUpdate = autoCheckForUpdate;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public long getLastCheckForUpdate() {
        return lastCheckForUpdate;
    }

    public boolean isHasNewVersion() {
        return hasNewVersion;
    }

    public void setHasNewVersion(boolean hasNewVersion) {
        this.hasNewVersion = hasNewVersion;
    }

    public int getNewVersionCode() {
        return newVersionCode;
    }

    public void setNewVersionCode(int newVersionCode) {
        this.newVersionCode = newVersionCode;
    }

    public void setLastCheckForUpdate(long lastCheckForUpdate) {
        this.lastCheckForUpdate = lastCheckForUpdate;
    }

    public String getNewVersionName() {
        return newVersionName;
    }

    public void setNewVersionName(String newVersionName) {
        this.newVersionName = newVersionName;
    }

    @Override
    public String toString() {
        return "AppState{" +
                "settingsVersion=" + settingsVersion +
                ", hasNewCrash=" + hasNewCrash +
                ", hasCrashLogs=" + hasCrashLogs +
                ", MIMEFilterDisabled=" + MIMEFilterDisabled +
                ", syntaxHighlighting=" + syntaxHighlighting +
                ", scrollAnimation=" + scrollAnimation +
                ", autoCheckForUpdate=" + autoCheckForUpdate +
                ", theme=" + theme +
                ", lastCheckForUpdate=" + lastCheckForUpdate +
                ", hasNewVersion=" + hasNewVersion +
                ", newVersionCode=" + newVersionCode +
                ", newVersionName='" + newVersionName + '\'' +
                '}';
    }
}
