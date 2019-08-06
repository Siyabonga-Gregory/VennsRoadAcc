package vennsroadacc.vennsroadacc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Picra on 10/12/2016.
 */
public class DBAdapter {

    //COLUMNS
    static final String ROWID="id";
    static final String NAME="name";
    static final String POSITION="position";

    //DB PROPERTIES
    static final String DBNAME="m_DB";
    static final String TBNAME="m_TB";
    static final int DBVERSION='1';

    static final String CREATE_TB="CREATE TABLE m_TB(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,position TEXT NOT NULL);";

    final Context c;
    SQLiteDatabase db;
    DBHelper helper;

    public DBAdapter(Context ctx)
    {
        this.c=ctx;
        helper=new DBHelper(c);
    }

    public static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVERSION);
        }

        public void onCreate(SQLiteDatabase db)
        {
            try{
                db.execSQL(CREATE_TB);

            }catch(SQLException e)
            {
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

            Log.w("DBAdapter","Upgrade DB");
            db.execSQL("DROP TABLE IF EXIST m_TB");
            onCreate(db);
        }

    }

    //OPEN THE DB

    public DBAdapter openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return this;
    }

    //close connection
    public DBAdapter close()
    {
        helper.close();
        return this;
    }


    //insert
    public long add(String name,String pos)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(NAME,name);
            cv.put(POSITION,pos);

            return db.insert(TBNAME,ROWID,cv);

        }catch(SQLException e)
        {
          e.printStackTrace();
        }

        return 0;
    }

    //get

    public Cursor getAllNames()
    {
        String[]column={ROWID,NAME,POSITION};

        return db.query(TBNAME,column,null,null,null,null,null);
    }


}
