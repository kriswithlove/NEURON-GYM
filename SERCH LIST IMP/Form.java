//Newneurongym-master\app\src\main\java\com\edlogiq\neuron\gym\signin
package com.edlogiq.neuron.gym.signin;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.edlogiq.neuron.gym.constant.DataBase;
import com.edlogiq.neuron.gym.constant.RefrenceWrapper;
import com.edlogiq.neuron.gym.neurongym.HomePage;
import com.edlogiq.neuron.gym.neurongym.ThemeSelect;
import com.edlogiq.neuron.gym.R;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.gc.materialdesign.views.ButtonRectangle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import io.branch.referral.Branch;

//import bolts.AppLinks;
//import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;

public class Form extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<People.LoadPeopleResult>, View.OnClickListener,
        CheckBox.OnCheckedChangeListener, GoogleApiClient.ServerAuthCodeCallbacks  {

    private RefrenceWrapper refrence;
    private RadioGroup radioGroup;
    private TextView name,emai,password;
    private EditText et;//ANIKET
    private ListView country;
    private ParseObject userinfo,userdata;
    private ParseUser user;
    private boolean userval=true;

    ProgressDialog progressDialog;

    private ArrayList<String> gender=new ArrayList<>();
    private String gender_val,profession,country_val;

    private String [] country_list;
    private String [] profession_list;
    private int mSignInProgress;
    private String proList[]=new String[]{"Profession","Actor","Administrator","Analyst","Architect","Athlete"
            ,"Author","Banker","Biotechnologist","Chef","Clerk","Defence Personnel","Designer","Doctor"
            ,"Ecologist","Editor","Engineer","Entrepreneur","Farmer","Film Maker","Geologist","Historian","House wife"
            ,"Journalist","Judge","Lawyer","Manager","Musician","Operator","Photographer","Pilot"
            ,"Scientist","Software Developer","Sports person","Other"};
    //ANIKET
    int textlength=0;
    private ArrayList<String> array_sort= new ArrayList<String>();
    //ANIKET
    private String Country_List[]=new String[]{"Afghanistan","Akrotiri","Albania","Algeria","American Samoa","Andorra",
            "Angola","Anguilla","Antarctica","Antigua and Barbuda","Argentina","Armenia","Aruba","Ashmore and Cartier Islands",
            "Australia","Austria","Azerbaijan","Bahamas, The","Bahrain","Bangladesh","Barbados","Bassas da India","Belarus",
            "Belgium","Belize","Benin","Bermuda","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Bouvet Island",
            "Brazil","British Indian Ocean Territory","British Virgin Islands","Brunei","Bulgaria","Burkina Faso","Burma",
            "Burundi","Cambodia","Cameroon","Canada","Cape Verde","Cayman Islands","Central African Republic","Chad",
            "Chile","China","Christmas Island","Clipperton Island","Cocos (Keeling) Islands","Colombia","Comoros",
            "Congo, Democratic Republic of the","Congo, Republic of the","Cook Islands","Coral Sea Islands","Costa Rica",
            "Cote d'Ivoire","Croatia","Cuba"," Cyprus","Czech Republic","Denmark","Dhekelia"," Djibouti","Dominica",
            "Dominican Republic","Ecuador","Egypt","El Salvador","Equatorial Guinea","Eritrea","Estonia","Ethiopia",
            "Europa Island","Falkland Islands (Islas Malvinas)","Faroe Islands","Fiji","Finland","France"," French Guiana",
            "French Polynesia"," French Southern and Antarctic Lands"," Gabon"," Gambia, The","Gaza Strip"," Georgia"," Germany",
            " Ghana"," Gibraltar","Glorioso Islands","Greece","Greenland","Grenada"," Guadeloupe"," Guam","Guatemala","Guernsey",
            "Guinea","Guinea-Bissau","Guyana","Haiti","Heard Island and McDonald Islands","Holy See (Vatican City)","Honduras",
            "Hong Kong","Hungary","Iceland","India","Indonesia"," Iran","Iraq","Ireland","Isle of Man","Israel","Italy","Jamaica",
            "Jan Mayen","Japan","Jersey","Jordan","Juan de Nova Island","Kazakhstan","Kenya","Kiribati","Korea, North","Korea, South",
            "Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein","Lithuania","Luxembourg",
            "Macau","Macedonia","Madagascar","Malawi","Malaysia"," Maldives"," Mali"," Malta"," Marshall Islands"," Martinique",
            "Mauritania","Mauritius","Mayotte"," Mexico"," Micronesia, Federated States of"," Moldova","Monaco"," Mongolia","Montserrat"
            ,"Morocco","Mozambique"," Namibia","Nauru"," Navassa Island"," Nepal","Netherlands","Netherlands Antilles",
            " New Caledonia","New Zealand","Nicaragua","Niger"," Nigeria","Niue","Norfolk Island","Northern Mariana Islands",
            "Norway ","Oman","Pakistan","Palau","Panama","Papua New Guinea","Paracel Islands","Paraguay","Peru","Philippines",
            "Pitcairn Islands","Poland","Portugal","Puerto Rico","Qatar","Reunion","Romania","Russia","Rwanda","Saint Helena",
            "Saint Kitts and Nevis","Saint Lucia","Saint Pierre and Miquelon","Saint Vincent and the Grenadines","Samoa",
            "San Marino","Sao Tome and Principe","Saudi Arabia","Senegal","Serbia and Montenegro","Seychelles","Sierra Leone",
            "Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa","South Georgia and the South Sandwich Islands",
            "Spain","Spratly Islands","Sri Lanka"," Sudan","Suriname","Svalbard","Swaziland","Sweden","Switzerland","Syria","Taiwan",
            "Tajikistan","Tanzania","Thailand","Timor-Leste"," Togo","Tokelau","Tonga","Trinidad and Tobago","Tromelin Island"
            ,"Tunisia","Turkey","Turkmenistan","Turks and Caicos Islands","Tuvalu","Uganda","Ukraine","United Arab Emirates",
            "United Kingdom","United States","Uruguay","Uzbekistan","Vanuatu","Venezuela","Vietnam","Virgin Islands",
            "Wake Island"," Wallis and Futuna","West Bank","Western Sahara","Yemen","Zambia","Zimbabwe"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        refrence= RefrenceWrapper.getRefrenceWrapper(this);


        gender.add(getResources().getString(R.string.Gender));
        gender.add(getResources().getString(R.string.male));
        gender.add(getResources().getString(R.string.female));

        final Calendar cal=Calendar.getInstance();
        year_x=cal.get(Calendar.YEAR);
        month_x=cal.get(Calendar.MONTH);
        day_x=cal.get(Calendar.DAY_OF_MONTH);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Form.this,android.R.layout.simple_list_item_1,gender);

        ((Spinner)findViewById(R.id.spinnergender)).setAdapter(adapter);
        ((Spinner)findViewById(R.id.spinnergender)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("Value",""+position);
                ((TextView) ((Spinner)findViewById(R.id.spinnergender)).getSelectedView()).setTextColor(getResources().getColor(R.color.cyan));
                if(parent.getItemAtPosition(position).toString().equals(getResources().getString(R.string.Gender))) {
                    ((TextView) ((Spinner)findViewById(R.id.spinnergender)).getSelectedView()).setTextColor(getResources().getColor(R.color.Text));
                }
               if(position==0) {
                   gender_val = "Gender";
               }else if(position==1){
                   gender_val = "Male";
               }else if(position==2){
                   gender_val = "Female";
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        init();
        if (savedInstanceState != null) {
            mSignInProgress = savedInstanceState
                    .getInt(SAVED_PROGRESS, STATE_DEFAULT);
        }

        Log.e("INTERNET",""+isConnectingToInternet());
        mGoogleApiClient = buildGoogleApiClient();
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);



    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }



    private void init() {
        //ANIKET
        et = (EditText) findViewById(R.id.EditText01);
        //ANIKET
        name=(EditText)findViewById(R.id.editName);
        emai=(EditText)findViewById(R.id.editemail);
        ((TextView)findViewById(R.id.editdate)).setOnClickListener(fioemlistner);

        country=(ListView)findViewById(R.id.countrylist);
        password=(EditText)findViewById(R.id.editpassword);

        ((ButtonRectangle)findViewById(R.id.sighin)).setOnClickListener(fioemlistner);
        ((TextView)findViewById(R.id.editTextcountry)).setOnClickListener(fioemlistner);

        ((ButtonFloatSmall)findViewById(R.id.buttonCamera)).setOnClickListener(listner);
        ((ButtonFloatSmall)findViewById(R.id.buttonGalary)).setOnClickListener(listner);
        ((ButtonRectangle)findViewById(R.id.layoutetwitter)).setOnClickListener(this);
        ((LinearLayout)findViewById(R.id.termandconditions)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.btncross)).setOnClickListener(this);
        ((ButtonRectangle)findViewById(R.id.layoutegoogle)).setOnClickListener(this);
        ((ButtonRectangle)findViewById(R.id.layoutgmail)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.cross)).setOnClickListener(listner);
        Bitmap bmp=BitmapFactory.decodeResource(getResources(), R.drawable.login_icon);




        country_list=getResources().getStringArray(R.array.country_array);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, country_list);
        country.setAdapter(adapter1);
          //ANIKET

        et.addTextChangedListener(new TextWatcher()
        {
            public void afterTextChanged(Editable s)
            {
                // Abstract Method of TextWatcher Interface.
            }
            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after)
            {
               // Abstract Method of TextWatcher Interface.
            }
            public void onTextChanged(CharSequence s,
                                      int start, int before, int count)
            {
                textlength = et.getText().length();
                array_sort.clear();
                for (int i = 0; i < country_list.length; i++)
                {
                    if (textlength <= country_list[i].length())
                    {
                        if(et.getText().toString().equalsIgnoreCase(
                                (String)
                                        country_list[i].subSequence(0,
                                                textlength)))
                        {
                            array_sort.add(country_list[i]);
                        }
                    }
                }
                country.setAdapter(new ArrayAdapter<String>
                        (Form.this,
                                android.R.layout.simple_list_item_1, array_sort));
            }
        });
        //ANIKET END
        country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                country_val =Country_List[position];
                if(((LinearLayout)findViewById(R.id.countrylayoute)).getVisibility()==(View.VISIBLE)){
                    ((LinearLayout)findViewById(R.id.countrylayoute)).setVisibility(View.GONE);
                }
                ((TextView)findViewById(R.id.editTextcountry)).setText(parent.getItemAtPosition(position).toString());

            }
        });


        profession_list=getResources().getStringArray(R.array.profession_array);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(Form.this,android.R.layout.simple_list_item_1,profession_list);

        ((Spinner)findViewById(R.id.spinnerprofession)).setAdapter(adapter);
        ((Spinner)findViewById(R.id.spinnerprofession)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) ((Spinner)findViewById(R.id.spinnerprofession)).getSelectedView()).setTextColor(getResources().getColor(R.color.cyan));
                if(parent.getItemAtPosition(position).toString().equals(getResources().getString(R.string.Profession))) {
                    ((TextView) ((Spinner)findViewById(R.id.spinnerprofession)).getSelectedView()).setTextColor(getResources().getColor(R.color.Text));
                }

                profession=proList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

