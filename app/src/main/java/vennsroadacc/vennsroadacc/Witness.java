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

public class Witness extends Activity {


    //For Vehicle, Other_Vehicle,Collision and Witness
    public static ArrayList<String > Other_VehicleArray = new ArrayList<String>();
    public final static String Other_VehicleData = "";
    public static String pKey="";
    public static int intPkey=0;


    //declaring variable
    EditText txtFullName,txtWorkTel,txtHomeTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_witness);

        Intent intent = getIntent();
        pKey=intent.getStringExtra(Collision.pKey);
        intPkey=new Integer(Integer.parseInt(intent.getStringExtra(Collision.pKey)));

        final DBHelper newDb=new DBHelper(this);
        Other_VehicleArray =new ArrayList<String>(intent.getStringArrayListExtra(Collision.Other_VehicleData));

        txtFullName=(EditText)findViewById(R.id.txtFullName_Witness);
        txtWorkTel=(EditText)findViewById(R.id.txtWorkTelNo_Witness);
        txtHomeTel=(EditText)findViewById(R.id.txtHomeTelNo_Witness);

        Cursor c=newDb.forYourVehicle("Witness_Details",pKey,Other_VehicleArray,"record");

        while (c.moveToNext())
        {
            txtFullName.setText(c.getString(0));
            txtWorkTel.setText(c.getString(1));
            txtHomeTel.setText(c.getString(2));
        }
    }

    public void ToInjured(View view) {

        Intent intent = new Intent(this, Injured.class);
        final DBHelper newDb = new DBHelper(this);

        txtFullName=(EditText)findViewById(R.id.txtFullName_Witness);
        txtWorkTel=(EditText)findViewById(R.id.txtWorkTelNo_Witness);
        txtHomeTel=(EditText)findViewById(R.id.txtHomeTelNo_Witness);

        //Adding data for Witness
        Other_VehicleArray.add(26, txtFullName.getText().toString());
        Other_VehicleArray.add(27, txtWorkTel.getText().toString());
        Other_VehicleArray.add(28, txtHomeTel.getText().toString());

        if (intPkey > 0)
        {
            Boolean updateResult = newDb.updateWitness(intPkey, Other_VehicleArray);

            if (updateResult == true)
            {
                Toast.makeText(getApplicationContext(), "Saved " + "", Toast.LENGTH_SHORT).show();
                intent.putStringArrayListExtra(Other_VehicleData, Other_VehicleArray);
                intent.putExtra(pKey, pKey);
                startActivity(intent);
            }
            else
            {

                Toast.makeText(getApplicationContext(), "Update Failed" + "", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            intent.putStringArrayListExtra(Other_VehicleData, Other_VehicleArray);
            intent.putExtra(pKey, pKey);
            startActivity(intent);
        }
    }
}
