package com.raviya.facekog.facekogsvc.model;

public class FaceResponse {
private String cropedData;
private int ageRangeLow;
private int ageRangeHigh;
private double smileConfidence;
private double eyeGlassConfidence;
private double sunGlassConfidence;
private double genderFemaleConfidence;
private double genderMaleConfidence;

private double happyConfidence;
private double sadConfidence;
private double calmConfidence;
private boolean needToRetake;

public FaceResponse() {
	
}

public FaceResponse(String cropedData,
		 int ageRangeLow,
		 int ageRangeHigh,
		 double smileConfidence,
		 double eyeGlassConfidence,
		 double sunGlassConfidence,
		 double genderFemaleConfidence,
		 double genderMaleConfidence,

		 double happyConfidence,
		 double sadConfidence,
		 double calmConfidence,
		 boolean needToRetake) {
	
	this.cropedData=cropedData;
	this.ageRangeLow=ageRangeLow;
	this.ageRangeHigh=ageRangeHigh;
	this.smileConfidence=smileConfidence;
	this.eyeGlassConfidence=eyeGlassConfidence;
	this.sunGlassConfidence=sunGlassConfidence;
	this.genderFemaleConfidence=genderFemaleConfidence;
	this.genderMaleConfidence=genderMaleConfidence;

	this.happyConfidence=happyConfidence;
	this.sadConfidence=sadConfidence;
	this.calmConfidence=calmConfidence;
	this.needToRetake=needToRetake;
	
}

public String getCropedData() {
	return cropedData;
}
public void setCropedData(String cropedData) {
	this.cropedData = cropedData;
}
public int getAgeRangeLow() {
	return ageRangeLow;
}
public void setAgeRangeLow(int ageRangeLow) {
	this.ageRangeLow = ageRangeLow;
}
public int getAgeRangeHigh() {
	return ageRangeHigh;
}
public void setAgeRangeHigh(int ageRangeHigh) {
	this.ageRangeHigh = ageRangeHigh;
}
public double getSmileConfidence() {
	return smileConfidence;
}
public void setSmileConfidence(double smileConfidence) {
	this.smileConfidence = smileConfidence;
}
public double getEyeGlassConfidence() {
	return eyeGlassConfidence;
}
public void setEyeGlassConfidence(double eyeGlassConfidence) {
	this.eyeGlassConfidence = eyeGlassConfidence;
}
public double getSunGlassConfidence() {
	return sunGlassConfidence;
}
public void setSunGlassConfidence(double sunGlassConfidence) {
	this.sunGlassConfidence = sunGlassConfidence;
}
public double getGenderFemaleConfidence() {
	return genderFemaleConfidence;
}
public void setGenderFemaleConfidence(double genderFemaleConfidence) {
	this.genderFemaleConfidence = genderFemaleConfidence;
}
public double getGenderMaleConfidence() {
	return genderMaleConfidence;
}
public void setGenderMaleConfidence(double genderMaleConfidence) {
	this.genderMaleConfidence = genderMaleConfidence;
}
public double getHappyConfidence() {
	return happyConfidence;
}
public void setHappyConfidence(double happyConfidence) {
	this.happyConfidence = happyConfidence;
}
public double getSadConfidence() {
	return sadConfidence;
}
public void setSadConfidence(double sadConfidence) {
	this.sadConfidence = sadConfidence;
}
public double getCalmConfidence() {
	return calmConfidence;
}
public void setCalmConfidence(double calmConfidence) {
	this.calmConfidence = calmConfidence;
}
public boolean isNeedToRetake() {
	return needToRetake;
}
public void setNeedToRetake(boolean needToRetake) {
	this.needToRetake = needToRetake;
}


}
