package hr.frenesius.list;

import hr.frenesius.todolist.MainActivity;
import hr.frenesius.data.DbHelper;
import hr.frenesius.list.User;
public class Reward {
	private int picture;
	private String title;
	private String description;
	private int point;
	private boolean rewardBought = false;
	private boolean selected = false;
	

	public Reward(){
		
	}
	public Reward(int drawable, String title1, String description1, int point1){
		this.picture = drawable;
		this.title = title1;
		this.description = description1;
		this.point = point1;
		
	}
	//HERSCHRIJVEN
	public void buyReward(){
		
	}
	public void sellReward(){

		
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
	
	public int getPicture() {
		return picture;
	}
	public void setPicture(int picture) {
		this.picture = picture;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}


	
}

