package raccoonman.reterraforged.common.worldgen.data.preset;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import raccoonman.reterraforged.common.level.levelgen.noise.curve.DistanceFunction;

public class WorldSettings {
	public static final Codec<WorldSettings> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Continent.CODEC.fieldOf("continent").forGetter((o) -> o.continent),
		ControlPoints.CODEC.fieldOf("controlPoints").forGetter((o) -> o.controlPoints),
		Properties.CODEC.fieldOf("properties").forGetter((o) -> o.properties)
	).apply(instance, WorldSettings::new));
	
    public Continent continent;
    public ControlPoints controlPoints;
    public Properties properties;

    public WorldSettings(Continent continent, ControlPoints controlPoints, Properties properties) {
        this.continent = continent;
        this.controlPoints = controlPoints;
        this.properties = properties;
    }
    
    public WorldSettings copy() {
    	return new WorldSettings(this.continent.copy(), this.controlPoints.copy(), this.properties.copy());
    }
    
    public static WorldSettings makeDefault() {
    	return new WorldSettings(Continent.makeDefault(), ControlPoints.makeDefault(), Properties.makeDefault());
    }
    
    public static class Continent {
    	public static final Codec<Continent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
    		ContinentType.CODEC.fieldOf("continentType").forGetter((o) -> o.continentType),
    		DistanceFunction.CODEC.fieldOf("continentShape").forGetter((o) -> o.continentShape),
    		Codec.INT.fieldOf("continentScale").forGetter((o) -> o.continentScale),
    		Codec.FLOAT.fieldOf("continentJitter").forGetter((o) -> o.continentJitter),
    		Codec.FLOAT.fieldOf("continentSkipping").forGetter((o) -> o.continentSkipping),
    		Codec.FLOAT.fieldOf("continentSizeVariance").forGetter((o) -> o.continentSizeVariance),
    		Codec.INT.fieldOf("continentNoiseOctaves").forGetter((o) -> o.continentNoiseOctaves),
    		Codec.FLOAT.fieldOf("continentNoiseGain").forGetter((o) -> o.continentNoiseGain),
    		Codec.FLOAT.fieldOf("continentNoiseLacunarity").forGetter((o) -> o.continentNoiseLacunarity)
    	).apply(instance, Continent::new));
    	
        public ContinentType continentType;
        public DistanceFunction continentShape;
        public int continentScale;
        public float continentJitter;
        public float continentSkipping;
        public float continentSizeVariance;
        public int continentNoiseOctaves;
        public float continentNoiseGain;
        public float continentNoiseLacunarity;
        
        public Continent(ContinentType continentType, DistanceFunction continentShape, int continentScale, float continentJitter, float continentSkipping, float continentSizeVariance, int continentNoiseOctaves, float continentNoiseGain, float continentNoiseLacunarity) {
            this.continentType = continentType;
            this.continentShape = continentShape;
            this.continentScale = continentScale;
            this.continentJitter = continentJitter;
            this.continentSkipping = continentSkipping;
            this.continentSizeVariance = continentSizeVariance;
            this.continentNoiseOctaves = continentNoiseOctaves;
            this.continentNoiseGain = continentNoiseGain;
            this.continentNoiseLacunarity = continentNoiseLacunarity;
        }
        
        public Continent copy() {
        	return new Continent(this.continentType, this.continentShape, this.continentScale, this.continentJitter, this.continentSkipping, this.continentSizeVariance, this.continentNoiseOctaves, this.continentNoiseGain, this.continentNoiseLacunarity);
        }
        
        public static Continent makeDefault() {
        	return new Continent(ContinentType.MULTI_IMPROVED, DistanceFunction.EUCLIDEAN, 3000, 0.7F, 0.25F, 0.25F, 5, 0.26F, 4.33F);
        }
    }
    
    public static class ControlPoints {
    	public static final Codec<ControlPoints> CODEC = RecordCodecBuilder.create(instance -> instance.group(
    		Codec.FLOAT.fieldOf("deepOcean").forGetter((o) -> o.deepOcean),
    		Codec.FLOAT.fieldOf("shallowOcean").forGetter((o) -> o.shallowOcean),
    		Codec.FLOAT.fieldOf("beach").forGetter((o) -> o.beach),
    		Codec.FLOAT.fieldOf("coast").forGetter((o) -> o.coast),
    		Codec.FLOAT.fieldOf("inland").forGetter((o) -> o.inland)
        ).apply(instance, ControlPoints::new));

        public float deepOcean;
        public float shallowOcean;
        public float beach;
        public float coast;
        public float inland;
        
        public ControlPoints(float deepOcean, float shallowOcean, float beach, float coast, float inland) {
            this.deepOcean = deepOcean;
            this.shallowOcean = shallowOcean;
            this.beach = beach;
            this.coast = coast;
            this.inland = inland;
        }
        
        public ControlPoints copy() {
        	return new ControlPoints(this.deepOcean, this.shallowOcean, this.beach, this.coast, this.inland);
        }
        
        public static ControlPoints makeDefault() {
        	return new ControlPoints(0.1F, 0.25F, 0.327F, 0.448F, 0.502F);
        }
    }
    
    public static class Properties {
    	public static final Codec<Properties> CODEC = RecordCodecBuilder.create(instance -> instance.group(
    		SpawnType.CODEC.fieldOf("spawnType").forGetter((o) -> o.spawnType),
    		Codec.INT.fieldOf("worldHeight").forGetter((o) -> o.worldHeight),
    		Codec.INT.optionalFieldOf("worldDepth", 0).forGetter((o) -> o.worldDepth), // this has to be defaulted to be able to parse legacy presets
    		Codec.INT.fieldOf("seaLevel").forGetter((o) -> o.seaLevel)
    	).apply(instance, Properties::new));
    	
        public SpawnType spawnType;
        public int worldHeight;
        public int worldDepth;
        public int seaLevel;
        
        public Properties(SpawnType spawnType, int worldHeight, int worldDepth, int seaLevel) {
        	this.spawnType = spawnType;
        	this.worldHeight = worldHeight;
        	this.worldDepth = worldDepth;
        	this.seaLevel = seaLevel;
        }
        
        public Properties copy() {
        	return new Properties(this.spawnType, this.worldHeight, this.worldDepth, this.seaLevel);
        }
        
        public static Properties makeDefault() {
        	return new Properties(SpawnType.CONTINENT_CENTER, 384, 64, 63);
        }
    }
}
