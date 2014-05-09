package com.sampas.socbs.core.dataset.feature.impl;

import java.util.ArrayList;
import java.util.List;

public class LandmarkCollection {

	private List<LandmarkDataObject> landmarkCollection = new ArrayList<LandmarkDataObject>();

	public void addLandmark(LandmarkDataObject dataObject) {
		this.landmarkCollection.add(dataObject);
	}

	public void addLandmark(LandmarkDataObject dataObject, int index) {
		this.landmarkCollection.add(index, dataObject);
	}

	public void removeLandMark(int index) {

		this.landmarkCollection.remove(index);
	}

	public int landmarkCollectionSize() {
		return this.landmarkCollection.size();
	}

	public LandmarkDataObject getLandmarkObject(int index) {
		return this.landmarkCollection.get(index);
	}

	public List<LandmarkDataObject> getLandmarkCollection() {
		return landmarkCollection;
	}

	public LandmarkDataObject getLandmarkObjectById(String id) {
		for (int i = 0; i < this.landmarkCollection.size(); i++) {
			if (this.landmarkCollection.get(i).getId().equals(id)) {
				return this.landmarkCollection.get(i);
			}
		}
		return null;
	}
}
