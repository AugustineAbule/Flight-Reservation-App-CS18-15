package booking.application.com.flyapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

public class FlightsActivity extends AppCompatActivity {

    private Button b;
    private ProgressDialog dialog;

   // MaterialSpinner from;
   // MaterialSpinner to;
    private EditText pickdate,flightnumber;
    Calendar myCalendar;
    private String picto="",picfrom="",picdate="";
    private String reservationurl="http://flyapp.freeoda.com/api/trackFlight.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);

        pickdate=(EditText)findViewById(R.id.datepick);
        flightnumber=(EditText)findViewById(R.id.flightnumber);
        myCalendar = Calendar.getInstance();
        dialog=new ProgressDialog(FlightsActivity.this);

        //new GetFlights().execute();
        //from = (MaterialSpinner) findViewById(R.id.from);


        //to = (MaterialSpinner) findViewById(R.id.to);



        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                int month = monthOfYear + 1;
                String formattedMonth = "" + month;
                String formattedDayOfMonth = "" + dayOfMonth;

                if(month < 10){

                    formattedMonth = "0" + month;
                }
                if(dayOfMonth < 10){

                    formattedDayOfMonth = "0" + dayOfMonth;
                }

                pickdate.setText(year+"-"+formattedMonth+"-"+formattedDayOfMonth);
                Log.v("Year",year+":"+formattedMonth+":"+formattedDayOfMonth);

                picdate=year+"-"+formattedMonth+"-"+formattedDayOfMonth;
            }

        };

        pickdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FlightsActivity.this,R.style.DialogTheme,date , myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });










        b=(Button)findViewById(R.id.btnreserve);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(picdate.equalsIgnoreCase("") ||  flightnumber.getText().toString().equalsIgnoreCase("") ){

                    Toast.makeText(FlightsActivity.this,"Insufficient Credentials provided",Toast.LENGTH_SHORT).show();

                }else{

                    Log.v("THE DATE",picdate);

                   // new  Savereservation().execute();

Intent intent=new Intent(FlightsActivity.this,ShowFlightStatusActivity.class);
intent.putExtra("picdate",picdate);

                    intent.putExtra("flightnumber",flightnumber.getText().toString());
                   // intent.putExtra("picfrom",picfrom);
                    startActivity(intent);



                }












            }
        });





    }




    public class GetFlights extends AsyncTask<String,String,String> {

        String output="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage("Fetching reservations");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {


            try{

                URL url=new URL("http://flyapp.freeoda.com/api/api.php");
                BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));

                String line="";

                while ((line=reader.readLine())!=null){

                    output+=line;

                }



            }catch (Exception e){e.printStackTrace();}


            return output;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            // String[] fromdata={"Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};
            //String[] todata={"Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lollipop", "Marshmallow"};

            //String[] fromdata={};
            //String[] todata={};

            ArrayList<String> fromdata=new ArrayList<>();
            ArrayList<String> todata=new ArrayList<>();



            try{

                //  Toast.makeText(ReservationsActivity.this,s,Toast.LENGTH_SHORT).show();


                Log.v("DATA",s);

                JSONObject object=new JSONObject(s);
                JSONObject object1=object.getJSONObject("AirportResource");
                JSONObject object2=object1.getJSONObject("Airports");
                Log.v("ARRAY",object2.toString());
                JSONArray jsonArray=object2.getJSONArray("Airport");
                JSONObject ob;
                int x=0,n=0;
                for(x=0;x<jsonArray.length();x++){
                    ob=jsonArray.getJSONObject(x);

                    fromdata.add(ob.getString("AirportCode"));
                    todata.add(ob.getString("AirportCode"));




                }





              //  from.setItems(fromdata);
               /*from.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                      //  Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();

                        Log.v("Item:" ,item);
                        picfrom=item;

                    }
                });

                to.setItems(todata);
                to.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        // Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
                        Log.v("Item:" ,item);

                        picto=item;

                    }
                });*/




            }catch (Exception e){e.printStackTrace();}
        }
    }




}
