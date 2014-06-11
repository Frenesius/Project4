package hr.frenesius.list;

import java.util.Calendar;

public class User {
	//Gegevens over user
	private String name;							//Name of user
	private int rewardPoint;						//The reward points the user has
	private Calendar visitDate; 					//When user started playing this game
	private int startedGames;						//How many times user visited the app
	private int aantalHabits;
	private Calendar firstLogin;
	
	//Setters
	public void setfirstLogin(Calendar firstLogin){
		this.firstLogin = firstLogin;
	}
	public void setAantalHabits(int aantalHabits){
		this.aantalHabits = aantalHabits;
	}
	public void setvisitDate(Calendar visitDate) {
		this.visitDate = visitDate;
	}
	public void setStartedGames(int startedGames) {
		this.startedGames = startedGames;
	}
	public void setName(String name1) {
		this.name = name1;
	}
	public void setRewardpoint(int rewardPoint) {
		this.rewardPoint = rewardPoint;
	}
	
	//Getters
	public Calendar getfirstLogin(){
		return firstLogin;
	}
	public int getAantalHabits(){
		return aantalHabits;
	}
	public String getName() {
		return name;
	}
	public Calendar getvisitDate() {
		return visitDate;
	}
	public int getStartedGames() {
		return startedGames;
	}
	public int getRewardpoint() {
		return rewardPoint;
	}
}
