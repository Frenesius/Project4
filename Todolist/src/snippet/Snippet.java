package snippet;

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
	
**/	

	
	
	
	
	
	
	
	
}
