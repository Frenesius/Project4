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
	static int RADIOGROUP_ID = 213131;
	static int RADIOBUTTON_ID = 113131;

	ArrayList<RadioGroup> radiogroupList = 
			new ArrayList<RadioGroup>();
	
	ArrayList<RadioButton> radiobuttonList = 
			new ArrayList<RadioButton>();
	
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
		selectDatabaseReward();	//TODO NIEUWE THREAD
		addButton();
		addRewardToRewardList();
		parseRadiobuttonToRadioGroup();
		addRadioGroupToView();
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

private void addButton(){
	
	
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
	
}
	private void addRewardToRewardList(){	
		int rewardId = 0;
		for(int i = 0; i<rewardList.size();i++){
			
			Reward r = rewardList.get(i);
			RadioButton rb = new RadioButton(this);
			
			LayoutParams rbl = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			
			Drawable d;
			//Assigned drawable
				if(r.isRewardBought()){
					d = getResources().getDrawable(r.getPictureUnlockThumb());
					rb.setButtonDrawable(d);
						if(r.isSelected()){
							d = getResources().getDrawable(r.getPictureSelectThumb());
							rb.setButtonDrawable(d);
						}
				}if(!r.isRewardBought() && !r.isSelected()){
					d = getResources().getDrawable(r.getPictureLockThumb());	
					rb.setButtonDrawable(d);
				}
			rb.setLayoutParams(rbl);
			
			int id = RADIOBUTTON_ID + rewardId;
			rb.setId(id);	
			
			radiobuttonList.add(rewardId, rb);
			rewardId++;
		}
		
	}
	private void parseRadiobuttonToRadioGroup(){	
		int rwCounter2 = 0;
		int rwCounter3 = 0;
		RadioGroup rg = new RadioGroup(this);
		
		for(int i = 0; i < radiobuttonList.size(); i++){
			RadioButton rb = radiobuttonList.get(i);
			rg.setOrientation(TableLayout.HORIZONTAL);
			
			//LayoutParams rgPm = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			
			rg.addView(rb);
			rwCounter2++;
			
			if(rwCounter2%3 == 0){
				rg.setId(RADIOGROUP_ID + rwCounter3);
				radiogroupList.add(rwCounter3, rg);
				rwCounter2 = 0;
				rwCounter3++;
				rg = new RadioGroup(this);
				
			}	
		}
	}
	private void addRadioGroupToView(){
		TableLayout tl = (TableLayout) findViewById(R.id.tableLayoutReward1);
		for(int i=0; i< radiogroupList.size(); i++){
			TableRow tr = new TableRow(this);
			tr.setOrientation(TableRow.HORIZONTAL);
			
			RadioGroup rGroup = radiogroupList.get(i);
			
			tr.addView(rGroup);
			tl.addView(tr);
		}
		
	}
	
	private void selectDatabaseReward(){
		rewardList.clear();
	
		entry = new DbDatabaseCreate(RewardActivity.this);
		entry.open();
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//Boek
		//Code Complete
		//Beautifull code
		//11
		
		try {
			cursor = db.rawQuery(DbHelper.SELECT_REWARDTABLEQUERY, null);//
		
			cursor.move(0);
			while (cursor.moveToNext()) {
					int dbId = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_ID)) -1;
					int dbPicture_lock = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURELOCK));
					int dbPicture_lock_thumb = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURELOCK_THUMB));
					int dbPicture_lock_thumb_onclick = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURELOCK_THUMB_ONCLICK));
					
					int dbPicture_unlock = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTUREUNLOCK));
					int dbPicture_unlock_thumb = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTUREUNLOCK_THUMB)); 
					int dbPicture_unlock_thumb_onclick = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTUREUNLOCK_THUMB_ONCLICK));
					
					int dbPicture_select = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURESELECT));
					int dbPicture_select_thumb = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURESELECT_THUMB));	
					int dbPicture_select_thumb_onclick = cursor.getInt(cursor.getColumnIndex(DbHelper.KEY_PICTURESELECT_THUMB_ONCLICK));
					
					
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
					}if(dbSelect == 0){
						rewardSelect = false;
					}else if(dbSelect == 1){
						rewardSelect = true;
					}
				
				
				Reward rw  = new Reward();
				
					rw.setPictureUnlock(dbPicture_unlock);
					rw.setPictureUnlockThumb(dbPicture_unlock_thumb);
					rw.setPictureUnlockThumbOnclick(dbPicture_unlock_thumb_onclick);
					
					rw.setPictureLock(dbPicture_lock);
					rw.setPictureLockThumb(dbPicture_lock_thumb);
					rw.setPictureLockThumbOnclick(dbPicture_lock_thumb_onclick);
					
					rw.setPictureSelect(dbPicture_select);
					rw.setPictureSelectThumb(dbPicture_select_thumb);
					rw.setPictureSelectThumbOnclick(dbPicture_select_thumb_onclick);
					
					rw.setTitle(dbTitle);
					rw.setDescription(dbDescription);
					rw.setRewardBought(rewardBought); 
					rw.setPoint(dbPoint);
					rw.setSelected(rewardSelect);
				rewardList.add(dbId, rw);
			}
		
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
			Toast.makeText(getApplicationContext(), e1.toString(), Toast.LENGTH_SHORT).show();
		}
		cursor.close();
		entry.close();
	}
	
	//TODO IF CHECKED CHANGE PLAATJE NAAR BLAUW
	
	View.OnClickListener buyButtonListener = new View.OnClickListener() {
		public void onClick(View v) {	
			
			RadioGroup rg1 = (RadioGroup) findViewById(RADIOGROUP_ID);
			RadioGroup rg2 = (RadioGroup) findViewById(RADIOGROUP_ID+1);
			RadioGroup rg3 = (RadioGroup) findViewById(RADIOGROUP_ID+2);
			RadioGroup selectedGroup = null;
			String checkGroup = checkGroupSelected(rg1,rg2,rg3);
			if(checkGroup == "rg1"){	
				selectedGroup = rg1;
			}if(checkGroup == "rg2"){
				selectedGroup = rg2;
			}if(checkGroup =="rg3"){	
				selectedGroup = rg3;
			}
			
			boolean defaultCheck = false;
			
			if(MainActivity.user.getRewardpoint()>0){
				switch(selectedGroup.getCheckedRadioButtonId()){
				case 113131:
					updateBuyTable(0);
					break;
				case 113132:
					updateBuyTable(1);
					break;
				case 113133:
					updateBuyTable(2);
					break;
				case 113134:
					updateBuyTable(3);
					break;
				case 113135:
					updateBuyTable(4);
					break;
				case 113136:
					updateBuyTable(5);
					break;
				case 113137:
					updateBuyTable(6);
					break;
				case 113138:
					updateBuyTable(7);
					break;
				case 113139:
					updateBuyTable(8);
					break;
				case 113140:
					updateBuyTable(9);
					break;
				default:
					Toast.makeText(getApplicationContext(), "Please select a reward.", Toast.LENGTH_SHORT).show();
					defaultCheck = true;
			}
			
				if(!defaultCheck){
					updateScore();
					restartActivity();
				}
			}else{
				Toast.makeText(getApplicationContext(), "You do not have enough money.", Toast.LENGTH_SHORT).show();
			}
		}
	};
	private String checkGroupSelected(RadioGroup a, RadioGroup b, RadioGroup c){
		String checkGroup = "0";
		if(a.getCheckedRadioButtonId() != -1){
			checkGroup = "rg1";
		}if(b.getCheckedRadioButtonId() != -1){
			checkGroup = "rg2";
		}if(c.getCheckedRadioButtonId() != -1){
			checkGroup = "rg3";
		}
		return checkGroup;
	}
	//TODO
	View.OnClickListener selectButtonListener = new View.OnClickListener() {
		public void onClick(View v) {
			unselectEverything();
			
			RadioGroup rg1 = (RadioGroup) findViewById(RADIOGROUP_ID);
			RadioGroup rg2 = (RadioGroup) findViewById(RADIOGROUP_ID+1);
			RadioGroup rg3 = (RadioGroup) findViewById(RADIOGROUP_ID+2);
			RadioGroup selectedGroup = null;
			String checkGroup = checkGroupSelected(rg1,rg2,rg3);
			
			if(checkGroup == "rg1"){	
				selectedGroup = rg1;
			}if(checkGroup == "rg2"){
				selectedGroup = rg2;
			}if(checkGroup =="rg3"){	
				selectedGroup = rg3;
			}
			
			boolean defaultCheck = false;
			
			try{
				switch(selectedGroup.getCheckedRadioButtonId()){
				case 113131:
					updateSelectTable(0);
					break;
				case 113132:
					updateSelectTable(1);
					break;
				case 113133:
					updateSelectTable(2);
					break;
				case 113134:
					updateSelectTable(3);
					break;
				case 113135:
					updateSelectTable(4);
					break;
				case 113136:
					updateSelectTable(5);
					break;
				case 113137:
					updateSelectTable(6);
					break;
				case 113138:
					updateSelectTable(7);
					break;
				case 113139:
					updateSelectTable(8);
					break;
				case 113140:
					updateSelectTable(9);
					break;
				default:
					Toast.makeText(getApplicationContext(), "Please select a reward.", Toast.LENGTH_SHORT).show();
					defaultCheck = true;
			}
			
				if(!defaultCheck){
					updateScore();
					restartActivity();
				}
			}catch(Exception e){
				Toast.makeText(getApplicationContext(), 
						"You do not have this reward bought yet.", Toast.LENGTH_SHORT).show();
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
	 
				

	//TODO SELECT
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
