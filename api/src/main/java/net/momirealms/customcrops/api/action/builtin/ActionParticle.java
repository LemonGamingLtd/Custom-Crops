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

package net.momirealms.customcrops.api.action.builtin;

import dev.dejvokep.boostedyaml.block.implementation.Section;
import net.momirealms.customcrops.api.BukkitCustomCropsPlugin;
import net.momirealms.customcrops.api.context.Context;
import net.momirealms.customcrops.api.context.ContextKeys;
import net.momirealms.customcrops.api.misc.value.MathValue;
import net.momirealms.customcrops.api.util.particle.ParticleConfig;
import org.bukkit.Location;
import org.bukkit.World;

import static java.util.Objects.requireNonNull;

public class ActionParticle<T> extends AbstractBuiltInAction<T> {

    private final ParticleConfig<T> config;

    public ActionParticle(
            BukkitCustomCropsPlugin plugin,
            Section section,
            MathValue<T> chance
    ) {
        super(plugin, chance);
        this.config = ParticleConfig.fromConfig(section);
    }

    @Override
    protected void triggerAction(Context<T> context) {
        if (context.argOrDefault(ContextKeys.OFFLINE, false)) return;
        Location location = requireNonNull(context.arg(ContextKeys.LOCATION));
        World world = location.getWorld();
        double x = location.getX() + config.x.evaluate(context);
        double y = location.getY() + config.y.evaluate(context);
        double z = location.getZ() + config.z.evaluate(context);
        int count = (int) config.count.evaluate(context);
        double xOffset = config.xOffset.evaluate(context);
        double yOffset = config.yOffset.evaluate(context);
        double zOffset = config.zOffset.evaluate(context);
        double speed = config.speed.evaluate(context);
        Location spawnLocation = new Location(world, x, y, z);
        Object data = (config.particleData == null) ? null : config.particleData.toBukkitData(context, spawnLocation);
        world.spawnParticle(config.particleType, x, y, z, count, xOffset, yOffset, zOffset, speed, data);
    }

    public ParticleConfig<T> config() {
        return config;
    }
}
