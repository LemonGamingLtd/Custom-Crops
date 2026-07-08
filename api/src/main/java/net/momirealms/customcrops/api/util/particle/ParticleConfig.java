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

import dev.dejvokep.boostedyaml.block.implementation.Section;
import net.momirealms.customcrops.api.misc.value.MathValue;
import net.momirealms.customcrops.api.util.ParticleUtils;
import org.bukkit.Particle;

import java.util.Locale;

/**
 * Immutable configuration for a particle spawn, mirroring
 * {@code net.momirealms.craftengine.core.world.particle.ParticleConfig}.
 *
 * <p>The structural numeric fields ({@code x}, {@code y}, {@code z}, {@code count}, offsets and {@code speed})
 * are dynamic {@link MathValue}s evaluated against the spawning context, while the type-specific
 * {@link ParticleData} (colours, scale, item, ...) is resolved up-front from the particle type through
 * {@link ParticleDataTypes}.</p>
 *
 * <p>Unlike craft-engine, {@code x}/{@code y}/{@code z} are offsets added to the context location (default
 * {@code 0}) rather than absolute world coordinates, so existing Custom-Crops configs keep working.</p>
 *
 * @param <T> the context holder type
 */
public final class ParticleConfig<T> {

    public final Particle particleType;
    public final MathValue<T> x;
    public final MathValue<T> y;
    public final MathValue<T> z;
    public final MathValue<T> count;
    public final MathValue<T> xOffset;
    public final MathValue<T> yOffset;
    public final MathValue<T> zOffset;
    public final MathValue<T> speed;
    public final ParticleData particleData;

    public ParticleConfig(Particle particleType,
                          MathValue<T> x, MathValue<T> y, MathValue<T> z,
                          MathValue<T> count,
                          MathValue<T> xOffset, MathValue<T> yOffset, MathValue<T> zOffset,
                          MathValue<T> speed,
                          ParticleData particleData) {
        this.particleType = particleType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.count = count;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = zOffset;
        this.speed = speed;
        this.particleData = particleData;
    }

    private static final String[] OFFSET_X = {"offset_x", "offset-x"};
    private static final String[] OFFSET_Y = {"offset_y", "offset-y"};
    private static final String[] OFFSET_Z = {"offset_z", "offset-z"};
    private static final String[] SPEED = {"speed", "extra"};

    public static <T> ParticleConfig<T> fromConfig(Section section) {
        String particleName = section.getString("particle", "ASH");
        Particle particleType = ParticleUtils.getParticle(particleName.toUpperCase(Locale.ENGLISH));
        return new ParticleConfig<>(
                particleType,
                MathValue.auto(section.get("x", 0)),
                MathValue.auto(section.get("y", 0)),
                MathValue.auto(section.get("z", 0)),
                MathValue.auto(section.get("count", 1)),
                MathValue.auto(ParticleDataTypes.getFirst(section, OFFSET_X, 0)),
                MathValue.auto(ParticleDataTypes.getFirst(section, OFFSET_Y, 0)),
                MathValue.auto(ParticleDataTypes.getFirst(section, OFFSET_Z, 0)),
                MathValue.auto(ParticleDataTypes.getFirst(section, SPEED, 0)),
                ParticleDataTypes.create(particleName, section)
        );
    }

    public Particle particleType() {
        return particleType;
    }

    public MathValue<T> x() {
        return x;
    }

    public MathValue<T> y() {
        return y;
    }

    public MathValue<T> z() {
        return z;
    }

    public MathValue<T> count() {
        return count;
    }

    public MathValue<T> xOffset() {
        return xOffset;
    }

    public MathValue<T> yOffset() {
        return yOffset;
    }

    public MathValue<T> zOffset() {
        return zOffset;
    }

    public MathValue<T> speed() {
        return speed;
    }

    public ParticleData particleData() {
        return particleData;
    }
}
