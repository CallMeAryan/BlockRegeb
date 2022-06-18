package com.liverookie.voidcore;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.inventory.InventoryClickEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemSteak;
import cn.nukkit.plugin.PluginBase;
import com.liverookie.voidcore.Events.BlockBreak;

import java.util.UUID;

public class Main extends PluginBase implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("BlockRegen Enabled sucesfully");
    }


    @EventHandler
    public void onInventory(InventoryClickEvent e){




        Player player = e.getPlayer();

        Item ab = e.getInventory().getItem(0);

        if(ab.isNull()){


            player.sendMessage("Its a item");

        } else {

            player.sendMessage("Its Air");

        }

        player.sendMessage(String.valueOf(e.getSlot()));


    }


    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Block block = e.getBlock();
        int blockid = block.getId();

        Player player = e.getPlayer();
        {
            Block item = Block.get(Block.IRON_ORE);

            block.getBlock().equals(Block.get(blockid));



//            switch (blockid) {
//                case Block.IRON_ORE:
//                    event.setDrops(new Item[]{Item.get(Item.IRON_ORE, 0, 1)});
//                case Block.GOLD_ORE:
//                    event.setDrops(new Item[]{Item.get(Item.GOLD_ORE, 0, 1)});
//                case Block.COAL_ORE:
//                    event.setDrops(new Item[]{Item.get(Item.COAL_ORE, 0, 1)});
//                case Block.COBBLESTONE:
//                    event.setDrops(new Item[]{Item.get(Item.COBBLESTONE, 0, 1)});
//                case Block.STONE:
//                    event.setDrops(new Item[]{Item.get(Item.COBBLESTONE, 0, 1)});
//                case Block.DIAMOND_ORE:
//                    event.setDrops(new Item[]{Item.get(Item.GOLD_INGOT, 0, 1)});
//                case Block.EMERALD_ORE:
//                    event.setDrops(new Item[]{Item.get(Item.EMERALD, 0, 1)});
//                case Block.REDSTONE_ORE:
//                    event.setDrops(new Item[]{Item.get(Item.REDSTONE, 0, 1)});
//            }

        }
    }
}
