package hr.frenesius.data;

import hr.frenesius.list.Message;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class FDatabaseHelper extends SQLiteOpenHelper {
	//GENERAL DATABASE VARIABLES
	private static final String MYDATABASE = "Happit";
	private static final int VERSION = 1;

	//TABLE1
	private static final String SPELER = "";
	
	//TABLE2
	private static final String HABIT = "Habit";
	private static final String ID = "_id";
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String DATE = "date";
	private static final String REWARD = "reward";
	
	//QUERY's
	private static final String CREATETABLE_HABIT = "CREATE TABLE " + HABIT 	+" (" + 
														ID 				+" INT PKEY AUTOINCREMENT NOT NULL," 	 +
														TITLE			+" VARCHAR(45) NOT NULL," 	 +
														DESCRIPTION		+" VARCHAR(255) NOT NULL,"	 +
														DATE			+" DATETIME,"	 +
														REWARD			+" INT NOT NULL DEFAULT '10'"		 +
																		");" ;
	
	
	//MAAK ERD 
	
	
	public FDatabaseHelper(Context connection) {
		 super(connection, MYDATABASE, null, VERSION);
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATETABLE_HABIT);
		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(FDatabaseHelper.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
	        db.execSQL("DROP TABLE IF EXISTS " + HABIT);
	        onCreate(db);
		
	}
}

