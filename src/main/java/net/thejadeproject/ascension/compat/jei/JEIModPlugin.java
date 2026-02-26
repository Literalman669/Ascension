package net.thejadeproject.ascension.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.thejadeproject.ascension.AscensionCraft;
import net.thejadeproject.ascension.blocks.ModBlocks;
import net.thejadeproject.ascension.events.ModDataComponents;
import net.thejadeproject.ascension.items.ModItems;
import net.thejadeproject.ascension.recipe.LowHumanPillCauldronRecipe;
import net.thejadeproject.ascension.recipe.ModRecipes;

import java.util.List;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    private static IJeiRuntime jeiRuntime;

    @Override
    public void registerItemSubtypes(ISubtypeRegistration registration) {
        registration.registerSubtypeInterpreter(ModItems.PHYSIQUE_ESSENCE.get(), (IIngredientSubtypeInterpreter<ItemStack>) (stack, context) -> {
            String physiqueId = stack.get(ModDataComponents.PHYSIQUE_ID.get());
            Integer purity = stack.get(ModDataComponents.PURITY.get());
            if (physiqueId == null || purity == null) {
                return IIngredientSubtypeInterpreter.NONE;
            }
            return physiqueId + "|purity=" + purity;
        });

        registration.registerSubtypeInterpreter(ModItems.FORMATION_PLATE.get(), (IIngredientSubtypeInterpreter<ItemStack>) (stack, context) -> {
            String formationId = stack.get(net.lucent.formation_arrays.data_components.ModDataComponents.FORMATION_PLATE_COMPONENT);
            return formationId == null ? IIngredientSubtypeInterpreter.NONE : formationId;
        });
    }

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(AscensionCraft.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new PillCauldronRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (Minecraft.getInstance().level == null) {
            return;
        }
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<LowHumanPillCauldronRecipe> cauldronRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.CAULDRON_LOW_HUMAN_TYPE.get()).stream()
                .map(RecipeHolder::value)
                .toList();

        registration.addRecipes(PillCauldronRecipeCategory.CAULDRON_RECIPE_TYPE, cauldronRecipes);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(
                new ItemStack(ModBlocks.PILL_CAULDRON_HUMAN_LOW.get()),
                PillCauldronRecipeCategory.CAULDRON_RECIPE_TYPE
        );
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime runtime) {
        jeiRuntime = runtime;
    }

    public static IJeiRuntime getJeiRuntime() {
        return jeiRuntime;
    }
}
