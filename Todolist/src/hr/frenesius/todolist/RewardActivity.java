package hr.frenesius.todolist;

import hr.frenesius.list.Habit;
import hr.frenesius.list.Reward;
import hr.frenesius.list.User;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
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
	static Reward r1 = new Reward(100);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		//setReward();
		setButtons();
		
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
		TextView tv = (TextView) findViewById(R.id.YourScore);
		tv.setText("Your score is: " + score);
	}


	private void setButtons(){
		buyButton = (Button) findViewById(R.id.buyButton);
		buyButton.setOnClickListener(buyButtonListener);
		
		sellButton = (Button) findViewById(R.id.sellButton);
		sellButton.setOnClickListener(sellButtonListener);	
		if (r1.isRewardBought() == true){
			updateTableBuy();
		}
		if (r1.isRewardBought() == false){
			updateTableSell();
		}
		
	}
	
	
	private void buyReward1(){
		r1.buyReward();
		setUserPoints();
		if(r1.isRewardBought() == true){
			updateTableBuy();
		}
	}
	
	private void updateTableBuy(){
		Button b = (Button) findViewById(R.id.buyButton);
		Button s = (Button) findViewById(R.id.sellButton);
		b.setText("You have already bought this item");
		b.setEnabled(false);
		s.setText("Sell Item");
		s.setEnabled(true);
		
	}
	
	private void sellReward(){
		r1.sellReward();
		if(r1.isRewardBought() == false){
			updateTableSell();
		}
		
	}
	
	private void updateTableSell(){
		Button b = (Button) findViewById(R.id.buyButton);
		Button s = (Button) findViewById(R.id.sellButton);
		b.setText("Buy Item");
		b.setEnabled(true);
		s.setText("You don't have this item");
		s.setEnabled(false);
		
	}
	
	
	
	
	
	View.OnClickListener buyButtonListener = new View.OnClickListener() {
		public void onClick(View v) {
			buyReward1();
				
				Toast.makeText(getApplicationContext(), "Buy",
					   Toast.LENGTH_LONG).show();
			}
	};
	
	
	
	View.OnClickListener sellButtonListener = new View.OnClickListener() {
		public void onClick(View v) {
			sellReward();
				
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
		getMenuInflater().inflate(R.menu.reward, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_reward,
					container, false);
			return rootView;
		}
	}

}
