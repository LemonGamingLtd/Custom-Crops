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
 * Particle data for {@code SCULK_CHARGE}, whose data argument is the roll (a {@code float}).
 */
public final class SculkChargeParticleData implements ParticleData {

    private final float roll;

    public SculkChargeParticleData(float roll) {
        this.roll = roll;
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        return roll;
    }

    public float roll() {
        return roll;
    }
}
