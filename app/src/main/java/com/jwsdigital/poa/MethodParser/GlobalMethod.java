package com.jwsdigital.poa.MethodParser;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by eko on 10/26/17.
 */

public class GlobalMethod{
private static Context context;
private static View view;
public static void cekLogin(Context context,String username, String password){
	Toasty.warning(context,username + " " +password,Toast.LENGTH_LONG).show();
}

}
