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

public class Injured extends Activity {

    //For Vehicle, Other_Vehicle,Collision, Witness and Injured

    public static ArrayList<String > Other_VehicleArray = new ArrayList<String>();
    public final static String Other_VehicleData = "";
    public static String pKey="";
    public static int intPkey=0;

    //declaring variable
    EditText txtFullName,txtWorkTel,txtHomeTel,txtFullName2,txtWorkTel2,txtHomeTel2,txtNatureOfInjuries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_injured);

        Intent intent = getIntent();
        pKey=intent.getStringExtra(Witness.pKey);
        intPkey=new Integer(Integer.parseInt(intent.getStringExtra(Witness.pKey)));

        Other_VehicleArray =new ArrayList<String>(intent.getStringArrayListExtra(Witness.Other_VehicleData));

        txtFullName=(EditText)findViewById(R.id.txtFullName1_Injured);
        txtWorkTel=(EditText)findViewById(R.id.txtWorkTelNo1_Injured);
        txtHomeTel=(EditText)findViewById(R.id.txtHomeTelNo1_Injured);

        txtFullName2=(EditText)findViewById(R.id.txtFullName2_Injured);
        txtWorkTel2=(EditText)findViewById(R.id.txtWorkTel2_Injured);
        txtHomeTel2=(EditText)findViewById(R.id.txtHomeTelNo2_Injured);

        txtNatureOfInjuries=(EditText)findViewById(R.id.txtNatureOfInjuries_Injured);

        final DBHelper newDb=new DBHelper(this);

       Cursor c=newDb.forYourVehicle("Injured_Persons_Details",pKey,Other_VehicleArray,"record");

        while (c.moveToNext())
        {
            txtFullName.setText(c.getString(0));
            txtWorkTel.setText(c.getString(1));
            txtHomeTel.setText(c.getString(2));
            txtFullName2.setText(c.getString(3));
            txtWorkTel2.setText(c.getString(4));
            txtHomeTel2.setText(c.getString(5));
            txtNatureOfInjuries.setText(c.getString(6));
        }
    }

    public void ToPolice(View view) {

        Intent intent = new Intent(this, Police.class);
        final DBHelper newDb = new DBHelper(this);

        txtFullName=(EditText)findViewById(R.id.txtFullName1_Injured);
        txtWorkTel=(EditText)findViewById(R.id.txtWorkTelNo1_Injured);
        txtHomeTel=(EditText)findViewById(R.id.txtHomeTelNo1_Injured);

        txtFullName2=(EditText)findViewById(R.id.txtFullName2_Injured);
        txtWorkTel2=(EditText)findViewById(R.id.txtWorkTel2_Injured);
        txtHomeTel2=(EditText)findViewById(R.id.txtHomeTelNo2_Injured);

        txtNatureOfInjuries=(EditText)findViewById(R.id.txtNatureOfInjuries_Injured);

        //Adding data for Injured
        Other_VehicleArray.add(29, txtFullName.getText().toString());
        Other_VehicleArray.add(30, txtWorkTel.getText().toString());
        Other_VehicleArray.add(31, txtHomeTel.getText().toString());
        Other_VehicleArray.add(32, txtFullName2.getText().toString());
        Other_VehicleArray.add(33, txtWorkTel2.getText().toString());
        Other_VehicleArray.add(34, txtHomeTel2.getText().toString());
        Other_VehicleArray.add(35,txtNatureOfInjuries.getText().toString());


        if (intPkey > 0)
        {
            Boolean updateResult = newDb.updateInjured(intPkey, Other_VehicleArray);
            if (updateResult == true) {

                Toast.makeText(getApplicationContext(), "Saved " + "", Toast.LENGTH_SHORT).show();
                intent.putStringArrayListExtra(Other_VehicleData,Other_VehicleArray);
                intent.putExtra(pKey,pKey);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Update Failed  " + "", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            intent.putStringArrayListExtra(Other_VehicleData,Other_VehicleArray);
            intent.putExtra(pKey,pKey);
            startActivity(intent);
        }



    }
}
