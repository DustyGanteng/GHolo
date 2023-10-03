package dev.geco.gholo.manager;

import java.util.*;

import org.bukkit.*;

import dev.geco.gholo.GHoloMain;

public class SVManager {

    private final GHoloMain GPM;

    private final String SERVER_VERSION;

    private final String PACKAGE_PATH;

    protected final HashMap<String, String> VERSION_MAPPING = new HashMap<>(); {

        VERSION_MAPPING.put("v1_18_1", "v1_18");
        VERSION_MAPPING.put("v1_19_2", "v1_19_1");
        VERSION_MAPPING.put("v1_20_1", "v1_20");
    }

    public SVManager(GHoloMain GPluginMain) {
        GPM = GPluginMain;
        String version = Bukkit.getServer().getBukkitVersion();
        SERVER_VERSION = version.substring(0, version.indexOf('-'));
        PACKAGE_PATH = GPM.getClass().getPackage().getName() + ".mcv." + getPackageVersion() + ".";
    }

    public String getPackageVersion() {
        String package_version = "v" + SERVER_VERSION.replace(".", "_");
        return VERSION_MAPPING.getOrDefault(package_version, package_version);
    }

    public boolean isNewerOrVersion(int Version, int SubVersion) {
        String[] version = SERVER_VERSION.split("\\.");
        return Integer.parseInt(version[1]) > Version || (Integer.parseInt(version[1]) == Version && (version.length > 2 ? Integer.parseInt(version[2]) >= SubVersion : SubVersion == 0));
    }

    public boolean isVersion(int Version, int SubVersion) {
        String[] version = SERVER_VERSION.split("\\.");
        return version.length > 2 ? Integer.parseInt(version[1]) == Version && Integer.parseInt(version[2]) == SubVersion : Integer.parseInt(version[1]) == Version && SubVersion == 0;
    }

    public Object getPackageObject(String ClassName, Object Object) {
        try {
            Class<?> mcvClass = Class.forName(PACKAGE_PATH + ClassName);
            return Object == null ? mcvClass.getConstructor().newInstance() : mcvClass.getConstructor(Object.getClass()).newInstance(Object);
        } catch (Throwable e) { e.printStackTrace(); }
        return null;
    }

    public boolean hasPackageClass(String ClassName) {
        try {
            Class.forName(PACKAGE_PATH + ClassName);
            return true;
        } catch (Throwable ignored) { }
        return false;
    }

}