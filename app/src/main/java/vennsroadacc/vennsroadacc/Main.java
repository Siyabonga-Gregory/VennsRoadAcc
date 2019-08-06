package vennsroadacc.vennsroadacc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main extends Activity {

    ListView txtMainList;
    Button btnBegin;
    ArrayList<String >accidents=new ArrayList<String>();
    ArrayList<String >pkeys=new ArrayList<String>();
    public static String pKey ="";
    public  int deleteKey=0;
   // EditText index;

    ArrayAdapter<String> Adapter;
    final DBHelper newDb=new DBHelper(this);
    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //index=(EditText)findViewById(R.id.txtRegistrationNo);

        btnBegin = (Button) findViewById(R.id.btnBegin);

        txtMainList = (ListView) findViewById(R.id.txtMainList);

        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, accidents);
        reloadListView();
        txtMainList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
                        public void onItemClick(AdapterView<?> arg0, View agr1, int index, long id) {
                        pKey="";
                        confirmMessage(agr1);
                        Toast.makeText(getApplicationContext(), accidents.get(index), Toast.LENGTH_LONG).show();
                            intent = new  Intent(Main.this,Vehicle.class);
                        intent.putExtra(pKey,pkeys.get(index));
                        deleteKey=new Integer(pkeys.get(index));
            }
        });
    }

    public  void reloadListView()
    {
        accidents.clear();

        Cursor c=newDb.getYVAllData();
        while (c.moveToNext())
        {
            accidents.add("Accident Number : "+c.getString(0));
            pkeys.add(c.getString(0));
        }

        txtMainList.setAdapter(Adapter);
        newDb.close();
    }


    public void ToVehicle(View view) {
         Intent intent = new Intent(this, Vehicle.class);
         intent.putExtra(pKey,"0");
         startActivity(intent);
    }

    public void confirmMessage(View view)
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Would you like to update this record or delete from database ");

        alertDialogBuilder.setPositiveButton("Edit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        startActivity(intent);
                    }
                });


        alertDialogBuilder.setNegativeButton("Delete",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        boolean result=newDb.deleteRecord(deleteKey);
                        if(result==true)
                        {
                            Toast.makeText(getApplicationContext(),"Record has been successfully deleted  "+deleteKey, Toast.LENGTH_SHORT).show();
                           reloadListView();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Process failed  "+"", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}
