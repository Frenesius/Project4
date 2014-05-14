package hr.frenesius.todolist;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.*;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

    Button b1;	//Button
    
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
//Buttons voor on click        
        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(abc);
        
    }
// Onclick listener
	View.OnClickListener abc = new View.OnClickListener() {
	    public void onClick(View v) {
	    manText(); 
	    testDate(); //TEST
	    }
	};
	
	
	//Intent + bundle
    //Test
    //Datepicker testen
	//Datum pakken
	public void testDate(){			// On click add calendar view
		
		DatePicker a2 = (DatePicker) findViewById(R.id.datePicker1);	//Maakt datepicker var aan
		int Dateday = a2.getDayOfMonth();	//Pakt datepicker dag
		int Datemonth = a2.getMonth();		//Pakt datepicker maand
		int Dateyear = a2.getYear();		//Pakt datepicker jaar
		
		String day = String.valueOf(Dateday);		//Parsed dag in string
		String month = String.valueOf(Datemonth);	//Parsed maand in string
		String year = String.valueOf(Dateyear);		//Parsed jaar in string
		
		String a = day + "-" + month + "-" + year;	//Maakt er een DD-MM-YYYY van
		
		
		
		TextView tv = (TextView) findViewById(R.id.dateView1);	//Pakt textview in een variabele
		tv.setText(a);	//Zet de datum in textview
		
		
		Toast.makeText(getApplicationContext(), "testDate() triggered", 
				   Toast.LENGTH_LONG).show();	//Debug Datum
	}
	
	
	
	//Pakt text van edittext en zet in view
    	public void manText(){
    			//Try catch removed
    		Toast.makeText(getApplicationContext(), "mantext() triggered", 
    				   Toast.LENGTH_LONG).show(); // Debug
    		
    		
    		EditText i = (EditText) findViewById(R.id.editText1);
    		Editable text = i.getText();
    		String b = text.toString(); //Parse
    			
    		TextView a = (TextView) findViewById(R.id.textView1);
    		a.setText(b);
    		
    	}
  //Einde
    
    
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
