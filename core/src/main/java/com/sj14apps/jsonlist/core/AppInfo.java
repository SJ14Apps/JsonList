package com.sj14apps.jsonlist.core;

public class AppInfo {
    String name;
    String package_name;
    String version;
    int version_code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVersion_code() {
        return version_code;
    }

    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "name='" + name + '\'' +
                ", package_name='" + package_name + '\'' +
                ", version='" + version + '\'' +
                ", version_code=" + version_code +
                '}';
    }
}
