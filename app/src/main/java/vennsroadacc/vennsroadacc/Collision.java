package vennsroadacc.vennsroadacc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Collision extends Activity {

    //For Vehicle and Other_Vehicle
    public static ArrayList<String > Other_VehicleArray = new ArrayList<String>();
    public final static String Other_VehicleData = "";
    public static String pKey="";
    public static int intPkey=0;

    //declaring variable
    EditText txtDate,txtTime,txtResidentAddress,txtWeather,txtRoadSurface,txtNarrationEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collision);

        Intent intent = getIntent();
        pKey=intent.getStringExtra(Other_Vehicle.pKey);
        intPkey=new Integer(Integer.parseInt(intent.getStringExtra(Other_Vehicle.pKey)));

        Other_VehicleArray =new ArrayList<String>(intent.getStringArrayListExtra(Other_Vehicle.Other_VehicleData));
        final DBHelper newDb=new DBHelper(this);

        txtDate=(EditText)findViewById(R.id.txtDate_Collision);
        txtTime=(EditText)findViewById(R.id.txtTime_Collision);
        txtResidentAddress=(EditText)findViewById(R.id.txtResident_Collision);
        txtWeather=(EditText)findViewById(R.id.txtWeather_Collision);
        txtRoadSurface=(EditText)findViewById(R.id.txtRoadSurface_Collision);
        txtNarrationEvents=(EditText)findViewById(R.id.txtNarrationEvent_Collision);

        Cursor c=newDb.forYourVehicle("Collision_Details",pKey,Other_VehicleArray,"record");

        while (c.moveToNext())
        {
            txtDate.setText(c.getString(0));
            txtTime.setText(c.getString(1));
            txtResidentAddress.setText(c.getString(2));
            txtWeather.setText(c.getString(3));
            txtRoadSurface.setText(c.getString(4));
            txtNarrationEvents.setText(c.getString(5));
        }


    }

    public void ToWitness(View view) {

        Intent intent = new Intent(this, Witness.class);
        final DBHelper newDb = new DBHelper(this);

        txtDate = (EditText) findViewById(R.id.txtDate_Collision);
        txtTime = (EditText) findViewById(R.id.txtTime_Collision);
        txtResidentAddress = (EditText) findViewById(R.id.txtResident_Collision);
        txtWeather = (EditText) findViewById(R.id.txtWeather_Collision);
        txtRoadSurface = (EditText) findViewById(R.id.txtRoadSurface_Collision);
        txtNarrationEvents = (EditText) findViewById(R.id.txtNarrationEvent_Collision);

        //Adding data for Collision
        Other_VehicleArray.add(20, txtDate.getText().toString());
        Other_VehicleArray.add(21, txtTime.getText().toString());
        Other_VehicleArray.add(22, txtResidentAddress.getText().toString());
        Other_VehicleArray.add(23, txtWeather.getText().toString());
        Other_VehicleArray.add(24, txtRoadSurface.getText().toString());
        Other_VehicleArray.add(25, txtNarrationEvents.getText().toString());

        if (intPkey > 0)
        {
            Boolean updateResult = newDb.updateCollision(intPkey, Other_VehicleArray);
            if (updateResult == true)
            {
                Toast.makeText(getApplicationContext(), "Saved " + "", Toast.LENGTH_SHORT).show();
                intent.putStringArrayListExtra(Other_VehicleData, Other_VehicleArray);
                intent.putExtra(pKey, pKey);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Update Failed  " + "", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            intent.putStringArrayListExtra(Other_VehicleData, Other_VehicleArray);
            intent.putExtra(pKey, pKey);
            startActivity(intent);
        }
    }
}

