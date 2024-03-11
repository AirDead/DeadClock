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

       BuildUtil.startClockUpdater(clockLocation);

        return true;
    }
}