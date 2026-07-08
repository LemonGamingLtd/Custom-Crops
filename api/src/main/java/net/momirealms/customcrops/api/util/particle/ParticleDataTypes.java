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

package net.momirealms.customcrops.api.util.particle;

import dev.dejvokep.boostedyaml.block.implementation.Section;
import net.momirealms.customcrops.api.context.Context;
import net.momirealms.customcrops.api.misc.value.MathValue;
import net.momirealms.customcrops.api.util.ParticleUtils;
import org.bukkit.Color;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;

public final class ParticleDataTypes {

    private ParticleDataTypes() {
    }

    private static final Map<String, Function<Section, ParticleData>> TYPES = new HashMap<>();

    private static final String[] FROM = {"from", "color"};
    private static final String[] TO = {"to", "to-color", "to_color"};
    private static final String[] COLOR = {"color"};
    private static final String[] SCALE = {"scale"};
    private static final String[] ITEM = {"item", "itemStack", "item_stack"};
    private static final String[] ROLL = {"roll", "charge"};
    private static final String[] SHRIEK = {"shriek"};
    private static final String[] WATER_BLOCKS = {"blocks", "water_blocks", "water-blocks"};
    private static final String[] BURST_IMPULSE_BASE = {"base", "burst_impulse_base", "burst-impulse-base"};
    private static final String[] POWER = {"power"};
    private static final String[] BLOCK_STATE = {"blockstate", "block_state", "block-state"};
    private static final String[] TARGET_X = {"target_x", "target-x"};
    private static final String[] TARGET_Y = {"target_y", "target-y"};
    private static final String[] TARGET_Z = {"target_z", "target-z"};
    private static final String[] ARRIVAL_TIME = {"arrival_time", "arrival-time"};
    private static final String[] DURATION = {"duration"};

    static {
        registerParticleData(section -> {
            Color from = ParticleUtils.parseColor(getFirstString(section, FROM, "255,255,255"));
            String toRaw = getFirstString(section, TO, null);
            Color to = (toRaw == null) ? null : ParticleUtils.parseColor(toRaw);
            float scale = getFirstFloat(section, SCALE, 1f);
            if (to != null) {
                return new DustTransitionParticleData(from, to, scale);
            }
            return new DustParticleData(from, scale);
        }, "dust", "redstone");

        registerParticleData(section -> new DustTransitionParticleData(
                ParticleUtils.parseColor(getFirstString(section, FROM, "255,255,255")),
                ParticleUtils.parseColor(getFirstString(section, TO, "255,255,255")),
                getFirstFloat(section, SCALE, 1f)
        ), "dust_color_transition");

        registerParticleData(section -> new ColorParticleData(
                ParticleUtils.parseColor(getFirstString(section, COLOR, "255,255,255"))
        ), "entity_effect", "spell_mob", "ambient_entity_effect", "tinted_leaves");

        registerParticleData(section -> new ItemParticleData(
                getFirstString(section, ITEM, null)
        ), "item");

        registerParticleData(section -> new SculkChargeParticleData(
                getFirstFloat(section, ROLL, 0f)
        ), "sculk_charge");

        registerParticleData(section -> new ShriekParticleData(
                getFirstInt(section, SHRIEK, 0)
        ), "shriek");

//        registerParticleData(section -> new GeyserParticleData(
//                getFirstInt(section, WATER_BLOCKS, 1)
//        ), "geyser", "geyser_plume");

//        registerParticleData(section -> new GeyserBaseParticleData(
//                getFirstInt(section, WATER_BLOCKS, 1),
//                getFirstFloat(section, BURST_IMPULSE_BASE, 0f)
//        ), "geyser_base", "geyser_poof");

        registerParticleData(section -> new SpellParticleData(
                ParticleUtils.parseColor(getFirstString(section, COLOR, "255,255,255")),
                getFirstFloat(section, POWER, 1f)
        ), "spell");

        registerParticleData(section -> new BlockParticleData(
                getFirstString(section, BLOCK_STATE, null)
        ), "block", "falling_dust", "dust_pillar", "block_crumble", "block_marker");

        registerParticleData(section -> new VibrationParticleData(
                MathValue.auto(getFirst(section, TARGET_X, 0)),
                MathValue.auto(getFirst(section, TARGET_Y, 0)),
                MathValue.auto(getFirst(section, TARGET_Z, 0)),
                MathValue.auto(getFirst(section, ARRIVAL_TIME, 10))
        ), "vibration");

        registerParticleData(section -> new TrailParticleData(
                MathValue.auto(getFirst(section, TARGET_X, 0)),
                MathValue.auto(getFirst(section, TARGET_Y, 0)),
                MathValue.auto(getFirst(section, TARGET_Z, 0)),
                ParticleUtils.parseColor(getFirstString(section, COLOR, "255,255,255")),
                MathValue.auto(getFirst(section, DURATION, 10))
        ), "trail");
    }

    public static void registerParticleData(Function<Section, ParticleData> function, String... types) {
        for (String type : types) {
            TYPES.put(type.toLowerCase(Locale.ENGLISH), function);
        }
    }

    public static ParticleData create(String particleType, Section section) {
        if (particleType == null) {
            return null;
        }
        Function<Section, ParticleData> function = TYPES.get(particleType.toLowerCase(Locale.ENGLISH));
        return (function == null) ? null : function.apply(section);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    static double evaluate(MathValue<?> value, Context<?> context) {
        return value.evaluate((Context) context);
    }

    static Object getFirst(Section section, String[] keys, Object def) {
        for (String key : keys) {
            if (section.contains(key)) {
                return section.get(key);
            }
        }
        return def;
    }

    static String getFirstString(Section section, String[] keys, String def) {
        for (String key : keys) {
            if (section.contains(key)) {
                return section.getString(key);
            }
        }
        return def;
    }

    static float getFirstFloat(Section section, String[] keys, float def) {
        for (String key : keys) {
            if (section.contains(key)) {
                Object value = section.get(key);
                if (value instanceof Number number) {
                    return number.floatValue();
                }
                try {
                    return Float.parseFloat(String.valueOf(value).trim());
                } catch (NumberFormatException ignored) {
                    return def;
                }
            }
        }
        return def;
    }

    static int getFirstInt(Section section, String[] keys, int def) {
        for (String key : keys) {
            if (section.contains(key)) {
                Object value = section.get(key);
                if (value instanceof Number number) {
                    return number.intValue();
                }
                try {
                    return Integer.parseInt(String.valueOf(value).trim());
                } catch (NumberFormatException ignored) {
                    return def;
                }
            }
        }
        return def;
    }
}
