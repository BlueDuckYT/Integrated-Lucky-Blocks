package blueduck.ilb.event.actions;

import blueduck.ilb.event.EventAction;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.CommandContextBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.commands.ExecuteCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.server.command.CommandHelper;

public class CommandAction extends EventAction {

      public String commandToRun;


      public CommandAction(String command) {
            commandToRun = command;
      }

      public void execute(ServerLevel level, BlockPos pos, Entity entity) {
            super.execute(level, pos, entity);
//            assert Minecraft.getInstance().player != null;
//            Minecraft.getInstance().player.sendSystemMessage(Component.literal(commandToRun));
//            level.getServer().getCommands().performCommand(entity.createCommandSourceStack().withPermission(4).withSource());
            try {
                  Command command = Commands.literal(commandToRun).getCommand();
                  command.run(new CommandContextBuilder(level.getServer().getCommands().getDispatcher(), entity.createCommandSourceStack().withPermission(4), level.getServer().getCommands().getDispatcher().getRoot(), 1).build(commandToRun));
            }
            catch (Exception e) {

            }
      }

}
