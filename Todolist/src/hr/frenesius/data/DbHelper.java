package hr.frenesius.data;

import hr.frenesius.list.Message;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


	
	
	
public class DbHelper extends SQLiteOpenHelper{

		
	public static final String KEY_ID = "_id";

		
	public static final String KEY_TITLE = "title";
	public static final String KEY_DESCRIPTION = "description" ;
	public static final String KEY_REWARD = "reward";
		
	public static final String DATABASE_NAME = "Happit.db";
	public static final int DATABASE_VERSION = 1;
	//Tables
	public static final String GOODHABIT_TABLE = "habit";
	
	
	public  Context context1;
		
		
	private String CREATEGOODHABITTABLE = "CREATE TABLE "+GOODHABIT_TABLE+"("+
									KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
									KEY_TITLE+" VARCHAR(255) NOT NULL,"+
									KEY_DESCRIPTION+" VARCHAR(255) NOT NULL,"+
									KEY_REWARD +" INTEGER NOT NULL);";
									
									
		
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context1 = context;
		
		Toast.makeText(context1, "DbHelper()", 1).show();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
		db.execSQL(CREATEGOODHABITTABLE);
		
		Toast.makeText(context1, "onCreate()", 1).show();
		}catch(SQLException e){
			Message.message(context1, "onCreate" + e);
		}
			
			 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		try {
			db.execSQL("DROP TABLE IF EXISTS "+GOODHABIT_TABLE+";");
			onCreate(db);
			Toast.makeText(context1, "onUpgrade()", 1).show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message.message(context1, "U" + e);
		}
	}
		
		
	
	
}


