package hr.frenesius.list;


//Imports
import java.util.Calendar;

public class Habit {

	// Attributes
	private String text = "test"; // Text voor de to do list
	Calendar date = Calendar.getInstance();
	private Boolean checkbox; // Checkbox voor de to do list
	private Integer reward; // Reward voor de to do list
	
	
	
	// Constructors
	public Habit() {

	}

	public Habit(String string) {
		Habit habit = new Habit();
		habit.text = string;
		habit.reward = 10;
	}

	public Habit(String string, Boolean Checkbox) {
		Habit habit = new Habit();
		habit.text = string;
		habit.checkbox = Checkbox;
		habit.reward = 10;
	}

	public Habit(String string, Boolean Checkbox, Calendar date) {
		Habit habit = new Habit();

		habit.text = string;
		habit.checkbox = Checkbox;
		habit.date = date;
		habit.reward = 10;
	}

	// Methods

	public void Alarm() { // Als datum over eenkomt met datum.now(), stuur push
							// bericht
		// Calendar cal = Calendar.getInstance();
		// If (cal = date){
		//
		// }

	}

	// Getters

	public String getText() {
		return text;
	}

	public Calendar getDate() {
		return date;
	}

	public Boolean getCheckbox() {
		return checkbox;
	}

	public Integer getReward() {
		return reward;
	}

	// Setters

	public void setText(String text1) {
		text = text1;
	}
	
	
//Set date changed the constructor, 
	public void setDate(int year, int month, int day) {
	
		int lyear = year;
		int lmonth = month;
		int lday = day;
		date.set(lyear, lmonth, lday);
			
			
		//	Date.set(2014, Calendar.JANUARY, 1);
			
	}

	public void setCheckbox(Boolean checkbox) {
		checkbox = checkbox;
	}

	public void setReward(Integer reward) {
		reward = reward;
	}
}
