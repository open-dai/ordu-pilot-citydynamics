package com.sampas.socbs.core.data.wms.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.geotools.data.ows.Layer;
import org.geotools.data.ows.WMSCapabilities;
import org.geotools.data.wms.WMSUtils;
import org.geotools.data.wms.WebMapServer;
import org.geotools.data.wms.request.GetFeatureInfoRequest;
import org.geotools.data.wms.request.GetLegendGraphicRequest;
import org.geotools.data.wms.request.GetMapRequest;
import org.geotools.data.wms.response.GetFeatureInfoResponse;
import org.geotools.data.wms.response.GetLegendGraphicResponse;
import org.geotools.data.wms.response.GetMapResponse;
import org.opengis.layer.Style;
import com.sampas.socbs.core.coordinatesystem.ICoordinateSystem;
import com.sampas.socbs.core.coordinatesystem.impl.SmpCoordinateSystem;
import com.sampas.socbs.core.data.impl.AWMSDataProvider;
import com.sampas.socbs.core.data.impl.SmpCapabilities;
import com.sampas.socbs.core.dataset.feature.impl.SmpMaxFeatureCount;
import com.sampas.socbs.core.geometry.IDimension;
import com.sampas.socbs.core.geometry.IEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpBoundingRectangle;
import com.sampas.socbs.core.geometry.impl.SmpDimension;
import com.sampas.socbs.core.geometry.impl.SmpEnvelope;
import com.sampas.socbs.core.geometry.impl.SmpScrDimension;
import com.sampas.socbs.core.geometry.impl.SmpScrPoint;
import com.sampas.socbs.core.maplayer.ILayerNames;
import com.sampas.socbs.core.maplayer.IWMSLayer;
import com.sampas.socbs.core.maplayer.impl.SmpLayer;
import com.sampas.socbs.core.maplayer.impl.SmpWMSLayer;
import com.sampas.socbs.core.style.IWMSNamedStyle;
import com.sampas.socbs.core.style.impl.WMSNamedStyle;
import com.sampas.socbs.core.symbology.IStyle;


@SuppressWarnings("unused")
public class WMSDataStore extends AWMSDataProvider {

	public enum WmsVersion {
		
		ver100, ver110, ver111, ver130
	}

	public enum WmsGMLVersion {
		
		gml2, gml3
	}

	public enum WmsImageReturnType {
		
		jpeg, jpg, png, gif
	}

	public enum WmsResponseType {
		plain, html
	}

	private WebMapServer wms = null;

	private URL wmsUrl = null;

	private WmsGMLVersion wmsGMLVersion = null;

	private WmsResponseType wmsResponseType = null;

	private String version = null;

	private ByteArrayOutputStream responseOutputStream;

	
	public WMSDataStore(URL wmsUrl, WmsVersion wmsVersion, WmsGMLVersion wmsGMLVersion) {

		this.wmsUrl = wmsUrl;

		if (wmsVersion.toString().compareTo("ver100") == 0) {
			
			this.version = "1.0.0";
		} else if (wmsVersion.toString().compareTo("ver110") == 0) {
			
			this.version = "1.1.0";
		}
		if (wmsVersion.toString().compareTo("ver111") == 0) {
			
			this.version = "1.1.1";
		} else if (wmsVersion.toString().compareTo("ver130") == 0) {
			
			this.version = "1.3.0";
		}
		this.wmsGMLVersion = wmsGMLVersion;
	}

	@Override
	public SmpCapabilities getCapabilities() {
		try {

			getWms().getCapabilities().setVersion(version);

			WMSCapabilities wmsCapabilities = new WMSCapabilities();

			wmsCapabilities = getWms().getCapabilities();

			SmpCapabilities smpCapabilities = new SmpCapabilities(wmsCapabilities);

			smpCapabilities.setVersion(wmsCapabilities.getVersion());
			
			smpCapabilities.setUpdateSequence(wmsCapabilities.getUpdateSequence());
			
			smpCapabilities.setService(wmsCapabilities.getService());

			if (smpCapabilities != null) {
				
				return (smpCapabilities);
			} else {
				
				return (null);
			}
		} catch (Exception e) {

			return (null);
		}
	}

