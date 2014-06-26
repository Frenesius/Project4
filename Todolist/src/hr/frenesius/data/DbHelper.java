package hr.frenesius.data;

import hr.frenesius.list.Message;
import hr.frenesius.todolist.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper{
/*
 * Database Helper class
 * Contains all the queries used in the application
 */
	DbDatabaseCreate db;
	
	//DATABASE
	public static final String DATABASE_NAME = "Happit.db";
	public static int DATABASE_VERSION = 53;
	
	//TABLES
	public static final String GOODHABIT_TABLE = "goodhabit";
	public static final String BADHABIT_TABLE = "badhabit";
	public static final String REWARD_TABLE = "reward";
	
	//ATTRIBUTES
	public static final String KEY_ID = "_id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_DESCRIPTION = "description" ;
	public static final String KEY_REWARD = "reward";
	public static final String KEY_SELECTREWARD = "select_reward";
	
	public static final String KEY_PICTUREUNLOCK = "picture_unlock";
	public static final String KEY_PICTUREUNLOCK_THUMB = "picture_unlock_thumb";
	public static final String KEY_PICTUREUNLOCK_THUMB_ONCLICK = "picture_unlock_thumb_onclick";
	
	public static final String KEY_PICTURELOCK = "picture_lock";
	public static final String KEY_PICTURELOCK_THUMB = "picture_lock_thumb";
	public static final String KEY_PICTURELOCK_THUMB_ONCLICK ="picture_lock_thumb_onclick";
	
	public static final String KEY_PICTURESELECT = "picture_select";
	public static final String KEY_PICTURESELECT_THUMB = "picture_select_thumb";
	public static final String KEY_PICTURESELECT_THUMB_ONCLICK = "picture_select_thumb_onclick";
	
	//BOOLEAN WAARDE 0 = FALSE 1 = TRUE
	public static final String KEY_POINT = "point";	
	public static final String KEY_BOUGHT = "bought";	
	
	public  Context context1;
		
		
	private String CREATEGOODHABITTABLE = "CREATE TABLE "+GOODHABIT_TABLE+"("+
									KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
									KEY_TITLE+" VARCHAR(255) NOT NULL,"+
									KEY_DESCRIPTION+" VARCHAR(255) NOT NULL,"+
									KEY_REWARD +" INTEGER NOT NULL);";
									
	private String CREATEBADHABITTABLE = "CREATE TABLE "+BADHABIT_TABLE+"("+
									KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
									KEY_TITLE+" VARCHAR(255) NOT NULL,"+
									KEY_DESCRIPTION+" VARCHAR(255) NOT NULL,"+
									KEY_REWARD +" INTEGER NOT NULL);";
	
	private String CREATEREWARDTABLE = "CREATE TABLE "+REWARD_TABLE+"("+
									KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
									KEY_TITLE+" VARCHAR(255) NOT NULL,"+
									KEY_DESCRIPTION+" VARCHAR(255) NOT NULL,"+
									KEY_BOUGHT+" INTEGER NOT NULL,"+
									KEY_SELECTREWARD+" INTEGER NOT NULL,"+
									KEY_POINT +" INTEGER NOT NULL,"+
	
									KEY_PICTUREUNLOCK+" INTEGER NOT NULL,"+
									KEY_PICTUREUNLOCK_THUMB +" INTEGER NOT NULL,"+
									KEY_PICTUREUNLOCK_THUMB_ONCLICK+" INTEGER NOT NULL,"+
	
									KEY_PICTURELOCK+" INTEGER NOT NULL,"+
									KEY_PICTURELOCK_THUMB+" INTEGER NOT NULL,"+
									KEY_PICTURELOCK_THUMB_ONCLICK+" INTEGER NOT NULL,"+
	
									KEY_PICTURESELECT+" INTEGER NOT NULL,"+
									KEY_PICTURESELECT_THUMB+" INTEGER NOT NULL,"+
									KEY_PICTURESELECT_THUMB_ONCLICK+" INTEGER NOT NULL);";
	
	
	//SELECT QUERIES
	public static final String 	SELECT_REWARDTABLEQUERY = "SELECT "
								+DbHelper.KEY_ID+
							", "+DbHelper.KEY_PICTURELOCK+
							", "+DbHelper.KEY_PICTURELOCK_THUMB+
							", "+DbHelper.KEY_PICTURELOCK_THUMB_ONCLICK+
		
							", "+DbHelper.KEY_PICTUREUNLOCK+
							", "+DbHelper.KEY_PICTUREUNLOCK_THUMB+
							", "+DbHelper.KEY_PICTUREUNLOCK_THUMB_ONCLICK+
		
							", "+DbHelper.KEY_PICTURESELECT+
							", "+DbHelper.KEY_PICTURESELECT_THUMB+
							", "+DbHelper.KEY_PICTURESELECT_THUMB_ONCLICK+
		
							", "+DbHelper.KEY_TITLE+
							", "+DbHelper.KEY_DESCRIPTION+
							", "+DbHelper.KEY_SELECTREWARD+
							", "+DbHelper.KEY_BOUGHT+
							", "+DbHelper.KEY_POINT+
							" FROM "+DbHelper.REWARD_TABLE+";";
	
		
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context1 = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(CREATEGOODHABITTABLE);
			db.execSQL(CREATEBADHABITTABLE);
			db.execSQL(CREATEREWARDTABLE);
			
		}catch(SQLException e){
			Message.message(context1, "Error, something went wrong.");
		}	 
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		try {
			db.execSQL("DROP TABLE IF EXISTS "+GOODHABIT_TABLE+";");
			db.execSQL("DROP TABLE IF EXISTS "+BADHABIT_TABLE+";");
			db.execSQL("DROP TABLE IF EXISTS "+REWARD_TABLE+";");
			
			
			onCreate(db);
			Toast.makeText(context1, "onUpgrade()", 1).show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message.message(context1, "Well.. That didn't work well." + e);
		}
	}
}


