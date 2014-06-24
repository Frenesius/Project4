package hr.frenesius.todolist;

import java.util.ArrayList;
import android.widget.RadioGroup.OnCheckedChangeListener;
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


public class RewardActivity extends ActionBarActivity {

	
	int userPoints;
	User user = MainActivity.user;
	String userName;
	public static String USER_POINTS = "UserPoints";
	final static String PREFS_NAME = "Happits";
	public static String USER_PICTURE = "UserPicture";
	
	SharedPreferences SHAREDPREFS;
	
	RadioGroup rg;
	static int RADIOGROUP_ID = 21313;

	
	
	static List<Reward> rewardList 
	= new ArrayList<Reward>();							//List met alle Habit objecten
	
	DbHelper helper;
	SQLiteDatabase db;
	DbDatabaseCreate entry;
	Cursor cursor;


	//Rewards toevoegen, wijzig mainactivity ContentValues en add in RewardActivity de cases aan beide switches
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
		//Reward related
		selectDatabaseReward();
		addReward();
		

	}
	protected void onResume(){
		super.onResume();
		try{
			setUserPicture();	//Mogelijk kans dat er geen picture in sharedprefs staat
		}catch(Exception e){
			e.getStackTrace();
		}

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
		TableRow tr2 = new TableRow(this);
		
		RadioGroup rg = new RadioGroup(this); //create the RadioGroup
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		int rwCount = 0;
			for (int i = 0; i < N; i++) {
				
				//TODO IF i>3 NIEUWE RIJ -> set padding(?)
				
				
			    rg.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
				rg.setId(RADIOGROUP_ID);
				LayoutParams rbl = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				RadioButton rb = new RadioButton(this);
				
				Drawable d;
				Reward rw = rewardList.get(i); //
				
				if(rw.isRewardBought()){
					d = getResources().getDrawable(rw.getPictureUnlock());
					rb.setButtonDrawable(d);
					
						if(rw.isSelected()){
							d = getResources().getDrawable(rw.getPictureSelect());
							rb.setButtonDrawable(d);
						}
				}if(rw.isSelected()){
					d = getResources().getDrawable(rw.getPictureSelect());
					rb.setButtonDrawable(d);
						if(rw.isSelected()){
							d = getResources().getDrawable(rw.getPictureSelect());
							rb.setButtonDrawable(d);
						}
				}if(!rw.isRewardBought() && !rw.isSelected()){
					d = getResources().getDrawable(rw.getPictureLock());	
					rb.setButtonDrawable(d);
						if(rw.isSelected()){
							d = getResources().getDrawable(rw.getPictureSelect());
							rb.setButtonDrawable(d);
						}
				}
				rbl.width = 100;
				rbl.height = 140 ;
				
				//ID
				rb.setLayoutParams(rbl);
				int startValue = 1121;
				int id = startValue + rwCount;
				
				rb.setId(id);
				rb.setText(String.valueOf(id));
				
				rwCount++;
				//Afmaken van TL
				rg.addView(rb);
				
				
				} 
			
			tr2.addView(rg);
			tl.addView(tr2);
			
	}
	
	
	
	
	//WIJZIGEN
	
	
	
	

	private void selectDatabaseReward(){
		rewardList.clear();
	
		entry = new DbDatabaseCreate(RewardActivity.this);
		entry.open();
		SQLiteDatabase db = helper.getWritableDatabase();
		
		String selectQuery = "SELECT "+DbHelper.KEY_ID+
				", "+DbHelper.KEY_PICTURELOCK+
				", "+DbHelper.KEY_PICTUREUNLOCK+
				", "+DbHelper.KEY_PICTURESELECT+
				", "+DbHelper.KEY_TITLE+
				", "+DbHelper.KEY_DESCRIPTION+
				", "+DbHelper.KEY_SELECTREWARD+
				", "+DbHelper.KEY_BOUGHT+
				", "+DbHelper.KEY_POINT+
				" FROM "+DbHelper.REWARD_TABLE+";";
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
					int dbSelect = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_SELECTREWARD));
					int dbPoint = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_POINT));
					boolean rewardBought = false;
					boolean rewardSelect = false;
					
						if(dbBought == 0){
							rewardBought = false;
						}else if(dbBought == 1){
							rewardBought = true;
						}
						if(dbSelect == 0){
							rewardSelect = false;
						}else if(dbSelect == 1){
							rewardSelect = true;
						}
				
				
				Reward rw  = new Reward();
					rw.setPictureUnlock(dbPicture_unlock);
					rw.setPictureLock(dbPicture_lock);
					rw.setPictureSelect(dbPicture_select);
					rw.setTitle(dbTitle);
					rw.setDescription(dbDescription);
					rw.setRewardBought(rewardBought); // VERANDEREN IN INT
					rw.setPoint(dbPoint);
					rw.setSelected(rewardSelect);
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
	
			
			
			boolean defaultCheck = false;
			
			rg = (RadioGroup) findViewById(RADIOGROUP_ID);

			if(MainActivity.user.getRewardpoint()>0){
				switch(rg.getCheckedRadioButtonId()){
					case 1121:
						updateBuyTable(0);
						break;
					case 1122:
						updateBuyTable(1);
						break;
					case 1123:
						updateBuyTable(2);
						break;
					default:
						Toast.makeText(getApplicationContext(), "Please select a reward.", Toast.LENGTH_SHORT).show();
						defaultCheck = true;
				}if(!defaultCheck){
						updateScore();
						restartActivity();
					}
				}else{
					Toast.makeText(getApplicationContext(), "You do not have enough points.", Toast.LENGTH_SHORT).show();
				}
		}
		
	};
	

	
	
	private void updateBuyTable(int habitNumber){
		Reward rw = rewardList.get(habitNumber);
		rw.buyReward();
		
		int intBool = 0;
			if(rw.isRewardBought()){
				intBool = 1;
			}

		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
    	values.put(DbHelper.KEY_BOUGHT, intBool); // get title
    
    	habitNumber = habitNumber + 1;
    	int i = db.update(DbHelper.REWARD_TABLE, //table
    			values, // column/value
    			"_id = "+habitNumber, // selections
    			null); //selection args
    	db.close();
	}
	
	private void restartActivity(){
		Intent i = new Intent();
		i.setClass(this, RewardActivity.class);
		startActivity(i);
		finish();
	
	}
	
	private void updateScore(){
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		
		Editor a  = SHAREDPREFS.edit();
		a.putInt(USER_POINTS, user.getRewardpoint());
		a.commit();
		
		TextView tv = (TextView) findViewById(R.id.YourScore);
		tv.setText("Your score is: " + user.getRewardpoint());
	} 
	//TODO 
	View.OnClickListener selectButtonListener = new View.OnClickListener() {
		public void onClick(View v) {
	
			boolean defaultCheck = false;
			
			rg = (RadioGroup) findViewById(RADIOGROUP_ID);
			unselectEverything();
				switch(rg.getCheckedRadioButtonId()){
					case 1121:
						updateSelectTable(0);
						break;
					case 1122:
						updateSelectTable(1);
						break;
					case 1123:
						updateSelectTable(2);
						break;
					default:
						Toast.makeText(getApplicationContext(), "Please select a reward.", Toast.LENGTH_SHORT).show();
						defaultCheck = true;
				}if(!defaultCheck){
						restartActivity();
				}else{
					Toast.makeText(getApplicationContext(), "You do not have enough points.", Toast.LENGTH_SHORT).show();
				}
}
	};
				

	//TODO
	private void updateSelectTable(int habitNumber){
		Reward rw = rewardList.get(habitNumber);
		rw.selectReward();
		
		int intBool = 0;
			if(rw.isSelected()){
				intBool = 1;
			}
		
		//Write image to SharedPrefs
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		Editor a  = SHAREDPREFS.edit();
		a.putInt(USER_PICTURE, rw.getPictureUnlock());
		a.commit();
		
		//Write to Database
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DbHelper.KEY_SELECTREWARD, intBool); // get title
    
    	habitNumber = habitNumber + 1;
    	int i = db.update(DbHelper.REWARD_TABLE, //table
    		values, // column/value
    		"_id = "+habitNumber, // selections
    		null); //selection args
    	db.close();
	}
	private void unselectEverything(){
		int N = rewardList.size();
		for(int C=0; C<N; C++){
			Reward rw = rewardList.get(C);
			rw.selectReward();
		
			int intBool = 0;
				if(rw.isSelected()){
					intBool = 0;
			

				SQLiteDatabase db = helper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put(DbHelper.KEY_SELECTREWARD, intBool); // get title
    
    		
    			int Z = db.update(DbHelper.REWARD_TABLE, //table
    				values, // column/value
    				"_id = "+C, // selections
    				null); //selection args
    			db.close();
				}	
		}
	}
	private void setUserPicture(){
		SHAREDPREFS = getSharedPreferences(MainActivity.PREFS_NAME, Context.MODE_PRIVATE);
		ImageView iv = (ImageView) findViewById(R.id.ImageViewDashMain);
		Drawable draw =getResources().getDrawable(SHAREDPREFS.getInt(RewardActivity.USER_PICTURE, 0)); 
		iv.setBackground(draw);
	}
	
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
