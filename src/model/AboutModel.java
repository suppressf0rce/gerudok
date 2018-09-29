package model;

public class AboutModel {

	private boolean isLemur = false;
	private boolean isSloth = false;
	private boolean isMouse = false;
	private boolean isLion = false;
	private static AboutModel aboutModel = null;

	public static AboutModel getInstance() {
		if (aboutModel == null) {
			aboutModel = new AboutModel(false,false,false,false);
		}
		return aboutModel;
	}


	
	private AboutModel(boolean isLemur, boolean isSloth, boolean isMouse, boolean isLion) {
		super();
		this.isLemur = isLemur;
		this.isSloth = isSloth;
		this.isMouse = isMouse;
		this.isLion = isLion;
	}



	public boolean getIsLemur() {
		return isLemur;
	}

	public void setIsLemur(boolean isLemur) {
		this.isLemur = isLemur;
	}

	public boolean getIsSloth() {
		return isSloth;
	}

	public void setIsSloth(boolean isSloth) {
		this.isSloth = isSloth;
	}

	public boolean getIsMouse() {
		return isMouse;
	}

	public void setIsMouse(boolean isMouse) {
		this.isMouse = isMouse;
	}

	public boolean getIsLion() {
		return isLion;
	}

	public void setIsLion(boolean isLion) {
		this.isLion = isLion;
	}

}
