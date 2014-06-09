/**
 * Package to manage Data
 * 	-Database
 * 	-Shared pref
 * 	-etc
 */
/**
 * @author 0878685
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *###Snippets
 *

private static final String MYDATABASE = "Frenesius";
private static final int VERSION = 1;

public DatabaseConnect(Context connection) {
 super(connection, MYDATABASE, null, VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
 db.execSQL("CREATE TABLE mynames(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);");
}

@Override
public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
 db.execSQL("DROP TABLE IF EXIST mynames");
 onCreate(db);
}

 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */







package hr.frenesius.data;