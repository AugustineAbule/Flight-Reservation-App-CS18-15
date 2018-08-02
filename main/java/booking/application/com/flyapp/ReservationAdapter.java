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

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyHolder>{


    private Context context;
    private ArrayList<Reservation> reservations;

    public ReservationAdapter(Context context, ArrayList<Reservation> reservations) {
        this.context = context;
        this.reservations = reservations;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_reservation_item,null);
        MyHolder holder=new MyHolder(layout);



        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        String placeholder="\nOrigin: \t"+reservations.get(position).getOrigin()+"\n";
        placeholder+="\nDestination: \t"+reservations.get(position).getDestination()+"\n";
        placeholder+="\nDeparture Date:\t"+reservations.get(position).getDepartureTime()+"\n";
        placeholder+="\nType: \t"+reservations.get(position).getType()+"\n";

        holder.holdreservation.setText(placeholder);



    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public  static class MyHolder extends RecyclerView.ViewHolder{


        TextView holdreservation;

        public MyHolder(View itemView){

            super(itemView);

            holdreservation=(TextView)itemView.findViewById(R.id.holdreservation);


        }





    }
}
