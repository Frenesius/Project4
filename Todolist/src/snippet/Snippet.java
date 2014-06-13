package snippet;

import hr.frenesius.list.Habit;

import java.util.Calendar;

import android.widget.Toast;

public class Snippet {
	/**		//SETS
	private void setDateTextview(){
		//set date
		TextView tv = (TextView) findViewById(R.id.DatetextView); // Pakt textview in een variabele
		
		//Parse date
		Calendar a = h.getDate();
		
		int ab = a.get(Calendar.DAY_OF_MONTH);
		int ac = a.get(Calendar.MONTH);
		int ad = a.get(Calendar.YEAR);
		
		String ca = String.valueOf(ab); // Parsed dag in string
		String ba = String.valueOf(ac); // Parsed maand in string
		String aa = String.valueOf(ad); // Parsed jaar in string
		
		String c = ca + "-" + ba + "-" + aa;
	
		//Set date	
		tv.setText(c); // Zet de datum in textview
	}

	private void setHabitTextview(){
		String habittext = habit.getText();
		TextView a = (TextView) findViewById(R.id.inputtextView);
		
		a.setText(habittext);	
	}			
	
//EINDE SETS	
	
	
	
	
	
	
	
		private void DATABASETEST(){
		//Om dit te gebruiken moet je het in mainactivity zetten onder:
		//if(MainActivityTRIGGER == true)  processObject(); onder regel 97
		//Ook eerst een habit toevoegen, daarna pas kan dit uitgevoerd worden
		Habit h = PositiveHabitlist.get(0);
		String title = h.getTitle();				//title is de string voor title die je in databse moet zetten
		String description = h.getDescription();	//description is een string die je in databse moet zetten
		int reward = h.getReward();					//Ook in database maar let op integer!
		
		
		//Probeer eerst de Calendar object date in database te zetten, 
		//Mocht dat niet lukken, heb ik string gemaakt dateString
		Calendar date = h.getDate();				//pakt calender object, maar dit is neit in string formaat
			int ab = date.get(Calendar.DAY_OF_MONTH);	//pakt dag in int
			int ac = date.get(Calendar.MONTH);			//Pakt maand in int
			int ad = date.get(Calendar.YEAR);			//Pakt jaar in int
		
			String ca = String.valueOf(ab); // Parsed dag in string
			String ba = String.valueOf(ac); // Parsed maand in string
			String aa = String.valueOf(ad); // Parsed jaar in string
		
		String dateString = ca + "-" + ba + "-" + aa;	//dd-mm-yyyy
		//
		//voeg codes die je gaat verwijderen toe in snippet(als het grote code is en niet een int)
		//Voer hier onder je Query's toe
		//V
		
		
		
		
		//^
		Toast.makeText(getApplicationContext(), "exec DATABASETEST()", Toast.LENGTH_LONG).show();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
**/	

	
	
	
	
	
	
	
	
}
