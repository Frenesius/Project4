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

public class RewardActivity extends ActionBarActivity {

	Button buyButton;
	Button sellButton;
	
	int userPoints;
	User user = MainActivity.user;
	String userName;
	public static String USER_POINTS = "UserPoints";
	final static String PREFS_NAME = "Happits";
	SharedPreferences SHAREDPREFS;
	RadioGroup rGroup;
	

	
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
		
			Button buyButton = (Button) findViewById(R.id.buyButton);
			Button sellButton = (Button) findViewById(R.id.selectButton);
			buyButton.setOnClickListener(buyButtonListener);
			sellButton.setOnClickListener(sellButtonListener);
	
		//User related
		updateUserPoints();
		setUserName();
		setUserPoints();	
		selectDatabaseReward();
		
			// This will get the radiogroup
			RadioGroup rGroup = (RadioGroup)findViewById(R.id.radioGroup1);
			// This will get the radiobutton in the radiogroup that is checked
			RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(rGroup.getCheckedRadioButtonId());
			rGroup.setOnCheckedChangeListener(rListener);
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

	
	
	
	//WIJZIGEN
	private void addGoodHabitToDashboard(){
		//Variabelen
		int length = rewardList.size();	//
		
		RelativeLayout brl = (RelativeLayout) findViewById(R.id.rewardLayout1);
		//ln.setOrientation(LinearLayout.VERTICAL); //
		
		


		
		
		
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		
			for (int i = 0; i < N; i++) {
				TableRow tr = new TableRow(this);
				TextView tv = new TextView(this); //
				Reward rw = rewardList.get(i); //
				
				//Get strings
				String Title = rw.getTitle(); //
				String description = rw.getDescription(); //
				
				//Set text for the row
				tv.setText(Title + " \n" + description + "\n --------------------"); //
				
				//layouts
				TableLayout ll = (TableLayout) findViewById(R.id.tableLayoutReward1); //
				
				//Buttons
				Button b1 = new Button(this);
				b1.setBackgroundResource(R.drawable.button_good);
				

				
				tr.addView(tv);
				tr.addView(b1);
				//Add row in Tableview
				brl.addView(tr);	 
				
				
				
						
//ADD EEN STREEP VIEW HIERONDER 				
} 
	}
	
	
	
	    
	
	//WIJZIGEN
	RadioGroup.OnCheckedChangeListener rListener = new RadioGroup.OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
		
			// This will get the radiobutton that has changed in its check state
	        RadioButton checkedRadioButton = (RadioButton)rGroup.findViewById(checkedId);
	        
	        // This puts the value (true/false) into the variable
	        boolean isChecked = checkedRadioButton.isChecked();
	        
	        // If the radiobutton that has changed in check state is now checked...
	        if (isChecked)
	        {
	        	checkedRadioButton.getId();
	            
	        }
		}
	};

	
	
	
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
			Toast.makeText(getApplicationContext(), e1.toString(), 1).show();
			
		}
		
		cursor.close();
		entry.close();
	}
	
	
	
	
	View.OnClickListener buyButtonListener = new View.OnClickListener() {
		public void onClick(View v) {
	
				
				Toast.makeText(getApplicationContext(), "Buy",
					   Toast.LENGTH_LONG).show();
			}
	};
	
	
	
	View.OnClickListener sellButtonListener = new View.OnClickListener() {
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
