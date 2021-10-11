// 
// Decompiled by Procyon v0.5.36
// 

package net.mcreator.obsidiangear.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.IItemProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.IItemTier;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraft.item.Item;
import net.mcreator.obsidiangear.ObsidianGearModElements.ModElement;
import net.mcreator.obsidiangear.ObsidianGearModElements;

@Tag
public class VoidiumSwordItem extends ObsidianGearModElements.ModElement
{
    @ObjectHolder("obsidian_gear:voidium_sword")
    public static final Item block;
    
    public VoidiumSwordItem(final ObsidianGearModElements instance) {
        super(instance, 49);
    }
    
    @Override
    public void initElements() {
        final SwordItem swordItem;
        this.elements.items.add(() -> {
            new SwordItem(new IItemTier() {
                public int func_200926_a() {
                    return 2500;
                }
                
                public float func_200928_b() {
                    return 8.0f;
                }
                
                public float func_200929_c() {
                    return 13.0f;
                }
                
                public int func_200925_d() {
                    return 3;
                }
                
                public int func_200927_e() {
                    return 21;
                }
                
                public Ingredient func_200924_f() {
                    return Ingredient.func_193369_a(new ItemStack[] { new ItemStack((IItemProvider)VoidiumIngotItem.block) });
                }
            }, 3, -3.0f, new Item.Properties().func_200916_a(ItemGroup.field_78037_j)) {};
            return (Item)swordItem.setRegistryName("voidium_sword");
        });
    }
    
    static {
        block = null;
    }
}