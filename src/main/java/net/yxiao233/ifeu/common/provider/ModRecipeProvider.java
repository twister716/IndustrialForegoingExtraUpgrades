package net.yxiao233.ifeu.common.provider;

import com.buuz135.industrial.module.ModuleCore;
import com.hrznstudio.titanium.api.IRecipeProvider;
import com.hrznstudio.titanium.block.BasicBlock;
import com.hrznstudio.titanium.recipe.generator.TitaniumRecipeProvider;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapedRecipeBuilder;
import com.hrznstudio.titanium.recipe.generator.TitaniumShapelessRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import net.yxiao233.ifeu.common.item.ModEfficiencyAddonItem;
import net.yxiao233.ifeu.common.item.ModProcessingAddonItem;
import net.yxiao233.ifeu.common.item.ModSpeedAddonItem;
import net.yxiao233.ifeu.common.registry.ModContents;
import net.yxiao233.ifeu.common.registry.ModTags;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ModRecipeProvider extends TitaniumRecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    public void register(Consumer<FinishedRecipe> consumer) {
        //Item
        ForgeRegistries.ITEMS.getEntries().forEach((reg) -> {
            //Upgrades
            if (reg.getValue() instanceof ModSpeedAddonItem speedAddonItem) {
                ((IRecipeProvider) speedAddonItem).registerRecipe(consumer);
            } else if (reg.getValue() instanceof ModEfficiencyAddonItem efficiencyAddonItem) {
                ((IRecipeProvider) efficiencyAddonItem).registerRecipe(consumer);
            } else if (reg.getValue() instanceof ModProcessingAddonItem processingAddonItem) {
                ((IRecipeProvider) processingAddonItem).registerRecipe(consumer);
            }
        });


        //Block
        ForgeRegistries.BLOCKS.getEntries().forEach((reg) -> {
            if(reg.getValue() instanceof BasicBlock){
                String s = reg.getValue().toString();
                String nameSpace = s.substring(6,s.indexOf(':'));
                if(nameSpace.equals("ifeu")){
                    ((IRecipeProvider) reg.getValue()).registerRecipe(consumer);
                }
            }
        });


        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModContents.LASER_LENS_SCULK.get())
                .requires(Ingredient.of(Arrays.stream(ModuleCore.LASER_LENS).map(itemRegistryObject -> new ItemStack(itemRegistryObject.get())).collect(Collectors.toList()).stream()))
                .requires(Items.SCULK,4)
                .save(consumer);

        TitaniumShapelessRecipeBuilder.shapelessRecipe(ModuleCore.LASER_LENS[0].get())
                .requires(ModContents.LASER_LENS_SCULK.get())
                .requires(Tags.Items.DYES_WHITE)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.NETHERITE_GEAR.get())
                .pattern(" A ").pattern("A A").pattern(" A ")
                .define('A',Tags.Items.INGOTS_NETHERITE)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.SCULK_GEAR.get())
                .pattern(" A ").pattern("ABA").pattern(" A ")
                .define('A',Items.SCULK)
                .define('B', ModTags.Items.GEARS_NETHERITE)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_SWORD.get())
                .pattern(" A ").pattern(" B ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_SWORD)
                .define('C', Tags.Items.RODS)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_PICKAXE.get())
                .pattern("AAA").pattern(" B ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_PICKAXE)
                .define('C', Tags.Items.RODS)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_AXE.get())
                .pattern("AA ").pattern("AB ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_AXE)
                .define('C', Tags.Items.RODS)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_SHOVEL.get())
                .pattern(" A ").pattern(" B ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_SHOVEL)
                .define('C', Tags.Items.RODS)
                .save(consumer);

        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_HOE.get())
                .pattern("AA ").pattern(" B ").pattern(" C ")
                .define('A',ModContents.DRAGON_STAR.get())
                .define('B',Items.NETHERITE_HOE)
                .define('C', Tags.Items.RODS)
                .save(consumer);

//        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_HELMET.get())
//                .pattern("AAA").pattern("ABA").pattern("   ")
//                .define('A',ModContents.DRAGON_STAR.get())
//                .define('B',Items.NETHERITE_HELMET)
//                .save(consumer);
//
//        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_CHESTPLATE.get())
//                .pattern("A A").pattern("ABA").pattern("AAA")
//                .define('A',ModContents.DRAGON_STAR.get())
//                .define('B',Items.NETHERITE_CHESTPLATE)
//                .save(consumer);
//
//        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_LEGGINGS.get())
//                .pattern("AAA").pattern("ABA").pattern("A A")
//                .define('A',ModContents.DRAGON_STAR.get())
//                .define('B',Items.NETHERITE_LEGGINGS)
//                .save(consumer);
//
//        TitaniumShapedRecipeBuilder.shapedRecipe(ModContents.DRAGON_STAR_BOOTS.get())
//                .pattern("   ").pattern("A A").pattern("ABA")
//                .define('A',ModContents.DRAGON_STAR.get())
//                .define('B',Items.NETHERITE_BOOTS)
//                .save(consumer);
    }
}
