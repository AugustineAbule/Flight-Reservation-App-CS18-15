package booking.application.com.flyapp;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Menu> menus;
    RecyclerView recyclerView;
    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        menus=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
dialog=new ProgressDialog(MainActivity.this);

        menus.add(new Menu("Make Reservation",R.drawable.reservations));
        menus.add(new Menu("Track Flights",R.drawable.flights));
        menus.add(new Menu("Weather Focus",R.drawable.weather));
        menus.add(new Menu("Additional Services",R.drawable.additionalservices));

        MenuAdapter adapter=new MenuAdapter(MainActivity.this,menus);


        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);



    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {


        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int pos=item.getItemId();
        switch (pos){
            case R.id.action_share:

                Toast.makeText(MainActivity.this,"Share",Toast.LENGTH_SHORT);
                break;
            case R.id.action_logout:

                new Logout().execute();

              //  Toast.makeText(MainActivity.this,"Logout",Toast.LENGTH_SHORT);

                break;




        }


        return true;


    }

    public class Logout extends AsyncTask<String,String,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog.setMessage("Signing out...");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {

                Thread.sleep(3000);
                //Do some other work here


            }catch (Exception e){e.printStackTrace();}



            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            dialog.dismiss();

            try{

                //
                ;
startActivity(new Intent(MainActivity.this,LoginActivity.class));

            }catch (Exception e){e.printStackTrace();}


        }
    }

}
