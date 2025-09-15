package com.yostomabagel.runelite.solotemp.resources;

import com.yostomabagel.runelite.solotemp.SoloTempPlugin;

public enum GuideStep
{
	Failed(null)
	{
		{
			this.stepText = new String[] {
				"Guide has been desynchronized...!"};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			return false;
		}
	},
	
	Inactive(null)
	{
		{
			this.stepText = new String[] {
				"Guide is inactive (you shouldn't be seeing this text!)"};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			return false;
		}
	},
	
	AttackFive(GuideStep.Inactive)
	{
		{
			this.stepText = new String[] {
				"Fish at the spirit pool!"};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			return false;
		}
	},
	
	LoadFourTwo(GuideStep.AttackFive)
	{
		{
			this.stepText = new String[] {
				"Load the remaining 9 harpoonfish into the OTHER cannon.",
				"Then fish at the spirit pool to achieve victory!"};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 0 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 0);
		}
	},
	
	LoadFourOne(GuideStep.LoadFourTwo)
	{
		{
			this.stepText = new String[] {
				"Load EXACTLY 9 harpoonfish."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 9 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 9);
		}
	},
	
	AttackFour(GuideStep.LoadFourOne)
	{
		{
			this.stepText = new String[] {
				"Do NOT fish at the spirit pool.",
				"Acquire 9 more cooked harpoonfish for 18 total."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 18 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 18);
		}
	},
	
	LoadThreeThird(GuideStep.AttackFour)
	{
		{
			this.stepText = new String[] {
				"Load EXACTLY 13 harpoonfish into the OTHER cannon.",
				"Keep 9 harpoonfish."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 9 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 9);
		}
	},
	
	LoadThreeSecond(GuideStep.LoadThreeThird)
	{
		{
			this.stepText = new String[] {
				"Wait for Tempoross to submerge,",
				"then load EXACTLY 5 harpoonfish and stop."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 22 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 22);
		}
	},
	
	LoadThreeFirst(GuideStep.LoadThreeSecond)
	{
		{
			this.stepText = new String[] {
				"Load EXACTLY 1 harpoonfish, then stop."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 27 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 27);
		}
	},
	
	FishThree(GuideStep.LoadThreeFirst)
	{
		{
			this.stepText = new String[] {
					"Do NOT fish at the spirit pool.",
					"Acquire 28 cooked harpoonfish."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 28 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 28);
		}
	},
	
	AttackThree(GuideStep.FishThree)
	{
		{
			this.stepText = new String[] {
					"Do NOT fish at the spirit pool!",
					"Grab and fill enough buckets to put out any fires,",
					"then drop your bucket(s) for 28 slots of fish."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has one item and it is a cooked harpoonfish, or has no items
			return (context.getPlayerInv().count() == 1 && context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 1)
					|| context.getPlayerInv().count() == 0;
		}
	},
	
	LoadTwoSecond(GuideStep.AttackThree)
	{
		{
			this.stepText = new String[] {
					"Load EXACTLY 17 harpoonfish, then stop.",
					"(You should have one remaining.)"};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 1 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 1);
		}
	},
	
	LoadTwoFirst(GuideStep.LoadTwoSecond)
	{
		{
			this.stepText = new String[] {
				"Load EXACTLY 9 harpoonfish, then stop."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 18 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 18);
		}
	},
	
	FishTwo(GuideStep.LoadTwoFirst)
	{
		{
			this.stepText = new String[] {
					"Fish at the spirit pool until it disappears,",
					"then acquire 27 cooked harpoonfish.",
					"Fish at double spots and cook otherwise."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 27 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 27);
		}
	},
	
//	@Deprecated
//	AttackOne(
//			GuideStep.FishTwo,
//			new String[] {
//				"Fish at the spirit pool."}
//			) {
//		@Override public boolean isStepCompleted(SoloTempPlugin context) {
//			return false;
//		}
//	},
	
	LoadOneSecond(GuideStep.FishTwo)
	{
		{
			this.stepText = new String[] {
					"Once Tempoross has submerged,",
					"load your remaining 9 harpoonfish."};
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 0 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 0);
		}
	},
	
	LoadOneFirst(GuideStep.LoadOneSecond)
	{
		{
			this.stepText = new String[] {
				"Load EXACTLY 18 harpoonfish, then stop."};
			this.desiredCookedHarp = 9;
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 9 cooked harpoonfish
			return (context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 9);
		}
	},
	
	FishOne(GuideStep.LoadOneFirst)
	{
		{
			this.stepText = new String[] {
					"Acquire 27 cooked harpoonfish.",
					"Fish at double spots and cook otherwise."};
			this.desiredEmptySlots = 0;
			this.desiredOtherItems = 0;
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has 27 cooked harpoonfish
			return context.getPlayerInv().count(HARPOONFISHCOOKED_ID) == 27;
		}
	},
	
	Start(GuideStep.FishOne)
	{
		{
			stepText = new String[] {
					"Grab a bucket and fill it with water.",
					"You should have 27 free inventory slots."};
			this.desiredBuckets = 1;
			this.desiredOtherItems = 0;
		}
		
		@Override public boolean isStepCompleted(SoloTempPlugin context)
		{
			if (context.getPlayerInv() == null) return false;
			//player has exactly 27 free slots and a water bucket, or player has a harpoonfish and space for 27 harpoonfish total
			if (context.getPlayerInv().count() == 1 && context.getPlayerInv().contains(WATERBUCKET_ID))
				return true;
			if (context.getPlayerInv().contains(HARPOONFISHRAW_ID)) {//if player started fishing, just make sure they can carry enough
				return 28 - context.getPlayerInv().count() >= 27 - context.getPlayerInv().count(HARPOONFISHRAW_ID);
			}
			return false;
		}
	};

	private static final int HARPOONFISHRAW_ID = 25564;
	private static final int HARPOONFISHCOOKED_ID = 25565;
	private static final int WATERBUCKET_ID = 1929;
	
	protected GuideStep nextStep;
	protected String[] stepText;
	protected int desiredCookedHarp = -1;
	protected int desiredBuckets = -1;
	protected int desiredEmptySlots = -1;
	protected int desiredOtherItems = -1;
	
	public int getDesiredCookedHarp() { return desiredCookedHarp; }
	public int getDesiredBuckets() { return desiredBuckets; }
	public int getDesiredEmptySlots() { return desiredEmptySlots; }
	public int getDesiredOtherItems() { return desiredOtherItems; }
	public String[] getStepText() { return stepText; }
	
	GuideStep(GuideStep nextStep) { this.nextStep = nextStep; }
	
	public GuideStep resolveToNextStep(SoloTempPlugin context) { return resolveToNextStep(context, true); }
	
	public GuideStep resolveToNextStep(SoloTempPlugin context, boolean doCheckCompletion) {
		if (!doCheckCompletion) return this.nextStep;
		if (this.nextStep == GuideStep.Inactive) return this.nextStep;
		if (this.nextStep.isStepCompleted(context)) return this.nextStep.resolveToNextStep(context);
		else return this.nextStep;
	}
	
	public abstract boolean isStepCompleted(SoloTempPlugin context);
	
	public boolean isStepFailed(SoloTempPlugin context) {
		return false;
	}
}