//    Set Date Of BirthDay

    int year_x,month_x,day_x;
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id==0)
            return  new DatePickerDialog(this,datepicker,year_x,month_x,day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener datepicker=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x=year;
            month_x=monthOfYear+1;
            day_x=dayOfMonth;
            ((TextView)findViewById(R.id.editdate)).setText(day_x+"/"+month_x+"/"+year_x);
        }
    };



    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    View.OnClickListener fioemlistner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.sighin){
                if(userval) {
                    register();
                }else{
                    login();
                }
            }else if(v.getId()==R.id.editdate){
                showDialog(0);
            }else if(v.getId()==R.id.editTextcountry){
                ((LinearLayout)findViewById(R.id.countrylayoute)).setVisibility(View.VISIBLE);
            }
        }
    };

    private final  boolean isValidDOB(String target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            String dob[]=target.split("/");
            Log.e("date",target+"   "+dob[0]+"  "+dob[1]+"  "+dob[2]);
            if(dob[0].length()<=2 && dob[0].length()>0){
                if(dob[1].length()<=2 && dob[1].length()>0) {
                    if(dob[2].length()==4) {
                        return true;
                    }else{ return false;}
                }else{ return false;}
            }else{ return false;}
        }
    }


    View.OnClickListener listner=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.buttonCamera){
                Captureimage();
                ((RelativeLayout)findViewById(R.id.imagelayoute)).setVisibility(View.GONE);
            }
            else if(v.getId()==R.id.buttonGalary) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                ((RelativeLayout)findViewById(R.id.imagelayoute)).setVisibility(View.GONE);
            }else if(v.getId()==R.id.cross){
                if(((RelativeLayout)findViewById(R.id.imagelayoute)).getVisibility()==(View.VISIBLE)){
                    ((RelativeLayout)findViewById(R.id.imagelayoute)).setVisibility(View.GONE);
                }
            }
        }
    };

    @Override
    public void onClick(View v) {

        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }
        if( ((RelativeLayout)findViewById(R.id.loginform)).getVisibility()==View.VISIBLE){
            return;
        }
        if(v.getId()==R.id.layoutgmail){
            userval=true;
            ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.VISIBLE);
        }else if(v.getId()==R.id.layoutetwitter){
            userval=false;
            tweeter();
        }else if(v.getId()==R.id.layoutegoogle ){  //&& mGoogleApiClient.isConnected()
            userval=false;
            mSignInProgress = STATE_SIGN_IN;
            mGoogleApiClient.connect();
        }else if(v.getId()==R.id.termandconditions){
            ((RelativeLayout)findViewById(R.id.privacypolicy)).setVisibility(View.VISIBLE);
        }else if(v.getId()==R.id.btncross){
            if(((RelativeLayout)findViewById(R.id.privacypolicy)).getVisibility()==View.VISIBLE){
                ((RelativeLayout)findViewById(R.id.privacypolicy)).setVisibility(View.GONE);
            }
        }
    }

    //    Register New User
    private void register() {

        if (name.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show();
        } else if (!isValidEmail(emai.getText())){
            Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show();
        } else if(password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show();
        }else if(!isValidDOB( ((TextView)findViewById(R.id.editdate)).getText().toString())) {
            Toast.makeText(this, "Enter the dateofbirth or dateofbirth is invalid", Toast.LENGTH_SHORT).show();
        }else if(gender_val.equals("Gender")){
            Toast.makeText(this, "Select Your Gender", Toast.LENGTH_SHORT).show();
        }else if(profession.equals(getResources().getString(R.string.Profession))){
            Toast.makeText(this, "Select your Profession", Toast.LENGTH_SHORT).show();
        }else if(country_val.toString().isEmpty()) {
            Toast.makeText(this, "Enter the country", Toast.LENGTH_SHORT).show();
        }else{

            user = new ParseUser();
            user.setEmail(emai.getText().toString());
            user.setUsername(emai.getText().toString());
            user.setPassword(password.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException er) {
                    if (er != null) {
                        Toast.makeText(Form.this,"User Already login!",Toast.LENGTH_LONG).show();
                    } else {
                        new myAsyncTask().execute();
                        Log.e("hello","hello");
                    }
                }
            });

        }
    }

    //    Register New User for facebook google.
    private void login() {
        if (name.getText().equals(" ")) {
            Toast.makeText(this, "Enter the name", Toast.LENGTH_SHORT).show();
        }else if(!isValidDOB( ((TextView)findViewById(R.id.editdate)).getText().toString())) {
            Toast.makeText(this, "Enter the dateofbirth or dateofbirth is invalid", Toast.LENGTH_SHORT).show();
        }else if(country_val.equals(null)) {
            Toast.makeText(this, "Enter the country", Toast.LENGTH_SHORT).show();
        }else if(gender_val.equals("Gender")){
            Toast.makeText(this, "Select your Gender", Toast.LENGTH_SHORT).show();
        }else if(profession.equals(getResources().getString(R.string.Profession))){
            Toast.makeText(this, "Select your Profession", Toast.LENGTH_SHORT).show();
//        }else if(camera==null) {
//            Toast.makeText(this, "Select your image", Toast.LENGTH_SHORT).show();
        }else{
            new myAsyncTask().execute();
        }
    }


