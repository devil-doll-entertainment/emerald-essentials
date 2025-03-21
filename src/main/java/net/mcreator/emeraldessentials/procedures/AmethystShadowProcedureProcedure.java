package net.mcreator.emeraldessentials.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;

import net.mcreator.emeraldessentials.init.EmeraldEssentialsModEnchantments;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AmethystShadowProcedureProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player);
		}
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		ItemStack iteminLegs = ItemStack.EMPTY;
		double enchantLevel = 0;
		iteminLegs = (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY);
		enchantLevel = EnchantmentHelper.getItemEnchantmentLevel(EmeraldEssentialsModEnchantments.AMETHYST_SHADOW.get(), iteminLegs);
		if (EnchantmentHelper.getItemEnchantmentLevel(EmeraldEssentialsModEnchantments.AMETHYST_SHADOW.get(), iteminLegs) != 0 && entity.isSprinting()) {
			if (enchantLevel < 2) {
				if (entity instanceof LivingEntity _entity)
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 1));
			}
		} else if (EnchantmentHelper.getItemEnchantmentLevel(EmeraldEssentialsModEnchantments.AMETHYST_SHADOW.get(), iteminLegs) != 0 && entity.isShiftKeyDown()) {
			if (entity instanceof LivingEntity _entity)
				_entity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20, 1));
		}
	}
}
