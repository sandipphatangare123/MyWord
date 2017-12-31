package com.sandip.sqliteDbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sandip.model.ModelChapter;

import java.util.ArrayList;

public class BeanChapter {

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

 public ArrayList<ModelChapter> getChapterList(Context context)
    {
        ArrayList<ModelChapter> rateList	= new ArrayList<ModelChapter>();
        SQLiteDatabase 		 sqlObj		= null;
        String				 query		= "";

        try
        {
            Log.i(TAG, "Search For " + "Mode Search");

            query	+=	"   select *	"+
                    "	from 	"+TABLE_NAME;


            System.out.println(query + "......query..");
            DBManager manager=DBManager.getInstance(context);
            sqlObj	=	manager.getConnection(SQLiteDatabase.OPEN_READONLY);

            if(sqlObj != null)
            {
                cursor	=	sqlObj.rawQuery(query, null);
                while(cursor.moveToNext())
                {
                    ModelChapter modelRating =	new ModelChapter();
                    modelRating.setChapterID(cursor.getString(0));
                    modelRating.setChapterName(cursor.getString(1));
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
            rateList	=	new ArrayList<ModelChapter>();
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