//    Tweeter Login

    private void tweeter(){
        ParseTwitterUtils.logIn(this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.e("MyApp", "Uh oh. The user cancelled the Twitter login.");
                } else if (user.isNew()) {
                    ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.VISIBLE);
                    ((EditText)findViewById(R.id.editemail)).setVisibility(View.GONE);
                    ((EditText)findViewById(R.id.editpassword)).setVisibility(View.GONE);

                    userval=false;
                    Log.e("MyApp", "User signed up and logged in through Twitter!");
                } else {
                    progressDialog = ProgressDialog.show(Form.this, "", "Loading...", true,false);
                    userinformation(user);
                    Toast.makeText(Form.this,"User Already logged up through Twitter!",Toast.LENGTH_LONG).show();
                    Log.e("MyApp", "User Already logged up through Twitter!");

                }

            }
        });
    }


    private void userinformation(ParseUser parseUser) {
        Log.e("Values",parseUser+"  "+parseUser.getObjectId());
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("UserIformation");
        query.whereEqualTo("parent", parseUser);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> markers, ParseException e) {
                Log.e("Values","   "+markers);
                if(markers!=null) {
                    if (e == null && markers.size() != 0) {
                        retriveAllData(markers.get(0));
                    } else {
                        Log.e("DISMISS1111111", "DISMISS");
                        if (markers.size() == 0)

                        ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.VISIBLE);
                        ((EditText)findViewById(R.id.editemail)).setVisibility(View.GONE);
                        ((EditText)findViewById(R.id.editpassword)).setVisibility(View.GONE);
                        name.setText(_name);

                        userval=false;

                        if(progressDialog!=null && progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                }
            }
        });
    }


    private void retriveAllData(ParseObject parseObject) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserIformation");
        query.getInBackground(parseObject.getObjectId().toString(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {

                    DataBase.setUserObjectId(object.getObjectId().toString(), Form.this);
                    DataBase.setUserName(object.get("name").toString(), Form.this);
                    DataBase.setGender(object.get("gender").toString(), Form.this);
                    DataBase.setAge(object.get("age").toString(), Form.this);
                    ParseFile image = (ParseFile) object.getParseFile("image");
                    image.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] bytes, ParseException e) {
                            if(e==null){
                                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                                if(bmp!=null){
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                                    byte imageInByte[] = stream.toByteArray();
                                    String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                                    DataBase.setUserImage(Form.this, encodedImage);
                                }
                            }else{
                                Log.e("errorimage",e.getMessage());
                            }
                        }
                    });


                } else {
                    Log.e("errorimagennnnnnnnnn",e.getMessage());
                    // something went wrong
                }
            }
        });

        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("HighScoreMultiplierSubscription");
        query1.getInBackground(parseObject.getObjectId().toString(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {

                    DataBase.setAdFreeTime((object.get("SubscriptionDate").toString()),Form.this);

                    DataBase.setReversal(Integer.parseInt(object.get("HReversal").toString()),Form.this);
                    DataBase.setShapes(Integer.parseInt(object.get("HShapes").toString()),Form.this);
                    DataBase.setSpotIt(Integer.parseInt(object.get("HSpotIt").toString()),Form.this);
                    DataBase.setSolveIt(Integer.parseInt(object.get("HSolveIt").toString()),Form.this);
                    DataBase.setMatchIt(Integer.parseInt(object.get("HMatchIt").toString()),Form.this);
                    DataBase.setTrackTheRoute(Integer.parseInt(object.get("HTrackTheRoute").toString()),Form.this);
                    DataBase.setDualFocus(Integer.parseInt(object.get("HDualFocus").toString()),Form.this);
                    DataBase.setSpeedShop(Integer.parseInt(object.get("HSpeedShop").toString()),Form.this);
                    DataBase.setMemoryMatrix(Integer.parseInt(object.get("HMemoryMatrix").toString()),Form.this);
                    DataBase.setBlink(Integer.parseInt(object.get("HBlink").toString()),Form.this);
                    DataBase.setMoneyGame(Integer.parseInt(object.get("HMoneyGame").toString()),Form.this);
                    DataBase.setDancingBall(Integer.parseInt(object.get("HDancingBalls").toString()),Form.this);
                    DataBase.setBrainFlash(Integer.parseInt(object.get("HBrainFlash").toString()),Form.this);
                    DataBase.setAfterMath(Integer.parseInt(object.get("HAfterMath").toString()),Form.this);
                    DataBase.setReversalPro(Integer.parseInt(object.get("HReversalPro").toString()),Form.this);
                    DataBase.setBlinkPro(Integer.parseInt(object.get("HBlinkPro").toString()),Form.this);
                    DataBase.setMemoryMatrixPro(Integer.parseInt(object.get("HMemoryMatrixPro").toString()),Form.this);
                    DataBase.setSpotItPro(Integer.parseInt(object.get("HSpotItPro").toString()),Form.this);
                    DataBase.setMatchItPro(Integer.parseInt(object.get("HMatchItPro").toString()),Form.this);
                    DataBase.setDualFocusPro(Integer.parseInt(object.get("HDualFocusPro").toString()),Form.this);


                    DataBase.setReversalM(Integer.parseInt(object.get("MReversal").toString()),Form.this);
                    DataBase.setShapesM(Integer.parseInt(object.get("MShapes").toString()),Form.this);
                    DataBase.setSpotItM(Integer.parseInt(object.get("MSpotIt").toString()),Form.this);
                    DataBase.setSolveItM(Integer.parseInt(object.get("MSolveIt").toString()),Form.this);
                    DataBase.setMatchItM(Integer.parseInt(object.get("MMatchIt").toString()),Form.this);
                    DataBase.setTrackTheRouteM(Integer.parseInt(object.get("MTrackTheRoute").toString()),Form.this);
                    DataBase.setDualFocusM(Integer.parseInt(object.get("MDualFocus").toString()),Form.this);
                    DataBase.setSpeedShopM(Integer.parseInt(object.get("MSpeedShop").toString()),Form.this);
                    DataBase.setMemoryMatrixM(Integer.parseInt(object.get("MMemoryMatrix").toString()),Form.this);
                    DataBase.setBlinkM(Integer.parseInt(object.get("MBlink").toString()),Form.this);
                    DataBase.setMoneyGameM(Integer.parseInt(object.get("MMoneyGame").toString()),Form.this);
                    DataBase.setDancingBallM(Integer.parseInt(object.get("MDancingBalls").toString()),Form.this);
                    DataBase.setBrainFlashM(Integer.parseInt(object.get("MBrainFlash").toString()),Form.this);
                    DataBase.setAfterMathM(Integer.parseInt(object.get("MAfterMath").toString()),Form.this);
                    DataBase.setReversalProM(Integer.parseInt(object.get("MReversalPro").toString()),Form.this);
                    DataBase.setBlinkProM(Integer.parseInt(object.get("MBlinkPro").toString()),Form.this);
                    DataBase.setMemoryMatrixProM(Integer.parseInt(object.get("MMemoryMatrixPro").toString()),Form.this);
                    DataBase.setSpotItProM(Integer.parseInt(object.get("MSpotItPro").toString()),Form.this);
                    DataBase.setMatchItProM(Integer.parseInt(object.get("MMatchItPro").toString()),Form.this);
                    DataBase.setDualFocusProM(Integer.parseInt(object.get("MDualFocusPro").toString()),Form.this);

                } else {
                    Log.e("errorimagennnnnnnnnn",e.getMessage());
                    // something went wrong
                }
            }
        });
        nextactiviy();
    }
    


    private class myAsyncTask extends AsyncTask<String, Integer, String> {

        String encodedImage=null;
        ParseFile image=null;
        ProgressDialog progressDialog1;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog1 = ProgressDialog.show(Form.this, "", "Loading...", true,false);
            Log.e("pre","prehello");
        }

        @Override
        protected String doInBackground(String... params) {


            if(camera!=null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                camera.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte imageInByte[] = stream.toByteArray();
                encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                 image = new ParseFile("image.txt", imageInByte);
            }else{
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image33);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte imageInByte[] = stream.toByteArray();
                encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
                 image = new ParseFile("image.txt", imageInByte);
            }

            DataBase.setUserImage(Form.this, encodedImage);
            DataBase.setUserName(name.getText().toString(), Form.this);


            userinfo = new ParseObject("UserIformation");
            userinfo.put("name", name.getText().toString());
            userinfo.put("dateofbirth", ( ((TextView)findViewById(R.id.editdate)).getText().toString()));
            userinfo.put("country", country_val.toString().toLowerCase());
            userinfo.put("profession", profession.toLowerCase());
            userinfo.put("gender",gender_val.toLowerCase());
            userinfo.put("image", image);
            userinfo.put("parent", ParseUser.getCurrentUser());
            userinfo.saveInBackground();

            userdata=new ParseObject("HighScoreMultiplierSubscription");
            userdata.put("HReversal", 0);
            userdata.put("HShapes", 0);
            userdata.put("HSpotIt", 0);
            userdata.put("HSolveIt", 0);
            userdata.put("HMatchIt", 0);
            userdata.put("HTrackTheRoute", 0);
            userdata.put("HDualFocus", 0);
            userdata.put("HSpeedShop", 0);
            userdata.put("HMemoryMatrix", 0);
            userdata.put("HBlink", 0);
            userdata.put("HMoneyGame", 0);
            userdata.put("HDancingBalls", 0);
            userdata.put("HBrainFlash", 0);
            userdata.put("HAfterMath", 0);
            userdata.put("HReversalPro", 0);
            userdata.put("HBlinkPro", 0);
            userdata.put("HMemoryMatrixPro", 0);
            userdata.put("HSpotItPro", 0);
            userdata.put("HMatchItPro", 0);
            userdata.put("HDualFocusPro", 0);

            userdata.put("MReversal", 0);
            userdata.put("MShapes", 0);
            userdata.put("MSpotIt", 0);
            userdata.put("MSolveIt", 0);
            userdata.put("MMatchIt", 0);
            userdata.put("MTrackTheRoute", 0);
            userdata.put("MDualFocus", 0);
            userdata.put("MSpeedShop", 0);
            userdata.put("MMemoryMatrix", 0);
            userdata.put("MBlink", 0);
            userdata.put("MMoneyGame", 0);
            userdata.put("MDancingBalls", 0);
            userdata.put("MBrainFlash", 0);
            userdata.put("MAfterMath", 0);
            userdata.put("MReversalPro", 0);
            userdata.put("MBlinkPro", 0);
            userdata.put("MMemoryMatrixPro", 0);
            userdata.put("MSpotItPro", 0);
            userdata.put("MMatchItPro", 0);
            userdata.put("MDualFocusPro", 0);

            Date condate = new Date();
            String date = new SimpleDateFormat("dd-MM-yyyy").format(condate);
            userdata.put("SubscriptionDate", date);
            userdata.put("parent", ParseUser.getCurrentUser());
            userdata.saveInBackground();


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(progressDialog1!=null && progressDialog1.isShowing()){
                progressDialog1.dismiss();
                Log.e("DISMISS", "DISMISS");
            }
            nextactiviy();
        }
    }

    private void nextactiviy() {

        if( ((RelativeLayout)findViewById(R.id.loginform)).getVisibility()==View.VISIBLE){
            ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.GONE);
        }
        Branch.getInstance(getApplicationContext()).setIdentity(ParseUser.getCurrentUser().toString());
        DataBase.setLogin("login", Form.this);
        DataBase.setGender(gender_val, this);
        Form.this.finish();
        Intent intent = new Intent(this, ThemeSelect.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


//    Image capture and choose to galary.

    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    public static final int MEDIA_TYPE_IMAGE = 1;
    private Uri fileUri;
    private static int RESULT_LOAD_IMAGE = 1;
    private Bitmap camera;
    private String picturePath;

    private void Captureimage() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);

    }
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type) {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),IMAGE_DIRECTORY_NAME);

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.e(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator+ "IMG_" + timeStamp + ".jpg");
        }else {
            return null;
        }
        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("String",resultCode+"   "+requestCode);
