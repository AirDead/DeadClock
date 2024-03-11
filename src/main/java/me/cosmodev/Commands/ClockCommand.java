package me.cosmodev.Commands;

import me.cosmodev.Plugin;
import me.cosmodev.Utils.BuildUtil;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ClockCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command __, @NotNull String ___, @NotNull String[] args) {
        if (sender instanceof ConsoleCommandSender) return false;

        Player player = (Player) sender;
        Location clockLocation = player.getLocation().clone();

        String region = Plugin.getInstance().getConfig().getString("region");
        if (region == null) {
            region = "Europe/Moscow";
        }

        new BukkitRunnable() {
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sdf.setTimeZone(TimeZone.getTimeZone(region));
                String currentTime = sdf.format(new Date());

                try {
                    Date date = sdf.parse(currentTime);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);

                    int hours = calendar.get(Calendar.HOUR_OF_DAY);
                    int minutes = calendar.get(Calendar.MINUTE);

                    BuildUtil.displayTimeWithBlocks(clockLocation, hours, minutes);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimer(Plugin.getInstance(), 0L, 1200L); 

        return true;
    }
}
