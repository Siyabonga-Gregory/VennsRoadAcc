package vennsroadacc.vennsroadacc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Police extends Activity {


    //For Vehicle, Other_Vehicle,Collision, Witness and Injured

    public static ArrayList<String > Other_VehicleArray = new ArrayList<String>();
    public final static String Other_VehicleData = "";
    public static String pKey="";
    public static int intPkey=0;

    Button btnSave;
    //declaring variable
    EditText txtDate,txtPoliceStation,txtAccidentReportNumber;
    final DBHelper newDb=new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_police);


        btnSave = (Button) findViewById(R.id.btnSave_Police);

        Intent intent = getIntent();
        pKey=intent.getStringExtra(Injured.pKey);
        intPkey=new Integer(Integer.parseInt(intent.getStringExtra(Injured.pKey)));

        Other_VehicleArray =new ArrayList<String>(intent.getStringArrayListExtra(Injured.Other_VehicleData));

        txtDate=(EditText)findViewById(R.id.txtPoliceDate);
        txtPoliceStation=(EditText)findViewById(R.id.txtPoliceStation);
        txtAccidentReportNumber=(EditText)findViewById(R.id.txtAccidentReportNo_Police);


        if(pKey.toString()=="new".toString()) {

            Cursor c=newDb.forYourVehicle("Police_Report_Details",intent.getStringExtra(Injured.pKey),Other_VehicleArray,"record");

            while (c.moveToNext())
            {
                txtDate.setText(c.getString(0));
                txtPoliceStation.setText(c.getString(1));
                txtAccidentReportNumber.setText(c.getString(2));
            }

        }


        btnSave.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
               saveToDatabase();
            }
        });
    }


    public void saveToDatabase()
    {

        //Adding data for Police
        Other_VehicleArray.add(36, txtDate.getText().toString());
        Other_VehicleArray.add(37, txtPoliceStation.getText().toString());
        Other_VehicleArray.add(38, txtAccidentReportNumber.getText().toString());

        Intent intent = new Intent(this, Main.class);

        if (intPkey > 0)
        {
            Boolean updateResult = newDb.updateInjured(intPkey, Other_VehicleArray);
            if (updateResult == true)
            {
                Toast.makeText(getApplicationContext(),"Saved " + "", Toast.LENGTH_SHORT).show();
                intent.putStringArrayListExtra(Other_VehicleData,Other_VehicleArray);
                intent.putExtra(pKey,pKey);
                startActivity(intent);
             } else
            {
                Toast.makeText(getApplicationContext(), "Update Failed  " + "", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            boolean vehicleResult         = newDb.SaveVehicle(Other_VehicleArray.get(0).toString(), Other_VehicleArray.get(1).toString(), Other_VehicleArray.get(2).toString(), Other_VehicleArray.get(3).toString(), Other_VehicleArray.get(4).toString(), Other_VehicleArray.get(5).toString(), Other_VehicleArray.get(6).toString(), Other_VehicleArray.get(7).toString(), Other_VehicleArray.get(8).toString());
            boolean otherVehicleResult    = newDb.SaveOVehicle(Other_VehicleArray.get(9).toString(),Other_VehicleArray.get(10).toString(),Other_VehicleArray.get(11).toString(),Other_VehicleArray.get(12).toString(),Other_VehicleArray.get(13).toString(),Other_VehicleArray.get(14).toString(),Other_VehicleArray.get(15).toString(),Other_VehicleArray.get(16).toString(),Other_VehicleArray.get(17).toString(),Other_VehicleArray.get(18).toString(),Other_VehicleArray.get(19).toString());
            boolean collisionResult       = newDb.SaveColDetails(Other_VehicleArray.get(20).toString(),Other_VehicleArray.get(21).toString(),Other_VehicleArray.get(22).toString(),Other_VehicleArray.get(23).toString(),Other_VehicleArray.get(24).toString(),Other_VehicleArray.get(25).toString());
            boolean witnessResult         = newDb.SaveWitDet(Other_VehicleArray.get(26).toString(),Other_VehicleArray.get(27).toString(),Other_VehicleArray.get(28).toString());
            boolean injuredResult         = newDb.SaveInjured(Other_VehicleArray.get(29).toString(),Other_VehicleArray.get(30).toString(),Other_VehicleArray.get(31).toString(),Other_VehicleArray.get(32).toString(),Other_VehicleArray.get(33).toString(),Other_VehicleArray.get(34).toString(),Other_VehicleArray.get(35).toString());
            boolean policeResult          = newDb.SavePolReport(Other_VehicleArray.get(36).toString(),Other_VehicleArray.get(37).toString(),Other_VehicleArray.get(38).toString());

            if(vehicleResult==true && otherVehicleResult==true && collisionResult==true && witnessResult==true && injuredResult==true && policeResult==true)
            {
                Toast.makeText(getApplicationContext(),"Record Successfully Created", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Transaction Failed", Toast.LENGTH_SHORT).show();
            }
        }


    }


}
