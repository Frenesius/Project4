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
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.widget.ImageView;
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
		
		
		
		
		
		
	}
	protected void onResume(){
		super.onResume();
		selectDatabaseReward();
		addReward();
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


	//TODO $## HERSCHRIJVEN
	//Moet objecten in lijstje gooien -> radio buttons
	private void addReward(){
		//Variabelen
		int length = rewardList.size();	//
		
		TableLayout tl = (TableLayout) findViewById(R.id.tableLayoutReward1);
		TableRow tr = new TableRow(this);
		Button buyB1 = new Button(this);
		Button selectB1 = new Button(this);
		LayoutParams bParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		bParams.weight = 4;
		
		buyB1.setOnClickListener(buyButtonListener);
		selectB1.setOnClickListener(selectButtonListener);
		
		buyB1.setText("Buy");
		selectB1.setText("Select");
		
		buyB1.setBackground(getResources().getDrawable(R.drawable.button_click));
		selectB1.setBackground(getResources().getDrawable(R.drawable.button_click));
		
		buyB1.setLayoutParams(bParams);
		selectB1.setLayoutParams(bParams);
		
		tr.addView(buyB1);
		tr.addView(selectB1);
	
		tl.addView(tr); 
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		int rwCount = 0;
			for (int i = 0; i < N; i++) {
				
				//TODO IF i>3 NIEUWE RIJ -> set padding(?)
				
				TableRow tr2 = new TableRow(this);
				
				RadioGroup rg = new RadioGroup(this); //create the RadioGroup
			    rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
				
				LayoutParams rbl = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				RadioButton rb = new RadioButton(this);
				
				Drawable d;
				Reward rw = rewardList.get(i); //
				if(rw.isRewardBought()){
					d = getResources().getDrawable(rw.getPictureUnlock());						
				}if(rw.isSelected()){
					d = getResources().getDrawable(rw.getPictureSelect());	
				}else{
					d = getResources().getDrawable(rw.getPictureLock());	
				}
				
				
				rb.setButtonDrawable(d);
				
				rb.setLayoutParams(rbl);
				
				
				//ID
				int id = 2550 + rwCount;
				rb.setId(id);
				
		
				
				//Afmaken van TL
				rg.addView(rb);
				tr2.addView(rg);
				tl.addView(tr2);
				rwCount++;
				
 
				
				
				
				
						
//ADD EEN STREEP VIEW HIERONDER 				
} 
	}
	
	
	
	
	//WIJZIGEN
	
	
	
	

	private void selectDatabaseReward(){
		rewardList.clear();
	
		entry = new DbDatabaseCreate(RewardActivity.this);
		entry.open();
		SQLiteDatabase db = helper.getWritableDatabase();
		
		String selectQuery = "SELECT "+DbHelper.KEY_ID+", "+DbHelper.KEY_PICTURELOCK+", "+DbHelper.KEY_PICTUREUNLOCK+", "+DbHelper.KEY_PICTURESELECT+", "+DbHelper.KEY_TITLE+", "+DbHelper.KEY_DESCRIPTION+", "+DbHelper.KEY_BOUGHT+", "+DbHelper.KEY_POINT+" FROM "+DbHelper.REWARD_TABLE+";";
		try {
			cursor = db.rawQuery(selectQuery, null);//
		
			cursor.move(0);
			while (cursor.moveToNext()) {
					int dbId = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_ID)) -1;
					int dbPicture_lock = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURELOCK));
					int dbPicture_unlock = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTUREUNLOCK));
					int dbPicture_select = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURESELECT));
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
					rw.setPictureUnlock(dbPicture_unlock);
					rw.setPictureLock(dbPicture_lock);
					rw.setPictureSelect(dbPicture_select);
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
	
			//Custom IDs 
			//TODO maak hier een switch van
			RadioButton r0 = (RadioButton) findViewById(2550);
			RadioButton r1 = (RadioButton) findViewById(2551);
			RadioButton r2 = (RadioButton) findViewById(2552);
			
			if(r0.isChecked()){
				Reward rw = rewardList.get(0);
				rw.buyReward();
				updateScore();
				int intBool = 0;
					if(rw.isRewardBought()){
						intBool = 1;
					}else{
						intBool = 0;
					}
				 // 1. get reference to writable DB
			    SQLiteDatabase db = helper.getWritableDatabase();
			 
			    // 2. create ContentValues to add key "column"/value
			    ContentValues values = new ContentValues();
			    values.put(DbHelper.KEY_BOUGHT, intBool); // get title
			    
			    // 3. updating row
			    int i = db.update(DbHelper.REWARD_TABLE, //table
			            values, // column/value
			            "_id = 1", // selections
			            null); //selection args
			 
			    // 4. close
			    db.close();
				
				
				
				
				
				
				restartActivity();
				Toast.makeText(getApplicationContext(), "fist", 1).show();
			}else if(r1.isChecked()){
				Reward rw = rewardList.get(1);
				rw.buyReward();
				updateScore();
				restartActivity();
				Toast.makeText(getApplicationContext(), "Skelet0r", 1).show();
			}else if(r2.isChecked()){
				Reward rw = rewardList.get(2);
				rw.buyReward();
				updateScore();
				restartActivity();
				Toast.makeText(getApplicationContext(), "3", 1).show();
			}
		
		}
	};
	
	private void restartActivity(){
	Intent intent = getIntent();
	finish();
	startActivity(intent);
	}
	
	private void updateScore(){
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		
		Editor a  = SHAREDPREFS.edit();
		a.putInt(USER_POINTS, user.getRewardpoint());
		a.commit();
		
		TextView tv = (TextView) findViewById(R.id.YourScore);
		tv.setText("Your score is: " + user.getRewardpoint());
	} 
	
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
