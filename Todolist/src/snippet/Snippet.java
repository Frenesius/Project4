package snippet;

import hr.frenesius.list.Habit;
import hr.frenesius.list.Reward;
import hr.frenesius.todolist.R;

import java.util.Calendar;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TableRow.LayoutParams;

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

		//
		//voeg codes die je gaat verwijderen toe in snippet(als het grote code is en niet een int)
		//Voer hier onder je Query's toe
		//V
		
		
		
		
		//^
		Toast.makeText(getApplicationContext(), "exec DATABASETEST()", Toast.LENGTH_LONG).show();
	}
	
	
	
	
	
	
	
	Datepicker
	
	
			private void getDateDatepicker(){
			
			DatePicker a2 = (DatePicker) findViewById(R.id.inputdatePicker1); // Maakt Datepicker var aan
			//Get date														
			day = a2.getDayOfMonth(); // Pakt datepicker dag
			month = a2.getMonth(); // Pakt datepicker maand
			year = a2.getYear(); // Pakt datepicker jaar	

		}
	
	
	
	
	

	
	
	private void setHabitCounter(){
		ln = (LinearLayout) this.findViewById(R.id.DashboardLinearLayout);
		ln.setOrientation(LinearLayout.VERTICAL); 
		
		TableLayout ll = (TableLayout) findViewById(R.id.DashboardMain);
		TextView tv = new TextView(this);
		
		//Table layout
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		lp.leftMargin = 10;
		lp.rightMargin = 15;
		lp.bottomMargin = 10;
		ll.setLayoutParams(lp);
		
		//Set counter 
		int hCounter = habitcounter -1;
		tv.setText("Aantal Habits:" + hCounter);
		ll.addView(tv);	
	}
	
	
	
	
	
		private void processObject(){
		if(goodHabitTRIGGER == true){
			addGoodHabitToDashboard();
			
			goodHabitTRIGGER = false;
		}
		if(badHabitTRIGGER == true){
			addBadHabitToDashboard();
			badHabitTRIGGER = false;
		}
	}
	
	
	private void setUserAantalHabits(){
		int aantalHabits = goodHabitlist.size() + badHabitlist.size();
		user.setAantalHabits(aantalHabits);
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void addReward(){
		//Variabelen
		int length = rewardList.size();	//
		int row = 0;
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
		
		RadioGroup rGroup = new RadioGroup(this); //create the RadioGroup	
		rGroup.setOrientation(RadioGroup.HORIZONTAL);//or RadioGroup.VERTICAL
		rGroup.setId(RADIOGROUP_ID + row );
		
			
		
		//Workaround voor probleem
		final int N = length; // total number of textviews to add
		int rwCount = 0;
		int rwCount2 = 0;
			for (int i = 0; i < N; i++) {
				
				//TODO IF i>3 NIEUWE RIJ -> set padding(?)
				
				Reward rw = rewardList.get(i); //Pakt reward
				//Maakt vars aan
				
		//
		//RADIOBUTTON
		//
				LayoutParams rbl = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				RadioButton rb = new RadioButton(this);
				
				Drawable d;
				//Assigned drawable
					if(rw.isRewardBought()){
						d = getResources().getDrawable(rw.getPictureUnlockThumb());
						rb.setButtonDrawable(d);
							if(rw.isSelected()){
								d = getResources().getDrawable(rw.getPictureSelectThumb());
								rb.setButtonDrawable(d);
							}
					}if(!rw.isRewardBought() && !rw.isSelected()){
						d = getResources().getDrawable(rw.getPictureLockThumb());	
						rb.setButtonDrawable(d);
					}
				rb.setLayoutParams(rbl);
				int startValue = 1121;
				int id = startValue + rwCount;
				rb.setId(id);	
		//
		//EINDE RADIOBUTTON
		//
				
		//
		//RADIOGROUP
		//
				
			rGroup.addView(rb);
			
		//
		//EINDE RADIOGROUP
		//
				
			//
			//TABLE ROW
			// 
				//
			//EINDE TABLE ROW
			//
						
			if(rwCount2 == 3){
				
				TableRow tr2 = new TableRow(this);
				tr2.addView(rGroup);
				tl.addView(tr2, row);				
				row++;
				rwCount2 = 0;
				
			}		
			//
			//TABLE LAYOUT
			//
			//
			//EINDE TABLE LAYOUT
			//

						rwCount++;
						rwCount2++;
				}
				
				
			
			
			
			
	}
	
	
	
	
	
	
	View.OnClickListener selectButtonListener = new View.OnClickListener() {
		public void onClick(View v) {
			try{
			boolean defaultCheck = false;
			
			rg = (RadioGroup) findViewById(RADIOGROUP_ID);
			unselectEverything();
				switch(rg.getCheckedRadioButtonId()){
					case 1121:
						updateSelectTable(0);
						break;
					case 1122:
						updateSelectTable(1);
						break;
					case 1123:
						updateSelectTable(2);
						break;
					default:
						Toast.makeText(getApplicationContext(), "Please select a reward.", Toast.LENGTH_SHORT).show();
						defaultCheck = true;
				}if(!defaultCheck){
						restartActivity();
				}else{
					Toast.makeText(getApplicationContext(), "You do not have enough points.", Toast.LENGTH_SHORT).show();
				}
			}catch(Exception e){
				Toast.makeText(getApplicationContext(), "You do not unlocked this reward.", Toast.LENGTH_LONG).show();
			}
			}
	};

	
	
	
	
	
	
	
	**/
	
}