	@Override
	public List<IWMSLayer> getLayerDefinitions() {

		try {
			// WebMapServer wms = new WebMapServer(wmsUrl);
			getWms().getCapabilities().setVersion(version);

			WMSCapabilities wmsCapabilities = new WMSCapabilities();

			wmsCapabilities = getWms().getCapabilities();

			Layer[] layers = WMSUtils.getNamedLayers(wmsCapabilities);
			// List<SmpLayer> smpLayers = new ArrayList<SmpLayer>();
			List<IWMSLayer> smpWmsLayers = new ArrayList<IWMSLayer>();

			int cntr = 0;

			for (int i = 0; i < layers.length; i++) {

				smpWmsLayers.add(new SmpWMSLayer());

				if (layers[i].getName() != null) {

					smpWmsLayers.get(cntr).setName(layers[i].getName());
				}
				// if (layers[i].getLatLonBoundingBox() != null) {
				//					
				// SmpEnvelope smpEnvelope = new
				// SmpEnvelope(layers[i].getLatLonBoundingBox().getMinX(),
				// layers[i].getLatLonBoundingBox().getMinY(),
				// layers[i].getLatLonBoundingBox().getMaxX(),
				// layers[i].getLatLonBoundingBox().getMaxY());
				//					
				// smpWmsLayers.get(cntr).setExtent(smpEnvelope);
				// }
				if (layers[i].getBoundingBoxes() != null) {

					// System.out.println(layers[i].getBoundingBoxes());
					String bboxStr = layers[i].getBoundingBoxes().toString();
					
					int epsgStart = bboxStr.indexOf("EPSG");
					
					int epsgEnd = bboxStr.indexOf("=");
					
					int coordinateStart = bboxStr.indexOf("[");
					
					int coordinateEnd = bboxStr.indexOf("]");

					String epsg = bboxStr.substring(epsgStart, epsgEnd);
					
					String coordinates = bboxStr.substring(coordinateStart + 1, coordinateEnd);
					// System.out.println(bboxStr);
					// System.out.println(epsg);
					// System.out.println(coordinates);
					int minXStrEnd = coordinates.indexOf(",");

					String minXStr = coordinates.substring(0, minXStrEnd);
					
					String buffer = coordinates.substring(minXStrEnd + 1);
					// System.out.println(minXStr);
					int minYStrEnd = buffer.indexOf(" ");
					
					String minYStr = buffer.substring(0, minYStrEnd + 1);
					
					buffer = buffer.substring(minYStrEnd + 1);
					// System.out.println(minYStr);
					int maxXStrEnd = buffer.indexOf(",");

					String maxXStr = buffer.substring(0, maxXStrEnd);
					// System.out.println(maxXStr);
					String maxYStr = buffer.substring(maxXStrEnd + 1);
					// System.out.println(maxYStr);
					try {

						smpWmsLayers.get(cntr).setCoordinateSystem(new SmpCoordinateSystem(epsg));

						SmpEnvelope smpEnvelope = new SmpEnvelope(Double.parseDouble(minXStr), Double.parseDouble(minYStr), Double.parseDouble(maxXStr), Double.parseDouble(maxYStr));
						
						smpWmsLayers.get(cntr).setExtent(smpEnvelope);
					} catch (Exception ex) {
						
						ex.printStackTrace();
					}
				}
				if (layers[i].getStyles() != null) {

					List<WMSNamedStyle> wmsNamedStyles = new ArrayList<WMSNamedStyle>();

					for (int k = 0; k < layers[i].getStyles().size(); k++) {

						WMSNamedStyle wmsNamedStyle = new WMSNamedStyle(((Style) layers[i].getStyles().get(k)).getName());
						
						wmsNamedStyles.add(wmsNamedStyle);
					}
					smpWmsLayers.get(cntr).setWmsNamedStyles(wmsNamedStyles);
				}
				cntr++;
			}
			if (smpWmsLayers.size() != 0) {

				return (smpWmsLayers);
			} else {
				
				return (null);
			}
		} catch (Exception e) {

			return (null);
		}
	}

