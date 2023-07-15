package com.chefmoon.ubesdelight.item.enumeration;

import com.chefmoon.ubesdelight.item.ConsumableItem;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

import java.util.function.Supplier;

public enum FoodItem {

    //Whole vegetables
    UBE(2, .4f),
    GARLIC(2, .4f),
    GINGER(2, .4f),
    LEMONGRASS(2, .4f),

    //Finger Foods TODO: Add status effects
    SINANGAG(6, .4f, null ,.0f, false, false, false),
    KINILAW(8, .6f, null ,.0f, true, false, false),
    LUMPIA(8, .7f, null ,.0f, true, false, false),
    TOCINO(10, .7f, null ,.0f, true, false, false),
    CHICKEN_INASAL(10, .75f, null ,.0f, true, false, false),

    //MealS TODO: Add status effects
    CHICKEN_INASAL_RICE(13, .75f, null ,.0f, true, false, false),
    TOSILOG(13, .75f, null ,.0f, true, false, false),
    BANGSILOG(13, .75f, null ,.0f, true, false, false),
    SISIG(13, .7f, null ,.0f, true, false, false),
    BULALO(14, .75f, null ,.0f, true, false, false),
    ARROZ_CALDO(14, .75f, null ,.0f, true, false, false),
    MECHADO(14, .75f, null ,.0f, true, false, false),


    //Drinkables
    CONDENSED_MILK_BOTTLE(0, 0, () -> new StatusEffectInstance(StatusEffects.SPEED, ConsumableItem.SHORT_DURATION, 0), 1.f, false, false, true),
    FISH_SAUCE_BOTTLE(0, 0, () -> new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, ConsumableItem.SHORT_DURATION, 0), 1.f, false, false, true),

    //Milk Teas
    MILK_TEA_UBE(0, 0, () -> new StatusEffectInstance(StatusEffects.NIGHT_VISION, ConsumableItem.SHORT_DURATION, 0), 1.f, false, false, true),
    HALO_HALO(0, 0, null, .0f, false, false, true),

    //Non-whole vegetables
    GARLIC_CLOVES(1, .4f),

    //Sweets
    LECHE_FLAN(3, .4f),
    COOKIES(2, .1f, null ,.0f, false, true, false),
    POLVORONE(2, .2f, null ,.0f, false, true, false);

    private final Supplier<FoodComponent> food;
    FoodItem(int hunger, float saturation) {
        this(hunger, saturation, null, .0f, false, false, false);
    }

    FoodItem(int hunger, float saturation, boolean isMeat) {
        this(hunger, saturation, null, .0f, isMeat, false, false);
    }

    FoodItem(int hunger, float saturation, Supplier<StatusEffectInstance> effect, float effectChance) {
        this(hunger, saturation, effect, effectChance, false, false, false);
    }

    FoodItem(int hunger, float saturation, Supplier<StatusEffectInstance> effect, float effectChance, boolean isMeat) {
        this(hunger, saturation, effect, effectChance, isMeat, false, false);
    }
    FoodItem(int hunger, float saturation, Supplier<StatusEffectInstance> effect, float effectChance, boolean isMeat, boolean isFastToEat, boolean alwaysEdible) {
        food = () -> {
            FoodComponent.Builder builder = new FoodComponent.Builder();
            builder.hunger(hunger).saturationModifier(saturation);
            if (effect != null) {
                builder.statusEffect(effect.get(), effectChance);
            }
            if (isMeat) {
                builder.meat();
            }
            if (isFastToEat) {
                builder.snack();
            }
            if (alwaysEdible) {
                builder.alwaysEdible();
            }
            return builder.build();
        };
    }

    public FoodComponent get() {
        return food.get();
    }
}