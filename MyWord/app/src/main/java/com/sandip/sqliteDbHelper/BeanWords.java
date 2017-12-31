package com.sandip.sqliteDbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.sandip.model.ModelWords;

import java.util.ArrayList;

public class BeanWords {

    String TABLE_NAME="Chapter";
    String TAG="BeanRates";
    Cursor cursor;

    public synchronized boolean checkrecord(){

        boolean flagResult=false;
        SQLiteDatabase sqlObj		= null;
        String query		= "";

        try
        {
            Log.i(TAG, "Search For " + "Mode Search");

            query	+=	"   select *	"+
                    "	from 	"+TABLE_NAME;


            System.out.println(query + "......query..");
            sqlObj	=	DBManager.getConnection(SQLiteDatabase.OPEN_READONLY);

            if(sqlObj != null) {
                cursor = sqlObj.rawQuery(query, null);
                if(cursor.getCount()>0) {
                    flagResult= true;
                }
                else {
                    flagResult= false;

                }
            }
        }
        catch(Exception e)
        {
            flagResult= false;
        }
        finally
        {
            if(cursor != null)
            {
                cursor.close();
            }
            if(sqlObj != null)
            {
                sqlObj.close();
            }
        }

     return flagResult;
    }

    public synchronized boolean deleteRecord(Context context){

        boolean flagResult=false;
        SQLiteDatabase sqlObj		= null;
        String query		= "";

        try
        {
            Log.i(TAG, "Search For " + "Mode Search");

            query	+=	"   delete	"+
                    "	from 	"+TABLE_NAME;


            System.out.println(query + "......query..");
            DBManager manager=DBManager.getInstance(context);
            sqlObj	=	manager.getConnection(SQLiteDatabase.OPEN_READWRITE);

            if(sqlObj != null) {
              //  cursor = sqlObj.rawQuery(query, null);
                sqlObj.delete(TABLE_NAME,null,null);
                flagResult=true;

            }
        }
        catch(Exception e)
        {
            flagResult= false;
        }
        finally
        {
            if(cursor != null)
            {
                cursor.close();
            }
            if(sqlObj != null)
            {
                sqlObj.close();
            }
        }

        return flagResult;
    }

 public ArrayList<ModelWords> getWords(Context context, String tableName)
    {
        ArrayList<ModelWords> rateList	= new ArrayList<ModelWords>();
        SQLiteDatabase 		 sqlObj		= null;
        String				 query		= "";

        try
        {
            Log.i(TAG, "Search For " + "Mode Search");

            query	+=	"   select *	"+
                    "	from 	"+tableName;


            System.out.println(query + "......query..");
            DBManager manager=DBManager.getInstance(context);
            sqlObj	=	manager.getConnection(SQLiteDatabase.OPEN_READONLY);

            if(sqlObj != null)
            {
                cursor	=	sqlObj.rawQuery(query, null);
                while(cursor.moveToNext())
                {
                    ModelWords modelRating =	new ModelWords();
                    modelRating.setWordsNo(cursor.getString(0));
                    modelRating.setWord(cursor.getString(1));
                    modelRating.setWordsType(cursor.getString(2));
                    modelRating.setWordsEngMeaning(cursor.getString(3));
                    modelRating.setWordsMarathiMeaning(cursor.getString(4));
                    modelRating.setWordUse(cursor.getString(5));
                    rateList.add(modelRating);
                }
            }
            else
            {
                return rateList;
            }
        }
        catch(Exception e)
        {
            rateList	=	new ArrayList<ModelWords>();
        }
        finally
        {
            if(cursor != null)
            {
                cursor.close();
            }
            if(sqlObj != null)
            {
                sqlObj.close();
            }
        }
        return rateList;
    }
}
