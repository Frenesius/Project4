package hr.frenesius.todolist;

import java.util.Calendar;




public class Todo {
	//Attributes
	private String 	 Text;			//Text voor de to do list
	private Calendar Date;				//Datum voor de to do list
	private Boolean  Checkbox;		//Checkbox voor de to do list
	private Integer  Reward;			//Reward voor de to do list
	
	//Constructors
	public Todo(){

	}
	
	public Todo(String string){
		Todo todo = new Todo();
		
		todo.Text = string;		
		todo.Reward = 10;
	}
	public Todo(String string, Boolean Checkbox){
		Todo todo = new Todo();
		
		todo.Text = string;	
		todo.Checkbox = Checkbox;
		
		todo.Reward = 10;
	}
	
	public Todo(String string, Boolean Checkbox, Calendar date){
		Todo todo = new Todo();
		
		todo.Text = string;	
		todo.Checkbox = Checkbox;
		todo.Date = date;	
		todo.Reward = 10;
	}
	//Methods

	public void Alarm(){		//Als datum over eenkomt met datum.now(), stuur push bericht
		//Calendar cal = Calendar.getInstance();
		//If (cal = date){
		//	
		//}
		
	 }
	
	
	
	
	//Getters
	
	public String getText() {
		return Text;
	}

	public Calendar getDate() {
		return Date;
	}

	public Boolean getCheckbox() {
		return Checkbox;
	}

	public Integer getReward() {
		return Reward;
	}


		
	
	//Setters
	
	public void setText(String text) {
		Text = text;
	}

	public void setDate(Calendar date) {
		Date = date;
	}

	public void setCheckbox(Boolean checkbox) {
		Checkbox = checkbox;
	}

	public void setReward(Integer reward) {
		Reward = reward;
	}	
	
}
