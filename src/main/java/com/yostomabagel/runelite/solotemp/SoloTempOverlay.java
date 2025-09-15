package com.yostomabagel.runelite.solotemp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
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
	
	private Color badItemColor = Color.red;
	private Color dryBucketColor = Color.blue;
	private Color fishDepositColor = Color.green;
	private Color fishKeepColor = Color.yellow;
	
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
		
		int numFish = 0;
		int numBuckets = 0;
		int numOthers = 0;
		
		for (Widget itemWidget : itemWidgets) 
		{
			Rectangle bounds = itemWidget.getBounds();
			
			Color outlineColor = null;
			
			switch (itemWidget.getItemId())
			{
			case SoloTempPlugin.HARPOONFISHRAW_ID:
				break;
			case SoloTempPlugin.HARPOONFISHCOOKED_ID:
				if (plugin.getCurrentGuideStep().getFishToDeposit() < 0) break;
				numFish++;
				if (numFish <= plugin.getCurrentGuideStep().getFishToDeposit() - plugin.getFishShotCount())
					//TODO config option for fish to drop
					outlineColor = fishDepositColor;
				else
					//TODO config option for fish to keep
					outlineColor = fishKeepColor;
				break;
			case SoloTempPlugin.BUCKET_ID:
				//TODO config option for dry bucket reminder
				outlineColor = dryBucketColor;
				break;
			case SoloTempPlugin.WATERBUCKET_ID:
				//TODO config option for extra items warning
				if (plugin.getCurrentGuideStep().getDesiredBuckets() < 0) break;
				numBuckets++;
				if (numBuckets > plugin.getCurrentGuideStep().getDesiredBuckets())
					outlineColor = badItemColor;
				break;
			default:
				//TODO config option for extra items warning
				if (plugin.getCurrentGuideStep().getDesiredOtherItems() < 0) break;
				numOthers++;
				if (numOthers > plugin.getCurrentGuideStep().getDesiredOtherItems())
					outlineColor = badItemColor;
				break;
			}
			
			if (outlineColor != null)
			{
				graphics.drawImage(
					itemManager.getItemOutline(itemWidget.getItemId(), itemWidget.getItemQuantity(), outlineColor),
					bounds.x, bounds.y, null);
			}
		}
		
		return null;
	}
}
