package com.mxn.soul.flowingdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

//    private RecyclerView rvFeed;
    private FlowingDrawer mDrawer;
    GridLayout mainGrid;
    private FirebaseAuth mAuth;
    private TextView signup;
    private TextView points;
    private String userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);

//        rvFeed = (RecyclerView) findViewById(R.id.rvFeed);
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        setupToolbar();
//        setupFeed();

        signup = (TextView) findViewById(R.id.noAccount);
        mAuth = FirebaseAuth.getInstance(FirebaseApp.initializeApp(this));
        setupMenu();
        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        if(getIntent() != null)
        {
            String user_info = getIntent().getStringExtra("user_info");
//            txtInfo.setText(info);
        }
        points = (TextView) findViewById(R.id.points);


        points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(MainActivity.this, AccountSettingsActivity.class);
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
//                requestAccount profileDialog = new requestAccount();
//                profileDialog.show(getSupportFragmentManager(),"Edit profile");
            }
        });


        //Set Event
        setSingleEvent(mainGrid);

    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.toggleMenu();
            }
        });
    }


    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    switch (finalI){
                        case 0:
                            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            Intent Accountintent = new Intent(MainActivity.this,NextActivity.class);
                            Accountintent.putExtra("category","lease");
                            startActivity(Accountintent);
                            break;

                        case 1:
                            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            Intent notice = new Intent(MainActivity.this,NextActivity.class);
                            notice.putExtra("category","warrant");
                            startActivity(notice);
                            break;

                        case 2:
                            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            Intent eventintent = new Intent(MainActivity.this,NextActivity.class);
                            eventintent.putExtra("category","license");
                            startActivity(eventintent);
                            break;

                        case 3:
                            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            Intent intent3 = new Intent(MainActivity.this,NextActivity.class);
                            intent3.putExtra("category","subpeona");
                            startActivity(intent3);
                            break;

                        case 4:
                            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            Intent loan = new Intent(MainActivity.this,NextActivity.class);
                            loan.putExtra("category","loan");
                            startActivity(loan);
                            break;

                        case 5:
                            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            Intent re = new Intent(MainActivity.this,NextActivity.class);
                            re.putExtra("category","real_estate");
                            startActivity(re);
                            break;


                        default:
                            Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                            Intent Secintent = new Intent(MainActivity.this,NextActivity.class);
                            startActivity(Secintent);


                    }

//                    if (finalI!=2){
//                    Intent intent = new Intent(MainActivity.this,NextActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
//                    startActivity(intent);
//                    }
//                    else
//                    if (finalI==3){
//                    Intent intent = new Intent(MainActivity.this,smsActivity.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
//                    startActivity(intent);
//                    }
//                    else
//                    {
//                        Intent intent = new Intent(MainActivity.this,EventActivity.class);
//                        intent.putExtra("info","This is activity from card item index  "+finalI);
//                        startActivity(intent);
//                    }

                }
            });
        }
    }


    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){

            sendToStart();

        } else {

//               mUserRef.child("online").setValue("true");

        }


    }


    @Override
    protected void onStop() {
        super.onStop();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null) {

//            mUserRef.child("online").setValue(ServerValue.TIMESTAMP);

        }

    }

    private void sendToStart() {

        Intent startIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(startIntent);
        finish();

    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
