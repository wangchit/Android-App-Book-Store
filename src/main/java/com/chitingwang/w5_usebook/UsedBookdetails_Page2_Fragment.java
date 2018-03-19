package com.chitingwang.w5_usebook;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsedBookdetails_Page2_Fragment extends Fragment {

    public static String OPINION = "opinion";
    public static String TITLE = "title";
    public static String DESCRIP = "description";
    public static String PRICE = "price";
    public static String ARTHOR = "arthor";
    public static String IMAGE = "image";
    public static String LIKE = "check";

    public UsedBookdetails_Page2_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_used_book_page_2, container, false);
    }

    public void setBookInfo(String bookDetailTitle, String descripDetail, String arthor, String price, String like, int imageResource){

        TextView tvBookDetailTitle = (TextView) getActivity().findViewById(R.id.bookDetailTitle);
        TextView tvDescripDetail = (TextView) getActivity().findViewById(R.id.descripDetail);
        TextView tvArthor = (TextView) getActivity().findViewById(R.id.arthor);
        TextView tvPrice = (TextView) getActivity().findViewById(R.id.price);
        TextView tvLikeText = (TextView) getActivity().findViewById(R.id.likeText);
        ImageView ivImage = (ImageView) getActivity().findViewById(R.id.bookPage2Image);
        ivImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        tvBookDetailTitle.setText(bookDetailTitle);
        tvDescripDetail.setText(descripDetail);
        tvArthor.setText(arthor);
        tvPrice.setText(price);
        tvLikeText.setText(like);
        ivImage.setImageResource(imageResource);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //Below links the Intent in UsedBookshelf_Page1_Fragment.java with the Bundle.
        Intent intent = getActivity().getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            // Get the info out of the bundle
            String title = bundle.getString(TITLE);
            String description = bundle.getString(DESCRIP);
            String arthor = bundle.getString(ARTHOR);
            String price = bundle.getString(PRICE);
            String like = bundle.getString(LIKE);
            int icon_id = bundle.getInt(IMAGE, -1);

            setBookInfo(title, description, arthor, price, like, icon_id);
        }


        final CheckBox checkbox = (CheckBox)getActivity().findViewById(R.id.Page2Like);

        Button b = (Button) getActivity().findViewById(R.id.ReturnBtn);

        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean check = checkbox.isChecked();
                Intent data = new Intent();
                data.putExtra(OPINION, check);
                getActivity().setResult(Activity.RESULT_OK, data);
                getActivity().finish();
            }
        });
    }
}
