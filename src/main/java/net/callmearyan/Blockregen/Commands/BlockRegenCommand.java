package net.callmearyan.Blockregen.Commands;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandExecutor;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import net.callmearyan.Blockregen.BlockRegen;

import java.util.ArrayList;
import java.util.List;

import static net.callmearyan.Blockregen.BlockRegen.conf_blocks;


public class BlockRegenCommand implements CommandExecutor {

    BlockRegen main;

    public BlockRegenCommand(BlockRegen main) {

        this.main = main;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){



            Player player = (Player) sender;


            if(args.length == 1 && args[0].equalsIgnoreCase("reload") && player.hasPermission("blockregen.admin")){
                conf_blocks.reload();
                player.sendMessage(TextFormat.GREEN + "config reloaded successfully!");
            }


            if (args.length == 2 && args[0].equalsIgnoreCase("set") && player.hasPermission("blockregen.admin")){

                if (args[1].equalsIgnoreCase("helditem")){

                     Item item = player.getInventory().getItemInHand();

                     int ItemIDtoBlockID = item.getBlockId();


                    List<Integer> NewBlockList = new ArrayList<>();
                    NewBlockList.add(ItemIDtoBlockID);
                    conf_blocks.set("Blocks", NewBlockList);
                    conf_blocks.save();
                    conf_blocks.reload();

                } else {
                    try {
                        int BlockID = Integer.parseInt(args[1]);

                        List<Integer> NewBlockList = conf_blocks.getIntegerList("Blocks");
                        NewBlockList.add(BlockID);
                        conf_blocks.set("Blocks", NewBlockList);
                        conf_blocks.save();
                        conf_blocks.reload();

                        Block block = Block.get(BlockID);

                        player.sendMessage( TextFormat.GREEN + "Added " + TextFormat.YELLOW + block.getName() + TextFormat.GREEN + " Successfully");

                        return true;
                    } catch (NumberFormatException e) {
                        player.sendMessage(TextFormat.RED + "Invalid Block Id");

                    }
                }



            }else if (args.length == 2 && args[0].equalsIgnoreCase("remove") && player.hasPermission("blockregen.admin")) {

                try {
                    int BlockID = Integer.parseInt(args[1]);

                    List<String> getID = conf_blocks.getStringList("Blocks");

                    Block block = Block.get(BlockID);

                    if (getID.contains(args[1])){
                        getID.remove(args[1]);
                        player.sendMessage( TextFormat.GREEN + "Added " + TextFormat.YELLOW + block.getName() + TextFormat.GREEN + " Successfully");
                    } else {
                        player.sendMessage(TextFormat.RED + "Block Doesn't Exist. Please Enter a Valid Block");
                    }

                    conf_blocks.save();
                    return true;
                } catch (NumberFormatException e) {
                    player.sendMessage(TextFormat.RED + "Invalid Block Id");
                }


            }
        }

        return false;
    }
}
