package hr.frenesius.list;

import hr.frenesius.todolist.MainActivity;
import hr.frenesius.list.User;
public class Reward {
	private String title;
	private String description;
	private int point;
	private boolean rewardBought = false;
	

	public Reward(){
		
	}
	public Reward(int point1){
		this.point = point1;
	}
	public void buyReward(){
		User u = MainActivity.user;
		int userPoints = u.getRewardpoint();
		int remainingPoints = userPoints - point;
		u.setRewardpoint(remainingPoints);
		rewardBought = true;
		
	}
	public void sellReward(){
		User u = MainActivity.user;
		int userPoints = u.getRewardpoint();
		int remainingPoints = userPoints + point;
		u.setRewardpoint(remainingPoints);
		rewardBought = false;
		
	}
	
	//Getters setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isRewardBought() {
		return rewardBought;
	}

	public void setRewardBought(boolean rewardBought) {
		this.rewardBought = rewardBought;
	}
	
	
	
}

