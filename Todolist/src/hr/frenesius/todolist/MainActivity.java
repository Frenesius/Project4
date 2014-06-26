package hr.frenesius.todolist;


import hr.frenesius.data.DbDatabaseCreate;
import hr.frenesius.data.DbHelper;
import hr.frenesius.list.Habit;
import hr.frenesius.list.Message;
import hr.frenesius.list.User;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableRow.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
//PositiveHabitList.clear() als een reset knop
	 
	LinearLayout ln;									//Lineare Layout
	public static boolean SATRIGGER = false;
	public static boolean goodHabitTRIGGER = false; 	//Triggerchecker voor onResume
	public static boolean badHabitTRIGGER = false; 	//Triggerchecker voor onResume
	public static User user = new User();				//Een user met zijn eigen attributen
	public static Activity MainActivityACTIVITY;		//Gebruikt in InputHabitActivity.class om MainActivity te finish()
	public final static String PREFS_NAME = "Happits";
	
	//Habit lists
	public static List<Habit> goodHabitlist 
	= new ArrayList<Habit>();							//List met alle Habit objecten
	public static List<Habit> badHabitlist 
	= new ArrayList<Habit>();							//List met alle Habit objecten
	
	int habitcounter = 1;
	//Shared preferences settings 
	SharedPreferences SHAREDPREFS;
	String name1;
	DbHelper helper;
	SQLiteDatabase db;
	DbDatabaseCreate entry;
	Cursor cursor;
	
	public static String USER_POINTS = "UserPoints";

	LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); //
	LayoutParams lptr = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	LayoutParams lpb1 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	LayoutParams lptv = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	//Params

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		firstLaunch();		//Checks if game is launched for first time
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		getUserName();
		MainActivityACTIVITY = this;
		
		helper = new DbHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		
		DatabaseSelectGoodHabit();
		DatabaseSelectBadHabit();		
		
		addHabitsToDashboard();
	}
	
	
	protected void onResume(){
		super.onResume();
		setScore();
		addRewardsToDatabase();
		try{
			updateUserPoints();
		}catch(Exception e){
			e.getStackTrace();
		}
		try{
			setUserPicture();
		}catch(Exception e){
			e.getStackTrace();
		}
	}
	private void updateUserPoints(){
		TextView tv = (TextView) findViewById(R.id.YourScore);
		
		tv.setText(user.getRewardpoint());
	}
	private void addHabitsToDashboard(){
		try{
			addGoodHabitToDashboard();			//VOEG HIER OOK DE BAD HABIT PROCESS AN TOE
			addBadHabitToDashboard();
		}catch(Exception e){
			}
	}
private void setUserPicture(){
	SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
	ImageView iv = (ImageView) findViewById(R.id.ImageViewDashMain);
	Drawable draw =getResources().getDrawable(SHAREDPREFS.getInt(RewardActivity.USER_PICTURE, 0)); 
	iv.setBackground(draw);
}	
	
