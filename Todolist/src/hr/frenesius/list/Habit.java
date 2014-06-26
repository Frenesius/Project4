package hr.frenesius.list;



public class Habit {
/*
 * Class contains Habits
 * Habits have points and can be triggered in the main activity
 * 
 */
	// Attributes
	private String title;
	private String description; // Text voor de to do list
	private int reward = 10; // Reward voor de to do list
	
	
	
	// Constructors
	public Habit() {

	}

	public Habit(String title, String description) {
		this.description = description;
		this.title = title;
		this.reward = 10;
	}
	
	// Getters
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
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
	public void setReward(int reward1) {
		reward = reward1;
	}
	
	

}
