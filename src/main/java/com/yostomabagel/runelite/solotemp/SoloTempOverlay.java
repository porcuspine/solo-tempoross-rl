package com.yostomabagel.runelite.solotemp;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.inject.Inject;

import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

class SoloTempOverlay extends OverlayPanel
{
	private final SoloTempPlugin plugin;
	private final SoloTempConfig config;

	@Inject
	private SoloTempOverlay(SoloTempPlugin plugin, SoloTempConfig config)
	{
		super(plugin);
		setPosition(OverlayPosition.TOP_LEFT);
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
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