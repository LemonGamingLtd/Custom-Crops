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

import net.momirealms.customcrops.api.BukkitCustomCropsPlugin;
import net.momirealms.customcrops.api.context.Context;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.Nullable;

/**
 * Particle data for block-based particles ({@code BLOCK}, {@code FALLING_DUST}, {@code DUST_PILLAR},
 * {@code BLOCK_CRUMBLE}, {@code BLOCK_MARKER}), producing a {@link BlockData} parsed from a config string.
 *
 * <p>The block string may be a plain material name (e.g. {@code stone}) or a full block-state string
 * (e.g. {@code minecraft:chest[facing=north]}).</p>
 */
public final class BlockParticleData implements ParticleData {

    private final String blockString;
    private final BlockData blockData;

    public BlockParticleData(@Nullable String blockString) {
        this.blockString = blockString;
        this.blockData = createBukkitBlockData(blockString);
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        return blockData;
    }

    @Nullable
    public String blockString() {
        return blockString;
    }

    @Nullable
    public BlockData blockData() {
        return blockData;
    }

    @Nullable
    private static BlockData createBukkitBlockData(@Nullable String input) {
        if (input == null || input.isBlank()) {
            return null;
        }
        String data = input.contains(":") ? input : "minecraft:" + input;
        try {
            return Bukkit.createBlockData(data);
        } catch (IllegalArgumentException e) {
            BukkitCustomCropsPlugin.getInstance().getPluginLogger().warn("Invalid block data for particle: " + input);
            return null;
        }
    }
}
