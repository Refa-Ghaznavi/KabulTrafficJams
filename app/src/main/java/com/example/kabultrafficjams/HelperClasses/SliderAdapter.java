package com.example.kabultrafficjams.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.kabultrafficjams.R;

// the slider adapter class is used to show the images or slider to our main screen
public class SliderAdapter extends PagerAdapter {

    Context context;
    // it is a global variable
    LayoutInflater layoutInflater;

    // contexts means on which activity
    // when we call the slider adapter constructor
    // and pass the context it means on that specific activity we wnat to use these things
    public SliderAdapter(Context context) {
        this.context = context;
    }

    // as we have the images headings and descriptions so we should create three arrays
    // we just added in here the images and etc
    int images[] = {
            R.drawable.sit_back_and_rekax,
            R.drawable.sit_back_and_rekax,
            R.drawable.sit_back_and_rekax,
            R.drawable.sit_back_and_rekax
    };

    int headings[] = {
            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,
            R.string.fourth_slide_title
    };

    int descriptions[] = {
            R.string.first_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc,
            R.string.fourth_slide_desc
    };


    // the get count methods is used that how many slides we use our project
    // we could use static to put 4 but i use heading bcz in each part we have headings
    @Override
    public int getCount() {
        return headings.length;
    }

    // setting the view
    // is view form object means is this view is used by object
    // which just return true or false according the situation
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        // constraint layout is basically the slider_layout which the three things are there images,headings and descriptions
        return view == (ConstraintLayout)object;
    }

    // changes of each items to each slide
    // in here we are instantiate that item and change that items for each slides
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        // basically we use the design from a system and then we decide in where it should be used
        // we use the services of design
        // do not forget the casting
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // then we point to that layout
        // in here we add the layout which we created the slides_layout
        // inside the view we have the design
        View view = layoutInflater.inflate(R.layout.slides_layout,container,false);


        // in here we should the set the images headings and descriptions
        // bcz we have these three object inside the view so we should use view
        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView heading = view.findViewById(R.id.slider_heading);
        TextView desc = view.findViewById(R.id.slider_desc);

        // in here we should set the three objects so images will get the position from instantiate item from position parameter
        imageView.setImageResource(images[position]);
        heading.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        // so this view will hold the new data of imageView headings and desc
        return view;
    }

    // so we should override the method by pres control + O the destroy item
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        // we use this container and remove view inside the view that we have created was the constraint layout
        // so in here we destroyed that items
        container.removeView((ConstraintLayout)object);

    }
}
