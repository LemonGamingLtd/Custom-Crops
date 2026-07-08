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
import net.momirealms.customcrops.api.misc.value.MathValue;
import org.bukkit.Location;
import org.bukkit.Vibration;

/**
 * Particle data for {@code VIBRATION}, producing a {@link Vibration} whose destination block is the spawn
 * location plus a dynamic target offset and whose arrival time is also dynamic.
 *
 * <p>Destination coordinates and arrival time are {@link MathValue}s evaluated against the spawning context,
 * mirroring craft-engine's {@code VibrationData}.</p>
 */
public final class VibrationParticleData implements ParticleData {

    private final MathValue<?> destinationX;
    private final MathValue<?> destinationY;
    private final MathValue<?> destinationZ;
    private final MathValue<?> arrivalTime;

    public VibrationParticleData(MathValue<?> destinationX, MathValue<?> destinationY, MathValue<?> destinationZ, MathValue<?> arrivalTime) {
        this.destinationX = destinationX;
        this.destinationY = destinationY;
        this.destinationZ = destinationZ;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        double dx = ParticleDataTypes.evaluate(destinationX, context);
        double dy = ParticleDataTypes.evaluate(destinationY, context);
        double dz = ParticleDataTypes.evaluate(destinationZ, context);
        int arrival = (int) ParticleDataTypes.evaluate(arrivalTime, context);
        return new Vibration(
                new Vibration.Destination.BlockDestination(spawnLocation.clone().add(dx, dy, dz)),
                arrival
        );
    }

    public MathValue<?> destinationX() {
        return destinationX;
    }

    public MathValue<?> destinationY() {
        return destinationY;
    }

    public MathValue<?> destinationZ() {
        return destinationZ;
    }

    public MathValue<?> arrivalTime() {
        return arrivalTime;
    }
}
