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
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Particle data for {@link org.bukkit.Particle#ITEM}, producing an {@link ItemStack}.
 *
 * <p>The item is built eagerly when the data is parsed, mirroring the previous {@code ActionParticle} behaviour.</p>
 */
public final class ItemParticleData implements ParticleData {

    private final String itemId;
    private final ItemStack itemStack;

    public ItemParticleData(@Nullable String itemId) {
        this.itemId = itemId;
        this.itemStack = (itemId == null) ? null
                : BukkitCustomCropsPlugin.getInstance().getItemManager().build(null, itemId);
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        return itemStack;
    }

    @Nullable
    public String itemId() {
        return itemId;
    }

    @Nullable
    public ItemStack itemStack() {
        return itemStack;
    }
}
