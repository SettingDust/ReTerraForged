package raccoonman.reterraforged.common.registries;

import com.mojang.serialization.Codec;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import raccoonman.reterraforged.common.ReTerraForged;
import raccoonman.reterraforged.common.level.levelgen.noise.Noise;
import raccoonman.reterraforged.common.level.levelgen.noise.curve.CurveFunction;
import raccoonman.reterraforged.common.level.levelgen.noise.domain.Domain;

public final class RTFRegistries {
	public static final ResourceKey<Registry<Codec<? extends Noise>>> NOISE_TYPE = resolve("worldgen/noise_type");
	public static final ResourceKey<Registry<Codec<? extends Domain>>> DOMAIN_TYPE = resolve("worldgen/domain_type");
	public static final ResourceKey<Registry<Codec<? extends CurveFunction>>> CURVE_FUNCTION_TYPE = resolve("worldgen/curve_function_type");
	public static final ResourceKey<Registry<Noise>> NOISE = resolve("worldgen/noise");

	private static <T> ResourceKey<Registry<T>> resolve(String key) {
		return ResourceKey.createRegistryKey(ReTerraForged.resolve(key));
	}
}
