package com.sampas.GeoInfoMngmnt.CityDynamics.V1;

public class CanPoiItem {
private int kurum_id;
private int tipi;
private int mahalle_id;
private int cadde_sokak_id;
private String aciklama;
private double x;
private double y;
public void setKurum_id(int kurum_id) {
	this.kurum_id = kurum_id;
}
public int getKurum_id() {
	return kurum_id;
}
public void setTipi(int tipi) {
	this.tipi = tipi;
}
public int getTipi() {
	return tipi;
}
public void setMahalle_id(int mahalle_id) {
	this.mahalle_id = mahalle_id;
}
public int getMahalle_id() {
	return mahalle_id;
}
public void setCadde_sokak_id(int cadde_sokak_id) {
	this.cadde_sokak_id = cadde_sokak_id;
}
public int getCadde_sokak_id() {
	return cadde_sokak_id;
}
public void setAciklama(String aciklama) {
	this.aciklama = aciklama;
}
public String getAciklama() {
	return aciklama;
}
public void setX(double x) {
	this.x = x;
}
public double getX() {
	return x;
}
public void setY(double y) {
	this.y = y;
}
public double getY() {
	return y;
}
}
