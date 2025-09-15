package com.yostomabagel.runelite.solotemp;

import com.google.inject.Provides;
import com.yostomabagel.runelite.solotemp.resources.GuideStep;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.gameval.InventoryID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Tempoross Solo-5 Guide",
	description = "Aid for the 5-round solo strategy for Tempoross.",
	enabledByDefault = true
)
public class SoloTempPlugin extends Plugin
{
	
	private static final int TEMPOROSS_REGION_ID = 12078;
	
	private GuideStep guideStep = GuideStep.Inactive;
	public GuideStep getCurrentGuideStep() {return guideStep;}
	
	private ItemContainer playerInv;
	public ItemContainer getPlayerInv() {return playerInv;}
	
	@Inject
	private Client client;

	@Inject
	private SoloTempConfig config;
	
	@Inject
	private SoloTempInstructionsOverlay overlay;
	
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
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() != GameState.LOADING) {
			guideStep = GuideStep.Inactive;
			return;
		}
		
		if (!(client.getPlayers().size() == 1)) {
			guideStep = GuideStep.Inactive;
			return;
		}
		
		boolean isAtTempoross = false;
		for (int regionID : client.getTopLevelWorldView().getMapRegions()) {
			if (regionID == TEMPOROSS_REGION_ID) {
				isAtTempoross = true;
				break;
			}
		}
		if (!isAtTempoross) {
			guideStep = GuideStep.Inactive;
			return;
		}
		
		guideStep = GuideStep.Start;
		if (guideStep.isStepCompleted(this)) guideStep = guideStep.resolveToNextStep(this);
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (guideStep == GuideStep.Inactive) return;
		
		ItemContainer eventInv = event.getItemContainer();
		
		if (eventInv == null || eventInv.getId() != InventoryID.INV)
			return;
		
		playerInv = eventInv;
		
		if (guideStep.isStepFailed(this)) guideStep = GuideStep.Failed;
		else if (guideStep.isStepCompleted(this)) guideStep = guideStep.resolveToNextStep(this);
	}

	@Provides
	SoloTempConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SoloTempConfig.class);
	}
}
