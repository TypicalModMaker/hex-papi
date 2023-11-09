package dev.isnow.hex.util;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {
    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
    public static String getHex(String msg) {
        msg = msg.replaceAll("&", "");
        Matcher matcher = pattern.matcher(msg);
        while (matcher.find()) {
            String color = msg.substring(matcher.start(), matcher.end());
            msg = msg.replace(color, ChatColor.of(color)+ "");
            matcher = pattern.matcher(msg);
        }
        return msg;
    }

    public static ArrayList<String> splitHex(String input) {
        ArrayList<String> output = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String color = input.substring(matcher.start(), matcher.end());
            input = input.replace(color, "");
            output.add(color);
            matcher = pattern.matcher(input);
        }
        output.add(input);
        return output;
    }

    public static ArrayList<Color> splitHexColor(String input) {
        ArrayList<Color> output = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String color = input.substring(matcher.start(), matcher.end());
            input = input.replace(color, "");
            output.add(Color.decode(color.replaceAll("&", "")));
            matcher = pattern.matcher(input);
        }
        return output;
    }
}