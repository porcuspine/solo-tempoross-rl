package com.yostomabagel.runelite.solotemp;

import com.yostomabagel.runelite.solotemp.resources.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

class SoloTempTextOverlay extends OverlayPanel
{
	private final SoloTempPlugin plugin;
	private final SoloTempConfig config;
	
	@Inject
	private SoloTempTextOverlay(SoloTempPlugin plugin, SoloTempConfig config)
	{
		super(plugin);
		setPosition(OverlayPosition.TOP_LEFT);
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		//TODO config options
		
		panelComponent.getChildren().clear();
		int lineWidth = 0;
		
		for (String textLine : plugin.getCurrentGuideStep().getStepText()) {
			panelComponent.getChildren().add(LineComponent.builder()
					.left(textLine)
					.leftColor(Color.LIGHT_GRAY)
					.build());
			lineWidth = Math.max(lineWidth, graphics.getFontMetrics().stringWidth(textLine));
		}
	
		panelComponent.setPreferredSize(new Dimension(lineWidth + 10, 0));
		
		return super.render(graphics);
	}
}

class SoloTempInventoryOverlay extends Overlay 
{
	@Inject private Client client;
	private final ItemManager itemManager;
	private final SoloTempPlugin plugin;
	private final SoloTempConfig config;
	
	@Inject
	SoloTempInventoryOverlay(ItemManager itemManager, SoloTempPlugin plugin, SoloTempConfig config)
	{
		super(plugin);
		this.itemManager = itemManager;
		this.plugin = plugin;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_WIDGETS);
	}
	
	public Dimension render(Graphics2D graphics) 
	{
		Widget inventoryWidget = client.getWidget(WidgetInfo.INVENTORY);
		if (inventoryWidget == null || inventoryWidget.isHidden()) return null;
		
		Widget[] itemWidgets = inventoryWidget.getDynamicChildren();
		
		for (int i = 0; i < itemWidgets.length; i++) 
		{
			Widget itemWidget = itemWidgets[i];
			//Item item = items[i];
			Rectangle bounds = itemWidget.getBounds();
			
			//if (item == null) continue;
			
			graphics.drawImage(itemManager.getItemOutline(itemWidget.getItemId(), itemWidget.getItemQuantity(), Color.red), bounds.x, bounds.y, null);
		}
		
		return null;
	}
}
