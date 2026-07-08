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
 * Particle data for {@code SHRIEK}, whose data argument is the shriek delay (an {@code int}).
 */
public final class ShriekParticleData implements ParticleData {

    private final int delay;

    public ShriekParticleData(int delay) {
        this.delay = delay;
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        return delay;
    }

    public int delay() {
        return delay;
    }
}
