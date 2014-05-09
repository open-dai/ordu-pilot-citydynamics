package com.sampas.socbs.core.symbology.impl;

import org.geotools.event.GTComponent;
import org.geotools.event.GTNote;

public class SmpGTNote {

	private GTNote geoToolsGTNote = null;

	public SmpGTNote() {

	}

	public SmpGTNote(GTNote geoToolsGTNote) {

		this.geoToolsGTNote = geoToolsGTNote;
	}

	public GTNote getGeotoolsGTNote() {

		return (this.geoToolsGTNote);
	}

	public static SmpGTNote EMPTY = new SmpGTNote() {
		
		public SmpGTComponent getParent() {
			throw new IllegalStateException("Invalid root");
		}

		
		public void setParent(SmpGTComponent newParent) {
			throw new IllegalStateException(
					"Invalid SmpGTNote (you need to create a new instance)");
		}

		
		public void setNotificationName(String name) {
			throw new IllegalStateException(
					"Invalid SmpGTNote (you need to create a new instance)");
		}

		
		public String getNotificationName() {
			return "";
		}

		
		public void setNotificationPosition(int index) {
			throw new IllegalStateException(
					"Invalid SmpGTNote (you need to create a new instance)");
		}

		
		public int getNotificationPosition() {

			return SmpGTDelta.NO_INDEX;
		}

		
		public String toString() {
			return "NO_PARENT";
		}
	};

	public SmpGTComponent getParent() {

		SmpGTComponent smpGTComponent = new SmpGTComponent(this.geoToolsGTNote
				.getParent());

		return (smpGTComponent);
	}

	public void setParent(SmpGTComponent newParent) {

		GTComponent gTComponent = newParent.getGeotoolsGTComponent();

		this.geoToolsGTNote.setParent(gTComponent);
	}

	public void setNotificationName(String name) {

		this.geoToolsGTNote.setNotificationName(name);
	}

	public String getNotificationName() {

		return (this.geoToolsGTNote.getNotificationName());
	}

	public void setNotificationPosition(int index) {

		this.setNotificationPosition(index);
	}

	public int getNotificationPosition() {

		return (this.getNotificationPosition());
	}

}
