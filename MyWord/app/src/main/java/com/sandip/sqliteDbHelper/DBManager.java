package com.sandip.sqliteDbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class DBManager extends SQLiteOpenHelper {
	
	private static 			String 		appPackage 				= "com.sandip.myword".trim();
	public static final 	String 		DB_PATH 				= "/data/data/"+appPackage+"/databases/";
	public static final 	String 		DB_NAME 				= "myword.sqlite";


	
	public 	static final 	int 		DB_VERSION 				= 10;	
	private static final	int 		MAX_ATTEMPTS 			= 5;
	private static final 	int 		DELAY_BETWEEN_ATTEMPTS 	= 2000;
	private 	   final    String 		TAG						= "DBManager";
	private 	   final	Context 	context;
	private static DBManager instance;
	
	private boolean createDatabase 	= false;
    private boolean upgradeDatabase = false;
    
	private DBManager(Context context) {
		
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
		initializeDataBase();
		//getWritableDatabase();
	}

	/**
     * Upgrade the database, in internal storage if it exists but is not current.
     * Create a new empty database in internal storage if it does not exist.
     */
    public void initializeDataBase() {
       
    	Log.i( TAG," initializing Database start ...");

    	getWritableDatabase();

        if (createDatabase) {
        	 Log.i( TAG," initializ .. creating database ...");
        	 
        	copyDataBase();
        	
        } else if (upgradeDatabase) {
        	 Log.i( TAG," initializ .. upgrading database ...");
        	copyDataBase();
        }
        Log.i( TAG," initializing Database end ...");
    }
    public  void resetDatabase() {
		String outFileName = DB_PATH + DB_NAME;

		File dbName=context.getDatabasePath(outFileName);
		context.deleteFile(dbName.toString());
    }

	private synchronized boolean copyDataBase(){

		try {
			Log.i( TAG," Start Copying database ...");
			// Open your local database as the input stream
			InputStream myInput = context.getAssets().open(DB_NAME);

			// Path to the just created empty database
			String outFileName = DB_PATH + DB_NAME;
			System.out.println( "COPYING DATABASE @ " + outFileName);

			// Open the empty database as the output stream
			OutputStream myOutput = new FileOutputStream(outFileName);
			
			//transfer bytes from the inputfile to the outputfile
	    	byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    	}

			
			// Close the streams
			myOutput.flush();
			myOutput.close();
			myInput.close();

			return true;

		} catch (FileNotFoundException e) {
			Log.i(TAG," Problem db file ont found in accets " + e.getMessage());
		} catch (IOException e) {
			Log.i( TAG," Problem reading db file " + e.getMessage());
		}finally
		{
			Log.i( TAG," Complete Copying database ...");
		}

		return false;

	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		createDatabase = true;
		//initializeDataBase();
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		upgradeDatabase = true;
		//initializeDataBase();
	}

	/**
	 * Provides the singleton instance of this class.
	 * 
	 * @return
	 */
	public static synchronized DBManager getInstance(Context context) {
		if (instance != null) {
			return instance;
		}
		else {
			instance=null;
			instance = new DBManager(context);
			return instance;
		}

	}

    
    public static boolean checkDataBase(String databasePath, String databaseName) {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(databasePath + databaseName,
                    null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
            Log.v("Debug", "Database exist");
            return true;
        } catch (SQLiteException e) {
            Log.v("Debug", "Database not exist");

        }
        return false;
    }    
    
	/**
	 * Method retrieves the SQLDatabase connection based on the mode being
	 * passed.Addresses the "database locked" related issue implemented in
	 * similar way as default Android Contact application.
	 * 
	 * @param mode
	 *            SQLiteDatabase.OPEN_READONLY & SQLiteDatabase.OPEN_READWRITE
	 * @return SQLiteDatabase object
	 */
	public static SQLiteDatabase getConnection(int mode) {

		String strDBPath = DB_PATH + DB_NAME;
		for (int i = 0; i < MAX_ATTEMPTS; i++) {
			try {
				if (mode == SQLiteDatabase.OPEN_READWRITE) {
					return SQLiteDatabase.openDatabase(strDBPath, null, SQLiteDatabase.OPEN_READWRITE);
				} else {
					return SQLiteDatabase.openDatabase(strDBPath, null, SQLiteDatabase.OPEN_READONLY);
				}

			} catch (SQLiteException e) {
				// We could get a "database locked" exception here, in which
				// case we should retry
				try {
					Thread.sleep(DELAY_BETWEEN_ATTEMPTS);
				} catch (InterruptedException ie) {
				}
			}
		}
		return null;
	}
}
