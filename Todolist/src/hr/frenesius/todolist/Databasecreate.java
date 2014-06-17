package hr.frenesius.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasecreate {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "Title";
	public static final String KEY_DESCRIPTION = "Description" ;
	public static final String KEY_REWARD = "Reward";
	
	private static final String DATABASE_NAME = "Habbitdb";
	private static final String DATABASE_TABLE = "HabbitTable";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE+ " (" +
			KEY_ROWID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
			KEY_TITLE + "TEXT NOT NULL, " +
			KEY_DESCRIPTION + " TEXT NOT NULL, " +
			KEY_REWARD + " INT);"
			
			);
			
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
			onCreate(db);
		}
		
		
	}
	
	public Databasecreate(Context c){
		ourContext = c;
		
	}
	
	public Databasecreate open(){
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		ourHelper.close();
		
	}

	public long createEntry(String title, String description, int reward) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_TITLE, title);
		cv.put(KEY_DESCRIPTION, description);
		cv.put(KEY_REWARD, reward);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	
}


