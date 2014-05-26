package hr.frenesius.todolist;

import java.util.ArrayList;
import java.util.List;

import hr.frenesius.list.Habit;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	Button button1; // Button
	Button button2; // Button
	private int a = 1;
	public static boolean MainActivityTRIGGER = false;
	
	List<Habit> Habitlist = new ArrayList<Habit>();
	

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

	}
	
	protected void onResume(){
		super.onResume();
		
		//Checkt of input getriggerd is
		if(MainActivityTRIGGER == true){
			TextView tv = (TextView) findViewById(R.id.ListTextview1);
			
			//Pakt habit van InputHabitActivity class
			Habit h = (Habit)getIntent().getExtras().getParcelable("INPUT_KEY");
			String s = h.getText();
			//Zet text in view
			tv.setText(s);
			
			taddTextView1();
			taddTextView();
			//Geeft toast terug
				if(h.getText() != null){
					Toast.makeText(getApplicationContext(), "NULL",
							Toast.LENGTH_LONG).show();
					
				}
				
		}
		
	}
	//TEST//1MAAK
	private void taddTextView1(){
		//ADD OBJECT TO LIST OBJECT
		Habit h = (Habit)getIntent().getExtras().getParcelable("INPUT_KEY");
		Habitlist.add(h);
		
		
		
	}//1MAAK
	private void taddTextView(){		
		
		
		int N = Habitlist.size(); // total number of textviews to add
		LinearLayout ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
		final TextView[] myTextViews = new TextView[N]; // create an empty array;
		
		//GEBRUIKEN VOOR LATER
		for (int i = 0; i < N; i++) {
		    // create a new textview
		    final TextView rowTextView = new TextView(this);

		    // set some properties of rowTextView or something
		    rowTextView.setText("This is row #" + i);

		    // add the textview to the linearlayout
		    ln.addView(rowTextView);

		    // save a reference to the textview for later
		    myTextViews[i] = rowTextView;
		    
		}
		//GEBRUIKEN VOOR LATER
		//1MAAK
	}
	//Wijzigt naar intent 
	public void nextIntent(){
		Intent intent = new Intent();
		intent.setClass(this, InputHabitActivity.class);
		startActivity(intent);
		
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
