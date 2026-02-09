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

package net.momirealms.customcrops.bukkit.integration.custom.lgfurniture_r1;

import ltd.lemongaming.furniture.furniture.FurnitureManager;
import ltd.lemongaming.furniture.stringblock.StringBlockManager;
import net.momirealms.customcrops.api.BukkitCustomCropsPlugin;
import net.momirealms.customcrops.api.core.CustomItemProvider;
import net.momirealms.customcrops.api.util.LocationUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

public class FurnitureProvider implements CustomItemProvider {

    private final StringBlockManager stringBlockManager = Objects.requireNonNull(StringBlockManager.getInstance());
    private final FurnitureManager furnitureManager = Objects.requireNonNull(FurnitureManager.getInstance());

    @Override
    public boolean removeCustomBlock(Location location) {
        Block block = location.getBlock();
        if (stringBlockManager.isStringBlock(location)) {
            block.setType(Material.AIR, false);
            return true;
        }
        return false;
    }

    @Override
    public boolean placeCustomBlock(Location location, String id) {
        return stringBlockManager.placeBlock(id, location, null) != null;
    }

    @Override
    public @Nullable Entity placeFurniture(Location location, String id) {
        Entity entity = furnitureManager.placeFurniture(id, LocationUtils.toSurfaceCenterLocation(location), 0.0F, null);
        if (entity == null) {
            BukkitCustomCropsPlugin.getInstance().getPluginLogger().warn("Furniture[" + id + "] doesn't exist. Please double check if that furniture exists.");
        }
        return entity;
    }

    @Override
    public boolean removeFurniture(Entity entity) {
        if (isFurniture(entity) && entity instanceof ItemDisplay itemDisplay) {
            furnitureManager.breakFurniture(itemDisplay, null, false);
            return true;
        }
        return false;
    }

    @Override
    public @Nullable String blockID(Block block) {
        return stringBlockManager.getBlockId(block.getLocation());
    }

    @Override
    public @Nullable String itemID(ItemStack itemStack) {
        final String furnitureId = furnitureManager.getFurnitureId(itemStack);
        if (furnitureId != null) {
            return furnitureId;
        }
        return stringBlockManager.getBlockIdFromItem(itemStack);
    }

    @Override
    public @Nullable ItemStack itemStack(Player player, String id) {
        final ItemStack furnitureItem = furnitureManager.createFurnitureItem(id);
        if (furnitureItem != null) {
            return furnitureItem;
        }
        return stringBlockManager.createItem(id);
    }

    @Override
    public @Nullable String furnitureID(Entity entity) {
        if (entity instanceof ItemDisplay itemDisplay) {
            return furnitureManager.getFurnitureIdFromEntity(itemDisplay);
        }
        return null;
    }

    @Override
    public boolean isFurniture(Entity entity) {
        return furnitureManager.isFurniture(entity);
    }
}
