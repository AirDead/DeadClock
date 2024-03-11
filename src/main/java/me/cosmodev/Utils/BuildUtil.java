package me.cosmodev.Utils;

import me.cosmodev.Plugin;
import org.bukkit.Location;
import org.bukkit.Material;

public class BuildUtil {

    public static void displayTimeWithBlocks(Location location, int hours, int minutes) {
        clearLocation(location);

        boolean use12HourFormat = Plugin.getInstance().getConfig().getBoolean("use12HourFormat", false);
        int size = Plugin.getInstance().getConfig().getInt("size", 1);

        if (use12HourFormat) {
            if (hours == 0) {
                hours = 12; // 0 = 12 aka полночь
            } else if (hours > 12) {
                hours -= 12; // 12-часовой формат
            }
        }

        displayDigit(location.clone().add(0, 0, 0), hours / 10, size);
        displayDigit(location.clone().add(4 * size, 0, 0), hours % 10, size);
        displayColon(location.clone().add(8 * size, -1 * size, 0), size);
        displayDigit(location.clone().add(10 * size, 0, 0), minutes / 10, size);
        displayDigit(location.clone().add(14 * size, 0, 0), minutes % 10, size);
    }

    private static void displayDigit(Location location, int digit, int size) {
        Material blockMaterial = Material.valueOf(Plugin.getInstance().getConfig().getString("numMaterial", "BEDROCK").toUpperCase());
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

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                if (digitShape[i][j] == 1) {
                    location.clone().add(j * size, -i * size, 0).getBlock().setType(blockMaterial);
                }
            }
        }
    }

    private static void displayColon(Location location, int digitSize) {
        Material blockMaterial = Material.valueOf(Plugin.getInstance().getConfig().getString("pointMaterial", "BEDROCK").toUpperCase());
        location.clone().add(0, 0, 0).getBlock().setType(blockMaterial);
        location.clone().add(0, -2 * digitSize, 0).getBlock().setType(blockMaterial);
    }

    private static void clearLocation(Location location) {
        int size = Plugin.getInstance().getConfig().getInt("size", 1);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 15; j++) {
                location.clone().add(j * size, -i * size, 0).getBlock().setType(Material.AIR);
            }
        }
    }
}
