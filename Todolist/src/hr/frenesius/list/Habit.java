package hr.frenesius.list;



//Imports
import java.util.Calendar;

import android.os.Parcel;
import android.os.Parcelable;

public class Habit {

	// Attributes
	private String title;
	private String description; // Text voor de to do list
	Calendar date = Calendar.getInstance();
	private int reward = 10; // Reward voor de to do list
	
	
	
	// Constructors
	public Habit() {

	}



	public Habit(String title, String description) {
		this.description = description;
		this.title = title;
		this.reward = 10;
	}
	
	
	public Habit(String title, String description, Calendar date) {
		this.title = title;
		this.description = description;
		this.reward = 10;
		this.date = date;
	}



	// Methods

	public void Alarm() { 

	}

	// Getters

	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}

	public Calendar getDate() {
		return date;
	}

	public Integer getReward() {
		return reward;
	}

	// Setters
	public void setTitle(String title1) {
		title = title1;
	}
	
	public void setDescription(String description1) {
		description = description1;
	}
	
	
//Set date changed the constructor, 
	public void setDate(int year, int month, int day) {
	
		int lyear = year;
		int lmonth = month;
		int lday = day;
		date.set(lyear, lmonth, lday);
			
			
		//	Date.set(2014, Calendar.JANUARY, 1);
			
	}



	public void setReward(int reward1) {
		reward = reward1;
	}
	
	

}
