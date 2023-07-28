package code.iamstcom.tictactoe;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Timer;
import java.util.TimerTask;

public class ChooseActivity extends AppCompatActivity {

    CharSequence player1 = "Player 1";
    CharSequence player2 = "Player 2";
    public boolean selectedsingleplayer;
    private AdView mAdView;

    boolean player1ax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


        CharSequence[] players = getIntent().getCharSequenceArrayExtra("playersnames");
        player1 = players[0];
        player2 = players[1];

        TextView textView = findViewById(R.id.text3);

        selectedsingleplayer = getIntent().getBooleanExtra("selectedsingleplayer", true);
        if (!selectedsingleplayer){
            textView.setText(player1 +" pick your side");



        }


        final ImageView imageView = findViewById(R.id.imageView);
        final ImageView imageView2 = findViewById(R.id.imageView2);
        imageView.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));
        imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));
        final RadioButton r1 = findViewById(R.id.player1o);
        final RadioButton r2 = findViewById(R.id.player1x);
        final int textColor = Color.parseColor("#e5e9ea");
        final int textColorBlue = Color.parseColor("#3b7df8");
        r1.post(new Runnable() {
            @Override
            public void run() {
                if (r1.isChecked()) {
                    r1.setButtonTintList(ColorStateList.valueOf(textColorBlue));


                } else {
                    r1.setButtonTintList(ColorStateList.valueOf(textColor));
                }
                r1.postDelayed(this, 10);
            }
        });

        r2.post(new Runnable() {
            @Override
            public void run() {
                if (r2.isChecked()) {
                    r2.setButtonTintList(ColorStateList.valueOf(textColorBlue));
                } else {
                    r2.setButtonTintList(ColorStateList.valueOf(textColor));

                }
                r2.postDelayed(this, 10);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2.setChecked(false);
                r1.setChecked(true);

                imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));
                player1ax = false;
                imageView2.setImageResource(R.drawable.oo);
                imageView.setImageResource(R.drawable.xxsh);
                imageView.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));


            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setChecked(false);
                r2.setChecked(true);
                player1ax = true;
                imageView.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));
                imageView2.setImageResource(R.drawable.ooh);
                imageView.setImageResource(R.drawable.xxs);
                imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));

            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r2.setChecked(false);
                player1ax = false;
                imageView2.setImageResource(R.drawable.oo);
                imageView.setImageResource(R.drawable.xxsh);
                imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));
                imageView.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));


            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r1.setChecked(false);
                player1ax = true;
                imageView2.setImageResource(R.drawable.ooh);
                imageView.setImageResource(R.drawable.xxs);
                imageView.setColorFilter(getApplicationContext().getResources().getColor(R.color.transparent));
                imageView2.setColorFilter(getApplicationContext().getResources().getColor(R.color.tint2));

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {


                if (r1.isChecked() || r2.isChecked()) {

                    Button ds = findViewById(R.id.button);
                    ds.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CharSequence[] players1 = getIntent().getCharSequenceArrayExtra("playersnames");
                            player1 = players1[0];
                            player2 = players1[1];


                            Intent i = new Intent(ChooseActivity.this, SceneActivity.class);
                            CharSequence[] players = {player1, player2};
                            i.putExtra("playersnames", players);
                            i.putExtra("player1ax", player1ax);
                            i.putExtra("selectedsingleplayer", selectedsingleplayer);
                            startActivity(i);
                        }
                    });
                }
            }


        }, 0, 20);//put here time 1000 milliseconds = 1 second


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ChooseActivity.this, NameActivity.class);
        startActivity(intent);
    }
}
