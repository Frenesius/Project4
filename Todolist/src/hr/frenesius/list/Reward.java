package hr.frenesius.list;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import hr.frenesius.todolist.MainActivity;
import hr.frenesius.todolist.R;
import hr.frenesius.data.DbDatabaseCreate;
import hr.frenesius.data.DbHelper;
import hr.frenesius.list.User;
public class Reward {

	private int pictureLock;
	private int pictureUnlock;
	private int pictureSelect;

	private String title;
	private String description;
	private int point;
	private boolean rewardBought = false;
	private boolean selected = false;
	
	
	DbHelper helper;
	SQLiteDatabase db;
	DbDatabaseCreate entry;
	Cursor cursor;
	

	public Reward(){
		
	}

	//HERSCHRIJVEN
	public void buyReward(){
		int userPoints = MainActivity.user.getRewardpoint();
		int remainingPoints = userPoints - point;
		MainActivity.user.setRewardpoint(remainingPoints);
		rewardBought = true;

		
	}
	
	public void sellReward(){

		
	}
	public void selectReward(){
		selected = true;
		
	}	
	public void deselectReward(){
		selected=false;
		
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

