package vennsroadacc.vennsroadacc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //Logcat
    private static final String LOG = "DBHelper";

    //Database Name
    public static final String DATABASE_NAME = "VennsRoadAcc.db";

    //Database Version
    public static final int DATABASE_VERSION = 3;


    SQLiteDatabase db;
    DBHelper helper;

    //Table Your Vehicle
    public static final String TABLE_NAME = "Your_Vehicle";
    public static final String Reg_Number = "Registration_No";
    public static final String Make_Model = "make_Model";
    public static final String Name_Owner = "Name_of_Owner";
    public static final String Add_Owner = "Address_of_Owner";
    public static final String Name_Driver = "Name_of_Driver";
    public static final String Add_Driver = "Address_of_Driver";
    public static final String Tel_Owner = "Tel_of_Owner";
    public static final String Tel_Driver = "Tel_of_Driver";
    public static final String Vehicle_Insured = "vehicle_Insured";
    public static final String ID_YV = "Accident_Number";

    //Table Other Vehicle
    public static final String TABLE_NAMEOV = "Other_Vehicle";
    public static final String Name_DriverOV = "Name_Driver";
    public static final String ID_No = "ID_Number";
    public static final String Res_Address = "Residential_Address";
    public static final String Tel_NoWOV = "Tel_NoW";
    public static final String Tel_NoHOV = "Tel_NoH";
    public static final String Reg_NoOV = "Registration_No";
    public static final String Make_Model1 = "Make_Model1";
    public static final String Owner_Vehicle = "Owner_of_Vehicle";
    public static final String Licence_No = "Licence_No";
    public static final String Licence_Expiry = "Licence_Expiry";
    public static final String Code = "Code";
    public static final String ID_OV = "Accident_Number";

    //Table Collision Details
    public static final String TABLE_ColDet = "Collision_Details";
    public static final String Date = "Date";
    public static final String Time = "Time";
    public static final String Place = "Place";
    public static final String Weather = "Weather";
    public static final String R_Surface = "Road_Surface";
    public static final String N_Events = "Narration_Events";
    public static final String ID_CD = "Accident_Number";

    //Table Witness Details
    public static final String TABLE_WitDet = "Witness_Details";
    public static final String FName = "Full_Name";
    public static final String TelNoW = "Tel_Number_Work";
    public static final String TelNoH = "Tel_Number_Home";
    public static final String ID_WD = "Accident_Number";

    //Table Injured Persons Details
    public static final String TABLE_Injured_Persons = "Injured_Persons_Details";
    public static final String F_Name1 = "Full_Name1";
    public static final String Tel_NoW1 = "Tel_NoW1";
    public static final String Tel_NoH1 = "Tel_NoH1";
    public static final String F_Name2 = "Full_Name2";
    public static final String Tel_NoW2 = "Tel_NoW2";
    public static final String Tel_NoH2 = "Tel_NoH2";
    public static final String Injuries = "Nature_of_injuries";
    public static final String ID_IP = "Accident_Number";

    //Police Collision Report Details
    public static final String TABLE_Pol_Report = "Police_Report_Details";
    public static final String Pol_Date = "Pol_Date";
    public static final String Pol_Station = "Police_Station";
    public static final String Acc_Report_No = "Accident_Report_Number";
    public static final String id_P = "Accident_Number";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(Accident_Number INTEGER PRIMARY KEY AUTOINCREMENT, Registration_No TEXT, make_Model TEXT, Name_of_Owner TEXT, " +
            "Address_of_Owner TEXT, Name_of_Driver TEXT, Address_of_Driver TEXT, Tel_of_Owner TEXT, Tel_of_Driver TEXT, vehicle_Insured TEXT);";

    public static final String CREATE_TABLE1 = "CREATE TABLE "+ TABLE_NAMEOV + "(Accident_Number INTEGER PRIMARY KEY AUTOINCREMENT, Name_Driver TEXT, ID_Number TEXT, Residential_Address TEXT, Tel_NoW TEXT, Tel_NoH TEXT, " +
            "Registration_No TEXT, Make_Model1 TEXT, Owner_of_Vehicle TEXT, Licence_No TEXT, Licence_Expiry TEXT, Code TEXT);";

    public static final String CREATE_TABLE2 = "CREATE TABLE " + TABLE_ColDet + "(Accident_Number INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Time TEXT, Place TEXT, Weather TEXT, Road_Surface TEXT, Narration_Events TEXT);";

    public static final String CREATE_TABLE3 = "CREATE TABLE " + TABLE_WitDet + "(Accident_Number INTEGER PRIMARY KEY AUTOINCREMENT, Full_Name TEXT, Tel_Number_Work TEXT, Tel_Number_Home TEXT);";

    public static final String CREATE_TABLE4 = "CREATE TABLE " + TABLE_Injured_Persons + "(Accident_Number INTEGER PRIMARY KEY AUTOINCREMENT, Full_Name1 TEXT, Tel_NoW1 TEXT, Tel_NoH1 TEXT, Full_Name2 TEXT, Tel_NoW2 TEXT, " +
            "Tel_NoH2 TEXT, Nature_of_Injuries TEXT);";

    public static final String CREATE_TABLE5 = "CREATE TABLE " + TABLE_Pol_Report + "(Accident_Number INTEGER PRIMARY KEY AUTOINCREMENT, Pol_Date TEXT, Police_Station TEXT, Accident_Report_Number TEXT);";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE1);
        sqLiteDatabase.execSQL(CREATE_TABLE2);
        sqLiteDatabase.execSQL(CREATE_TABLE3);
        sqLiteDatabase.execSQL(CREATE_TABLE4);
        sqLiteDatabase.execSQL(CREATE_TABLE5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMEOV);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ColDet);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WitDet);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Injured_Persons);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_Pol_Report);
        onCreate(sqLiteDatabase);
    }

    public boolean SaveVehicle(String registration_no, String make_Model, String Name_of_Owner, String Address_of_Owner, String Name_of_Driver, String Address_of_Driver, String Tel_of_Owner, String Tel_of_Driver, String vehicle_Insured)
    {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Reg_Number,registration_no);
        contentValues.put(Make_Model,make_Model);
        contentValues.put(Name_Owner,Name_of_Owner);
        contentValues.put(Add_Owner,Address_of_Owner);
        contentValues.put(Name_Driver,Name_of_Driver);
        contentValues.put(Add_Driver,Address_of_Driver);
        contentValues.put(Tel_Owner,Tel_of_Owner);
        contentValues.put(Tel_Driver,Tel_of_Driver);
        contentValues.put(Vehicle_Insured,Vehicle_Insured);
        long result = sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
        if (result > 0 )
            return true;
        else
            return false;
    }

    public boolean SaveOVehicle (String name_driver, String id_Number, String res_Address, String Tel_NoW, String Tel_NoH, String Registration_No, String make_model1, String owner_vehicle1, String licence_no, String licence_expiry, String code )
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Name_DriverOV, name_driver);
        contentValues.put(ID_No, id_Number);
        contentValues.put(Res_Address, res_Address);
        contentValues.put(Tel_NoWOV, Tel_NoW);
        contentValues.put(Tel_NoHOV, Tel_NoH);
        contentValues.put(Reg_NoOV, Registration_No);
        contentValues.put(Make_Model1, make_model1);
        contentValues.put(Owner_Vehicle, owner_vehicle1);
        contentValues.put(Licence_No, licence_no);
        contentValues.put(Licence_Expiry, licence_expiry);
        contentValues.put(Code, code);
        long result = sqLiteDatabase.insert(TABLE_NAMEOV,null,contentValues);
        if (result >0)
            return true;
        else
            return false;
    }

    public boolean SaveColDetails (String date, String time, String place, String weather, String road_surface, String n_events)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Date, date);
        contentValues.put(Time, time);
        contentValues.put(Place, place);
        contentValues.put(Weather, weather);
        contentValues.put(R_Surface, road_surface);
        contentValues.put(N_Events, n_events);
        long result = sqLiteDatabase.insert(TABLE_ColDet,null,contentValues);
        if (result >0)
            return true;
        else
            return false;
    }

    public boolean SaveWitDet (String F_Name, String Tel_NoW, String Tel_NoH)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FName, F_Name);
        contentValues.put(TelNoW,Tel_NoW );
        contentValues.put(TelNoH, Tel_NoH);
        long result = sqLiteDatabase.insert(TABLE_WitDet,null,contentValues);
        if (result >0)
            return true;
        else
            return false;
    }

    public  boolean SaveInjured (String full_name1, String tel_now1, String tel_noh1, String full_name2, String tel_now2, String tel_noh2, String injured)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(F_Name1, full_name1);
        contentValues.put(Tel_NoW1, tel_now1);
        contentValues.put(Tel_NoH1, tel_noh1);
        contentValues.put(F_Name2, full_name2);
        contentValues.put(Tel_NoW2, tel_now2);
        contentValues.put(Tel_NoH2, tel_noh2);
        contentValues.put(Injuries, injured);
        long result = sqLiteDatabase.insert(TABLE_Injured_Persons,null,contentValues);
        if (result >0)
            return true;
        else
            return false;
    }

    public boolean SavePolReport (String pol_Date, String pol_station, String accident_report_number)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Pol_Date, pol_Date);
        contentValues.put(Pol_Station, pol_station);
        contentValues.put(Acc_Report_No, accident_report_number);
        long result = sqLiteDatabase.insert(TABLE_Pol_Report, null, contentValues);
        if (result >0)
            return true;
        else
            return false;
    }

    public Cursor getYVAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return res;
    }

    public Cursor getOVAllData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor OVres = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAMEOV, null);
        return OVres;
    }



    public Cursor getCDAllData ()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor CDres = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_ColDet,null);
        return CDres;
    }

    public Cursor getWDAllData ()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor WDres = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_WitDet,null);
        return WDres;
    }

    public Cursor getIPAllData ()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor IPres = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_Injured_Persons,null);
        return IPres;
    }

    public Cursor getPRDAllData ()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor PRDres = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_Pol_Report,null);
        return PRDres;
    }

    public boolean UpdateData (String accident_number, String pol_date, String pol_stations, String accident_report_numbers)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(id_P, accident_number);
        contentValues.put(Pol_Date, pol_date);
        contentValues.put(Pol_Station, pol_stations);
        contentValues.put(Acc_Report_No, accident_report_numbers);
        sqLiteDatabase.update(TABLE_Pol_Report,contentValues,"ID = ?", new String[] {accident_number} );
        return true;
    }

    //OPEN THE DB
    public DBHelper openDB()
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


    // Close Connection
    public DBHelper closeDB()
    {
        helper.close();
        return this;
    }

    public boolean updateVehicle(Integer pkey,ArrayList mainList)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try
        {
            sqLiteDatabase.execSQL("UPDATE Your_Vehicle SET Registration_No='"
                    +mainList.get(0)
                    +"',make_Model ='"+mainList.get(1)
                    +"',Name_Of_Owner ='"+mainList.get(2)
                    +"',Address_of_Owner ='"+mainList.get(3)
                    +"',Name_of_Driver ='"+mainList.get(4)
                    +"',Address_of_Driver ='"+mainList.get(5)
                    +"',Tel_of_Owner ='"+mainList.get(6)
                    +"',Tel_of_Driver ='"+mainList.get(7)
                    +"',vehicle_Insured ='"+mainList.get(8)
                    +"' WHERE Accident_Number="+pkey);

            return true;
        }catch (Exception e)
        {
            return  false;
        }
    }

    public boolean updateOtherVehicle(Integer pkey,ArrayList mainList)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try
        {
            sqLiteDatabase.execSQL("UPDATE Other_Vehicle SET Name_Driver='"
                    +mainList.get(9)
                    +"',ID_Number ='"+mainList.get(10)
                    +"',Residential_Address ='"+mainList.get(11)
                    +"',Tel_NoW ='"+mainList.get(12)
                    +"',Tel_NoH ='"+mainList.get(13)
                    +"',Registration_No ='"+mainList.get(14)
                    +"',Make_Model1 ='"+mainList.get(15)
                    +"',Owner_of_Vehicle ='"+mainList.get(16)
                    +"',Licence_No ='"+mainList.get(17)
                    +"',Licence_Expiry ='"+mainList.get(18)
                    +"',Code ='"+mainList.get(19)
                    +"' WHERE Accident_Number="+pkey);

            return true;
        }catch (Exception e)
        {
            return  false;
        }
    }

    public boolean updateCollision(Integer pkey,ArrayList mainList)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try
        {
            sqLiteDatabase.execSQL("UPDATE Collision_Details SET Date='"
                    +mainList.get(20)
                    +"',Time ='"+mainList.get(21)
                    +"',Place ='"+mainList.get(22)
                    +"',Weather ='"+mainList.get(23)
                    +"',Road_Surface ='"+mainList.get(24)
                    +"',Narration_Events ='"+mainList.get(25)
                    +"' WHERE Accident_Number="+pkey);

            return true;
        }catch (Exception e)
        {
            return  false;
        }
    }

    public boolean updateWitness(Integer pkey,ArrayList mainList)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try
        {
            sqLiteDatabase.execSQL("UPDATE Witness_Details SET Full_Name='"
                    +mainList.get(26)
                    +"',Tel_Number_Work ='"+mainList.get(27)
                    +"',Tel_Number_Home ='"+mainList.get(28)
                    +"' WHERE Accident_Number="+pkey);

            return true;
        }catch (Exception e)
        {
            return  false;
        }
    }

    public boolean updateInjured(Integer pkey,ArrayList mainList)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try
        {
            sqLiteDatabase.execSQL("UPDATE Injured_Persons_Details SET Full_Name1='"
                    +mainList.get(29)
                    +"',Tel_NoW1 ='"+mainList.get(30)
                    +"',Tel_NoH1 ='"+mainList.get(31)
                    +"',Full_Name2 ='"+mainList.get(32)
                    +"',Tel_NoW2 ='"+mainList.get(33)
                    +"',Tel_NoH2 ='"+mainList.get(34)
                    +"',Nature_of_injuries ='"+mainList.get(35)
                    +"' WHERE Accident_Number="+pkey);

            return true;
        }catch (Exception e)
        {
            return  false;
        }
    }

    public boolean updatePolice(Integer pkey,ArrayList mainList)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try
        {
            sqLiteDatabase.execSQL("UPDATE Police_Report_Details SET Pol_Date='"
                    +mainList.get(36)
                    +"',Police_Station ='"+mainList.get(37)
                    +"',Accident_Report_Number ='"+mainList.get(38)
                    +"' WHERE Accident_Number="+pkey);

            return true;
        }catch (Exception e)
        {
            return  false;
        }
    }

    public boolean deleteRecord(Integer pkey)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try
        {
            sqLiteDatabase.execSQL("Delete From Your_Vehicle WHERE Accident_Number="+pkey);

            return true;
        }
        catch (Exception e)
        {
            return  false;
        }
    }















    public Cursor forYourVehicle(String tableName,String pkey,ArrayList mainList,String action)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String[]yourVehicleColumns={"Registration_No","make_Model","Name_of_Owner","Address_of_Owner","Name_of_Driver","Address_of_Driver","Tel_of_Owner","Tel_of_Driver","vehicle_Insured"};
        String[]otherVehicleColumns={"Name_Driver","ID_Number","Residential_Address","Tel_NoW","Tel_NoH","Registration_No","Make_Model1","Owner_of_Vehicle","Licence_No","Licence_Expiry","Code"};
        String[]collisionColumns={"Date","Time","Place","Weather","Road_Surface","Narration_Events"};
        String[]witnessColumns={"Full_Name","Tel_Number_Work","Tel_Number_Home"};
        String[]injuredColumns={"Full_Name1","Tel_NoW1","Tel_NoH1","Full_Name2","Tel_NoW2","Tel_NoH2","Nature_of_Injuries"};
        String[]policeColumns={"Pol_Date","Police_Station","Accident_Report_Number"};

        Cursor OVres=null;

         if (tableName=="Your_Vehicle") {
             OVres = sqLiteDatabase.query(tableName,yourVehicleColumns,helper.ID_YV +" ="+pkey,null,null,null,null);
             return OVres;
         }
        else if (tableName=="Other_Vehicle") {
             OVres = sqLiteDatabase.query(tableName,otherVehicleColumns,helper.ID_YV +" ="+pkey,null,null,null,null);
             return OVres;
         }
        else if (tableName=="Collision_Details") {
             OVres = sqLiteDatabase.query(tableName,collisionColumns,helper.ID_YV +" ="+pkey,null,null,null,null);
             return OVres;
         }
        else if (tableName=="Witness_Details") {
             OVres = sqLiteDatabase.query(tableName,witnessColumns,helper.ID_YV +" ="+pkey,null,null,null,null);
             return OVres;
         }
        else if (tableName=="Injured_Persons_Details") {
             OVres = sqLiteDatabase.query(tableName,injuredColumns,helper.ID_YV +" ="+pkey,null,null,null,null);
             return OVres;
         }
        else if (tableName=="Police_Report_Details") {
             OVres = sqLiteDatabase.query(tableName,policeColumns,helper.ID_YV +" ="+pkey,null,null,null,null);
             return OVres;
         }
        else{

           return OVres;
         }

    }
}
