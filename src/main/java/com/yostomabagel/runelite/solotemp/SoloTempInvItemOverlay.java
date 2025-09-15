package com.yostomabagel.runelite.solotemp;

import java.awt.Graphics2D;

import javax.inject.Inject;

import net.runelite.api.ItemContainer;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

public class SoloTempInvItemOverlay extends WidgetItemOverlay
{
	private static final int HARPOONFISHRAW_ID = 25564;
	private static final int HARPOONFISHCOOKED_ID = 25565;
	private static final int WATERBUCKET_ID = 1929;
	
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
	public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem)
	{
		ItemContainer inventory = plugin.getPlayerInv();
		if (inventory == null) return;
		
		int harpRawCount = 0;
		int harpCookCount = 0;
		int otherItemCount = 0;
		int emptyItemCount = 0;
		
		for (int i = 0; i < inventory.getItems().length; i++) 
		{
			if (inventory.getItems()[i].getId() == HARPOONFISHRAW_ID) 
			{
				harpRawCount++;
				
			}
		}
	}
	
}
