package dev.isnow.hex;

import dev.isnow.hex.util.ColorUtils;
import dev.isnow.hex.util.HexUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HexExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "hex";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Isnow";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    public String onPlaceholderRequest(Player p, String arg) {
        if(arg.split(" ").length == 1) {
            return ColorUtils.getHex(arg);
        } else {
            final String[] args = arg.split(" ");
            String input = String.join(" ", args).replace(args[0] + " ", "");
            if(args[0].equals("create")) {
                input = input.replace(args[1] + " ", "");
            }
            final ArrayList<String> inputChars = new ArrayList<>();
            for(final char c : input.toCharArray()) {
                inputChars.add(String.valueOf(c));
            }
            if(args[0].equals("create")) {
                final List<Color> colours = ColorUtils.splitHexColor(args[1]);
                final HexUtils.Gradient gradient = new HexUtils.Gradient(colours, inputChars.size());
                final StringBuilder output = new StringBuilder();

                for (String inputChar : inputChars) {
                    output.append(gradient.nextChatColor()).append(inputChar);
                }
                return output.toString();
            }
            final ArrayList<String> colours = ColorUtils.splitHex(args[0]);
            final StringBuilder output = new StringBuilder();
            for(int i = 0; i < colours.size() - 1; i++) {
                String gradient = colours.get(i);
                output.append(gradient).append(inputChars.get(i));
            }
            return ColorUtils.getHex(output.toString());
        }
    }
}
