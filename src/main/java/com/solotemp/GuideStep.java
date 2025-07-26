package com.solotemp;

public enum GuideStep {
	Failed(null,
			"Guide has been desynchronized."),
	Inactive(null,
			"Guide is inactive (you shouldn't be seeing this text!)"),
	AttackFive(GuideStep.Inactive,
			"Fish at the spirit pool!"),
	LoadFourTwo(GuideStep.AttackFive,
			"Load the remaining 9 harpoonfish into the OTHER cannon."),
	LoadFourOne(GuideStep.LoadFourTwo,
			"Load EXACTLY 9 harpoonfish."),
	AttackFour(GuideStep.LoadFourOne,
			"Do NOT fish at the spirit pool.\nAcquire 9 more cooked harpoonfish for a total of 18."),
	LoadThreeThird(GuideStep.AttackFour,
			"Load EXACTLY 13 harpoonfish into the OTHER cannon.\nKeep 9 harpoonfish."),
	LoadThreeSecond(GuideStep.LoadThreeThird,
			"Once Tempoross has submerged, load EXACTLY 5 harpoonfish and then stop."),
	LoadThreeFirst(GuideStep.LoadThreeSecond,
			"Load EXACTLY 1 harpoonfish, then stop."),
	FishThree(GuideStep.LoadThreeFirst,
			"Do NOT fish at the spirit pool.\nAcquire 28 cooked harpoonfish."),
	AttackThree(GuideStep.FishThree,
			"Do NOT fish at the spirit pool.\nDrop your bucket for 28 free inventory slots."),
	LoadTwoSecond(GuideStep.AttackThree,
			"Load EXACTLY 17 harpoonfish, then stop. You should have one remaining."),
	LoadTwoFirst(GuideStep.LoadTwoSecond,
			"Load EXACTLY 9 harpoonfish, then stop (e.g. go refill your bucket)."),
	FishTwo(GuideStep.LoadTwoFirst,
			"Acquire 27 cooked harpoonfish.\nOnly fish at double spots, and cook when there isn't one active."),
	AttackOne(GuideStep.FishTwo,
			"Fish at the spirit pool."),
	LoadOneSecond(GuideStep.AttackOne,
			"Load your remaining 9 harpoonfish."),
	LoadOneFirst(GuideStep.LoadOneSecond,
			"Load EXACTLY 18 harpoonfish, then stop (e.g. go refill your bucket)."),
	FishOne(GuideStep.LoadOneFirst,
			"Acquire 27 cooked harpoonfish.\nOnly fish at double spots, and cook when there isn't one active.") {
		@Override public boolean isStepCompleted() {
			//player has 27 cooked harpoonfish
			return false;
		}
	},
	Start(GuideStep.FishOne,
			"Grab a bucket and fill it with water.\nYou should have 27 free inventory slots.") {
		@Override public boolean isStepCompleted() {
			//player has exactly 27 free slots and a waterbucket, or player has a harpoonfish and 27 - harpoonfishcount slots
			return false;
		}
	};

	private final GuideStep nextStep;
	private final String stepText;
	
	GuideStep(GuideStep nextStep, String stepText) {
		this.nextStep = nextStep;
		this.stepText = stepText;
	}
	
	public GuideStep getNextStep() {return nextStep;}
	public String getStepText() {return stepText;}
	
	public boolean isStepCompleted() { //TODO make abstract
		return false;
	}
	
	public boolean isStepFailed() {
		return false;
	}
}
