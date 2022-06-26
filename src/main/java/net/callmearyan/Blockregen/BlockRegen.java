package net.callmearyan.Blockregen;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.command.PluginCommand;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import cn.nukkit.utils.Config;
import com.nukkitx.fakeinventories.inventory.FakeInventories;
import net.callmearyan.Blockregen.Commands.BlockRegenCommand;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BlockRegen extends PluginBase implements Listener {

    public static Config conf_blocks;


    @Override
    public void onEnable() {
        File conf_emotes = new File(getDataFolder(), "Blocks.yml");
        if (!conf_emotes.exists()) {
            try {
                conf_emotes.createNewFile();
                Config yml_blocks = new Config(conf_emotes);

                List<Integer> BlockList = new ArrayList<>();
                BlockList.add(1);
                BlockList.add(1);
                yml_blocks.set("Blocks", BlockList);
                yml_blocks.save();

            } catch (IOException e) {
                getLogger().info("can't load Blocks.yml");
            }


        }


        Config yml_blocks = new Config(conf_emotes);
        conf_blocks = yml_blocks;
        yml_blocks.save(conf_emotes);


        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("BlockRegen Enabled sucesfully");
        ((PluginCommand<?>) getCommand("setbl")).setExecutor(new BlockRegenCommand(this));
    }


    @EventHandler
    public void onBreak(BlockBreakEvent e) {


        BlockID BlockID = e.getBlock();
        Player player = e.getPlayer();
        Block PlacedBlock = e.getBlock();
        int intBlockID = e.getBlock().getId();


        Item ee = player.getInventory().getItemInHand();

        ee.setCompoundTag(e.getItem().getCompoundTag());
        player.getInventory().setItem(10, ee);

        List<Integer> LS_bl = conf_blocks.getIntegerList("Blocks");


        if (LS_bl.contains(BlockID)) {
            player.sendMessage(e.getBlock().getId() + ":" + e.getBlock().getDamage());


            getServer().getScheduler().scheduleDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    PlacedBlock.getLevel().setBlock(PlacedBlock, Block.get(Block.BEDROCK));
                }
            }, 1);

            getServer().getScheduler().scheduleDelayedTask(this, new Runnable() {
                @Override
                public void run() {
                    PlacedBlock.getLevel().setBlock(PlacedBlock, Block.get(intBlockID));
                }
            }, 80);


        } else {

        }
    }
}
