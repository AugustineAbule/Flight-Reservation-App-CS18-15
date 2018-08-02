package booking.application.com.flyapp;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {

    Button btn;
    ProgressDialog dialog;

private String loginurl="http://flyapp.freeoda.com/api/login.php";
private String Signupurl="http://flyapp.freeoda.com/api/signup.php";
private String getusername,getPassword="";
EditText username;
EditText password;
Button sign;
    String getinputusername="";
    String getinputpassword="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn=(Button)findViewById(R.id.login);
        dialog=new ProgressDialog(LoginActivity.this);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        sign=(Button)findViewById(R.id.btnsignup);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

AlertDialog alertDialog=new AlertDialog.Builder(LoginActivity.this).create();
View layout=getLayoutInflater().inflate(R.layout.sign_up,null);

alertDialog.setView(layout);

alertDialog.show();


Button signin=(Button)layout.findViewById(R.id.signin);
final EditText usernamed=(EditText)layout.findViewById(R.id.username);
final EditText passwordd=(EditText)layout.findViewById(R.id.password);

signin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

         getinputusername=usernamed.getText().toString();
         getinputpassword=passwordd.getText().toString();

        if(getinputpassword.equalsIgnoreCase("") || getinputusername.equalsIgnoreCase("")){


            Toast.makeText(LoginActivity.this,"Provide all necessary credentials",Toast.LENGTH_SHORT).show();

        }else{

            Log.v("DATA",getinputpassword+":"+getinputusername);

new Siginin().execute();



        }




    }
});








            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getusername=username.getText().toString();
                getPassword=password.getText().toString();

                if(getPassword.equalsIgnoreCase("") || getPassword.equalsIgnoreCase("")){

                    Toast.makeText(LoginActivity.this,"Please provide all details",Toast.LENGTH_SHORT).show();
                }else {

                    Log.v("ORIGINAL",getusername+":"+getPassword);

                   loginurl+="?username="+getusername;
                   loginurl+="&password="+getPassword;





                    new Login().execute();




                }




            }
        });
    }





    class Login extends AsyncTask<String,String,String>{


String outputdata="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.setMessage("Verifying...");
            dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                //model of Login Process with server
                //Thread.sleep(4000);

                //Log.v("DATA",getinputpassword+":"+getinputusername);
                HttpURLConnection con;
                String apiurl = loginurl;
                String username = getusername;
                String password = getPassword;
                String urlParameters = "username=" + username;
                urlParameters += "&password=" + password;
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
                        outputdata += line;
                    }
                    System.out.println("+++++++++++++"+outputdata);
                    con.disconnect();
                } catch (Exception e) {

                    e.printStackTrace();

                }

            }catch (Exception e){e.printStackTrace();}


               /* URL url=new URL(loginurl);

                BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
                String line="";

                while((line=reader.readLine())!=null){

                    outputdata+=line;

                }






            }catch (Exception e){e.printStackTrace();}

*/

            //Login Url here





            return outputdata;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();

            try{

                Log.v("DATA",s);

                JSONObject object=new JSONObject(s);

                String test=object.getString("response");

                Log.v("TEST",test);


                if(test.equalsIgnoreCase("SUCCESS")){

                    // Create object of SharedPreferences.
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    //now get Editor
                    SharedPreferences.Editor editor = sharedPref.edit();
                    //put your value
                    editor.putString("userName", object.getString("username"));

                    //commits your edits
                    editor.commit();

                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

                }else {

                    Toast.makeText(LoginActivity.this,object.getString("message"),Toast.LENGTH_SHORT).show();



                }


           //     startActivity(new Intent(LoginActivity.this,MainActivity.class));

            }catch (Exception e){e.printStackTrace();}



        }
    }


    public class Siginin extends AsyncTask<String,String,String>{


        private String data="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
dialog.setMessage("Creating Account...");
dialog.show();



        }

        @Override
        protected String doInBackground(String... strings) {

/*
*  Log.v("DATA",getinputpassword+":"+getinputusername);
*
* */
            HttpURLConnection con;
            String apiurl = Signupurl;
            String username = getinputusername;
            String password = getinputpassword;
            String urlParameters = "username=" + username;
            urlParameters += "&password=" + password;
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


                Log.v("SERVER",s);

                JSONObject jsonObject=new JSONObject(s);
                 String check=jsonObject.getString("response");
                 if(check.equalsIgnoreCase("SUCCESS")){

                     // Create object of SharedPreferences.
                     SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                     //now get Editor
                     SharedPreferences.Editor editor = sharedPref.edit();
                     //put your value
                     editor.putString("userName", jsonObject.getString("username"));

                     //commits your edits
                     editor.commit();


                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                 }else {

                     Toast.makeText(LoginActivity.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();


                 }



            }catch (Exception e){e.printStackTrace();}

        }
    }
}
