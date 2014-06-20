package hr.frenesius.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbDatabaseCreate {

long a;

DbHelper ourHelper;
final Context ourContext;
SQLiteDatabase ourDatabase;
	       
	       
	              
	public DbDatabaseCreate(Context c){
		ourContext = c;
	}
	       
	public DbDatabaseCreate open(){
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		Toast.makeText(ourContext, "open()", 1).show();
		return this;
	}
	public void close(){
		ourHelper.close();
		Toast.makeText(ourContext, "Close()", 1).show();
	}
	 
	public long createEntryGoodHabit(String title, String description, int reward) {
	
		ContentValues cv = new ContentValues();
	    cv.put(DbHelper.KEY_TITLE, title);
	    cv.put(DbHelper.KEY_DESCRIPTION, description);
	    cv.put(DbHelper.KEY_REWARD, reward);
	    return ourDatabase.insert(DbHelper.GOODHABIT_TABLE, null, cv);
	    
	}	       
}



