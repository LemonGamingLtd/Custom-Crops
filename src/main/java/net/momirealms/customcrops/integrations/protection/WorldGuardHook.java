/*
 *  Copyright (C) <2022> <XiaoMoMi>
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

package net.momirealms.customcrops.integrations.protection;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.internal.platform.WorldGuardPlatform;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import net.momirealms.customcrops.integrations.CCAntiGrief;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WorldGuardHook implements CCAntiGrief {

    @Override
    public String getName() {
        return "WorldGuard";
    }

    public static StateFlag HARVEST_FLAG;
    public static StateFlag PLACE_FLAG;

    public static void initialize() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        if (HARVEST_FLAG == null) {
            HARVEST_FLAG = new StateFlag("customcrops-harvest", false);
            registry.register(HARVEST_FLAG);
        }
        if (PLACE_FLAG == null) {
            PLACE_FLAG = new StateFlag("customcrops-place", false);
            registry.register(PLACE_FLAG);
        }
    }

    @Override
    public boolean canPlace(Location location, Player player) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        WorldGuardPlatform platform = WorldGuard.getInstance().getPlatform();
        RegionQuery query = platform.getRegionContainer().createQuery();
        return query.testBuild(BukkitAdapter.adapt(location), localPlayer, PLACE_FLAG);
    }

    @Override
    public boolean canBreak(Location location, Player player) {
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        WorldGuardPlatform platform = WorldGuard.getInstance().getPlatform();
        RegionQuery query = platform.getRegionContainer().createQuery();
        return query.testBuild(BukkitAdapter.adapt(location), localPlayer, HARVEST_FLAG);
    }
}