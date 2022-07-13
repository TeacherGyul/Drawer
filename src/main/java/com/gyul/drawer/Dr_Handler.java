package com.gyul.drawer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Dr_Handler implements Listener {
    HashMap<Location, Inventory> map = new HashMap<>();

    @EventHandler
    public void putDrawer(BlockPlaceEvent e){
        if(e.getBlock().getType().equals(Material.BROWN_GLAZED_TERRACOTTA)){
            Block block = e.getBlock();
            Location loc = block.getLocation();
            Inventory inv = Bukkit.createInventory(null,27,"서랍");
            map.put(loc,inv);
        }
    }

    @EventHandler
    public void breakDrawer(BlockBreakEvent e){
        if(e.getBlock().getType().equals(Material.BROWN_GLAZED_TERRACOTTA)){
            if(map.containsKey(e.getBlock().getLocation())){
                Location loc = e.getBlock().getLocation();
                
                //서랍이 부서졌을 때, 아이템 드롭
                for(ItemStack i : map.get(loc).getContents()){
                    e.getBlock().getWorld().dropItem(loc, i);
                }
                
                map.remove(loc);
            }
        }
    }

    @EventHandler
    public void clickDrawer(PlayerInteractEvent e){
        if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(e.getClickedBlock().getType().equals(Material.BROWN_GLAZED_TERRACOTTA)){
                if(map.containsKey(e.getClickedBlock().getLocation())){
                    e.getPlayer().openInventory(map.get(e.getClickedBlock().getLocation()));
                }
                else{
                    e.setCancelled(true);
                }
            }

        }

    }
}
