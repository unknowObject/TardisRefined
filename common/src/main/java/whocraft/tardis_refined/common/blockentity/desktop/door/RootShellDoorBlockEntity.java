package whocraft.tardis_refined.common.blockentity.desktop.door;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import whocraft.tardis_refined.common.blockentity.desktop.InternalDoorBlockEntity;
import whocraft.tardis_refined.common.capability.TardisLevelOperator;
import whocraft.tardis_refined.common.tardis.interior.exit.AbstractEntityBlockDoor;
import whocraft.tardis_refined.registry.BlockEntityRegistry;

import java.util.Optional;

public class RootShellDoorBlockEntity extends AbstractEntityBlockDoor {

    public RootShellDoorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.ROOT_SHELL_DOOR.get(), blockPos, blockState);
    }

    public void onRightClick(Level level, Player player) {
        if (!level.isClientSide()) {
            Optional<TardisLevelOperator> operator = TardisLevelOperator.get((ServerLevel) level);
            operator.ifPresent(x -> {
                x.setInternalDoor(this);
                x.exitTardis(player);
            });
        }
    }
}