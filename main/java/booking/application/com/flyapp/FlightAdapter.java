package booking.application.com.flyapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by archie on 7/18/2018.
 */

public class FlightAdapter  extends RecyclerView.Adapter<FlightAdapter.MyHolder> {

    private Context context;
    private ArrayList<Flight> flights;


    public FlightAdapter(Context context, ArrayList<Flight> flights) {
        this.context = context;
        this.flights = flights;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_flight_item,null);
        MyHolder holder=new MyHolder(layout);




        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        String arrivalinfo="";
        String departureinfo="";

        departureinfo+=" Time :"+flights.get(position).getDepartTime();
        departureinfo+="\nAirport Code :"+flights.get(position).getDepartAirportCode();
        departureinfo+="\n Time Status :"+flights.get(position).getDepartTimeStatus();
       // departureinfo+="\nAirport Code :"+flights.get(position).getDepartAirportCode();
        holder.departureinfo.setText(departureinfo);


        arrivalinfo+="Time :"+flights.get(position).getArrivalTime();
        arrivalinfo+="\nAirport Code :"+flights.get(position).getArrivalAirportCode();
        arrivalinfo+="\n Time Status :"+flights.get(position).getArrivalTimeStatus();
        arrivalinfo+="\nTerminal: "+flights.get(position).getArraivalTerminalGate();

        holder.arrivalinfo.setText(arrivalinfo);





    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView arrivalinfo;
        TextView departureinfo;

        public MyHolder(View itemView){
            super(itemView);

            arrivalinfo=(TextView)itemView.findViewById(R.id.arrivalinfo);
            departureinfo=(TextView)itemView.findViewById(R.id.departureinfo);





        }





    }

}
