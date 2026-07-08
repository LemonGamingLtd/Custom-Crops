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
 * Particle data for {@code SPELL}, producing a {@link Particle.Spell}.
 *
 * <p>References the modern {@link Particle.Spell} type lazily: it is only resolved when such a particle is
 * actually spawned, so older servers that do not use it stay unaffected.</p>
 */
public final class SpellParticleData implements ParticleData {

    private final Color color;
    private final float power;

    public SpellParticleData(Color color, float power) {
        this.color = color;
        this.power = power;
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        return new Particle.Spell(color, power);
    }

    public Color color() {
        return color;
    }

    public float power() {
        return power;
    }
}
