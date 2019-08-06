package vennsroadacc.vennsroadacc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Vehicle extends Activity {

    public final static ArrayList<String > VehicleArray = new ArrayList<String>();
    public final static String VehicleData = "";

    //public variable
    public final static String registrationNo = "";
    public final static String makeModel = "";
    public final static String ownerName = "";
    public final static String ownerAddress = "";
    public final static String driverName = "";
    public final static String driverAddress = "";
    public final static String ownerTel = "";
    public final static String driverTel = "";
    public final static String vehicleInsured = "";
    public static String pKey="";
    public static int intPkey=0;

    //declaring variable
    EditText txtRegistrationNo,txtMakeModel,txtOwnerName,txtOwnerAddress,txtDriverName,txtDriverAddress,txtOwnerTel,txtDriverTel,txtVehicleInsured;
    Button btnToOtherVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        final DBHelper newDb=new DBHelper(this);
        Intent intent = getIntent();

        intPkey=new Integer(Integer.parseInt(intent.getStringExtra(Main.pKey)));
        pKey=intent.getStringExtra(Main.pKey);

        //getting value from control onto variables
        txtRegistrationNo=(EditText) findViewById(R.id.txtRegistrationNo);
        txtMakeModel=(EditText) findViewById(R.id.txtMakeModel);
        txtOwnerName=(EditText) findViewById(R.id.txtOwnerName);
        txtOwnerAddress=(EditText) findViewById(R.id.txtOwnerAddress);
        txtDriverName=(EditText) findViewById(R.id.txtDriverName);
        txtDriverAddress=(EditText) findViewById(R.id.txtDriverAddress);
        txtOwnerTel=(EditText) findViewById(R.id.txtOwnerTel);
        txtDriverTel =(EditText) findViewById(R.id.txtDriverTel);
        txtVehicleInsured=(EditText) findViewById(R.id.txtVehicleInsured);

        Cursor c=newDb.forYourVehicle("Your_Vehicle",intent.getStringExtra(Main.pKey),VehicleArray,"record");

        while (c.moveToNext())
        {
            txtRegistrationNo.setText(c.getString(0));
            txtMakeModel.setText(c.getString(1));
            txtOwnerName.setText(c.getString(2));
            txtOwnerAddress.setText(c.getString(3));
            txtDriverName.setText(c.getString(4));
            txtDriverAddress.setText(c.getString(5));
            txtOwnerTel.setText(c.getString(6));
            txtDriverTel.setText(c.getString(7));
            txtVehicleInsured.setText(c.getString(8));
        }
    }

    public void ToOtherVehicle(View view) {
        final DBHelper newDb=new DBHelper(this);
        //getting value from control onto variables
        txtRegistrationNo=(EditText) findViewById(R.id.txtRegistrationNo);
        txtMakeModel=(EditText) findViewById(R.id.txtMakeModel);
        txtOwnerName=(EditText) findViewById(R.id.txtOwnerName);
        txtOwnerAddress=(EditText) findViewById(R.id.txtOwnerAddress);
        txtDriverName=(EditText) findViewById(R.id.txtDriverName);
        txtDriverAddress=(EditText) findViewById(R.id.txtDriverAddress);
        txtOwnerTel=(EditText) findViewById(R.id.txtOwnerTel);
        txtDriverTel =(EditText) findViewById(R.id.txtDriverTel);
        txtVehicleInsured=(EditText) findViewById(R.id.txtVehicleInsured);

        Intent intent = new Intent(this, Other_Vehicle.class);

        VehicleArray.add(0,txtRegistrationNo.getText().toString());
        VehicleArray.add(1,txtMakeModel.getText().toString());
        VehicleArray.add(2,txtOwnerName.getText().toString());
        VehicleArray.add(3,txtOwnerAddress.getText().toString());
        VehicleArray.add(4,txtDriverName.getText().toString());
        VehicleArray.add(5,txtDriverAddress.getText().toString());
        VehicleArray.add(6,txtOwnerTel.getText().toString());
        VehicleArray.add(7,txtDriverTel.getText().toString());
        VehicleArray.add(8,txtVehicleInsured.getText().toString());

        if(intPkey >0)
        {

            Boolean updateResult=newDb.updateVehicle(intPkey,VehicleArray);
            if(updateResult==true)
            {
                Toast.makeText(getApplicationContext(),"Saved"+"", Toast.LENGTH_SHORT).show();
                intent.putStringArrayListExtra(VehicleData,VehicleArray);
                intent.putExtra(pKey,pKey);
                startActivity(intent);
            }else
            {
                Toast.makeText(getApplicationContext(),"Update Failed  "+"", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            intent.putStringArrayListExtra(VehicleData,VehicleArray);
            intent.putExtra(pKey,pKey);
            startActivity(intent);
        }
    }

}
