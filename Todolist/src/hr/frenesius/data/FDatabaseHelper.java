package hr.frenesius.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FDatabaseHelper extends SQLiteOpenHelper {
	//GENERAL DATABASE VARIABLES
	private static final String MYDATABASE = "Happit";
	private static final int VERSION = 1;

	//TABLE1
	private static final String SPELER = "";
	
	//TABLE2
	private static final String HABIT = "";
	
	//QUERY's
	private static final String CREATETABLE1 = "";
	
	
	//MAAK ERD 
	
	
	public FDatabaseHelper(Context connection) {
		 super(connection, MYDATABASE, null, VERSION);
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATETABLE1);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
}

