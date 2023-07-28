package code.iamstcom.tictactoe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    public static final String EXTRA_CIRCULAR_REVEAL_X = "EXTRA_CIRCULAR_REVEAL_X";

    public static final String EXTRA_CIRCULAR_REVEAL_Y = "EXTRA_CIRCULAR_REVEAL_Y";
    View rootLayout;

    private int revealX;
    private static final String PREFS_NAME = "vibration";
    private static final String PREF_VIBRATION = "TicVib";
    private int revealY;
    private   boolean Vibration ;
    private boolean isChecked;
    private String[] Randomfirst;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Vibration = preferences.getBoolean(PREF_VIBRATION, true);

        setContentView(R.layout.activity_settings);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        final Intent intent = getIntent();



        Switch swit = findViewById(R.id.swith2);
        swit.setChecked(Vibration);
        swit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Vibration){
                    isChecked=false;
                    SharedPreferences.Editor editor=getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(PREF_VIBRATION, isChecked);
                    editor.apply();
                }else {
                    isChecked=true;

                    SharedPreferences.Editor editor=getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putBoolean(PREF_VIBRATION, isChecked);
                    editor.apply();
                }
             }
        });


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int savedValue = sharedPreferences.getInt("key", 0);





       Spinner spinner2 = (Spinner) findViewById(R.id.spinner);

        if (savedValue == 1)
            Randomfirst = new String[]{"Easy" ,"Random" , "Medium" , "Hard","Impossible"};
        if (savedValue == 2)
            Randomfirst = new String[]{"Medium" ,"Random" ,"Easy" ,  "Hard","Impossible"};
        if (savedValue == 3)
            Randomfirst = new String[]{"Hard","Random" ,"Easy" , "Medium" , "Impossible"};
        if (savedValue == 4)
            Randomfirst = new String[]{"Impossible","Random" ,"Easy" , "Medium" , "Hard"};
        if (savedValue == 5)
            Randomfirst = new String[]{"Random" ,"Easy" , "Medium" , "Hard","Impossible"};
        if (savedValue == 0)
            Randomfirst = new String[]{"Random" ,"Easy" , "Medium" , "Hard","Impossible"};

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, Randomfirst);


        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);



        List<String> statusCheck = new ArrayList<String>();
        statusCheck = Arrays.asList("Easy");
        spinner2.setSelection(statusCheck.indexOf(1));





        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (selectedItem.equals("Random")) {
                    editor.putInt("key", 5);
                }

                if (selectedItem.equals("Easy")) {
                    editor.putInt("key", 1);
                }
                if (selectedItem.equals("Medium")) {
                    editor.putInt("key", 2);
                }
                if (selectedItem.equals("Hard")) {
                    editor.putInt("key", 3);
                }
                if (selectedItem.equals("Impossible")) {
                    editor.putInt("key", 4);
                }
                editor.commit();
            }


            // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
RelativeLayout r6 = findViewById(R.id.r6);
r6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rating();
            }
        });

        RelativeLayout r5 = findViewById(R.id.r5);
        r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "App doesn't have ads", Toast.LENGTH_SHORT).show();
            }
        });

        RelativeLayout r7 = findViewById(R.id.r7);
        r7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedbacks();
            }
        });



        rootLayout = findViewById(R.id.rootlay);

        if (savedInstanceState == null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                intent.hasExtra(
                        EXTRA_CIRCULAR_REVEAL_X) &&
                intent.hasExtra(
                        EXTRA_CIRCULAR_REVEAL_Y)) {

            rootLayout.setVisibility(View.INVISIBLE);

            revealX = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_X, 0);

            revealY = intent.getIntExtra(EXTRA_CIRCULAR_REVEAL_Y, 0);
            ViewTreeObserver viewTreeObserver =
                    rootLayout.getViewTreeObserver();

            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(
                        new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override

                            public void onGlobalLayout() {
                                revealActivity(
                                        revealX, revealY);

                                rootLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        });
            }
        }
        else {

            rootLayout.setVisibility(View.VISIBLE);
        }
    }

    protected void revealActivity(int x, int y) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);

// create the animator for this view (the start radius is zero)

            Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, x, y, 0, finalRadius);
            circularReveal.setDuration(400);
            circularReveal.setInterpolator(
                    new AccelerateInterpolator());

// make the view visible and start the animation

            rootLayout.setVisibility(View.VISIBLE);
            circularReveal.start();
        }
        else {
            finish();
        }
    }

    protected void unRevealActivity() {



            float finalRadius = (float) (Math.max(rootLayout.getWidth(), rootLayout.getHeight()) * 1.1);
            Animator circularReveal = ViewAnimationUtils.
                    createCircularReveal(

                            rootLayout, revealX, revealY, finalRadius, 0);
            circularReveal.setDuration(400);
            circularReveal.addListener(
                    new AnimatorListenerAdapter() {
                        @Override

                        public void onAnimationEnd(Animator animation) {

                            rootLayout.setVisibility(View.INVISIBLE);

                             //finish Activity.
                            finish();

                        }
                    });
            circularReveal.start();

    }

    private void rating(){
        Uri uri = Uri.parse("market://details?id=" + getApplicationContext().getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName())));
        }
    }

    private void feedbacks(){
        Intent intent=new Intent(Intent.ACTION_SEND);
        String[] recipients={"yusronwirawan@yahoo.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Tic Tac Toe Feedbacks");
        intent.putExtra(Intent.EXTRA_CC,"yusronwirawan@yahoo.com");
        intent.setType("text/html");
        intent.setPackage("com.google.android.gm");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    @Override
    public void onBackPressed()
    {
     unRevealActivity();
    }
}
