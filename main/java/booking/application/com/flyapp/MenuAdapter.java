package booking.application.com.flyapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by archie on 7/18/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyHolder> {

    private Context context;
    private ArrayList<Menu> list;

    public MenuAdapter(Context context, ArrayList<Menu> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_menu_item,null);

        MyHolder holder=new MyHolder(layout);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

holder.imageView.setImageResource(list.get(position).getImage());
holder.tv1.setText(list.get(position).getMenu());

holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        switch (position){

            case 0:
                context.startActivity(new Intent(context,ReservationsActivity.class));
                break;
            case 1:
                context.startActivity(new Intent(context,FlightsActivity.class));
                break;
            case 2:
                break;
            case 3:
                break;




        }

    }
});



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder{

        TextView tv1;
        ImageView imageView;
        RelativeLayout relativeLayout;
         public MyHolder(View itemView){
             super(itemView);

             tv1=(TextView)itemView.findViewById(R.id.item);
             imageView=(ImageView)itemView.findViewById(R.id.image);
             relativeLayout=(RelativeLayout)itemView.findViewById(R.id.box);


         }




    }

}
