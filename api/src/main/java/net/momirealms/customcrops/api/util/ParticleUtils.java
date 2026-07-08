/*
 *  Copyright (C) <2024> <XiaoMoMi>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.momirealms.customcrops.api.util;

import org.bukkit.Color;
import org.bukkit.Particle;

public class ParticleUtils {

    public static Particle getParticle(String particle) {
        try {
            return Particle.valueOf(particle);
        } catch (IllegalArgumentException e) {
            return switch (particle) {
                case "REDSTONE" -> Particle.valueOf("DUST");
                case "VILLAGER_HAPPY" -> Particle.valueOf("HAPPY_VILLAGER");
                default -> Particle.valueOf(particle);
            };
        }
    }

    /**
     * Parses a colour from a config value. Supports {@code "r,g,b"}, hex ({@code "#rrggbb"}) and a raw integer.
     * Returns {@link Color#WHITE} for {@code null}/blank or unparseable input so that particle spawning never
     * fails with a {@link NullPointerException}.
     *
     * @param input the colour string to parse
     * @return the parsed colour
     */
    public static Color parseColor(String input) {
        if (input == null || input.isBlank()) {
            return Color.WHITE;
        }
        input = input.trim();
        try {
            if (input.startsWith("#")) {
                return Color.fromRGB(Integer.parseInt(input.substring(1), 16));
            }
            String[] parts = input.split(",");
            if (parts.length == 3) {
                return Color.fromRGB(
                        Integer.parseInt(parts[0].trim()),
                        Integer.parseInt(parts[1].trim()),
                        Integer.parseInt(parts[2].trim())
                );
            }
            return Color.fromRGB(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return Color.WHITE;
        }
    }
}
