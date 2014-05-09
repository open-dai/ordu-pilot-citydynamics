package com.sampas.socbs.core.provider.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import junit.framework.TestCase;

import com.sampas.socbs.core.data.IFeatureDataStore;
import com.sampas.socbs.core.data.provider.services.impl.ShapeFileDataStoreSrv;
import com.sampas.socbs.core.data.providers.IFeatureLayerProvider;
import com.sampas.socbs.core.data.providers.impl.SmpFeatureLayerProviderSrv;
import com.sampas.socbs.core.dataset.feature.IFeatureCollection;
import com.sampas.socbs.core.maplayer.IFeatureLayer;

public class ShpDataStoreJTest extends TestCase {

	private File file = null;
	
	public ShpDataStoreJTest() {
		
	}
	
	public void testDataStore() {

		try {
			
			
			file = new File("src\\test\\resources\\caddeSokak.shp");
			
			if (!file.exists()) {
				
				System.out.println("Error: on Opening File");
				this.file = promptShapeFile();
			}
				
			ShapeFileDataStoreSrv shpDataStoreCreatorSrv = new ShapeFileDataStoreSrv(file, true);
			
			IFeatureDataStore dataProvider = shpDataStoreCreatorSrv.getShapeFileDataStore();
			
			IFeatureLayerProvider layerProvider = new SmpFeatureLayerProviderSrv();
			
			List<IFeatureLayer> smpLayers = layerProvider.getLayers(dataProvider);
			
			IFeatureCollection smpFeatures = smpLayers.get(0).getFeatureCollection();
			
			////////////////////////////////////////////////////////////////////////////////////////////
			//Check results
			System.out.println("======================================================================");
			System.out.println("Test Adi : WfsDataStoreJTest JUnit Test");
			System.out.println("Elde Edilen Nesne Sayısı : " + smpFeatures.getFeaturesCount());
			System.out.println("**********************************************************************" + "\n");
			////////////////////////////////////////////////////////////////////////////////////////////
		
			//Test criteria		
			assertNotSame(0, smpFeatures.getFeaturesCount());
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	
	private static File promptShapeFile() throws FileNotFoundException {
		
		File file;		
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Open Shapefile for Reprojection");
		chooser.setFileFilter(new FileFilter() {public boolean accept(File f) {return f.isDirectory() || f.getPath().endsWith("shp")|| f.getPath().endsWith("SHP");}
		public String getDescription() {
			return "Shapefiles";
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
