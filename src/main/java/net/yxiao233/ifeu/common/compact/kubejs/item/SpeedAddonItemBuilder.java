package net.yxiao233.ifeu.common.compact.kubejs.item;

import dev.latvian.mods.kubejs.item.ItemBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.yxiao233.ifeu.common.item.ModSpeedAddonItem;
import net.yxiao233.ifeu.common.registry.ModItems;

public class SpeedAddonItemBuilder extends ItemBuilder {
    private int tier;
    private int formTier;
    public SpeedAddonItemBuilder(ResourceLocation i) {
        super(i);
    }

    public SpeedAddonItemBuilder setTier(int tier){
        this.tier = tier;
        this.formTier = tier;
        return this;
    }

    public SpeedAddonItemBuilder setFormTier(int tier){
        this.formTier = tier;
        return this;
    }

    @Override
    public Item createObject() {
        int formTier = this.formTier == this.tier ? this.tier : this.formTier;
        return new ModSpeedAddonItem(formTier,tier, ModItems.TAB_ADDONS);
    }
}
