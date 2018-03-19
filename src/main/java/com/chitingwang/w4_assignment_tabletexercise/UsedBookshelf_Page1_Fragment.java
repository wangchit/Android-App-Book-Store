package com.chitingwang.w4_assignment_tabletexercise;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class UsedBookshelf_Page1_Fragment extends Fragment {

    public static final String TAG = "Hash_Fragment";
    static final int REQUEST_HASH_DETAILS = 1;
    TextView likeAws;
    TextView likeMicro;
    TextView likeKuber;
    //Below Strings are to indicate which status the checkbox is, in which book page the user is
    public static String WHICHBOOK = "UNKNOWN YET" ;
    public static String LIKEAWS = "OFF";
    public static String LIKEMICRO = "OFF";
    public static String LIKEKUBER = "OFF";
    public static String CHECK_TEXT_STATUS_AWS = "LIKEIT";
    public static String CHECK_TEXT_STATUS_MICRO = "LIKEIT";
    public static String CHECK_TEXT_STATUS_KUBER = "LIKEIT";
    public String unlikeText = "You've liked it. Want to unlike it?";
    //checkbox is not checked yet
    boolean check = false;

    public UsedBookshelf_Page1_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_used_book_page_1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //Each book has its own like.
        likeAws = (TextView)getActivity().findViewById(R.id.like_AWS);
        likeAws.setVisibility(View.INVISIBLE);
        likeMicro = (TextView)getActivity().findViewById(R.id.like_Micro);
        likeMicro.setVisibility(View.INVISIBLE);
        likeKuber = (TextView)getActivity().findViewById(R.id.like_Kuber);
        likeKuber.setVisibility(View.INVISIBLE);

        //Each Book has its own button.
        Button showAwsDetails = (Button) getActivity().findViewById(R.id.awsSeeDetailBtn);
        Button showMicroDetails = (Button) getActivity().findViewById(R.id.microSeeDetailBtn);
        Button showKuberDetails = (Button) getActivity().findViewById(R.id.kuberSeeDetailBtn);

        //Each Button lead to their own book detail info by calling it showDetails() method.
        //The class variable WHICHBOOK indicates which book details is shown.
        showAwsDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAwsDetails();
                WHICHBOOK = "AWS";
            }
        });
        showMicroDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMicroDetails();
                WHICHBOOK = "MICRO";
            }
        });
        showKuberDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKuberDetails();
                WHICHBOOK = "KUBER";
            }
        });
    }

    //The showDetails() methods for each book is listed below
    private void showAwsDetails() {
        Intent intent = new Intent(getActivity(), UsedBookdetails_Page2.class);
        intent.putExtra(UsedBookdetails_Page2_Fragment.TITLE, "AWS Certified Solution Architect" );
        intent.putExtra(UsedBookdetails_Page2_Fragment.DESCRIP, "This official study guide covers exam concepts, and provides key review on exam topics." );
        intent.putExtra(UsedBookdetails_Page2_Fragment.ARTHOR, "Joe Baron" );
        intent.putExtra(UsedBookdetails_Page2_Fragment.PRICE, "25 Euro" );
        if(LIKEAWS == "ON") { //Class variables LIKEAWS indicates that the user has checked and likes AWS book
            intent.putExtra(UsedBookdetails_Page2_Fragment.LIKE, unlikeText);//The text near the checkbox is changed to "You've liked it. Want to unlike it?"
            CHECK_TEXT_STATUS_AWS = "UNLIKE";//The CHECK_TEXT_STATUS indicates the status of the text near the checkbox.
        }else intent.putExtra(UsedBookdetails_Page2_Fragment.LIKE, "Like it!");
        intent.putExtra(UsedBookdetails_Page2_Fragment.IMAGE, R.drawable.aws);
        startActivityForResult(intent,REQUEST_HASH_DETAILS);
    }

    private void showMicroDetails() {
        Intent intent = new Intent(getActivity(), UsedBookdetails_Page2.class);
        intent.putExtra(UsedBookdetails_Page2_Fragment.TITLE, "Production Ready MicroServices" );
        intent.putExtra(UsedBookdetails_Page2_Fragment.DESCRIP, "Microservices architectural style is an approach to developing a single application." );
        intent.putExtra(UsedBookdetails_Page2_Fragment.ARTHOR, "Susan J. Fowler" );
        intent.putExtra(UsedBookdetails_Page2_Fragment.PRICE, "29 Euro" );
        if(LIKEMICRO == "ON"){
            intent.putExtra(UsedBookdetails_Page2_Fragment.LIKE, unlikeText );
            CHECK_TEXT_STATUS_MICRO = "UNLIKE";
        } else intent.putExtra(UsedBookdetails_Page2_Fragment.LIKE, "Like it!" );
        intent.putExtra(UsedBookdetails_Page2_Fragment.IMAGE, R.drawable.microservice);
        startActivityForResult(intent,REQUEST_HASH_DETAILS);
    }

    private void showKuberDetails() {
        Intent intent = new Intent(getActivity(), UsedBookdetails_Page2.class);
        intent.putExtra(UsedBookdetails_Page2_Fragment.TITLE, "The Kubernetes Book" );
        intent.putExtra(UsedBookdetails_Page2_Fragment.DESCRIP, "Google revealed the secret through a project called Kubernetes." );
        intent.putExtra(UsedBookdetails_Page2_Fragment.ARTHOR, "Nigel Poulton" );
        intent.putExtra(UsedBookdetails_Page2_Fragment.PRICE, "30 Euro" );
        if(LIKEKUBER == "ON") {
            intent.putExtra(UsedBookdetails_Page2_Fragment.LIKE, unlikeText);
            CHECK_TEXT_STATUS_KUBER = "UNLIKE";
        }else intent.putExtra(UsedBookdetails_Page2_Fragment.LIKE, "Like it!" );
        intent.putExtra(UsedBookdetails_Page2_Fragment.IMAGE, R.drawable.kuber);
        startActivityForResult(intent,REQUEST_HASH_DETAILS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == REQUEST_HASH_DETAILS) {
            // Make sure the request was successful
            if (resultCode == Activity.RESULT_OK) {
                // Show/hide "check it" textView depending on intent extra info
                if (data.hasExtra(UsedBookdetails_Page2_Fragment.OPINION)) {
                    check = data.getBooleanExtra(UsedBookdetails_Page2_Fragment.OPINION, false);
                    Log.d(TAG," onActivityResult RESULT_OK REQUEST_CAR_DETAILS check: " + check);
                    if (check) { //The checkbox is checked. Here indicates the user like the book
                        if (WHICHBOOK == "AWS") {
                            likeAws.setVisibility(View.VISIBLE); //thumb up
                            LIKEAWS = "ON"; // The user likes AWS book
                        };
                        if (WHICHBOOK == "MICRO") {
                            likeMicro.setVisibility(View.VISIBLE);
                            LIKEMICRO = "ON";
                        }
                        if (WHICHBOOK == "KUBER" ) {
                            likeKuber.setVisibility(View.VISIBLE);
                            LIKEKUBER = "ON";
                        }
                    }else{ //The user did not make the check
                        if (WHICHBOOK == "AWS")    likeAws.setVisibility(View.INVISIBLE);
                        if (WHICHBOOK == "MICRO")  likeMicro.setVisibility(View.INVISIBLE);
                        if (WHICHBOOK == "KUBER" ) likeKuber.setVisibility(View.INVISIBLE);
                    }

                    if (CHECK_TEXT_STATUS_AWS == "UNLIKE") {
                        if (check) {//The checkbox is checked again
                            likeAws.setVisibility(View.INVISIBLE);
                            CHECK_TEXT_STATUS_AWS = "LIKEIT"; //Set the class variable back to original status
                            LIKEAWS = "OFF";//The Class variable indicates the user did not like the book
                        }
                    }
                    if (CHECK_TEXT_STATUS_MICRO == "UNLIKE") {
                        if (check) {
                            likeMicro.setVisibility(View.INVISIBLE);
                            CHECK_TEXT_STATUS_MICRO = "LIKEIT";
                            LIKEMICRO = "OFF";
                        }
                    }
                    if (CHECK_TEXT_STATUS_KUBER == "UNLIKE") {
                        if (check) {
                            likeKuber.setVisibility(View.INVISIBLE);
                            CHECK_TEXT_STATUS_KUBER = "LIKEIT";
                            LIKEKUBER = "OFF";
                        }
                    }
                }//if OPINION code close
            } /*if resultCode close*/else {
                Log.d(TAG,"onActivityResult result is not RESULT_NOT_OK");
            }
        }//if requestCode close
    }//method close


}//class close
