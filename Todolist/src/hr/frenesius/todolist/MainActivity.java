package hr.frenesius.todolist;

import hr.frenesius.list.Habit;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.widget.TableRow.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
//PositiveHabitList.clear() als een reset knop
	 
	Button button1; // Button
	Button button2; // Button
	LinearLayout ln;
	public static boolean MainActivityTRIGGER = false; //Triggerchecker voor onResume
	public static Activity MainActivityACTIVITY;
	
	static List<Habit> PositiveHabitlist = new ArrayList<Habit>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		// Buttons voor on click
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(button2listener);
		MainActivityACTIVITY = this;
	}
	
	protected void onResume(){
		super.onResume();
		
		//Checkt of input getriggerd is
		if(MainActivityTRIGGER == true){
			processObject();
			MainActivityTRIGGER = false;
				}
			
			try{
				addHabitToDashboard();
			}catch(Exception e){
			
			}
		}
		
	private void addHabitToDashboard(){
		//Variabelen
		int length = PositiveHabitlist.size();
		ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
		ln.setOrientation(LinearLayout.VERTICAL); 
		
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		

		 int habitcounter = 1;
			for (int i = 0; i < N; i++) {
				//Local vars
				TableLayout ll = (TableLayout) findViewById(R.id.GoodHabitsMain);
				TextView tv = new TextView(this);
				Habit habit = PositiveHabitlist.get(i);
				
				//Table layout
				LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				lp.leftMargin = 10;
				lp.rightMargin = 15;
				
				ll.setLayoutParams(lp);
				
				//Get strings
				String habitnumber = "Habit Number: " + String.valueOf(habitcounter);
				String Title = habit.getTitle();
				String description = habit.getDescription();
				
				//Set text for the row
				tv.setText(habitnumber+ "\n" + Title + " \n" + description + "\n --------------------");
		
				//Add row in Tableview
				ll.addView(tv);	
				habitcounter++;
//ADD EEN STREEP HIERONDER
				
}
	}
	
	private void processObject(){
		//Pakt habit van Activity Input
		Habit h = (Habit)getIntent().getExtras().getParcelable("INPUT_HABIT");
		PositiveHabitlist.add(h);		
	}

	//Wijzigt naar intent 
	public void nextIntent(){
		Intent i = new Intent();
		i.setClass(this, InputHabitActivity.class);
		startActivity(i);
		
	}
	
	
	// Onclick listener	
	View.OnClickListener button2listener = new View.OnClickListener() {
		public void onClick(View v) {
			nextIntent();
			
			Toast.makeText(getApplicationContext(), "Button2 clicked",
					   Toast.LENGTH_LONG).show();
		}
	};
	

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
