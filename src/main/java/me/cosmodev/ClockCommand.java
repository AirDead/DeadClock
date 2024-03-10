package me.cosmodev;

import me.cosmodev.Utils.BuildUtil;
import org.bukkit.Location;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ClockCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command __, @NotNull String ___, @NotNull String[] args) {

        // Задумка в том чтобы в usage написать что комманда только для игроков
        if (sender instanceof ConsoleCommandSender || sender instanceof CommandBlock) return false;

        Player player = (Player) sender;

        Location clockLocation = player.getLocation().clone();

        String region = Plugin.getInstance().getConfig().getString("region");
        if (region == null) {
            region = "Europe/Moscow";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(region));
        String currentTime = sdf.format(new Date());

        try {
            System.out.println("time: " + currentTime);

            Date date = sdf.parse(currentTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int hours = calendar.get(Calendar.HOUR_OF_DAY);
            int minutes = calendar.get(Calendar.MINUTE);
            System.out.println("hour: " + hours);
            System.out.println("min: " + minutes);

            BuildUtil.displayTimeWithBlocks(clockLocation, hours, minutes);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}
