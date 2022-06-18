package com.liverookie.voidcore;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.item.Item;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("BlockRegen Enabled sucesfully");
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Block block = event.getBlock();
        int blockid = block.getId();

        Player player = event.getPlayer();
        {
            switch (blockid) {
                case Block.IRON_ORE:
                    event.setDrops(new Item[]{Item.get(Item.IRON_ORE, 0, 1)});
                case Block.GOLD_ORE:
                    event.setDrops(new Item[]{Item.get(Item.GOLD_ORE, 0, 1)});
                case Block.COAL_ORE:
                    event.setDrops(new Item[]{Item.get(Item.COAL_ORE, 0, 1)});
                case Block.COBBLESTONE:
                    event.setDrops(new Item[]{Item.get(Item.COBBLESTONE, 0, 1)});
                case Block.STONE:
                    event.setDrops(new Item[]{Item.get(Item.COBBLESTONE, 0, 1)});
                case Block.DIAMOND_ORE:
                    event.setDrops(new Item[]{Item.get(Item.GOLD_INGOT, 0, 1)});
                case Block.EMERALD_ORE:
                    event.setDrops(new Item[]{Item.get(Item.EMERALD, 0, 1)});
                case Block.REDSTONE_ORE:
                    event.setDrops(new Item[]{Item.get(Item.REDSTONE, 0, 1)});
            }

        }
    }
}
