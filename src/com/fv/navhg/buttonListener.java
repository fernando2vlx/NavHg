package com.fv.navhg;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class buttonListener implements OnClickListener 
{

	private LocManagerGps lmg;
	private int idactivar,idsalir;
	private boolean state;
	
	public buttonListener(LocManagerGps lm)
	{
		lmg=lm;
		state=true;
	}
	
	public void setId (int ida,int ids)
	{
		idactivar=ida;
		idsalir=ids;
	}
	
	@Override
	public void onClick(View button) 
	{
		Button b=(Button)button;
		if(b.getId()==idactivar)
		{
			if(state)
			{
			 lmg.startReception();
			}
		}
		else
		{
			if(b.getId()==idsalir)
			{
				lmg.stopReception();
				lmg.exit();
			}
		}
	}

}
