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
 * Particle data for {@link Particle#DUST_COLOR_TRANSITION}, producing a {@link Particle.DustTransition}.
 */
public final class DustTransitionParticleData implements ParticleData {

    private final Color from;
    private final Color to;
    private final float scale;

    public DustTransitionParticleData(Color from, Color to, float scale) {
        this.from = from;
        this.to = to;
        this.scale = scale;
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        return new Particle.DustTransition(from, to, scale);
    }

    public Color from() {
        return from;
    }

    public Color to() {
        return to;
    }

    public float scale() {
        return scale;
    }
}