//TODO wijzigen
	private void addRewardsToDatabase(){
		
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		Editor a  = SHAREDPREFS.edit();
		int i = SHAREDPREFS.getInt("Reward", 0);
		if(i == 0){
			entry.open();
			//USAGE FOR CREATEENTRYREWARD
			//int picture_lock, int picture_lock_thumb, int picture_lock_thumb_onclick,
			//int picture_unlock, int picture_unlock_thumb, int picture_unlock_thumb_onclick,
			//int picture_select, int picture_select_thumb, int picture_select_thumb_onclick,
			//String title1, String description1, int point1
		
		entry.createEntryReward( //Char1 Fist
				R.drawable.char1_lock, R.drawable.char1_lock_thumb, R.drawable.char1_lock_thumb_onclick,
				R.drawable.char1_unlock, R.drawable.char1_unlock_thumb, R.drawable.char1_unlock_thumb_onclick,
				R.drawable.char1_select, R.drawable.char1_select_thumb, R.drawable.char1_select_thumb_onclick,
				"Fist", "NULL", 100
				);
		entry.createEntryReward( //Char2 Fist
				R.drawable.char2_lock, R.drawable.char2_lock_thumb, R.drawable.char2_lock_thumb_onclick,
				R.drawable.char2_unlock, R.drawable.char2_unlock_thumb, R.drawable.char2_unlock_thumb_onclick,
				R.drawable.char2_select, R.drawable.char2_select_thumb, R.drawable.char2_select_thumb_onclick,
				"Fist", "NULL", 200
				);
		entry.createEntryReward( //Char3 Fist
				R.drawable.char3_lock, R.drawable.char3_lock_thumb, R.drawable.char3_lock_thumb_onclick,
				R.drawable.char3_unlock, R.drawable.char3_unlock_thumb, R.drawable.char3_unlock_thumb_onclick,
				R.drawable.char3_select, R.drawable.char3_select_thumb, R.drawable.char3_select_thumb_onclick,
				"Fist", "NULL", 300
				);
		entry.createEntryReward( //Char4 Fist
				R.drawable.char4_lock, R.drawable.char4_lock_thumb, R.drawable.char4_lock_thumb_onclick,
				R.drawable.char4_unlock, R.drawable.char4_unlock_thumb, R.drawable.char4_unlock_thumb_onclick,
				R.drawable.char4_select, R.drawable.char4_select_thumb, R.drawable.char4_select_thumb_onclick,
				"Fist", "NULL", 400
				);
		entry.createEntryReward( //Char5 Fist
				R.drawable.char5_lock, R.drawable.char5_lock_thumb, R.drawable.char5_lock_thumb_onclick,
				R.drawable.char5_unlock, R.drawable.char5_unlock_thumb, R.drawable.char5_unlock_thumb_onclick,
				R.drawable.char5_select, R.drawable.char5_select_thumb, R.drawable.char5_select_thumb_onclick,
				"Fist", "NULL", 500
				);
		entry.createEntryReward( //Char6 Fist
				R.drawable.char6_lock, R.drawable.char6_lock_thumb, R.drawable.char6_lock_thumb_onclick,
				R.drawable.char6_unlock, R.drawable.char6_unlock_thumb, R.drawable.char6_unlock_thumb_onclick,
				R.drawable.char6_select, R.drawable.char6_select_thumb, R.drawable.char6_select_thumb_onclick,
				"Fist", "NULL", 600
				);
		entry.createEntryReward( //Char7 Fist
				R.drawable.char7_lock, R.drawable.char7_lock_thumb, R.drawable.char7_lock_thumb_onclick,
				R.drawable.char7_unlock, R.drawable.char7_unlock_thumb, R.drawable.char7_unlock_thumb_onclick,
				R.drawable.char7_select, R.drawable.char7_select_thumb, R.drawable.char7_select_thumb_onclick,
				"Fist", "NULL", 700
				);
		entry.createEntryReward( //Char8 Fist
				R.drawable.char8_lock, R.drawable.char8_lock_thumb, R.drawable.char8_lock_thumb_onclick,
				R.drawable.char8_unlock, R.drawable.char8_unlock_thumb, R.drawable.char8_unlock_thumb_onclick,
				R.drawable.char8_select, R.drawable.char8_select_thumb, R.drawable.char8_select_thumb_onclick,
				"Fist", "NULL", 800
				);
		entry.createEntryReward( //Char9 Fist
				R.drawable.char9_lock, R.drawable.char9_lock_thumb, R.drawable.char9_lock_thumb_onclick,
				R.drawable.char9_unlock, R.drawable.char9_unlock_thumb, R.drawable.char9_unlock_thumb_onclick,
				R.drawable.char9_select, R.drawable.char9_select_thumb, R.drawable.char9_select_thumb_onclick,
				"Fist", "NULL", 900
				);
		
			entry.close();
		}
		a.putInt("Reward", 1).commit();
		
		
	}

	
	private void DatabaseSelectBadHabit(){
		badHabitlist.clear();
		entry = new DbDatabaseCreate(MainActivity.this);
		entry.open();
		SQLiteDatabase db = helper.getWritableDatabase();

		try {
			String selectQuery = "SELECT "+DbHelper.KEY_ID+", "+DbHelper.KEY_TITLE+", "+DbHelper.KEY_DESCRIPTION+", "+DbHelper.KEY_REWARD+" FROM "+DbHelper.BADHABIT_TABLE+";";
			cursor = db.rawQuery(selectQuery, null);
			cursor.move(0);
			while (cursor.moveToNext()) {
				int dbId = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_ID)) -1;
				String dbTitle = cursor.getString(cursor.getColumnIndex(DbHelper.KEY_TITLE));
				String dbDescription = cursor.getString(cursor.getColumnIndex(DbHelper.KEY_DESCRIPTION));
				int dbReward = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_REWARD));
				
				Habit h  = new Habit();
				h.setTitle(dbTitle);
				h.setDescription(dbDescription);
				h.setReward(dbReward);
				badHabitlist.add(dbId, h);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Toast.makeText(getApplicationContext(), e1.toString(), 1).show();
		}
		cursor.close();
		entry.close();
	}
	
	
	
	private void DatabaseSelectGoodHabit(){
		goodHabitlist.clear();
		entry = new DbDatabaseCreate(MainActivity.this);
		entry.open();
		SQLiteDatabase db = helper.getWritableDatabase();
		
		String selectQuery = "SELECT "+DbHelper.KEY_ID+", "+DbHelper.KEY_TITLE+", "+DbHelper.KEY_DESCRIPTION+", "+DbHelper.KEY_REWARD+" FROM "+DbHelper.GOODHABIT_TABLE+";";
			try {
			cursor = db.rawQuery(selectQuery, null);
		
			cursor.move(0);
			while (cursor.moveToNext()) {
				int dbId = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_ID)) -1;
				String dbTitle = cursor.getString(cursor.getColumnIndex(DbHelper.KEY_TITLE));
				String dbDescription = cursor.getString(cursor.getColumnIndex(DbHelper.KEY_DESCRIPTION));
				int dbReward = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_REWARD));
				Habit h  = new Habit();
				h.setTitle(dbTitle);
				h.setDescription(dbDescription);
				h.setReward(dbReward);
				goodHabitlist.add(dbId, h);
			}
		
			
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			Toast.makeText(getApplicationContext(), e1.toString(), 1).show();
			
		}
		
		cursor.close();
		entry.close();
	}
	
		
