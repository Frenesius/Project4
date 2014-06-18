package hr.frenesius.data;

import hr.frenesius.list.Message;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


	
	
	
	public class DbHelper extends SQLiteOpenHelper{

		
		public static final String KEY_ID = "_id";
		public static final String KEY_TITLE = "title";
		public static final String KEY_DESCRIPTION = "description" ;
		public static final String KEY_REWARD = "reward";
		
		private static final String DATABASE_NAME = "Happit.db";
		private static final String DATABASE_TABLE = "habit";
		private static final int DATABASE_VERSION = 12;
		private  Context context1;
		
		
		private String QUERYCREATE = "CREATE TABLE "+DATABASE_TABLE+"("+
										KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
										KEY_TITLE+" VARCHAR(255) NOT NULL,"+
										KEY_DESCRIPTION+" VARCHAR(255) NOT NULL,"+
										KEY_REWARD +" INTEGER NOT NULL);";
										
									
		
		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			this.context1 = context;
			Message.message(context1, "DbHelper()");
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
			db.execSQL(QUERYCREATE);
			
			Message.message(context1, "onCreate()");
			}catch(SQLException e){
				Message.message(context1, "onCreate" + e);
			}
			
			 
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			try {
				db.execSQL("DROP TABLE IF EXISTS habit;");
				onCreate(db);
				Message.message(context1, "onUpgrade()");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Message.message(context1, "U" + e);
			}
		}
		
		
	
	
}

