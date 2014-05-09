package com.sampas.socbs.core.symbology.impl;

import com.sampas.socbs.core.coordinatesystem.ITransformation;
import com.sampas.socbs.core.display.IDevice;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.IGeometry;
import com.sampas.socbs.core.symbology.ISymbol;
import com.sampas.socbs.core.tools.IColor;

public class SmpSymbol implements ISymbol {
	

	public void draw(double x, double y) {
		// TODO Auto-generated method stub

	}

	public void draw(int x0, int y0, int x1, int y1) {
		// TODO Auto-generated method stub

	}

	public void draw(int[] xpoints, int[] ypoints, int npoints) {
		// TODO Auto-generated method stub

	}

	public IEnvelope getBoundary(IDevice device,
			ITransformation transformation, IGeometry geometry) {
		// TODO Auto-generated method stub
		return null;
	}

	public IColor getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public float[] getDashline() {
		// TODO Auto-generated method stub
		return null;
	}

	public IDevice getDevice() {
		// TODO Auto-generated method stub
		return null;
	}

	public float getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isUseDash() {
		// TODO Auto-generated method stub
		return false;
	}

	public void removeDevice() {
		// TODO Auto-generated method stub

	}

	public void setDevice(IDevice device, ITransformation transformation) {
		// TODO Auto-generated method stub

	}

}
