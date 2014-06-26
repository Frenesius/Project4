package hr.frenesius.list;

import java.util.Calendar;

public class User {
	/**Class about the user
	 * Contains all info about the user
	 * 
	 * */
	
	//Gegevens over user
	private String name;							//Name of user
	private int rewardPoint;						//The reward points the user has



	
	public void addRewardPoint(int reward){
		rewardPoint += reward;
	}
	
	
	
	
	//Setters


	public void setName(String name1) {
		this.name = name1;
	}
	public void setRewardpoint(int rewardPoint) {
		this.rewardPoint = rewardPoint;
	}
	
	//Getters

	public String getName() {
		return name;
	}

	public int getRewardpoint() {
		return rewardPoint;
	}
}
