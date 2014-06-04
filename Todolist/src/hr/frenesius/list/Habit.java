package hr.frenesius.list;


import java.io.Serializable;
//Imports
import java.util.Calendar;

import android.os.Parcel;
import android.os.Parcelable;

public class Habit implements Parcelable {

	// Attributes
	private String text = "test"; // Text voor de to do list
	Calendar date = Calendar.getInstance();
	private Boolean checkbox; // Checkbox voor de to do list
	private Integer reward; // Reward voor de to do list
	
	
	
	// Constructors
	public Habit() {

	}

	
	public Habit(String string) {
		this.text = string;
		this.reward = 10;
	}

	public Habit(String string, Boolean Checkbox) {
		this.text = string;
		this.checkbox = Checkbox;
		this.reward = 10;
	}
	
	
	public Habit(String string, Calendar date) {
		this.text = string;
		this.reward = 10;
		this.date = date;
	}

	public Habit(String string, Boolean Checkbox, Calendar date) {
		this.text = string;
		this.checkbox = Checkbox;
		this.date = date;
		this.reward = 10;
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
	
	//PARCELABLE
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(text);
		//Write dingen in je parcelabel
		
	}
    public static final Parcelable.Creator<Habit> CREATOR = new Parcelable.Creator<Habit>() {
    	public Habit createFromParcel(Parcel in) {
    		return new Habit(in);
    	}

		public Habit[] newArray(int size) {
			return new Habit[size];
		}
    };

	private Habit(Parcel in) {
		this.text = in.readString();
	}

}
