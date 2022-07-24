package me.duncangaming.skills;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;

public class Mining  {
    HashMap miningLvl = new HashMap();
    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        Player p = e.getPlayer();
        Integer newPoints = getValue(e.getBlock());
        if (miningLvl.get(p) != null) {
            Integer currentPoints = (Integer) miningLvl.get(p) + newPoints;
            miningLvl.put(p, currentPoints);
            if (newPoints != 0) {
                p.sendMessage("You now have " + currentPoints + "mining points!");
            }
        } else {
            miningLvl.put(p, newPoints);
            if (newPoints == 0) {
                p.sendMessage("You now have " + newPoints + "mining points!");
                p.sendMessage("You are now mining level 1");
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        e.getBlock().setMetadata("PLACED", new FixedMetadataValue(Skills.plugin, "true"));

    }

    public int getValue(Block block) {
        if (block.getMetadata("PLACED").equals(null) && block.getType() == Material.STONE ) {
            return 1;
        }
        if (block.getType() == Material.COAL_ORE && block.getMetadata("PLACED").equals(null)) {
            return 3;
        }
        if (block.getType() == Material.IRON_ORE && block.getMetadata("PLACED").equals(null)) {
            return 5;
        }
        if (block.getType() == Material.GOLD_ORE && block.getMetadata("PLACED").equals(null)) {
            return 8;
        }
        if (block.getType() == Material.DIAMOND_ORE && block.getMetadata("PLACED").equals(null)) {
            return 12;
        }
        return 0;
    }
}
