package net.thejadeproject.ascension.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.thejadeproject.ascension.entity.ModEntities;
import net.thejadeproject.ascension.entity.custom.shaders.RiftEntity;

public class ShaderSummonerItem extends Item {
    public ShaderSummonerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Calculate position 2 blocks in front of the player
            Vec3 lookDirection = player.getLookAngle();
            double distance = 2.0;

            double spawnX = player.getX() + lookDirection.x * distance;
            double spawnY = player.getY() + player.getEyeHeight() + lookDirection.y * distance;
            double spawnZ = player.getZ() + lookDirection.z * distance;

            RiftEntity rift = new RiftEntity(ModEntities.RIFT.get(), level);
            rift.setPos(spawnX, spawnY, spawnZ);
            level.addFreshEntity(rift);
        }

        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide);
    }
}