//START	
//MISC
//	
	private void firstLaunch(){					//Checks if app is launched for first time
		SHAREDPREFS = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		if (SHAREDPREFS.getBoolean("first_run", true)) {
		    intentStartupActiviy();
		    // record the fact that the app has been started at least once
		    SHAREDPREFS.edit().putBoolean("first_run", false).commit(); 
		}
	}
	private void intentStartupActiviy(){
		Intent i = new Intent();
		i.setClass(this, StartupActivity.class);
		startActivity(i);	
	}
	private void nextIntentBadHabit(){
		Intent i = new Intent();
		i.setClass(this, InputBadHabitActivity.class);
		startActivity(i);	
	}
	private void nextIntent(){
		Intent i = new Intent();
		i.setClass(this, InputHabitActivity.class);
		startActivity(i);
	}
	private void intentRewardActivity(){
		Intent i = new Intent();
		i.setClass(this, RewardActivity.class);
		startActivity(i);
	}
	private void settingsActivity(){
		Intent i = new Intent();
		i.setClass(this, SettingsActivity.class);
		startActivity(i);
	}
	private void updateScore(){
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		
		Editor a  = SHAREDPREFS.edit();
		a.putInt(USER_POINTS, user.getRewardpoint());
		a.commit();
		
		TextView tv = (TextView) findViewById(R.id.YourScore);
		tv.setText("Your score is: " + user.getRewardpoint());
	} 
	private void setScore(){
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		
		user.setRewardpoint(SHAREDPREFS.getInt(USER_POINTS, 0));
		
		
		TextView tv = (TextView) findViewById(R.id.YourScore);
		tv.setText("Your score is: " + user.getRewardpoint());
	}
//EINDE	
//MISC
//		
	
//START	
//DASHBOARD RELATED
//	
	private void getUserName(){
		SHAREDPREFS = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		TextView i = (TextView) findViewById(R.id.YourName);
		name1 = SHAREDPREFS.getString("Name", "Hai");
		
		user.setName(name1);
		
		i.setText("Welkom " + user.getName());
		}
	

