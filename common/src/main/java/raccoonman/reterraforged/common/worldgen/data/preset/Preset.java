package raccoonman.reterraforged.common.worldgen.data.preset;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import raccoonman.reterraforged.common.registries.RTFRegistries;
import raccoonman.reterraforged.common.worldgen.data.MCNoiseGeneratorSettings;
import raccoonman.reterraforged.common.worldgen.data.MCPlacedFeatures;
import raccoonman.reterraforged.common.worldgen.data.MCStructureSets;
import raccoonman.reterraforged.common.worldgen.data.RTFNoise;
import raccoonman.reterraforged.common.worldgen.data.RTFDensityFunctions;

public record Preset(WorldSettings world, ClimateSettings climate, TerrainSettings terrain, RiverSettings rivers, FilterSettings filters, StructureSettings structures, MiscellaneousSettings miscellaneous) {
	public static final Codec<Preset> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		WorldSettings.CODEC.fieldOf("world").forGetter(Preset::world),
		ClimateSettings.CODEC.fieldOf("climate").forGetter(Preset::climate),
		TerrainSettings.CODEC.fieldOf("terrain").forGetter(Preset::terrain),
		RiverSettings.CODEC.fieldOf("rivers").forGetter(Preset::rivers),
		FilterSettings.CODEC.fieldOf("filters").forGetter(Preset::filters),
		StructureSettings.CODEC.fieldOf("structures").forGetter(Preset::structures),
		MiscellaneousSettings.CODEC.fieldOf("miscellaneous").forGetter(Preset::miscellaneous)
	).apply(instance, Preset::new));
	
	public Preset copy() {
		return new Preset(this.world.copy(), this.climate.copy(), this.terrain.copy(), this.rivers.copy(), this.filters.copy(), this.structures.copy(), this.miscellaneous.copy());
	}
	
	public static Preset makeDefault() {
		return new Preset(WorldSettings.makeDefault(), ClimateSettings.makeDefault(), TerrainSettings.makeDefault(), RiverSettings.makeDefault(), FilterSettings.makeDefault(), StructureSettings.makeDefault(), MiscellaneousSettings.makeDefault()); 
	}
	
	public HolderLookup.Provider buildPatch(RegistryAccess.Frozen registries) {
		RegistrySetBuilder builder = new RegistrySetBuilder();
		builder.add(RTFRegistries.NOISE, (ctx) -> RTFNoise.bootstrap(ctx, this));
		builder.add(Registries.DENSITY_FUNCTION, (ctx) -> RTFDensityFunctions.bootstrap(ctx, this));
		builder.add(Registries.NOISE_SETTINGS, (ctx) -> MCNoiseGeneratorSettings.bootstrap(ctx, this));
		builder.add(Registries.PLACED_FEATURE, (ctx) -> MCPlacedFeatures.bootstrap(ctx, this));
		builder.add(Registries.STRUCTURE_SET, (ctx) -> MCStructureSets.bootstrap(ctx, this));
		return builder.buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), registries);
	}
}
