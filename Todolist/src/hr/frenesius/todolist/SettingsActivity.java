package hr.frenesius.todolist;

import hr.frenesius.data.DbHelper;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class SettingsActivity extends ActionBarActivity {
	SharedPreferences SHAREDPREFS;
	Button b1;
	Button b2;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		Button b1 = (Button) findViewById(R.id.settingsButton1);
		b1.setOnClickListener(b1Listener);
		
		Button b2 = (Button) findViewById(R.id.settingsResetButton1);
		b2.setOnClickListener(b2Listener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}


	View.OnClickListener b1Listener = new View.OnClickListener() {
		public void onClick(View v) {
		EditText e1 = (EditText) findViewById(R.id.settingsEditText1);
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		
		SHAREDPREFS.edit().putString("Name", e1.getText().toString()).commit();
		MainActivity.user.setName(e1.getText().toString());
			Toast.makeText(getApplicationContext(), "Name changed to: "+ MainActivity.user.getName(), Toast.LENGTH_SHORT).show();
		intentMainActivity();
		
		}
	};
	View.OnClickListener b2Listener = new View.OnClickListener() {
		public void onClick(View v) {
			//TODO ARE U SURE?
			resetGame();
			
		}
	};
	
	private void resetGame(){
		clearSharedPrefs();
		clearDatabase();
		clearHabits();
		//intentMainActivity();
		restartGame();
	}
	
	private void clearSharedPrefs(){
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		SHAREDPREFS.edit().clear().commit();
	}
	private void clearDatabase(){
		DbHelper dbHelper = new DbHelper(this);
		dbHelper.addDatabaseVersion();
	}
	private void clearHabits(){
		MainActivity.goodHabitlist.clear();
		MainActivity.badHabitlist.clear();
	}
	private void restartGame(){
		Intent i = getBaseContext().getPackageManager()
					.getLaunchIntentForPackage( getBaseContext().getPackageName() );
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
	private void intentMainActivity(){
		Intent i = new Intent();
		i.setClass(this, MainActivity.class);
		startActivity(i);
		finish();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_settings,
					container, false);
			return rootView;
		}
	}

}
