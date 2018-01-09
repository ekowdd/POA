package com.jwsdigital.poa.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.jwsdigital.lib_fonts.CustomeButtonView;
import com.jwsdigital.lib_fonts.CustomeEditTextView;
import com.jwsdigital.lib_fonts.CustomeTextView;
import com.jwsdigital.poa.ApiService.WebServiceHelper;
import com.jwsdigital.poa.MethodParser.GlobalMethod;
import com.jwsdigital.poa.R;
import com.jwsdigital.poa.Views.LoginViews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{
private ProgressBar progressBar;
private ContentLoadingProgressBar contentLoadingProgressBar;
private static final int SIGN_IN_GOO = 9001;
private static final int SIGN_IN_FB = 8001;
private static final String TAG = "LoginActivity";
private GoogleApiClient mGoogleApiClient;
GoogleSignInResult result;
GoogleSignInAccount acct;
AccessTokenTracker accessTokenTracker;
AccessToken accessToken;
@BindView(R.id.login) CustomeButtonView Logins;
@BindView(R.id.username) CustomeEditTextView username;
@BindView(R.id.password) CustomeEditTextView password;
@BindView(R.id.login_button) LoginButton LoginFB;
@BindView(R.id.LoginGOOGLE) LinearLayout LoginGOOGLE;
@BindView(R.id.onRegisterForm)
CustomeTextView onRegisterForm;
CallbackManager callbackManager;
ProfileTracker profileTracker;
@Override
protected void onCreate ( Bundle savedInstanceState ) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	ButterKnife.bind(this);
	GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
			.requestEmail()
			.build();

	mGoogleApiClient = new GoogleApiClient.Builder(this)
			.enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
			.addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions)
			.build();
	callbackManager = CallbackManager.Factory.create();
	LoginFB.setReadPermissions("email");
	accessTokenTracker = new AccessTokenTracker() {
		@Override
		protected void onCurrentAccessTokenChanged ( AccessToken oldAccessToken, AccessToken currentAccessToken ) {

		}
	};
	accessToken = AccessToken.getCurrentAccessToken();
	profileTracker = new ProfileTracker() {
		@Override
		protected void onCurrentProfileChanged ( Profile oldProfile, Profile currentProfile ) {

		}
	};
	try {
		PackageInfo info = getPackageManager().getPackageInfo(
				"com.jwsdigital.poa.activity", PackageManager.GET_SIGNATURES);
		for ( Signature    signature :info.signatures ){
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(signature.toByteArray());
			Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
		}
	}catch ( PackageManager.NameNotFoundException e ){
		Log.d("API_FB_PACKAGAE", e.getMessage());
		e.fillInStackTrace();
	}catch ( NoSuchAlgorithmException e ){
		Log.d("API_FB", e.getMessage());
		e.fillInStackTrace();
	}
	LoginFB.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
		@Override
		public void onSuccess ( LoginResult loginResult ) {
			getUserDetails(loginResult);
		}

		@Override
		public void onCancel () {

		}

		@Override
		public void onError ( FacebookException error ) {
			Toasty.error(LoginActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
		}
	});
}

@OnClick(R.id.login) void Logins(){
	String mUsername = username.getText().toString();
	String mPassword = password.getText().toString();
	if ( TextUtils.isEmpty(mUsername) ){
		username.setError("Not Null");
		username.requestFocus();
	}else if ( TextUtils.isEmpty(mPassword) ){
		password.setError("Not Null");
		password.requestFocus();
	}else{
		new LoginAuth().execute((Void)null);
	}
}
//@OnClick(R.id.LoginFB) void LoginFB(){
//
//	Toasty.success(this, "Test", Toast.LENGTH_LONG).show();
//}
@OnClick(R.id.LoginGOOGLE) void LoginGOOGLE(){
	Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
	startActivityForResult(intent,SIGN_IN_GOO);
}
@OnClick(R.id.onRegisterForm) void onRegisterForm(){
	Toasty.success(this,"", Toast.LENGTH_LONG).show();
}

@Override
protected void onActivityResult ( int requestCode, int resultCode, Intent data ) {
	super.onActivityResult(requestCode, resultCode, data);
	if ( requestCode == SIGN_IN_GOO ){
		result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
		handleSignInResult(result);
	}else if ( requestCode == SIGN_IN_FB ){
		callbackManager.onActivityResult(requestCode,resultCode,data);
	}
}

protected void getUserDetails(LoginResult loginResult){
	GraphRequest data_request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
			new GraphRequest.GraphJSONObjectCallback() {
		@Override
		public void onCompleted ( JSONObject object, GraphResponse response ) {
			Log.d(TAG,object.toString() + "   " + response.toString());
			Intent intent = new Intent(LoginActivity.this,MainActivity.class);
			intent.putExtra("userProfile",object.toString());
			startActivity(intent);
		}
	});
	Bundle permission_params = new Bundle();
	permission_params.putString("fields", "id,name,email,picture.width(120).height(120)");
	data_request.setParameters(permission_params);
	data_request.executeAsync();
}
private void handleSignInResult(GoogleSignInResult result){
	Log.d(TAG, " Handler : " + result.isSuccess());
	if ( result.isSuccess() ){
		acct = result.getSignInAccount();
		Toasty.success(
				this,
				acct.getDisplayName()
						+ " "
						+ acct.getEmail(),
				Toast.LENGTH_LONG).show();
	}

}
@Override
public void onConnectionFailed ( @NonNull ConnectionResult connectionResult ) {
	Log.d(TAG, connectionResult.toString());
}

public class LoginAuth extends AsyncTask<Void, Void, String>{
	String status,response = "";
	@Override
	protected void onPreExecute () {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground ( Void... voids ) {

		RequestBody body = new FormBody.Builder()
				.add("grant_type","password")
				.add("client_id","2")
				.add("client_secret","87A0UW3aOFpOoBuxlDCdLeXQ5iPTQ0R2rVU79bA4")
				.add("username","root@email.com")
				.add("password","secret")
				.build();
		try {
			response = WebServiceHelper.doPOST("http://192.168.8.109/oauth/token",body,"");
			status = "OK";
		} catch ( IOException e ) {
			e.printStackTrace();
			status = "NOK";
		}
		return status;
	}

	@Override
	protected void onPostExecute ( String status ) {
		super.onPostExecute(status);
		if ( status.equals("OK") ){
			Toasty.success(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
		}
	}
}

@Override
protected void onResume () {
	super.onResume();
	//noinspection deprecation
	AppEventsLogger.activateApp(this);
}

@Override
protected void onPause () {
	super.onPause();
	//noinspection deprecation
	AppEventsLogger.deactivateApp(this);
}

@Override
protected void onDestroy () {
	super.onDestroy();
	accessTokenTracker.stopTracking();
	profileTracker.stopTracking();
}
}
