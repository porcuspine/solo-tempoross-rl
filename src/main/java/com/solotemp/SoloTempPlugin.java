package com.solotemp;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Tempoross Solo-5 Guide"
)
public class SoloTempPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private SoloTempConfig config;
	
	@Inject
	private SoloTempOverlay overlay;
	
	@Inject
	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception
	{
		//log.info("Example started!");
		
		overlayManager.add(overlay);
		
	}

	@Override
	protected void shutDown() throws Exception
	{
		//log.info("Example stopped!");
		
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "Plugin.SoloTemp", "Hello World!", null);
		}
	}

	@Provides
	SoloTempConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SoloTempConfig.class);
	}
}