	public List<IWMSLayer> getLayerDefinitions(ILayerNames layerNames) {

		try {

			getWms().getCapabilities().setVersion(version);

			WMSCapabilities wmsCapabilities = new WMSCapabilities();

			wmsCapabilities = getWms().getCapabilities();

			Layer[] layers = WMSUtils.getNamedLayers(wmsCapabilities);

			List<IWMSLayer> smpWmsLayers = new ArrayList<IWMSLayer>();

			int cntr = 0;

			for (int i = 0; i < layers.length; i++) {

				for (int j = 0; j < layerNames.getLayerNames().size(); j++) {

					if (layers[i].getName() != null && layerNames.getLayerName(j).equals(layers[i].getName())) {

						smpWmsLayers.add(new SmpWMSLayer());

						smpWmsLayers.get(cntr).setName(layers[i].getName());

						if (layers[i].getBoundingBoxes() != null) {

							String minXStr = "";

							String minYStr = "";

							String maxXStr = "";

							String maxYStr = "";

							String epsg = "";

							if (!layers[i].getBoundingBoxes().isEmpty()) {

								String bboxStr = layers[i].getBoundingBoxes().toString();

								int epsgStart = bboxStr.indexOf("EPSG");

								int epsgEnd = bboxStr.indexOf("=");

								int coordinateStart = bboxStr.indexOf("[");

								int coordinateEnd = bboxStr.indexOf("]");

								epsg = bboxStr.substring(epsgStart, epsgEnd);

								String coordinates = bboxStr.substring(coordinateStart + 1, coordinateEnd);

								int minXStrEnd = coordinates.indexOf(",");

								minXStr = coordinates.substring(0, minXStrEnd);

								String buffer = coordinates.substring(minXStrEnd + 1);

								int minYStrEnd = buffer.indexOf(" ");

								minYStr = buffer.substring(0, minYStrEnd + 1);

								buffer = buffer.substring(minYStrEnd + 1);

								int maxXStrEnd = buffer.indexOf(",");

								maxXStr = buffer.substring(0, maxXStrEnd);

								maxYStr = buffer.substring(maxXStrEnd + 1);
							} else if (!layers[i].getLatLonBoundingBox().equals("")) {

								minXStr = Double.toString(layers[i].getLatLonBoundingBox().getMinX());

								minYStr = Double.toString(layers[i].getLatLonBoundingBox().getMinY());

								maxXStr = Double.toString(layers[i].getLatLonBoundingBox().getMaxX());

								maxYStr = Double.toString(layers[i].getLatLonBoundingBox().getMaxY());

								if (layers[i].getLatLonBoundingBox().getEPSGCode() == null) {

									return null;
								} else {
									// Set it to default
									epsg = "EPSG:4326";
								}
							} else {

								return null;
							}
							try {

								smpWmsLayers.get(cntr).setCoordinateSystem(new SmpCoordinateSystem(epsg));

								SmpEnvelope smpEnvelope = new SmpEnvelope(Double.parseDouble(minXStr), Double.parseDouble(minYStr), Double.parseDouble(maxXStr), Double.parseDouble(maxYStr));

								smpWmsLayers.get(cntr).setExtent(smpEnvelope);
							} catch (Exception ex) {

								return null;
							}
						}
						if (layers[i].getStyles() != null) {

							List<WMSNamedStyle> wmsNamedStyles = new ArrayList<WMSNamedStyle>();

							for (int k = 0; k < layers[i].getStyles().size(); k++) {

								WMSNamedStyle wmsNamedStyle = new WMSNamedStyle(((Style) layers[i].getStyles().get(k)).getName());

								wmsNamedStyles.add(wmsNamedStyle);
							}
							smpWmsLayers.get(cntr).setWmsNamedStyles(wmsNamedStyles);
						}
						cntr++;
					}
				}
			}
			if (smpWmsLayers.size() != 0) {

				return (smpWmsLayers);
			} else {

				return (null);
			}

		} catch (Exception ex) {

			return (null);
		}
	}

	/**
	 * @return the wms
	 */
	public WebMapServer getWms() {

		if (wms == null && wmsUrl != null) {
			
			try {
				
				wms = new WebMapServer(wmsUrl);
			} catch (Exception e) {
				
				System.out.println("ERROR: Can not initialize Map Server at Url:" + wmsUrl);
				
				System.out.println("--- Error Mesasage and Stack Trace is :");
				
				System.out.println(e.getMessage());
				
				if (e.getCause() != null) {
				
					System.out.print(e.getCause().getMessage());
				}
				e.printStackTrace();
			}
		}
		return wms;
	}

