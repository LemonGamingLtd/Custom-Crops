package net.momirealms.customcrops.bukkit.integration.custom.lgfurniture_r1;

import ltd.lemongaming.furniture.event.furniture.FurnitureBreakEvent;
import ltd.lemongaming.furniture.event.furniture.FurnitureInteractEvent;
import ltd.lemongaming.furniture.event.furniture.FurniturePlaceEvent;
import ltd.lemongaming.furniture.event.stringblock.StringBlockBreakEvent;
import ltd.lemongaming.furniture.event.stringblock.StringBlockInteractEvent;
import ltd.lemongaming.furniture.event.stringblock.StringBlockPlaceEvent;
import net.momirealms.customcrops.api.core.AbstractCustomEventListener;
import net.momirealms.customcrops.api.core.AbstractItemManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FurnitureListener extends AbstractCustomEventListener {

    private static final Set<Material> IGNORED = new HashSet<>(
        List.of(
            Material.NOTE_BLOCK,
            Material.TRIPWIRE,
            Material.CHORUS_PLANT
        )
    );

    @Override
    protected Set<Material> ignoredMaterials() {
        return IGNORED;
    }

    public FurnitureListener(AbstractItemManager itemManager) {
        super(itemManager);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteractFurniture(FurnitureInteractEvent event) {
        itemManager.handlePlayerInteractFurniture(
            event.getPlayer(),
            event.getBaseEntity().getLocation(),
            event.getFurnitureId(),
            event.getHand(),
            event.getItemInHand(),
            event
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteractCustomBlock(StringBlockInteractEvent event) {
        itemManager.handlePlayerInteractBlock(
            event.getPlayer(),
            event.getBlock(),
            event.getBlockId(),
            event.getBlockFace(),
            event.getHand(),
            event.getItemInHand(),
            event
        );
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onBreakFurniture(FurnitureBreakEvent event) {
        itemManager.handlePlayerBreak(
            event.getPlayer(),
            event.getBaseEntity().getLocation(),
            event.getPlayer().getInventory().getItemInMainHand(),
            event.getFurnitureId(),
            event
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreakCustomBlock(StringBlockBreakEvent event) {
        itemManager.handlePlayerBreak(
            event.getPlayer(),
            event.getBlock().getLocation(),
            event.getPlayer().getInventory().getItemInMainHand(),
            event.getBlockId(),
            event
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlaceFurniture(FurniturePlaceEvent event) {
        itemManager.handlePlayerPlace(
            event.getPlayer(),
            event.getBaseEntity().getLocation(),
            event.getFurnitureId(),
            event.getHand(),
            event.getItemInHand(),
            event
        );
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlaceCustomBlock(StringBlockPlaceEvent event) {
        itemManager.handlePlayerPlace(
            event.getPlayer(),
            event.getBlock().getLocation(),
            event.getBlockId(),
            event.getHand(),
            event.getItemInHand(),
            event
        );
    }

}
