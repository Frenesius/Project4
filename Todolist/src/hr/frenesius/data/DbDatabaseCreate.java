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
	
		return this;
	}
	public void close(){
		ourHelper.close();
	
	}
	 
	public long createEntryGoodHabit(String title, String description, int reward) {
	
		ContentValues cv = new ContentValues();
	    cv.put(DbHelper.KEY_TITLE, title);
	    cv.put(DbHelper.KEY_DESCRIPTION, description);
	    cv.put(DbHelper.KEY_REWARD, reward);
	    return ourDatabase.insert(DbHelper.GOODHABIT_TABLE, null, cv);
	    
	}	 
	//AANPASSEN
	public long createEntryBadHabit(String title, String description, int reward) {
		
		ContentValues cv = new ContentValues();
	    cv.put(DbHelper.KEY_TITLE, title);
	    cv.put(DbHelper.KEY_DESCRIPTION, description);
	    cv.put(DbHelper.KEY_REWARD, reward);
	    return ourDatabase.insert(DbHelper.BADHABIT_TABLE, null, cv);
	    
	}
	public long createEntryReward(
			int picture_lock, int picture_lock_thumb, int picture_lock_thumb_onclick,
			int picture_unlock, int picture_unlock_thumb, int picture_unlock_thumb_onclick,
			int picture_select, int picture_select_thumb, int picture_select_thumb_onclick,
			String title1, String description1, int point1) {
		
		ContentValues cv = new ContentValues();
		cv.put(DbHelper.KEY_PICTURELOCK, picture_lock);
		cv.put(DbHelper.KEY_PICTURELOCK_THUMB, picture_lock_thumb);
		cv.put(DbHelper.KEY_PICTURELOCK_THUMB_ONCLICK, picture_lock_thumb_onclick);
		
		cv.put(DbHelper.KEY_PICTUREUNLOCK, picture_unlock);
		cv.put(DbHelper.KEY_PICTUREUNLOCK_THUMB, picture_unlock_thumb);
		cv.put(DbHelper.KEY_PICTUREUNLOCK_THUMB_ONCLICK, picture_unlock_thumb_onclick);
		
		cv.put(DbHelper.KEY_PICTURESELECT, picture_select);
		cv.put(DbHelper.KEY_PICTURESELECT_THUMB, picture_select_thumb);
		cv.put(DbHelper.KEY_PICTURESELECT_THUMB_ONCLICK, picture_select_thumb_onclick);
		
		cv.put(DbHelper.KEY_TITLE, title1);
	    cv.put(DbHelper.KEY_DESCRIPTION, description1);
	    cv.put(DbHelper.KEY_BOUGHT, 0);
	    cv.put(DbHelper.KEY_POINT, point1);
	    cv.put(DbHelper.KEY_SELECTREWARD, 0);
	    return ourDatabase.insert(DbHelper.REWARD_TABLE, null, cv);
	    
	}
	public long updateEntryReward(int bought1, int point1, String whereClause1) {
		String whereClause = whereClause1;
		ContentValues cv = new ContentValues();
			cv.put(DbHelper.KEY_BOUGHT, bought1);
			cv.put(DbHelper.KEY_POINT, point1);
	   
	    return ourDatabase.update(DbHelper.REWARD_TABLE, cv, whereClause, null);
	    
	}
	
}



