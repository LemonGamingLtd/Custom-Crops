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
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * Particle data for {@link Particle#DUST}, producing a {@link Particle.DustOptions}.
 */
public final class DustParticleData implements ParticleData {

    private final Color color;
    private final float scale;

    public DustParticleData(Color color, float scale) {
        this.color = color;
        this.scale = scale;
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        return new Particle.DustOptions(color, scale);
    }

    public Color color() {
        return color;
    }

    public float scale() {
        return scale;
    }
}
