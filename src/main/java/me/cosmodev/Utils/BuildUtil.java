package me.cosmodev.Utils;

import me.cosmodev.Plugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

public class BuildUtil {

    public static void displayTimeWithBlocks(Location location, int hours, int minutes) {
        boolean use12HourFormat = Plugin.getInstance().getConfig().getBoolean("use12HourFormat", false);

        if (use12HourFormat) {
            if (hours == 0) {
                hours = 12;
            } else if (hours > 12) {
                hours -= 12;
            }
        }
        displayDigit(location.clone().add(0, 0, 0), hours / 10);
        displayDigit(location.clone().add(4, 0, 0), hours % 10);
        displayColon(location.clone().add(8, 0, 0));
        displayDigit(location.clone().add(10, 0, 0), minutes / 10);
        displayDigit(location.clone().add(14, 0, 0), minutes % 10);
    }

    private static void displayDigit(Location location, int digit) {
        Material blockMaterial = Material.valueOf(Plugin.getInstance().getConfig().getString("numMaterial", "BEDROCK").toUpperCase());
        int[][] digitShape = new int[5][3];

        // Делал с помощью холста так-как по моему мнению это самый быстрый метод из возможных(И понятный)
        // Мы вспомнили про сореву за 1 день до сдачи проекта :)
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
                    location.clone().add(j, -i, 0).getBlock().setType(blockMaterial);
                    // Шла 4 кружка кофе и только потом я понял как это сделать
                }
            }
        }
    }

    private static void displayColon(Location location) {
        Material blockMaterial = Material.valueOf(Plugin.getInstance().getConfig().getString("pointMaterial", "BEDROCK").toUpperCase());
        location.clone().add(0, 0, 0).getBlock().setType(blockMaterial);
        location.clone().add(0, -2, 0).getBlock().setType(blockMaterial);
    }
}
