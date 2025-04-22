package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContextBuilder;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class MessageAction extends EventAction {

      public String messageToShow;


      public MessageAction(String message) {
            messageToShow = message;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
            entity.sendSystemMessage(Component.literal(messageToShow));
      }

}
