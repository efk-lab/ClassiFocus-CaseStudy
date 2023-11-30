package com.classifocus.classifieds.constant;

public enum ClassifiedCategory {

	ESTATE(1), VEHICLE(2), SHOPPING(3), OTHERS(4);

	private int value;

	ClassifiedCategory(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	static public boolean isMember(ClassifiedCategory classifiedCategory) {

		boolean isMember = false;

		ClassifiedCategory[] classifiedCategoryList = ClassifiedCategory.values();
		for (ClassifiedCategory classifiedCategoryItem : classifiedCategoryList) {
			if (classifiedCategoryItem.name().equals(classifiedCategory.name().toUpperCase())) {
				isMember = true;
			}
		}

		return isMember;
	}

	static public ClassifiedCategory nameOf(int value) {

		ClassifiedCategory[] classifiedCategoryList = ClassifiedCategory.values();
		for (ClassifiedCategory classifiedCategoryItem : classifiedCategoryList) {
			if (classifiedCategoryItem.value == value) {
				return classifiedCategoryItem;
			}
		}

		return null;
	}
}
