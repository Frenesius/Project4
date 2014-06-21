package hr.frenesius.todolist;

import java.util.ArrayList;
import java.util.List;

import hr.frenesius.list.Habit;
import hr.frenesius.list.Reward;
import hr.frenesius.list.User;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
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
	
	static List<Reward> rewardList 
	= new ArrayList<Reward>();							//List met alle Habit objecten
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);

		Button buyButton = (Button) findViewById(R.id.buyButton);
		Button sellButton = (Button) findViewById(R.id.sellButton);
		buyButton.setOnClickListener(buyButtonListener);
		sellButton.setOnClickListener(sellButtonListener);
	
		//User related
		updateUserPoints();
		setUserName();
		setUserPoints();	
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