//        callbackManager.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RC_SIGN_IN:
                if (resultCode == RESULT_OK) {
                    // If the error resolution was successful we should continue
                    // processing errors.
                    mSignInProgress = STATE_SIGN_IN;
                } else {
                    // If the error resolution was not successful or the user canceled,
                    // we should stop processing errors.
                    mSignInProgress = STATE_DEFAULT;
                }

                if (!mGoogleApiClient.isConnecting()) {
                    // If Google Play services resolved the issue with a dialog then
                    // onStart is not called so we need to re-attempt connection here.
                    mGoogleApiClient.connect();
                }
                break;
        }


        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {

                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
            camera= BitmapFactory.decodeFile(picturePath);
            Log.e("Camera111",""+camera);
//            setimage(camera);
        }
    }

    private void previewCapturedImage() {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            camera = BitmapFactory.decodeFile(fileUri.getPath(),options);
//            setimage(camera);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }






    @Override
    public void onBackPressed() {
        if(((LinearLayout)findViewById(R.id.countrylayoute)).getVisibility()==(View.VISIBLE)){
            ((LinearLayout)findViewById(R.id.countrylayoute)).setVisibility(View.GONE);
        }else  if(((RelativeLayout)findViewById(R.id.loginform)).getVisibility()==(View.VISIBLE)){
            ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.GONE);
        }else if(((RelativeLayout)findViewById(R.id.privacypolicy)).getVisibility()==View.VISIBLE){
            ((RelativeLayout)findViewById(R.id.privacypolicy)).setVisibility(View.GONE);
        } else{
            Intent intent= new Intent(this,LoginIn.class);
            startActivity(intent);
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            this.finish();
        }

    }

    private boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }

        }
        Toast.makeText(this,"Internet is not connected",Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    public void onResume() {
        super.onResume();
//        Profile profile=Profile.getCurrentProfile();
//        Log.e("profile",""+profile);
//        display(profile);
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }

        if (camera != null) {
            camera.recycle();
            camera = null;
        }
    }

