package com.classifocus.classifieds.constant;

public enum ClassifiedStatus {

	ACTIVE(1), PENDING_APPROVAL(2), DUPLICATE(3), PASSIVE(4);

	private int value;

	ClassifiedStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	static public boolean isMember(ClassifiedStatus classifiedStatus) {

		boolean isMember = false;

		ClassifiedStatus[] classifiedStatusList = ClassifiedStatus.values();
		for (ClassifiedStatus classifiedStatusItem : classifiedStatusList) {
			if (classifiedStatusItem.name().equals(classifiedStatus.name().toUpperCase())) {
				isMember = true;
			}
		}

		return isMember;
	}

	static public ClassifiedStatus nameOf(int value) {

		ClassifiedStatus[] classifiedStatusList = ClassifiedStatus.values();
		for (ClassifiedStatus classifiedStatusItem : classifiedStatusList) {
			if (classifiedStatusItem.value == value) {
				return classifiedStatusItem;
			}
		}

		return null;
	}
}
