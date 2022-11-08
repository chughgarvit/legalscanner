
package com.mxn.soul.flowingdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mxn.soul.flowingdrawer.Events.EventActivity;
import com.mxn.soul.flowingdrawer.db.DatabaseAdder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * Created by mxn on 2016/12/13.
 * MenuListFragment
 */

public class MenuListFragment extends Fragment {

    private FrameLayout menuHeader;
    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;
    private FirebaseUser mCurrentUser;
    private String current_uid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        final View header = ((NavigationView) view.findViewById(R.id.vNavigation)).getHeaderView(0);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
            current_uid = mCurrentUser.getUid();
            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_uid);
            mUserDatabase.keepSynced(true);


            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String name = dataSnapshot.child("Name").getValue().toString();
                    final String image = dataSnapshot.child("Image").getValue().toString();
                    int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
                    final ImageView ivMenuUserProfilePhoto;
                    TextView ivMenuUserName;

                    ivMenuUserProfilePhoto = (ImageView) header.findViewById(R.id.ivMenuUserProfilePhoto);
                    ivMenuUserName = (TextView) header.findViewById(R.id.ivMenuUserName);

                    ivMenuUserName.setText(name);

                    Picasso.with(getContext()).load(image).into(ivMenuUserProfilePhoto);


                    if(!image.equals("default")) {

//                        Picasso.with(AllUsersActivity.this).load(image).placeholder(R.drawable.default_avatar).into(mDisplayImage);

                        Picasso.with(getContext())
                                .load(image)
                                .placeholder(R.drawable.default_avatar)
                                .error(R.drawable.default_avatar)
                                .resize(avatarSize, avatarSize)
                                .centerCrop()
                                .transform(new CircleTransformation())
                                .into(ivMenuUserProfilePhoto);

                        Picasso.with(getContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE)
                                .placeholder(R.drawable.default_avatar).into(ivMenuUserProfilePhoto, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {

                                Picasso.with(getContext()).load(image).placeholder(R.drawable.default_avatar).into(ivMenuUserProfilePhoto);

                            }
                        });

                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }




        menuHeader = (FrameLayout) header.findViewById(R.id.mainHeader);

        mAuth = FirebaseAuth.getInstance();

        NavigationView vNavigation = (NavigationView) view.findViewById(R.id.vNavigation);
        vNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                if (menuItem.getTitle().equals("Log Out")){
                    FirebaseAuth.getInstance().signOut();
                    Intent startIntent = new Intent(getContext(), LoginActivity.class);
                    startActivity(startIntent);
                    getActivity().finish();
                    }
                    else
                if (menuItem.getTitle().equals("My Home")){
                    Intent startIntent = new Intent(getContext(), MainActivity.class);
                    startActivity(startIntent);
                    getActivity().finish();
                }else
                if (menuItem.getTitle().equals("Add more phrases")){
                    Intent startIntent = new Intent(getContext(), DatabaseAdder.class);
                    startActivity(startIntent);
                }else
                if (menuItem.getTitle().equals("News")){
                    Intent startIntent = new Intent(getContext(), EventActivity.class);
                    startActivity(startIntent);
                }else
                if (menuItem.getTitle().equals("Vulnerabilities in Legal dept.")){
                    Intent startIntent = new Intent(getContext(), CommonBaseActivity.class);
                    startIntent.putExtra("category","vul");
                    startActivity(startIntent);
                }else
                if (menuItem.getTitle().equals("Why this app?")){
                    Intent startIntent = new Intent(getContext(), CommonBaseActivity.class);
                    startIntent.putExtra("category","why");
                    startActivity(startIntent);
                }else
                if (menuItem.getTitle().equals("List of Legal Contracts")){
                    Intent startIntent = new Intent(getContext(), CommonBaseActivity.class);
                    startIntent.putExtra("category","list");
                    startActivity(startIntent);
                }else
                Toast.makeText(getActivity(),menuItem.getTitle(),Toast.LENGTH_SHORT).show();
                return false;
            }
        }) ;

        menuHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getContext(), AccountSettingsActivity.class);
                startActivity(startIntent);
            }
        });


//        setupHeader();
        return view ;
    }

//    private void setupHeader() {
//        int avatarSize = getResources().getDimensionPixelSize(R.dimen.global_menu_avatar_size);
//        String profilePhoto = getResources().getString(R.string.user_profile_photo);
//        Picasso.with(getActivity())
//                .load(profilePhoto)
//                .placeholder(R.drawable.img_circle_placeholder)
//                .resize(avatarSize, avatarSize)
//                .centerCrop()
//                .transform(new CircleTransformation())
//                .into(ivMenuUserProfilePhoto);
//    }

}
