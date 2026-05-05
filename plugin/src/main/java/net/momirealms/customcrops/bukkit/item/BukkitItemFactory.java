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

package net.momirealms.customcrops.bukkit.item;

import com.saicone.rtag.RtagItem;
import net.momirealms.customcrops.common.helper.VersionHelper;
import net.momirealms.customcrops.common.item.Item;
import net.momirealms.customcrops.common.item.ItemFactory;
import net.momirealms.customcrops.common.plugin.CustomCropsPlugin;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Optional;

public abstract class BukkitItemFactory extends ItemFactory<CustomCropsPlugin, RtagItem, ItemStack> {

    private static BukkitItemFactory instance;

    protected BukkitItemFactory(CustomCropsPlugin plugin) {
        super(plugin);
        instance = this;
    }

    public static BukkitItemFactory create(CustomCropsPlugin plugin) {
        Objects.requireNonNull(plugin, "plugin");
        if (VersionHelper.isVersionNewerThan1_21_5()) {
            return new ComponentItemFactory1_21_5(plugin);
        } else if (VersionHelper.isVersionNewerThan1_20_5()) {
            return new ComponentItemFactory(plugin);
        } else {
            return new UniversalItemFactory(plugin);
        }
    }

    public static BukkitItemFactory getInstance() {
        return instance;
    }

    public Item<ItemStack> wrap(ItemStack item) {
        Objects.requireNonNull(item, "item");
        return wrap(new RtagItem(item));
    }

    @Override
    protected void setTag(RtagItem item, Object value, Object... path) {
        item.set(value, path);
    }

    @Override
    protected Optional<Object> getTag(RtagItem item, Object... path) {
        return Optional.ofNullable(item.get(path));
    }

    @Override
    protected boolean hasTag(RtagItem item, Object... path) {
        return item.hasTag(path);
    }

    @Override
    protected boolean removeTag(RtagItem item, Object... path) {
        return item.remove(path);
    }

    @Override
    protected void update(RtagItem item) {
        item.update();
    }

    @Override
    protected ItemStack load(RtagItem item) {
        return item.load();
    }

    @Override
    protected ItemStack getItem(RtagItem item) {
        return item.getItem();
    }

    @Override
    protected ItemStack loadCopy(RtagItem item) {
        return item.loadCopy();
    }

    @Override
    protected boolean unbreakable(RtagItem item) {
        return item.isUnbreakable();
    }
}
