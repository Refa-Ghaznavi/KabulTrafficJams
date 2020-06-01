package com.example.kabultrafficjams.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kabultrafficjams.R;

import java.util.ArrayList;

// adapters hold the values and put that values to the design
// or it gets the design and values and work as a bridge between values and design
// and set the values or to the design or design to the values
// it is cleared we needs both values and design in the adapter
             /*  First  */                                 /*  Five */
                                                           // we using the adapter but adapter wil be the type featuredAdapter.FeaturedViewHolder
                                                          // so featuredAdapter will be the text
                                                                          // and FeaturedViewHolder will be the design
/*  Six*/
// then click the bulb icon to make its methods
public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    // so values will be an array list and this array list will be the type that we have created the helper class featured
           /*  Second */
    ArrayList<FeaturedHelperClass> featuredLocations;// -> it is just a variable

    // so just to get these things we are going to create its constructor
    // so when ever we call the FeaturedAdapter class from mainActivity we wil get the data
           /*  third   */
    // featured adapter hold the data
    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

/*  Seven    */
    @NonNull
    @Override
    // the ViewHolder as it is been written
    // that we have to pass the view which have the elements but these elements will get the value form which design
    // so our design is the Featured_card_design
    // so we have to mention somewhere in this adapter class if you want to use that design
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // so in here we have to pass the view to the parameter of FeaturedViewHolder which is the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        // so now we have to pass it to featureViewHolder
        // so basically we have just created the instance of the class
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        // so now we have to return it
        return featuredViewHolder;
        // so now we have created a simple view which is pointing to the design
        // and we have to passed the design to its featuredViewHolder
        // so now we have our code inside our ArrayList and FeaturedAdapter constructor
        // and in the FeaturedViewHolder we have the view
        // and we have set that into the variables of FeaturedViewHolder Class in the below line
    }

/*  Eight   */
    // so in here in the main fucntion we are going to bind or paste the design or the code
    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        // so in FeaturedHelperClass which we created and contains the all elements
        // so simply we have to call that
        // and we have assign those elements and values which contains image and title and description into this position each time
        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);

        // so then we have to set those elements
        // so the FeaturedViewHolder have the view Holder which contain that view
        // so it is an image so we have to set ImageResource
        // so that image resource will fetch or read from that Helper class
        // so then it automatically will display into the design
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());

    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    // so for the design we need to create the FeaturedViewedHolder
    // so basically it holds the view or the design

                          /*  Fourth  */
    // so it should be extended form ViewHolder of RecyclerView
    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        // so in design we have these three things
        ImageView image;
        TextView title, desc;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            // so in here we create hooks which is necessary for the design
            // so in here we do not have any resource layout and we do not know where is the file
            // so the file will be received inside this ItemView when we call this feature view Holder
            // so basically we will pass view to this
            // so currently we are just creating the hooks we are not setting or passing the view
            image = itemView.findViewById(R.id.feature_image);
            title = itemView.findViewById(R.id.feature_title);
            desc = itemView.findViewById(R.id.feature_desc);
        }
    }

}
