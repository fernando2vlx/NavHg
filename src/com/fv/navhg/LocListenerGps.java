package com.fv.navhg;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class LocListenerGps implements LocationListener 
{

	private LocManagerGps locManagerGps;
	
	public LocListenerGps(LocManagerGps lm)
	{
		super();
		locManagerGps=lm;
	}
	
	@Override
	public void onLocationChanged(Location loc) 
	{
	    locManagerGps.showLocation(loc);
	}

	@Override
	public void onProviderDisabled(String provider) 
	{
	  //Toast.makeText(locManagerGps.getApplicationContext(), "GPS Desactivado", Toast.LENGTH_SHORT).show();
	  //Intent intent=new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	  //locManagerGps.startActivity(intent);
	}

	@Override
	public void onProviderEnabled(String arg0) 
	{
		//Toast.makeText(locManagerGps.getApplicationContext(), "GPS Activado", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) 
	{
		
		
	}

}
