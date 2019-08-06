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

public class Other_Vehicle extends Activity {

    //For Vehicle
    public  static ArrayList<String > VehicleArray = new ArrayList<String>();
    public final static String VehicleData = "";

    //For Other_Vehicle
    public final static ArrayList<String > Other_VehicleArray = new ArrayList<String>();
    public final static String Other_VehicleData = "";
    public static String pKey="";
    public static int intPkey=0;

    //declaring variable
    EditText txtDriver,txtIdNo,txtResidentAddress,txtWorkTel,txtHomeTel,txtRegistrationNo,txtMakeModel,txtOwner,txtLicenceNo,txtExpiryDate,txtCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other__vehicle);

        Intent intent = getIntent();

        pKey=intent.getStringExtra(Vehicle.pKey);
        intPkey=new Integer(Integer.parseInt(intent.getStringExtra(Vehicle.pKey)));

        VehicleArray =new ArrayList<String>(intent.getStringArrayListExtra(Vehicle.VehicleData));

        final DBHelper newDb=new DBHelper(this);

        txtDriver=(EditText)findViewById(R.id.txtDriverName_Other);
        txtIdNo=(EditText)findViewById(R.id.txtIdNumber_Other);
        txtResidentAddress=(EditText)findViewById(R.id.txtResidentAddress_Other);
        txtWorkTel=(EditText)findViewById(R.id.txtWorkTelNo_Other);
        txtHomeTel=(EditText)findViewById(R.id.txtHomeTelNo_Other);
        txtRegistrationNo=(EditText)findViewById(R.id.txtRegistrationNo_Other);
        txtMakeModel=(EditText)findViewById(R.id.txtMakeModel_Other);
        txtOwner=(EditText)findViewById(R.id.txtOwner_Other);
        txtLicenceNo=(EditText)findViewById(R.id.txtLicenceNo_Other);
        txtExpiryDate=(EditText)findViewById(R.id.txtLicenceExpiryDate_Other);
        txtCode=(EditText)findViewById(R.id.txtCode_Other);

        Cursor c=newDb.forYourVehicle("Other_Vehicle",pKey,Other_VehicleArray,"record");

        while (c.moveToNext())
        {
            txtDriver.setText(c.getString(0));
            txtIdNo.setText(c.getString(1));
            txtResidentAddress.setText(c.getString(2));
            txtWorkTel.setText(c.getString(3));
            txtHomeTel.setText(c.getString(4));
            txtRegistrationNo.setText(c.getString(5));
            txtMakeModel.setText(c.getString(6));
            txtOwner.setText(c.getString(7));
            txtLicenceNo.setText(c.getString(8));
            txtExpiryDate.setText(c.getString(9));
            txtCode.setText(c.getString(10));
        }

    }
    public void ToCollision(View view) {

        final DBHelper newDb=new DBHelper(this);
        Intent intent = new Intent(this, Collision.class);

        txtDriver=(EditText)findViewById(R.id.txtDriverName_Other);
        txtIdNo=(EditText)findViewById(R.id.txtIdNumber_Other);
        txtResidentAddress=(EditText)findViewById(R.id.txtResidentAddress_Other);
        txtWorkTel=(EditText)findViewById(R.id.txtWorkTelNo_Other);
        txtHomeTel=(EditText)findViewById(R.id.txtHomeTelNo_Other);
        txtRegistrationNo=(EditText)findViewById(R.id.txtRegistrationNo_Other);
        txtMakeModel=(EditText)findViewById(R.id.txtMakeModel_Other);
        txtOwner=(EditText)findViewById(R.id.txtOwner_Other);
        txtLicenceNo=(EditText)findViewById(R.id.txtLicenceNo_Other);
        txtExpiryDate=(EditText)findViewById(R.id.txtLicenceExpiryDate_Other);
        txtCode=(EditText)findViewById(R.id.txtCode_Other);

        //adding data from Vehicle
        Other_VehicleArray.add(0,VehicleArray.get(0).toString());
        Other_VehicleArray.add(1,VehicleArray.get(1).toString());
        Other_VehicleArray.add(2,VehicleArray.get(2).toString());
        Other_VehicleArray.add(3,VehicleArray.get(3).toString());
        Other_VehicleArray.add(4,VehicleArray.get(4).toString());
        Other_VehicleArray.add(5,VehicleArray.get(5).toString());
        Other_VehicleArray.add(6,VehicleArray.get(6).toString());
        Other_VehicleArray.add(7,VehicleArray.get(7).toString());
        Other_VehicleArray.add(8,VehicleArray.get(8).toString());

        //Adding data for other_Vehicle
        Other_VehicleArray.add(9,txtDriver.getText().toString());
        Other_VehicleArray.add(10,txtIdNo.getText().toString());
        Other_VehicleArray.add(11,txtResidentAddress.getText().toString());
        Other_VehicleArray.add(12,txtWorkTel.getText().toString());
        Other_VehicleArray.add(13,txtHomeTel.getText().toString());
        Other_VehicleArray.add(14,txtRegistrationNo.getText().toString());
        Other_VehicleArray.add(15,txtMakeModel.getText().toString());
        Other_VehicleArray.add(16,txtOwner.getText().toString());
        Other_VehicleArray.add(17,txtLicenceNo.getText().toString());
        Other_VehicleArray.add(18,txtExpiryDate.getText().toString());
        Other_VehicleArray.add(19,txtCode.getText().toString());

        if(intPkey >0)
        {
            Boolean updateResult=newDb.updateOtherVehicle(intPkey,Other_VehicleArray);
            if(updateResult==true)
            {
                Toast.makeText(getApplicationContext(),"Saved "+"", Toast.LENGTH_SHORT).show();
                intent.putStringArrayListExtra(VehicleData,Other_VehicleArray);
                intent.putExtra(pKey,pKey);
                startActivity(intent);
            }else
            {
                Toast.makeText(getApplicationContext(),"Update Failed  "+"", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            intent.putStringArrayListExtra(VehicleData,Other_VehicleArray);
            intent.putExtra(pKey,pKey);
            startActivity(intent);
        }

    }
}
