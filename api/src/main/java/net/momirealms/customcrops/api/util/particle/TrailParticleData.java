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
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

/**
 * Particle data for {@code TRAIL}, producing a {@link Particle.Trail} pointing from the spawn location to a
 * dynamic target offset, with a fixed colour and a dynamic duration.
 *
 * <p>Target coordinates and duration are {@link MathValue}s evaluated against the spawning context,
 * mirroring craft-engine's {@code TrailData}.</p>
 *
 * <p>References the modern {@link Particle.Trail} type lazily: it is only resolved when such a particle is
 * actually spawned, so older servers that do not use it stay unaffected.</p>
 */
public final class TrailParticleData implements ParticleData {

    private final MathValue<?> targetX;
    private final MathValue<?> targetY;
    private final MathValue<?> targetZ;
    private final Color color;
    private final MathValue<?> duration;

    public TrailParticleData(MathValue<?> targetX, MathValue<?> targetY, MathValue<?> targetZ, Color color, MathValue<?> duration) {
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.color = color;
        this.duration = duration;
    }

    @Override
    public Object toBukkitData(Context<?> context, Location spawnLocation) {
        double tx = ParticleDataTypes.evaluate(targetX, context);
        double ty = ParticleDataTypes.evaluate(targetY, context);
        double tz = ParticleDataTypes.evaluate(targetZ, context);
        int durationValue = (int) ParticleDataTypes.evaluate(duration, context);
        return new Particle.Trail(spawnLocation.clone().add(tx, ty, tz), color, durationValue);
    }

    public MathValue<?> targetX() {
        return targetX;
    }

    public MathValue<?> targetY() {
        return targetY;
    }

    public MathValue<?> targetZ() {
        return targetZ;
    }

    public Color color() {
        return color;
    }

    public MathValue<?> duration() {
        return duration;
    }
}
