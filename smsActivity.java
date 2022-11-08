//package com.mxn.soul.flowingdrawer;
//
//import android.os.Bundle;
//import android.os.StrictMode;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
////import com.mxn.soul.flowingdrawer.backend.MSG91;
//
//public class smsActivity extends AppCompatActivity {
//
//    EditText contact,body;
//    Button send;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sms);
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.sms_toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Send SMS");
//
//        contact = (EditText) findViewById(R.id.contact);
//        body = (EditText) findViewById(R.id.smsbody);
//        send = (Button) findViewById(R.id.BTNSend);
//
//        final String contact_details = contact.getText().toString();
//        final String smsbody = body.getText().toString();
//
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    // Construct data
//                    String apiKey = "apikey=" + "YeQ2+aeJA-FTmgf8tXZAIZdIwfinuKxTdcdYOD2o";
//                    String message = "&message=" + smsbody;
//                    String sender = "&sender=" + "919968874330";
//                    String numbers = "&numbers=" + contact_details;
//
//
//                    // Send data
//                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
//                    String data = apiKey + numbers + message + sender;
//                    conn.setDoOutput(true);
//                    conn.setRequestMethod("POST");
//                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//                    conn.getOutputStream().write(data.getBytes("UTF-8"));
//                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    final StringBuffer stringBuffer = new StringBuffer();
//                    String line;
//                    while ((line = rd.readLine()) != null) {
//                        //stringBuffer.append(line);
//                        Toast.makeText(smsActivity.this, "the message is "+line, Toast.LENGTH_LONG).show();
//                    }
//                    rd.close();
//
//                    //return stringBuffer.toString();
//                } catch (Exception e) {
//                    //System.out.println("Error SMS " + e);
//                    //return "Error " + e;
//                    Log.d("ERROR local",""+e);
//                    Toast.makeText(smsActivity.this, "ERROR"+e, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//
//
//
////        /* Create MSG91 object with your Auth Key */
////
////        final MSG91 msg91 = new MSG91("235119Azm7Kwb25sa5b8acc02");
////
////        /* DEBUG MODE for testing */
////
////        MSG91 msg91Debug = new MSG91("235119Azm7Kwb25sa5b8acc02", true);
////
////        /* VALIDATE */
////
////        String validate = msg91Debug.validate();
////        Toast.makeText(this, "Validation : " + validate, Toast.LENGTH_SHORT).show();
//////        text.setText("Validation : " + validate);
////
////        /* BALANCE CHECK */
////
////        String balancePromotional = msg91.getBalance("1");
////        String balanceTransactional = msg91.getBalance("4");
////        Toast.makeText(this, "\n\nBalance : \nPromotional - " + balancePromotional + "\nTransactional - " + balanceTransactional, Toast.LENGTH_SHORT).show();
//////        text.setText(text.getText() + "\n\nBalance : \nPromotional - " + balancePromotional + "\nTransactional - " + balanceTransactional);
////
//////        msg91.composeMessage("ABCDEF", "This Sample message body that will be sent with sender id : ABCDEF to single mobile number");
////        msg91.to(""+contact_details);
//////        String sendStatus = msg91.send();
////
////
////        msg91.composeMessage("ABCDEF", "This Sample message body that will be sent with sender id : ABCDEF"+"\n"+smsbody);
////
////        // .to(String) : will send message to single mobile number
////        msg91.to(contact_details);
////
////        // .to(ArrayList<String>) : will send message to all the number in the String
//////        msg91.to(mobileNumbers);
////
////        // Optional Functions to set type of message
////
//////        msg91.setSchedule("2015-12-27 12:38:38");
//////        msg91.flash(true);
//////        msg91.unicode(true);
//////        msg91.setRoute("1");
//////        msg91.setCampaign("Campaign");
//////        msg91.setCountryCode("91");
////
////        // .send must me called at the end of all the optional functions
////        // NOTE : Before calling this function you MUST call .composeMessage & .to
////
////        send.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                msg91.send();
////                Toast.makeText(smsActivity.this, "Message will be sent if you are online", Toast.LENGTH_SHORT).show();
////            }
////        });
////
////
//
//
//    }
//}
