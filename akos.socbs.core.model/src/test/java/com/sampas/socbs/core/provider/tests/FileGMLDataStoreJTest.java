package com.sampas.socbs.core.provider.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.GMLDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IFeature;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.maplayer.IFeatureLayer;

public class FileGMLDataStoreJTest extends TestCase {

	private File file = null;
	
	public FileGMLDataStoreJTest() {
		
	}
	
	public void testDataStore() {

		try {
			
			file = new File("src\\test\\resources\\SampleSingleStreet.gml");
			
			if (!file.exists()) {
				
				System.out.println("Error: on Opening File");
				this.file = promptGMLFile();
			}
				
			GMLDataStoreSrv fileGMLDataStoreCreatorSrv = new GMLDataStoreSrv(file);
			
			IFeatureDataStore dataProvider = fileGMLDataStoreCreatorSrv.getFileGMLDataStore();
			
			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider);
			
			IFeatureCollection smpFeatures = smpLayers.get(0).getFeatureCollection();
			
			IFeature testFeature = smpFeatures.getFeatureAt(0);
			
			System.out.println(testFeature.getAttribute(0).toString());
			System.out.println(testFeature.getID());
			System.out.println(testFeature.getDefaultGeometry().getCoordinate().getX());
			System.out.println(testFeature.getFeatureType().getFeatureType());
			System.out.println(smpFeatures.getFeaturesCount());
			System.out.println(smpLayers.get(0).getCoordinateSystem().getEPSGCode());
			
			
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : File GML Data Store JUnit Test");
			System.out.println("Elde Edilen Nesne Sayısı : " + smpFeatures.getFeaturesCount());
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
		
			//Test criteria		
			assertNotSame(0, smpFeatures.getFeaturesCount());
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	
	private static File promptGMLFile() throws FileNotFoundException {
		
		File file;		
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Select GML File");
		chooser.setFileFilter(new FileFilter() {public boolean accept(File f) {return f.isDirectory() || f.getPath().endsWith("gml")|| f.getPath().endsWith("GML");}
		public String getDescription() {
			return "GML";
		} });
		
		int returnVal = chooser.showOpenDialog(null);

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			System.exit(0);
		}
		file = chooser.getSelectedFile();

		System.out.println("You chose to open this file: " + file.getName());

		if (!file.exists()) {
			throw new FileNotFoundException(file.getAbsolutePath());
		}
		
		return file;
	}

}
