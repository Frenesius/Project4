package hr.frenesius.todolist;

import java.util.ArrayList;
import java.util.List;

import hr.frenesius.data.DbDatabaseCreate;
import hr.frenesius.data.DbHelper;
import hr.frenesius.list.Habit;
import hr.frenesius.list.Reward;
import hr.frenesius.list.User;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;
import android.os.Build;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RewardActivity extends ActionBarActivity {

	
	int userPoints;
	User user = MainActivity.user;
	String userName;
	public static String USER_POINTS = "UserPoints";
	final static String PREFS_NAME = "Happits";
	SharedPreferences SHAREDPREFS;
	RadioGroup rGroup;
	
	RadioButton r0;
	RadioButton r1;
	RadioButton r2;
	
	
	static List<Reward> rewardList 
	= new ArrayList<Reward>();							//List met alle Habit objecten
	
	
	DbHelper helper;
	SQLiteDatabase db;
	DbDatabaseCreate entry;
	Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);
		

		helper = new DbHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		
		
		//User related
		updateUserPoints();
		setUserName();
		setUserPoints();	
		selectDatabaseReward();
		
		
		
		addGoodHabitToDashboard();
		
	}
	
	private void setUserName(){
		userName = user.getName();
		TextView tv = (TextView) findViewById(R.id.YourName);
		tv.setText("Hallo " + userName);
	}
	private void setUserPoints(){
		int score = user.getRewardpoint();
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		
		Editor a  = SHAREDPREFS.edit();
		a.putInt(USER_POINTS, score);
		a.commit();
		TextView tv = (TextView) findViewById(R.id.YourScore);
		tv.setText("Your score is: " + score);
	}

	//Code herschrijven
	private void rButtonListener(){
		//Custom IDs 
		//TODO maak hier een switch van
		RadioButton r0 = (RadioButton) findViewById(2550);
		RadioButton r1 = (RadioButton) findViewById(2551);
		RadioButton r2 = (RadioButton) findViewById(2552);
		
		if(r0.isChecked()){
			Toast.makeText(getApplicationContext(), "fist", 1).show();
		}else if(r1.isChecked()){
			Toast.makeText(getApplicationContext(), "Skelet0r", 1).show();
		}if(r2.isChecked()){
			Toast.makeText(getApplicationContext(), "3", 1).show();
		}
		
	}
	
	//TODO $## HERSCHRIJVEN
	//Moet objecten in lijstje gooien -> radio buttons
	private void addGoodHabitToDashboard(){
		//Variabelen
		int length = rewardList.size();	//
		
		TableLayout tl = (TableLayout) findViewById(R.id.tableLayoutReward1);
		Button buyB1 = new Button(this);
		Button selectB1 = new Button(this);
		
		buyB1.setOnClickListener(buyButtonListener);
		selectB1.setOnClickListener(selectButtonListener);
		
		buyB1.setBackground(getResources().getDrawable(R.drawable.button_click));
		
		selectB1.setBackground(getResources().getDrawable(R.drawable.button_click));
		tl.addView(buyB1);
		tl.addView(selectB1);
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		int rwCount = 0;
			for (int i = 0; i < N; i++) {
				
				//TODO IF i>3 NIEUWE RIJ -> set padding(?)
				
				tl.setOrientation(TableLayout.HORIZONTAL);
				
				
				
				LayoutParams rbl = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				RadioButton rb = new RadioButton(this);
				
				Reward rw = rewardList.get(i); //
				Drawable d = getResources().getDrawable(rw.getPicture());
				
				rb.setButtonDrawable(d);
				rb.setLayoutParams(rbl);
				
				//ID
				int id = 2550 + rwCount;
				rb.setId(id);
				
				//Get strings
				String title = rw.getTitle(); //
				String description = rw.getDescription(); //
				
				
				//Afmaken van TL
				
				tl.addView(rb);
				rwCount++;
				

				
				
				
				
						
//ADD EEN STREEP VIEW HIERONDER 				
} 
	}
	
	
	
	    
	
	//WIJZIGEN
	
	
	
	
	private android.widget.TableLayout.LayoutParams LayoutParams(
			int wrapContent, int wrapContent2) {
		// TODO Auto-generated method stub
		return null;
	}

	private void selectDatabaseReward(){
		rewardList.clear();
	
		entry = new DbDatabaseCreate(RewardActivity.this);
		entry.open();
		SQLiteDatabase db = helper.getWritableDatabase();
		
		String selectQuery = "SELECT "+DbHelper.KEY_ID+", "+DbHelper.KEY_PICTURE+", "+DbHelper.KEY_TITLE+", "+DbHelper.KEY_DESCRIPTION+", "+DbHelper.KEY_BOUGHT+", "+DbHelper.KEY_POINT+" FROM "+DbHelper.REWARD_TABLE+";";
		try {
			cursor = db.rawQuery(selectQuery, null);//
		
			cursor.move(0);
			while (cursor.moveToNext()) {
					int dbId = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_ID)) -1;
					int dbPicture = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURE));
					String dbTitle = cursor.getString(cursor.getColumnIndex(DbHelper.KEY_TITLE));
					String dbDescription = cursor.getString(cursor.getColumnIndex(DbHelper.KEY_DESCRIPTION));
					int dbBought = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_BOUGHT));
					int dbPoint = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_POINT));
					boolean rewardBought = false;
				
				if(dbBought == 0){
					rewardBought = false;
				}else if(dbBought == 1){
					rewardBought = true;
				}
				
				Reward rw  = new Reward();
					rw.setPicture(dbPicture);
					rw.setTitle(dbTitle);
					rw.setDescription(dbDescription);
					rw.setRewardBought(rewardBought); // VERANDEREN IN INT
					rw.setPoint(dbPoint);
					rw.setSelected(false);
				rewardList.add(dbId, rw);
			}
		
			
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
			
			Toast.makeText(getApplicationContext(), e1.toString(), 1).show();
			
		}
		
		cursor.close();
		entry.close();
	}
	
	//TODO IF CHECKED CHANGE PLAATJE NAAR BLAUW
	
	
	View.OnClickListener buyButtonListener = new View.OnClickListener() {
		public void onClick(View v) {
	
			rButtonListener();
		
		}
	};
	
	
	
	View.OnClickListener selectButtonListener = new View.OnClickListener() {
		public void onClick(View v) {

				Toast.makeText(getApplicationContext(), "Sell",
					   Toast.LENGTH_LONG).show();
			}
	};
	
	private void updateUserPoints(){
		userPoints = MainActivity.user.getRewardpoint();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
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


	
	

}