	@Override
	public String[] getLayerNames() {

		try {

			getWms().getCapabilities().setVersion(version);

			WMSCapabilities wmsCapabilities = new WMSCapabilities();

			wmsCapabilities = getWms().getCapabilities();

			Layer[] layers = WMSUtils.getNamedLayers(wmsCapabilities);

			String[] strLayers = new String[layers.length];

			if (layers.length != 0) {
				for (int i = 0; i < layers.length; i++) {
					strLayers[i] = layers[i].getName().toString();
				}

				return (strLayers);
			} else {
				
				return (null);
			}

		} catch (Exception e) {

			return (null);
		}
	}

	@Override
	public GetFeatureInfoResult getFeatureInfo(SmpLayer smpLayer, SmpBoundingRectangle bbox, SmpCoordinateSystem smpCoordinateSystem, SmpDimension smpDimension, SmpScrPoint smpScrPoint, WmsResponseType wmsResponseType, WmsImageReturnType wmsImageReturnType, SmpMaxFeatureCount maxFeatureCount) {

		GetFeatureInfoResult getFeatureInfoResult = null;

		try {

			GetMapRequest getMapRequest = getWms().createGetMapRequest();

			getMapRequest.setVersion(version);

			getMapRequest.setBBox(bbox.getMinX() + "," + bbox.getMinY() + "," + bbox.getMaxX() + "," + bbox.getMaxY());

			getMapRequest.setDimensions((int) smpDimension.getWidth(), (int) smpDimension.getHeight());

			if (wmsImageReturnType == WmsImageReturnType.gif) {
				
				getMapRequest.setFormat("image/gif");
			} else if (wmsImageReturnType == WmsImageReturnType.jpeg) {
				
				getMapRequest.setFormat("image/jpeg");
			} else if (wmsImageReturnType == WmsImageReturnType.png) {
				
				getMapRequest.setFormat("image/png");
			} else if (wmsImageReturnType == WmsImageReturnType.jpg) {
				
				getMapRequest.setFormat("image/jpeg");
			}
			getMapRequest.setSRS(smpCoordinateSystem.getEPSGCode());

			getMapRequest.setTransparent(true);

			GetFeatureInfoRequest getFeatureInfoRequest = getWms().createGetFeatureInfoRequest(getMapRequest);
			// getFeatureInfoRequest.addQueryLayer(smpWMSLayer.getGeoToolsLayer());
			getFeatureInfoRequest.setQueryPoint(smpScrPoint.getScrX(), smpScrPoint.getScrY());

			getFeatureInfoRequest.setProperty("VERSION", version);

			if (wmsResponseType == WmsResponseType.html) {
				
				getFeatureInfoRequest.setProperty("INFO_FORMAT", "text/html");
			} else {
				
				getFeatureInfoRequest.setProperty("INFO_FORMAT", "text/plain");
			}
			getFeatureInfoRequest.setProperty("LAYERS", smpLayer.getName());
			
			getFeatureInfoRequest.setProperty("QUERY_LAYERS", smpLayer.getName());
			// System.out.println(getMapRequest.getFinalURL().toString());
			// System.out.println(getFeatureInfoRequest.getFinalURL().toString());
			GetFeatureInfoResponse getFeatureInfoResponce = getWms().issueRequest(getFeatureInfoRequest);

			getFeatureInfoResult = new GetFeatureInfoResult();

			ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();

			int temp = 0;

			while ((temp = getFeatureInfoResponce.getInputStream().read()) != -1) {

				responseOutputStream.write(temp);
			}
			getFeatureInfoResult.setFeatures(responseOutputStream.toString());

			return (getFeatureInfoResult);

		} catch (Exception e) {

			getFeatureInfoResult.setException(e.getMessage());
			
			return (getFeatureInfoResult);
		}
	}

