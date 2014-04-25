package com.fv.navhg;

import com.fv.navhg.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class LocManagerGps extends Activity
{
    private LocationManager locManager;
    private LocListenerGps listenerGps;
    private Button aceptar,salir;
    private buttonListener butListener;
    
    
    public boolean isEnabledGPS()
    {
    	return locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    
    public void updatePosition()
    {
    	locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,listenerGps);
    }
    
    @Override
	public void onCreate(Bundle savedInstanceState)
    {
       super.onCreate(savedInstanceState);
 	   setContentView(R.layout.location_gps);
 	   
 	   locManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
  	   listenerGps=new LocListenerGps(this);
  	   butListener=new buttonListener(this);
  	   ((TextView) findViewById(R.id.latitud)).setText("Latitud: Sin Dato");
 	   ((TextView) findViewById(R.id.longitud)).setText("Longuitud: Sin Dato");
 	   ((TextView) findViewById(R.id.presicion)).setText("Presicion: Sin Dato");
 	   aceptar=(Button)findViewById(R.id.activar);
 	   aceptar.setOnClickListener(butListener);
 	   salir=(Button)findViewById(R.id.desactivar);
 	   salir.setOnClickListener(butListener);
 	   butListener.setId(aceptar.getId(), salir.getId());
 	   checkGps();
    }
    
    public void checkGps()
    {
    	if(!isEnabledGPS())
    	{
    		AlertDialog.Builder alertPwnGps = new AlertDialog.Builder(this);
     		alertPwnGps.setTitle("Activar GPS");
     		alertPwnGps.setMessage("Se necesita activar Gps");
     		alertPwnGps.setCancelable(false);
     		alertPwnGps.setNegativeButton("Cancelar", 
         			new DialogInterface.OnClickListener()
         	        {
    			     public void onClick(DialogInterface dialog, int arg1) 
    			     {
    			       dialog.cancel();
    			       finish();
    			     }
    		    });
     		alertPwnGps.setPositiveButton("Activar", 
     			new DialogInterface.OnClickListener() 
     		    {
				
				  public void onClick(DialogInterface dialog, int arg1) 
				  {
					  Intent intent=new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
					  startActivity(intent);
					  /*if(!isEnabledGPS())
					  {
					      /*acciones a hacer sin gps activo
						  aceptar.setEnabled(false);
					  }*/
				  }
			});
     	   AlertDialog adialog = alertPwnGps.create();
     	   adialog.show();
    	}
    }
    
     
    public void startReception()
    {
       //List<String> providers = locManager.getProviders(true);
       //for(String provider : providers)
       
        Location loc=locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
  	    showLocation(loc);
  	    updatePosition();
    }
    
    public void stopReception()
    {
    	if(isEnabledGPS())
    	{
    		locManager.removeUpdates(listenerGps);
    	}
    }
    
    public void exit()
    {
    	finish();
    }
    
    public void showLocation(Location loc)
    {
    	if(loc != null)
    	{
    	  ((TextView) findViewById(R.id.latitud)).setText("Latitud "+String.valueOf(loc.getLatitude()));
    	  ((TextView) findViewById(R.id.longitud)).setText("Longuitud "+String.valueOf(loc.getLongitude()));
    	  ((TextView) findViewById(R.id.presicion)).setText("Presicion "+String.valueOf(loc.getAccuracy()));
    	}
    	else
    	{
    	  ((TextView) findViewById(R.id.latitud)).setText("Latitud: error");
      	  ((TextView) findViewById(R.id.longitud)).setText("Longuitud: error");
      	  ((TextView) findViewById(R.id.presicion)).setText("Presicion: error");
    	}
    }
}
