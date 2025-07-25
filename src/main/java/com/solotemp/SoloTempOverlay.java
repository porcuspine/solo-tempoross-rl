package com.solotemp;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.inject.Inject;

import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

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
		//addMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Attack style overlay");
	}

	@Override
	public Dimension render(Graphics2D graphics)
	{
		final String textContent = "Hello world!";
		
		panelComponent.getChildren().add(TitleComponent.builder()
				.text(textContent)
				.color(Color.WHITE)
				.build());

		panelComponent.setPreferredSize(new Dimension(
			graphics.getFontMetrics().stringWidth(textContent) + 10,
			0));
		
		return super.render(graphics);
	}
}