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

/**
 * Particle data for particles that take a plain {@link Color} as their data argument
 * (e.g. {@code ENTITY_EFFECT} / {@code SPELL_MOB} / {@code TINTED_LEAVES}).
 */
public final class ColorParticleData implements ParticleData {

    private final Color color;

    public ColorParticleData(Color color) {
        this.color = color;
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        return color;
    }

    public Color color() {
        return color;
    }
}
