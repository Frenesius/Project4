package hr.frenesius.list;


import java.io.Serializable;
//Imports
import java.util.Calendar;

import android.os.Parcel;
import android.os.Parcelable;

public class Habit implements Parcelable {

	// Attributes
	private String title;
	private String description; // Text voor de to do list
	Calendar date = Calendar.getInstance();
	private Boolean checkbox; // Checkbox voor de to do list
	private Integer reward; // Reward voor de to do list
	
	
	
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

	public void Alarm() { // Als datum over eenkomt met datum.now(), stuur push
							// bericht
		// Calendar cal = Calendar.getInstance();
		// If (cal = date){
		//
		// }

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

	public Boolean getCheckbox() {
		return checkbox;
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
		dest.writeString(title);
		dest.writeString(description);
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
		this.title = in.readString();
		this.description = in.readString();
	}

}
