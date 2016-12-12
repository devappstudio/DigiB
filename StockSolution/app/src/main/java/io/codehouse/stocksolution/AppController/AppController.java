package io.codehouse.stocksolution.AppController;


import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppController extends Application {

	public static final String TAG = AppController.class.getSimpleName();

	private static AppController mInstance;
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		Realm.init(getApplicationContext());
		RealmConfiguration config = new RealmConfiguration.Builder()
				.schemaVersion(3)
				.deleteRealmIfMigrationNeeded()
				.build();
			}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

}