	@Override
	public GetFeatureInfoResult getFeatureInfo(String layer, SmpBoundingRectangle bbox, SmpCoordinateSystem smpCoordinateSystem, SmpDimension smpDimension, SmpScrPoint smpScrPoint, WmsResponseType wmsResponseType, WmsImageReturnType wmsImageReturnType, SmpMaxFeatureCount maxFeatureCount, String style) {

		GetFeatureInfoResult getFeatureInfoResult = null;

		try {

			GetMapRequest getMapRequest = getWms().createGetMapRequest();

			getMapRequest.setVersion(version);

			getMapRequest.addLayer(layer, style);

			getMapRequest.setBBox(bbox.getMinX() + "," + bbox.getMinY() + "," + bbox.getMaxX() + "," + bbox.getMaxY());

			getMapRequest.setDimensions((int) smpDimension.getWidth(), (int) smpDimension.getHeight());

			if (wmsImageReturnType == WmsImageReturnType.gif) {
				
				getMapRequest.setFormat("image/gif");
			} else if (wmsImageReturnType == WmsImageReturnType.jpeg) {
				
				getMapRequest.setFormat("image/jpeg");
			} else if (wmsImageReturnType == WmsImageReturnType.png) {
				
				getMapRequest.setFormat("image/png");
			} else if (wmsImageReturnType == WmsImageReturnType.jpg) {
				
				getMapRequest.setFormat("image/jpeg");
			}
			getMapRequest.setSRS(smpCoordinateSystem.getEPSGCode());

			getMapRequest.setTransparent(true);

			GetFeatureInfoRequest getFeatureInfoRequest = getWms().createGetFeatureInfoRequest(getMapRequest);

			getFeatureInfoRequest.setQueryPoint(smpScrPoint.getScrX(), smpScrPoint.getScrY());

			getFeatureInfoRequest.setProperty("VERSION", version);

			getFeatureInfoRequest.setProperty("INFO_FORMAT", wmsResponseType.toString());
			
			getFeatureInfoRequest.setProperty("LAYERS", layer);
		
			getFeatureInfoRequest.setProperty("QUERY_LAYERS", layer);
			// System.out.println(getMapRequest.getFinalURL().toString());
			// System.out.println(getFeatureInfoRequest.getFinalURL().toString());
			GetFeatureInfoResponse getFeatureInfoResponce = getWms().issueRequest(getFeatureInfoRequest);

			getFeatureInfoResult = new GetFeatureInfoResult();

			ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();

			int temp = 0;

			while ((temp = getFeatureInfoResponce.getInputStream().read()) != -1) {
				
				responseOutputStream.write(temp);
			}

			getFeatureInfoResult.setFeatures(responseOutputStream.toString());

			return (getFeatureInfoResult);

		} catch (Exception e) {

			getFeatureInfoResult.setException(e.getMessage());
			
			return (getFeatureInfoResult);
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public GetMapResult getMap(SmpLayer[] layers, SmpCoordinateSystem smpCoordinateSystem, SmpBoundingRectangle bbox, SmpScrDimension smpScrDimension, WmsImageReturnType wmsImageReturnType, String style) {

		GetMapResult mapResult = new GetMapResult();

		try {

			GetMapRequest getMapRequest = getWms().createGetMapRequest();

			getMapRequest.setVersion(version);

			getMapRequest.setDimensions(smpScrDimension.getWidth(), smpScrDimension.getHeight());

			for (int i = 0; i < layers.length; i++) {
				getMapRequest.addLayer(layers[i].getName(), style);
			}

			// System.out.println(getMapRequest.getProperties().toString());

			getMapRequest.setSRS(smpCoordinateSystem.getEPSGCode());

			if (wmsImageReturnType == WmsImageReturnType.gif) {
				
				getMapRequest.setFormat("image/gif");
			} else if (wmsImageReturnType == wmsImageReturnType.jpeg) {
				
				getMapRequest.setFormat("image/jpeg");
			} else if (wmsImageReturnType == WmsImageReturnType.png) {
				
				getMapRequest.setFormat("image/png");
			} else if (wmsImageReturnType == WmsImageReturnType.jpg) {
				
				getMapRequest.setFormat("image/jpeg");
			}
			getMapRequest.setBBox(bbox.getMinX() + "," + bbox.getMinY() + "," + bbox.getMaxX() + "," + bbox.getMaxY());

			GetMapResponse getMapResponse = getWms().issueRequest(getMapRequest);

			if (getMapResponse.getContentType() == "text/xml") {

				mapResult = new GetMapResult();
				
				mapResult.setException("");
			} else {

				InputStream responseInputStream = getMapResponse.getInputStream();

				responseOutputStream = new ByteArrayOutputStream();

				int temp = 0;

				while ((temp = responseInputStream.read()) != -1) {
					
					responseOutputStream.write(temp);
				}
				// mapResult = new GetMapResult();
				mapResult.setImage(responseOutputStream.toByteArray());
				responseOutputStream = null;
			}
			// System.out.println(getMapRequest.getProperties().toString());
			return (mapResult);

		} catch (Exception e) {

			mapResult.setException(e.getMessage());
			return (mapResult);
		}
	}

	@Override
	public GetMapResult getMap(String[] layers, SmpCoordinateSystem smpCoordinateSystem, SmpBoundingRectangle bbox, SmpScrDimension smpScrDimension, WmsImageReturnType wmsImageReturnType, String style) {

		GetMapResult mapResult = new GetMapResult();

		try {

			GetMapRequest getMapRequest = getWms().createGetMapRequest();

			getMapRequest.setVersion(version);

			getMapRequest.setDimensions(smpScrDimension.getWidth(), smpScrDimension.getHeight());

			for (int i = 0; i < layers.length; i++) {
				getMapRequest.addLayer(layers[i], style);
			}

			// System.out.println(getMapRequest.getProperties().toString());

			getMapRequest.setSRS(smpCoordinateSystem.getEPSGCode());

			if (wmsImageReturnType == WmsImageReturnType.gif) {
				
				getMapRequest.setFormat("image/gif");
			} else if (wmsImageReturnType == WmsImageReturnType.jpeg) {
				
				getMapRequest.setFormat("image/jpeg");
			} else if (wmsImageReturnType == WmsImageReturnType.png) {
				
				getMapRequest.setFormat("image/png");
			} else if (wmsImageReturnType == WmsImageReturnType.jpg) {
				
				getMapRequest.setFormat("image/jpeg");
			}
			getMapRequest.setBBox(bbox.getMinX() + "," + bbox.getMinY() + "," + bbox.getMaxX() + "," + bbox.getMaxY());
			// System.out.println(getMapRequest.getFinalURL());
			GetMapResponse getMapResponse = wms.issueRequest(getMapRequest);

			if (getMapResponse.getContentType() == "text/xml") {
				// mapResult = new GetMapResult();
				mapResult.setException("");
			} else {

				InputStream responseInputStream = getMapResponse.getInputStream();

				responseOutputStream = new ByteArrayOutputStream();

				int temp = 0;

				while ((temp = responseInputStream.read()) != -1) {
					responseOutputStream.write(temp);
				}
				// mapResult = new GetMapResult();
				mapResult.setImage(responseOutputStream.toByteArray());
				responseOutputStream = null;
			}
			// System.out.println(getMapRequest.getProperties().toString());
			return (mapResult);

		} catch (Exception e) {

			mapResult.setException(e.getMessage());
			
			return (mapResult);
		}
	}

	@SuppressWarnings("static-access")
	public GetMapResult getMap(List<IWMSLayer> layers, List<IWMSNamedStyle> styles, ICoordinateSystem smpCoordinateSystem, IEnvelope bbox, IDimension dimension, WmsImageReturnType wmsImageReturnType) {

		GetMapResult mapResult = new GetMapResult();

		try {

			GetMapRequest getMapRequest = getWms().createGetMapRequest();

			getMapRequest.setVersion(version);
			// SmpScrDimension fittedDimension =
			// getFittedDimension(bbox,smpScrDimension);
			int widthPointPlace = String.valueOf(dimension.getWidth()).indexOf(".");
			
			String widthStr = String.valueOf(dimension.getWidth()).substring(0, widthPointPlace);

			int heightPointPlace = String.valueOf(dimension.getHeight()).indexOf(".");
			
			String heightStr = String.valueOf(dimension.getHeight()).substring(0, heightPointPlace);

			getMapRequest.setDimensions(widthStr, heightStr);
			// getMapRequest.setDimensions(String.valueOf(dimension.getWidth()),
			// String.valueOf(dimension.getHeight()));
			// getMapRequest.setDimensions(fittedDimension.getWidth(),
			// fittedDimension.getHeight());
			if (styles == null || styles.size() == 0) {
				for (int i = 0; i < layers.size(); i++) {
					getMapRequest.addLayer(layers.get(i).getName(), "");
				}
			} else if (styles != null && layers.size() == styles.size()) {
				for (int i = 0; i < layers.size(); i++) {
					getMapRequest.addLayer(layers.get(i).getName(), styles.get(i).getName());
				}
			} else {
				mapResult.setException("Layer size and style size NOT MATCH...");
				return (mapResult);
			}
			// System.out.println(getMapRequest.getProperties().toString());
			getMapRequest.setSRS(smpCoordinateSystem.getEPSGCode());

			if (wmsImageReturnType == WmsImageReturnType.gif) {
				
				getMapRequest.setFormat("image/gif");
			} else if (wmsImageReturnType == wmsImageReturnType.jpeg) {
				
				getMapRequest.setFormat("image/jpeg");
			} else if (wmsImageReturnType == WmsImageReturnType.png) {
				
				getMapRequest.setFormat("image/png");
			} else if (wmsImageReturnType == WmsImageReturnType.jpg) {
				
				getMapRequest.setFormat("image/jpeg");
			}
			getMapRequest.setBBox(bbox.getMinX() + "," + bbox.getMinY() + "," + bbox.getMaxX() + "," + bbox.getMaxY());

			URL finalUrl = getMapRequest.getFinalURL();

			System.out.println(finalUrl);

			GetMapResponse getMapResponse = getWms().issueRequest(getMapRequest);

			if (getMapResponse.getContentType() == "text/xml") {
				// mapResult = new GetMapResult();
				mapResult.setException("");
			} else {

				InputStream responseInputStream = getMapResponse.getInputStream();

				responseOutputStream = new ByteArrayOutputStream();

				int temp = 0;

				while ((temp = responseInputStream.read()) != -1) {
					responseOutputStream.write(temp);
				}
				// mapResult = new GetMapResult();

				mapResult.setImage(responseOutputStream.toByteArray());

				responseOutputStream = null;
			}
			// System.out.println(getMapRequest.getProperties().toString());
			return (mapResult);

		} catch (Exception e) {

			mapResult.setException(e.getMessage());

			return (mapResult);
		}
	}

	public BufferedImage getLayerStyleLegend(IWMSLayer wmsLayer, IStyle style, IDimension iconDimension, WmsImageReturnType returnType) {

		try {

			GetLegendGraphicRequest legendRequest = getWms().createGetLegendGraphicRequest();

			legendRequest.setLayer(wmsLayer.getName());
			
			legendRequest.setStyle(style.getName());
			
			legendRequest.setHeight(String.valueOf((int) iconDimension.getHeight()));
			
			legendRequest.setWidth(String.valueOf((int) iconDimension.getWidth()));
			// Test
			// legendRequest.setProperty("TITLE", "Denemeee");
			// legendRequest.setRule(style.getName());
			// Properties properties = legendRequest.getProperties();
			if (returnType != null) {

				if (returnType.compareTo(WmsImageReturnType.png) == 0) {

					legendRequest.setFormat("image/png");
				} else if (returnType.compareTo(WmsImageReturnType.jpg) == 0) {

					legendRequest.setFormat("image/jpg");
				} else if (returnType.compareTo(WmsImageReturnType.jpeg) == 0) {

					legendRequest.setFormat("image/jpeg");
				} else if (returnType.compareTo(WmsImageReturnType.gif) == 0) {

					legendRequest.setFormat("image/gif");
				}
			}
			GetLegendGraphicResponse legendResponce = getWms().issueRequest(legendRequest);

			InputStream legendStream = legendResponce.getInputStream();

			BufferedImage bufferedImage = ImageIO.read(legendStream);

			return bufferedImage;

		} catch (Exception ex) {

			System.out.println("Error on getting legend graphic" + ex);
		}
		return null;
	}

	private SmpScrDimension getFittedDimension(SmpBoundingRectangle bbox, SmpScrDimension smpScrDimension) {

		double width = Math.min(smpScrDimension.getWidth(), smpScrDimension.getHeight() * (bbox.getWidth() / bbox.getHeight()));

		double height = Math.min(smpScrDimension.getHeight(), smpScrDimension.getWidth() * (bbox.getHeight() / bbox.getWidth()));

		return new SmpScrDimension((int) width, (int) height);
	}

}
