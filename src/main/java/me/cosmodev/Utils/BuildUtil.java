package me.cosmodev.Utils;

import me.cosmodev.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class BuildUtil {

    private static final Map<Location, List<Location>> clockLocationsMap = new HashMap<>();

    public static void startClockUpdater(Location clockLocation) {
        List<Location> clockLocations = new ArrayList<>();
        clockLocationsMap.put(clockLocation, clockLocations);

        Bukkit.getScheduler().runTaskTimer(Plugin.getInstance(), () -> {
            clearClock(clockLocations, clockLocation);
        }, 0, 1200);
    }

    private static int getHourForRegion(long currentTime, String region) {
        TimeZone timeZone = TimeZone.getTimeZone(region);
        long offsetInMillis = timeZone.getOffset(currentTime);
        return (int) ((currentTime + offsetInMillis) / (1000 * 60 * 60) % 24);
    }

    private static void clearClock(List<Location> clockLocations, Location clockLocation) {
        for (Location location : clockLocations) {
            location.getBlock().setType(Material.AIR);
        }
        clockLocations.clear();

        long currentTime = System.currentTimeMillis();
        String region = Plugin.getInstance().getConfig().getString("region", "Europe/Moscow");
        Material numMaterial = getMaterialFromConfig("numMaterial", Material.BEDROCK);
        Material pointMaterial = getMaterialFromConfig("pointMaterial", Material.BEDROCK);
        boolean use12HourFormat = Plugin.getInstance().getConfig().getBoolean("use12HourFormat", false);
        int size = Plugin.getInstance().getConfig().getInt("size", 1);

        int hours = getHourForRegion(currentTime, region);
        int minutes = (int) ((currentTime / (1000 * 60)) % 60);

        displayTimeWithBlocks(clockLocation, clockLocations, hours, minutes, numMaterial, pointMaterial, use12HourFormat, size);
    }

    private static Material getMaterialFromConfig(String key, Material defaultValue) {
        try {
            return Material.valueOf(Plugin.getInstance().getConfig().getString(key, defaultValue.toString()).toUpperCase());
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public static void displayTimeWithBlocks(Location clockLocation, List<Location> clockLocations, int hours, int minutes, Material numMaterial, Material pointMaterial, boolean use12HourFormat, int size) {
        if (use12HourFormat) {
            if (hours == 0) {
                hours = 12;
            } else if (hours > 12) {
                hours -= 12;
            }
        }

        displayDigit(clockLocation.clone().add(0, 0, 0), clockLocations, hours / 10, numMaterial, size);
        displayDigit(clockLocation.clone().add(4 * size, 0, 0), clockLocations, hours % 10, numMaterial, size);
        displayColon(clockLocation.clone().add(8 * size, -1 * size, 0), clockLocations, pointMaterial, size);
        displayDigit(clockLocation.clone().add(10 * size, 0, 0), clockLocations, minutes / 10, numMaterial, size);
        displayDigit(clockLocation.clone().add(14 * size, 0, 0), clockLocations, minutes % 10, numMaterial, size);
    }

    private static void displayDigit(Location location, List<Location> clockLocations, int digit, Material numMaterial, int size) {
        int[][] digitShape = getDigitShape(digit);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (digitShape[i][j] == 1) {
                    Location blockLocation = location.clone().add(j * size, -i * size, 0);
                    blockLocation.getBlock().setType(numMaterial);
                    clockLocations.add(blockLocation);
                }
            }
        }
    }

    private static void displayColon(Location location, List<Location> clockLocations, Material pointMaterial, int size) {
        Location blockLocation1 = location.clone().add(0, 0, 0);
        blockLocation1.getBlock().setType(pointMaterial);
        clockLocations.add(blockLocation1);

        Location blockLocation2 = location.clone().add(0, -2 * size, 0);
        blockLocation2.getBlock().setType(pointMaterial);
        clockLocations.add(blockLocation2);
    }

    private static int[][] getDigitShape(int digit) {
        int[][] digitShape = new int[5][3];

        switch (digit) {
            case 0:
                digitShape = new int[][]{
                        {1, 1, 1},
                        {1, 0, 1},
                        {1, 0, 1},
                        {1, 0, 1},
                        {1, 1, 1}
                };
                break;
            case 1:
                digitShape = new int[][]{
                        {0, 1, 0},
                        {1, 1, 0},
                        {0, 1, 0},
                        {0, 1, 0},
                        {1, 1, 1}};
                break;
            case 2:
                digitShape = new int[][]{
                        {1, 1, 1},
                        {0, 0, 1},
                        {1, 1, 1},
                        {1, 0, 0},
                        {1, 1, 1}
                };
                break;
            case 3:
                digitShape = new int[][]{
                        {1, 1, 1},
                        {0, 0, 1},
                        {0, 1, 1},
                        {0, 0, 1},
                        {1, 1, 1}
                };
                break;
            case 4:
                digitShape = new int[][]{
                        {1, 0, 1},
                        {1, 0, 1},
                        {1, 1, 1},
                        {0, 0, 1},
                        {0, 0, 1}
                };
                break;
            case 5:
                digitShape = new int[][]{
                        {1, 1, 1},
                        {1, 0, 0},
                        {1, 1, 1},
                        {0, 0, 1},
                        {1, 1, 1}
                };
                break;
            case 6:
                digitShape = new int[][]{
                        {1, 1, 1},
                        {1, 0, 0},
                        {1, 1, 1},
                        {1, 0, 1},
                        {1, 1, 1}
                };
                break;
            case 7:
                digitShape = new int[][]{
                        {1, 1, 1},
                        {0, 0, 1},
                        {0, 0, 1},
                        {0, 0, 1},
                        {0, 0, 1}
                };
                break;
            case 8:
                digitShape = new int[][]{
                        {1, 1, 1},
                        {1, 0, 1},
                        {1, 1, 1},
                        {1, 0, 1},
                        {1, 1, 1}
                };
                break;
            case 9:
                digitShape = new int[][]{
                        {1, 1, 1},
                        {1, 0, 1},
                        {1, 1, 1},
                        {0, 0, 1},
                        {1, 1, 1}
                };
                break;
        }

        return digitShape;
    }
}
