package com.solotemp;

import com.solotemp.SoloTempPlugin;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class SoloTempPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SoloTempPlugin.class);
		RuneLite.main(args);
	}
}
