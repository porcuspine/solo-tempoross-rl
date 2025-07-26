package com.solotemp;

import com.google.inject.Provides;

import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ItemContainer;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
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
	private static final int HARPOONFISHRAW_ID = 25564;
	private static final int HARPOONFISHCOOKED_ID = 25565;
	private static final int EMPTYBUCKET_ID = 1925;
	private static final int WATERBUCKET_ID = 1929;
	
	private static final int TEMPO_REG_ID = 12078;
	
	private GuideStep guideStep = GuideStep.Inactive;
	public GuideStep getCurrentGuideStep() {return guideStep;}
	
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
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOADING) {
			int[] regionIDs = client.getTopLevelWorldView().getMapRegions();
			for (int regionID : regionIDs) {
				if (regionID == TEMPO_REG_ID) {
					if (guideStep == GuideStep.Inactive) {
						goToStep(GuideStep.Start);
					}
					return;
				}
			}
			guideStep = GuideStep.Inactive;
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		//if (guideStep == GuideStep.Inactive) return;
		
		ItemContainer inventory = event.getItemContainer();
		
//		if (inventory == null || inventory.getId() != InventoryID.INVENTORY.getId())
//			return;
		
		if (guideStep.isStepFailed()) guideStep = GuideStep.Failed;
		else if (guideStep.isStepCompleted()) goToStep(guideStep.getNextStep());
		
	}
	
	private void goToStep(GuideStep step) 
	{
		guideStep = step;
		while (guideStep != GuideStep.Inactive && guideStep.isStepCompleted()) {
			guideStep = guideStep.getNextStep();
		}
	}

	@Provides
	SoloTempConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SoloTempConfig.class);
	}
}
