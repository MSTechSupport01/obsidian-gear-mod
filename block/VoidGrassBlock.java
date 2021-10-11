// 
// Decompiled by Procyon v0.5.36
// 

package net.mcreator.obsidiangear.block;

import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.World;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent;
import java.util.Random;
import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.template.RuleTest;
import java.util.Collections;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import java.util.List;
import net.minecraft.loot.LootContext;
import net.minecraftforge.common.IPlantable;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.world.gen.feature.template.IRuleTestType;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraft.block.Block;
import net.mcreator.obsidiangear.ObsidianGearModElements.ModElement;
import net.mcreator.obsidiangear.ObsidianGearModElements;

@Tag
public class VoidGrassBlock extends ObsidianGearModElements.ModElement
{
    @ObjectHolder("obsidian_gear:void_grass")
    public static final Block block;
    private static Feature<OreFeatureConfig> feature;
    private static ConfiguredFeature<?, ?> configuredFeature;
    private static IRuleTestType<CustomRuleTest> CUSTOM_MATCH;
    
    public VoidGrassBlock(final ObsidianGearModElements instance) {
        super(instance, 17);
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLJavaModLoadingContext.get().getModEventBus().register((Object)new FeatureRegisterHandler());
    }
    
    @Override
    public void initElements() {
        this.elements.blocks.add(() -> new CustomBlock());
        final BlockItem blockItem;
        this.elements.items.add(() -> {
            new BlockItem(VoidGrassBlock.block, new Item.Properties().func_200916_a(ItemGroup.field_78030_b));
            return (Item)blockItem.setRegistryName(VoidGrassBlock.block.getRegistryName());
        });
    }
    
    @SubscribeEvent
    public void addFeatureToBiomes(final BiomeLoadingEvent event) {
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).add(() -> VoidGrassBlock.configuredFeature);
    }
    
    static {
        block = null;
        VoidGrassBlock.feature = null;
        VoidGrassBlock.configuredFeature = null;
        VoidGrassBlock.CUSTOM_MATCH = null;
    }
    
    public static class CustomBlock extends Block
    {
        public CustomBlock() {
            super(AbstractBlock.Properties.func_200945_a(Material.field_151576_e).func_200947_a(SoundType.field_185849_b).func_200948_a(1.0f, 10.0f).func_235838_a_(s -> 0));
            this.setRegistryName("void_grass");
        }
        
        public int func_200011_d(final BlockState state, final IBlockReader worldIn, final BlockPos pos) {
            return 15;
        }
        
        public boolean canSustainPlant(final BlockState state, final IBlockReader world, final BlockPos pos, final Direction direction, final IPlantable plantable) {
            return true;
        }
        
        public List<ItemStack> func_220076_a(final BlockState state, final LootContext.Builder builder) {
            final List<ItemStack> dropsOriginal = (List<ItemStack>)super.func_220076_a(state, builder);
            if (!dropsOriginal.isEmpty()) {
                return dropsOriginal;
            }
            return Collections.singletonList(new ItemStack((IItemProvider)this, 1));
        }
    }
    
    private static class CustomRuleTest extends RuleTest
    {
        static final CustomRuleTest INSTANCE;
        static final Codec<CustomRuleTest> codec;
        
        public boolean func_215181_a(final BlockState blockAt, final Random random) {
            final boolean blockCriteria = false;
            return blockCriteria;
        }
        
        protected IRuleTestType<?> func_215180_a() {
            return VoidGrassBlock.CUSTOM_MATCH;
        }
        
        static {
            INSTANCE = new CustomRuleTest();
            codec = Codec.unit(() -> CustomRuleTest.INSTANCE);
        }
    }
    
    private static class FeatureRegisterHandler
    {
        @SubscribeEvent
        public void registerFeature(final RegistryEvent.Register<Feature<?>> event) {
            VoidGrassBlock.CUSTOM_MATCH = (IRuleTestType<CustomRuleTest>)Registry.func_218322_a(Registry.field_218363_D, new ResourceLocation("obsidian_gear:void_grass_match"), (Object)(() -> CustomRuleTest.codec));
            VoidGrassBlock.feature = (Feature<OreFeatureConfig>)new OreFeature(OreFeatureConfig.field_236566_a_) {
                public boolean func_241855_a(final ISeedReader world, final ChunkGenerator generator, final Random rand, final BlockPos pos, final OreFeatureConfig config) {
                    final RegistryKey<World> dimensionType = (RegistryKey<World>)world.func_201672_e().func_234923_W_();
                    boolean dimensionCriteria = false;
                    if (dimensionType == RegistryKey.func_240903_a_(Registry.field_239699_ae_, new ResourceLocation("obsidian_gear:the_void"))) {
                        dimensionCriteria = true;
                    }
                    return dimensionCriteria && super.func_241855_a(world, generator, rand, pos, config);
                }
            };
            VoidGrassBlock.configuredFeature = (ConfiguredFeature<?, ?>)((ConfiguredFeature)((ConfiguredFeature)VoidGrassBlock.feature.func_225566_b_((IFeatureConfig)new OreFeatureConfig((RuleTest)CustomRuleTest.INSTANCE, VoidGrassBlock.block.func_176223_P(), 16)).func_242733_d(64)).func_242728_a()).func_242731_b(10);
            event.getRegistry().register(VoidGrassBlock.feature.setRegistryName("void_grass"));
            Registry.func_218322_a(WorldGenRegistries.field_243653_e, new ResourceLocation("obsidian_gear:void_grass"), (Object)VoidGrassBlock.configuredFeature);
        }
    }
}
