package hr.frenesius.list;


//Imports
import java.util.Calendar;



public class Habit {

	// Attributes
	private String Text; // Text voor de to do list
	private Calendar Date; // Datum voor de to do list
	private Boolean Checkbox; // Checkbox voor de to do list
	private Integer Reward; // Reward voor de to do list

	// Constructors
	public Habit() {

	}

	public Habit(String string) {
		Habit Habit = new Habit();

		Habit.Text = string;
		Habit.Reward = 10;
	}

	public Habit(String string, Boolean Checkbox) {
		Habit Habit = new Habit();

		Habit.Text = string;
		Habit.Checkbox = Checkbox;

		Habit.Reward = 10;
	}

	public Habit(String string, Boolean Checkbox, Calendar date) {
		Habit Habit = new Habit();

		Habit.Text = string;
		Habit.Checkbox = Checkbox;
		Habit.Date = date;
		Habit.Reward = 10;
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
		return Text;
	}

	public Calendar getDate() {
		return Date;
	}

	public Boolean getCheckbox() {
		return Checkbox;
	}

	public Integer getReward() {
		return Reward;
	}

	// Setters

	public void setText(String text) {
		Text = text;
	}
//Set date changed the constructor, 
	public void setDate(int year, int month, int day) {
		try{ 
			Date.set(year, month, day);
		}catch(Exception e){
			e.getStackTrace();
		}
		
	}

	public void setCheckbox(Boolean checkbox) {
		Checkbox = checkbox;
	}

	public void setReward(Integer reward) {
		Reward = reward;
	}

	

}
