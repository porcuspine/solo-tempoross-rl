package com.yostomabagel.runelite.solotemp;

import java.awt.Graphics2D;

import javax.inject.Inject;

import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

public class SoloTempInvItemOverlay extends WidgetItemOverlay
{
	private final ItemManager itemManager;
	private final SoloTempPlugin plugin;
	private final SoloTempConfig config;
	
	@Inject
	private SoloTempInvItemOverlay(ItemManager itemManager, SoloTempPlugin plugin, SoloTempConfig config)
	{
		this.itemManager = itemManager;
		this.plugin = plugin;
		this.config = config;
		showOnInventory();
	}
	
	
	@Override
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
		// TODO Auto-generated method stub
		
	}
	
}