//    private void display(Profile profile){
//        if(profile!=null){
//            Log.e("Name",profile.getName());
//        }
//    }




    // Gmail ya Google Plus connection provider...

    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "MainActivity";

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    private static final String SAVED_PROGRESS = "sign_in_progress";
    private static final int STATE_DEFAULT = 0;
    private static final int STATE_SIGN_IN = 1;
    private static final int STATE_IN_PROGRESS = 2;
    private PendingIntent mSignInIntent;

    private boolean mServerHasToken = true;
    private static final String SERVER_BASE_URL = "SERVER_BASE_URL";

    // URL where the client should GET the scopes that the server would like granted
    // before asking for a serverAuthCode
    private static final String EXCHANGE_TOKEN_URL = SERVER_BASE_URL + "/exchangetoken";

    // URL where the client should POST the serverAuthCode so that the server can exchange
    // it for a refresh token,
    private static final String SELECT_SCOPES_URL = SERVER_BASE_URL + "/selectscopes";


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_PROGRESS, mSignInProgress);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onResult(People.LoadPeopleResult peopleData) {
        if (peopleData.getStatus().getStatusCode() == CommonStatusCodes.SUCCESS) {
//            mCirclesList.clear();
            PersonBuffer personBuffer = peopleData.getPersonBuffer();
            try {
                int count = personBuffer.getCount();
                for (int i = 0; i < count; i++) {
//                    mCirclesList.add(personBuffer.get(i).getDisplayName());
                    Log.e("Name_value",personBuffer.get(i).getDisplayName());
                }
            } finally {
                personBuffer.close();
            }

//            mCirclesAdapter.notifyDataSetChanged();
        } else {
            Log.e(TAG, "Error requesting visible circles: " + peopleData.getStatus());
        }
    }

    @Override
    public CheckResult onCheckServerAuthorization(String s, Set<Scope> scopes) {
        Log.i(TAG, "Checking if server is authorized.");
        Log.i(TAG, "Mocking server has refresh token: " + String.valueOf(mServerHasToken));

        if (!mServerHasToken) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(SELECT_SCOPES_URL);
            HashSet<Scope> serverScopeSet = new HashSet<Scope>();

            try {
                HttpResponse httpResponse = httpClient.execute(httpGet);
                int responseCode = httpResponse.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(httpResponse.getEntity());

                if (responseCode == 200) {
                    String[] scopeStrings = responseBody.split(" ");
                    for (String scope : scopeStrings) {
                        Log.i(TAG, "Server Scope: " + scope);
                        serverScopeSet.add(new Scope(scope));
                    }
                } else {
                    Log.e(TAG, "Error in getting server scopes: " + responseCode);
                }

            } catch (ClientProtocolException e) {
                Log.e(TAG, "Error in getting server scopes.", e);
            } catch (IOException e) {
                Log.e(TAG, "Error in getting server scopes.", e);
            }
            return CheckResult.newAuthRequiredResult(serverScopeSet);
        } else {
            // Server already has a valid refresh token with the correct scopes, no need to
            // ask the user for offline access again.
            return CheckResult.newAuthNotRequiredResult();
        }
    }

    @Override
    public boolean onUploadServerAuthCode(String s, String serverAuthCode) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(EXCHANGE_TOKEN_URL);

        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("serverAuthCode", serverAuthCode));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            final String responseBody = EntityUtils.toString(response.getEntity());
            Log.i(TAG, "Code: " + statusCode);
            Log.i(TAG, "Resp: " + responseBody);

            // Show Toast on UI Thread
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Form.this, responseBody, Toast.LENGTH_LONG).show();
                }
            });
            return (statusCode == 200);
        } catch (ClientProtocolException e) {
            Log.e(TAG, "Error in auth code exchange.", e);
            return false;
        } catch (IOException e) {
            Log.e(TAG, "Error in auth code exchange.", e);
            return false;
        }
    }


    private GoogleApiClient buildGoogleApiClient() {
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN);
        return builder.build();
    }



    @Override
    protected void onStart() {
        super.onStart();

        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }
        Log.e("google",mGoogleApiClient+"  "+mGoogleApiClient.isConnecting());
    }


    String _name;
    @Override
    public void onConnected(Bundle arg0) {
        Log.e(TAG, "onConnected");

        Plus.PeopleApi.loadVisible(mGoogleApiClient, null).setResultCallback(this);

        Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);

        Log.e("USERNAME_Con",Plus.AccountApi.getAccountName(mGoogleApiClient));
        Log.e("USERNAME_Con",currentUser.getBirthday()+"   "+currentUser.getImage().getUrl()+"   "+currentUser.getId());
        String personPhotoUrl=currentUser.getImage().getUrl();

        if(currentUser!=null){

            String email=Plus.AccountApi.getAccountName(mGoogleApiClient);
            String id=currentUser.getId();
             _name=currentUser.getDisplayName();
            progressDialog = ProgressDialog.show(Form.this, "", "Loading...", true,false);
            logingoogle(email,id,_name);
        }

        personPhotoUrl = personPhotoUrl.substring(0,
                personPhotoUrl.length() - 2)
                + 400;

        Log.e("USER Image",""+personPhotoUrl);
        new LoadProfileImage().execute(personPhotoUrl);

        // Indicate that the sign in process is complete.
        mSignInProgress = STATE_DEFAULT;
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }



    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.e(TAG, "onConnectionFailed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());

        if (result.getErrorCode() == ConnectionResult.API_UNAVAILABLE) {

            Log.w(TAG, "API Unavailable.");
        } else if (mSignInProgress != STATE_IN_PROGRESS) {

            mSignInIntent = result.getResolution();


            if (mSignInProgress == STATE_SIGN_IN) {
                resolveSignInError();
            }
        }

    }

    private void resolveSignInError() {
        if (mSignInIntent != null) {

            try {
                mSignInProgress = STATE_IN_PROGRESS;
                startIntentSenderForResult(mSignInIntent.getIntentSender(),
                        RC_SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                Log.i(TAG, "Sign in intent could not be sent: "
                        + e.getLocalizedMessage());
                mSignInProgress = STATE_SIGN_IN;
                mGoogleApiClient.connect();
            }
        } else {

        }
    }


    private void logingoogle(final String str_email, final String str_id, final String str_name){

        final ParseUser user = new ParseUser();
        user.setEmail(str_email);
        user.setUsername(str_email);
        user.setPassword(str_id);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException er) {
                if (er == null) {
                    if(progressDialog!=null && progressDialog.isShowing()){
                        progressDialog.dismiss();
                        Log.e("DISMISS", "DISMISS");
                    }
                    ((RelativeLayout)findViewById(R.id.loginform)).setVisibility(View.VISIBLE);
                    ((EditText)findViewById(R.id.editemail)).setVisibility(View.GONE);
                    ((EditText)findViewById(R.id.editpassword)).setVisibility(View.GONE);
                    name.setText(str_name);
                    userval=false;

                    Toast.makeText(getApplicationContext(),
                            "You are not registered user",
                            Toast.LENGTH_LONG).show();
                    ((EditText)findViewById(R.id.editName)).setText(str_name);
                } else {
                    ParseUser.logInInBackground(str_email, str_id, new LogInCallback() {
                        @Override
                        public void done(ParseUser parseUser, ParseException e) {
                            if (parseUser != null) {
                                userinformation(parseUser);

                            } else {
                                Toast.makeText(Form.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("usererror", e.getMessage());
                            }
                        }
                    });
                }
            }
        });
    }


    private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {
//        ImageView bmImage;



        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            camera=mIcon11;
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
//            bmImage.setImageBitmap(result);
//            setimage(camera);
        }
    }


    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = 100;
        int targetHeight = 100;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }

    private class getFacebookImage extends AsyncTask {
        String userID;

        public getFacebookImage(String id) {
            userID=id;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] params) {
            URL imageURL = null;
            Bitmap bitmap=null;
            try {
                imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
                bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("String Image",""+bitmap);
            camera=bitmap;
            return bitmap;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
//            setimage(camera);
            Log.e("String Image",""+o);
        }
    }

    private void facebookimage(String id) {
        new getFacebookImage(id).execute();
    }

}
