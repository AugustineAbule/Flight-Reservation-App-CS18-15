package booking.application.com.flyapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ShowFlightStatusActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ProgressDialog dialog;
    private String picdate="",picto="",picfrom="",flightnumber="";
    ArrayList<Flight> flights;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_flight_status);
         dialog=new ProgressDialog(ShowFlightStatusActivity.this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        flights=new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            picdate = extras.getString("picdate");
            flightnumber = extras.getString("flightnumber");




             Log.v("SEND",picdate+":"+flightnumber);

new GetStatus().execute();


        }




            //The key argument here must match that used in the other activity
        }



        public class GetStatus extends AsyncTask<String,String,String>{

        String data="";

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.setMessage("Please wait ....");
                dialog.show();
            }

            @Override
            protected String doInBackground(String... strings) {



                HttpURLConnection con;
                String apiurl = "http://flyapp.freeoda.com/api/trackFlight.php";;

                String urlParameters = "date=" + picdate;
                urlParameters += "&flightnumber=" + flightnumber;


                byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
                try {

                    URL myurl = new URL(apiurl);
                    con = (HttpURLConnection) myurl.openConnection();

                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    con.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.write(postData);

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        data += line;
                    }
                    System.out.println("+++++++++++++"+data);
                    con.disconnect();
                } catch (Exception e) {

                    e.printStackTrace();

                }






                return data;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();

                try{

                    Log.v("RESPONSE",s);




                    try{

                        JSONObject object=new JSONObject(s);
                        JSONObject object1=object.getJSONObject("FlightStatusResource");
                        JSONObject object2=object1.getJSONObject("Flights");
                        JSONObject object3=object2.getJSONObject("Flight");
                        JSONObject departure=object3.getJSONObject("Departure");
                        JSONObject departureTime=departure.getJSONObject("ScheduledTimeLocal");
                        JSONObject TimeStatus=departure.getJSONObject("TimeStatus");
                       // JSONObject Terminal=departure.getJSONObject("");






                        JSONObject arrival=object3.getJSONObject("Arrival");
                        JSONObject arrivalTime=arrival.getJSONObject("ScheduledTimeLocal");
                        JSONObject arrivalTimestatus=arrival.getJSONObject("TimeStatus");
                        JSONObject arrivalTerminal=arrival.getJSONObject("Terminal");


                        JSONObject arrivalMarketingCarrier=object3.getJSONObject("MarketingCarrier");
                       JSONObject arrivalOperatingCarrier=object3.getJSONObject("OperatingCarrier");
                       JSONObject arrivalEquipment=object3.getJSONObject("Equipment");

                        String str="";

                        str+="\n\nMarketing Carrier (AirlineID:)\t"+arrivalMarketingCarrier.getString("AirlineID");
                        str+="\n Marketing Carrier (FlightNumber) :\t"+arrivalMarketingCarrier.getString("FlightNumber");
                        str+="\n\nOperatingCarrier (AirlineID) :\t"+arrivalOperatingCarrier.getString("AirlineID");
                        str+="\n OperatingCarrier (FlightNumber:)\t"+arrivalOperatingCarrier.getString("FlightNumber");;
                        str+="\n Aircraft Code :\t"+arrivalEquipment.getString("AircraftCode");


                        flights.add(new Flight(departureTime.getString("DateTime"),departure.getString("AirportCode"),TimeStatus.getString("Definition"),"",arrivalTime.getString("DateTime"),arrival.getString("AirportCode"),arrivalTimestatus.getString("Definition"),arrivalTerminal.getString("Gate")+str));


                          //  flights.add(new Flight(ob22.getString("DateTime"),ob2.getString("AirportCode"),ob2222.getString("Definition"),ob222.getString("Gate"),ob33.getString("DateTime"),ob3.getString("AirportCode"),ob3333.getString("Definition"),ob333.getString("Gate")));


                    }catch (Exception e){e.printStackTrace();}


                   // flights.add(new Flight("10:20 AM","AC","Take on Arrival","A7","05:00 AM","BRU","Time on Arrival","A10"));


                    FlightAdapter adapter=new FlightAdapter(ShowFlightStatusActivity.this,flights);


                    LinearLayoutManager layoutManager=new LinearLayoutManager(ShowFlightStatusActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    RecyclerView.ItemDecoration itemDecoration = new
                            DividerItemDecoration(ShowFlightStatusActivity.this, DividerItemDecoration.VERTICAL);
                    recyclerView.addItemDecoration(itemDecoration);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setHasFixedSize(true);




                }catch (Exception e){e.printStackTrace();}

            }
        }


    }

