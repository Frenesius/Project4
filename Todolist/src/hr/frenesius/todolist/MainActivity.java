package hr.frenesius.todolist;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import hr.frenesius.list.Habit;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
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
	public static boolean MainActivityTRIGGER = false; //Triggerchecker voor onResume
	
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
			taddTextView();
				}
		}
		
	
	
	private void taddTextView(){
		//Habitlist.add voegt habits toe aan list
		//.get(); geeft het terug
//LOOP DIT VOOR RIJEN
		//Pakt habit van Activity Input
		Habit h = (Habit)getIntent().getExtras().getParcelable("INPUT_KEY");
		//Voegt habit toe aan list
		Habitlist.add(h);
		//Haalt habit uit list
	
		Habit l = Habitlist.get(0);

		TextView tv = (TextView) findViewById(R.id.ListTextview1);
		String s = l.getText();
		s = s + "\n";
		tv.setText(s);
		
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
