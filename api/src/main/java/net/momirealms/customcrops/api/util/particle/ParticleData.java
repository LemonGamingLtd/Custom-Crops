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

import net.momirealms.customcrops.api.context.Context;
import org.bukkit.Location;

/**
 * Type-specific extra data attached to a {@link org.bukkit.Particle} spawn, such as
 * {@link org.bukkit.Particle.DustOptions}, {@link org.bukkit.Color}, an {@link org.bukkit.inventory.ItemStack},
 * a {@link org.bukkit.Vibration} and so on.
 *
 * <p>This mirrors {@code net.momirealms.craftengine.core.world.particle.ParticleData}: the data parser is
 * selected by particle type through {@link ParticleDataTypes}, decoupling data reading from the action
 * that spawns the particle.</p>
 */
public interface ParticleData {

    /**
     * Resolves the platform-specific particle data object to pass to
     * {@link org.bukkit.World#spawnParticle(org.bukkit.Particle, double, double, double, int, double, double, double, double, Object)}.
     *
     * @param context       the context in which the particle is spawned
     * @param spawnLocation the resolved spawn location (context location plus any {@code x}/{@code y}/{@code z}
     *                      offsets), used by particles whose data is position-relative (e.g. vibration, trail)
     * @return the bukkit particle data object, or {@code null} if none is required
     */
    Object toBukkitData(Context<?> context, Location spawnLocation);
}
