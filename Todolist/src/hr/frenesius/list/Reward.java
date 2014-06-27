package hr.frenesius.list;


import hr.frenesius.todolist.MainActivity;
import hr.frenesius.todolist.R;

public class Reward {
	/**
	 * This class contains Reward object
	 * You can buy rewards with the Points in the User Object
	 * 
	 */
	private String title;
	private String description;
	private int point;
	private boolean rewardBought = false;
	private boolean selected = false;
	
	private int pictureLock;
	private int pictureLockThumb;
	private int pictureLockThumbOnclick;
	
	private int pictureUnlock;

	private int pictureUnlockThumb;
	private int pictureUnlockThumbOnclick;
	
	private int pictureSelect;
	private int pictureSelectThumb;
	private int pictureSelectThumbOnclick;
	
	public Reward(){
		
	}

	//HERSCHRIJVEN
	public void buyReward(){
		int userPoints = MainActivity.user.getRewardpoint();
		int remainingPoints = userPoints - point;
		MainActivity.user.setRewardpoint(remainingPoints);
		rewardBought = true;
	}
	
	
	public void selectReward(){
		selected = true;	
	}	
	public void deselectReward(){
		selected = false;
	}
	
	//Getters setters
	public int getPictureLockThumb() {
		return pictureLockThumb;
	}

	public void setPictureLockThumb(int pictureLockThumb) {
		this.pictureLockThumb = pictureLockThumb;
	}

	public int getPictureLockThumbOnclick() {
		return pictureLockThumbOnclick;
	}

	public void setPictureLockThumbOnclick(int pictureLockThumbOnclick) {
		this.pictureLockThumbOnclick = pictureLockThumbOnclick;
	}

	public int getPictureUnlockThumb() {
		return pictureUnlockThumb;
	}

	public void setPictureUnlockThumb(int pictureUnlockThumb) {
		this.pictureUnlockThumb = pictureUnlockThumb;
	}

	public int getPictureUnlockThumbOnclick() {
		return pictureUnlockThumbOnclick;
	}

	public void setPictureUnlockThumbOnclick(int pictureUnlockThumbOnclick) {
		this.pictureUnlockThumbOnclick = pictureUnlockThumbOnclick;
	}

	public int getPictureSelectThumb() {
		return pictureSelectThumb;
	}

	public void setPictureSelectThumb(int pictureSelectThumb) {
		this.pictureSelectThumb = pictureSelectThumb;
	}

	public int getPictureSelectThumbOnclick() {
		return pictureSelectThumbOnclick;
	}

	public void setPictureSelectThumbOnclick(int pictureSelectThumbOnclick) {
		this.pictureSelectThumbOnclick = pictureSelectThumbOnclick;
	}
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
	

	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected1) {
		this.selected = selected1;
	}
	public int getPictureLock() {
		return pictureLock;
	}

	public void setPictureLock(int pictureLock1) {
		this.pictureLock = pictureLock1;
	}

	public int getPictureUnlock() {
		return pictureUnlock;
	}

	public void setPictureUnlock(int pictureUnlock1) {
		this.pictureUnlock = pictureUnlock1;
	}

	public int getPictureSelect() {
		return pictureSelect;
	}

	public void setPictureSelect(int pictureSelect1) {
		this.pictureSelect = pictureSelect1;
	}

	
}