//EINDE	
//DASHBOARD RELATED
//		
	

	
//START
//HABIT RELATED
//
	

	
	private void addGoodHabitToDashboard(){
		//Variabelen
		int length = goodHabitlist.size();
		ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
		ln.setOrientation(LinearLayout.VERTICAL); 
		
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		
			for (int i = 0; i < N; i++) {
				TableRow tr = new TableRow(this);
				TextView tv = new TextView(this); //
				Habit habit = goodHabitlist.get(i); //
				
				//Get strings
				String Title = habit.getTitle(); //
				String description = habit.getDescription(); //
				
				//Set text for the row
				tv.setText(Title + " \n" + description + "\n --------------------"); //
				
				//layouts
				TableLayout ll = (TableLayout) findViewById(R.id.GoodHabitsMain); //
				lptr.weight = 8;				
				lptv.weight = 7;
				lpb1.weight = 1;
				lp.leftMargin = 10; 
				lp.rightMargin = 15; 
				lp.bottomMargin = 10; 
				
				//Delete row
				TableRow r = (TableRow) findViewById(R.id.tableRow75);
				ll.removeView(r);
				//Buttons
				Button b1 = new Button(this);
				b1.setBackgroundResource(R.drawable.button_good);
				
				b1.setLayoutParams(lpb1);
				tv.setLayoutParams(lptv);
				tr.setLayoutParams(lptr);
				ll.setLayoutParams(lp); 
				
				tr.addView(tv);
				tr.addView(b1);
				//Add row in Tableview
				ll.addView(tr);	 
				habitcounter++; 
				
				b1.setOnClickListener(habitB1);
						
//ADD EEN STREEP VIEW HIERONDER 				
} 
	}
	private void addBadHabitToDashboard(){
		//Variabelen
				int length = badHabitlist.size();
				ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
				ln.setOrientation(LinearLayout.VERTICAL); 
				
				//Workaround voor probleem
				final int N = length; // total number of textviews to add
				
					for (int i = 0; i < N; i++) {
						TableRow tr = new TableRow(this);
						TextView tv = new TextView(this); //
						Habit habit = badHabitlist.get(i); //
						
						//Get strings
						String Title = habit.getTitle(); 
						String description = habit.getDescription(); 
						
						//Set text for the row
						tv.setText(Title + " \n" + description + "\n --------------------"); 
						
						//layouts
						TableLayout ll = (TableLayout) findViewById(R.id.BadhabitsMain); 

						//Params
						lptr.weight = 8;				
						lptv.weight = 7;
						lpb1.weight = 0;
						lp.leftMargin = 10; 
						lp.rightMargin = 15; 
						lp.bottomMargin = 10; 
						
						//Buttons
						Button b2 = new Button(this);
						
						b2.setBackgroundResource(R.drawable.button_bad);
						
						b2.setLayoutParams(lpb1);
						tv.setLayoutParams(lptv);
						tr.setLayoutParams(lptr);
						ll.setLayoutParams(lp); 
					
						tr.addView(tv);
						tr.addView(b2);
						
						//Add row in Tableview
						ll.addView(tr);	 
						habitcounter++; 
						
						b2.setOnClickListener(habitB2);
					}
	}
//EINDE	
//HABIT RELATED
//	
	
//START	
//ONCLICK LISTENERS
//
	
	View.OnClickListener habitB1 = new View.OnClickListener() {
		public void onClick(View v) {
			int length = goodHabitlist.size();
			final int N = length; // total number of textviews to add
			for (int i = 0; i < N; i++) {
				
				Habit habit = goodHabitlist.get(i);
				user.addRewardPoint(habit.getReward());
				updateScore();
				
			}
		}
	};
		
	View.OnClickListener habitB2 = new View.OnClickListener() {
		public void onClick(View v) {
			int length = goodHabitlist.size();
			final int N = length; // total number of textviews to add
			
			for (int i = 0; i < N; i++) {
				
				Habit habit = goodHabitlist.get(i);
				int negativeReward = 0 - habit.getReward();
				user.addRewardPoint(negativeReward);
				updateScore();
				
			}
		}
	};	
	

//EINDE
//ONCLICK LISTENERS
//

	
	





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			settingsActivity();
			return true;
		}
		if (id == R.id.GoodHabitAction) {
			Message.message(getApplicationContext(), "Loading...");
			nextIntent();
			return true;
		}
		if (id == R.id.badHabitAction) {
			Message.message(getApplicationContext(), "Loading...");
			nextIntentBadHabit();
			return true;	
		}
		if (id == R.id.reward_activity) {
			intentRewardActivity();
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
