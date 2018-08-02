package booking.application.com.flyapp;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
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

public class ShowReservationActivity extends AppCompatActivity {

    ArrayList<Reservation> reservations;
    RecyclerView recyclerView;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reservation);
        dialog=new ProgressDialog(ShowReservationActivity.this);


        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);

        reservations=new ArrayList<>();

        new Pickreservation().execute();
    }



    public class Pickreservation extends AsyncTask<String,String,String>{

        String outdata="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Please wait ...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ShowReservationActivity.this);
            String userName = sharedPref.getString("userName", "Not Available");

            HttpURLConnection con;
            String apiurl = "http://flyapp.freeoda.com/api/getreservations.php";;
            String username = userName;
         //   String password = getPassword;
            String urlParameters = "username=" + username;
           // urlParameters += "&password=" + password;
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
                    outdata += line;
                }
                System.out.println("+++++++++++++"+outdata);
                con.disconnect();
            } catch (Exception e) {

                e.printStackTrace();

            }



            return outdata;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            try{

                Log.v("DATA",s);

                JSONArray jsonArray=new JSONArray(s);
                JSONObject object;
                for(int i=0;i<jsonArray.length();i++){
                    object=jsonArray.getJSONObject(i);

                    reservations.add(new Reservation(object.getString("origin"),object.getString("destination"),object.getString("date"),"Direct Flight"));

                }


               // reservations.add(new Reservation("EBB- Entebbe","LAX -Los Angles","9:30 AM","Direct Flight"));




                ReservationAdapter adapter=new ReservationAdapter(ShowReservationActivity.this,reservations);


                LinearLayoutManager layoutManager=new LinearLayoutManager(ShowReservationActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                RecyclerView.ItemDecoration itemDecoration = new
                        DividerItemDecoration(ShowReservationActivity.this, DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(itemDecoration);
                recyclerView.setAdapter(adapter);
                recyclerView.setHasFixedSize(true);



            }catch (Exception e){e.printStackTrace();}



        }
    }



}
