package kr.ac.kpu.game.s2016180003.pairgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] buttonIds={
            R.id.card_00, R.id.card_01, R.id.card_02, R.id.card_03,
            R.id.card_10, R.id.card_11, R.id.card_12, R.id.card_13,
            R.id.card_20, R.id.card_21, R.id.card_22, R.id.card_23,
            R.id.card_30, R.id.card_31, R.id.card_32, R.id.card_33,
            R.id.card_40, R.id.card_41, R.id.card_42, R.id.card_43,
    };

    private int[] cards={
      R.mipmap.turtle_1, R.mipmap.turtle_1, R.mipmap.turtle_2, R.mipmap.turtle_2,
      R.mipmap.turtle_3, R.mipmap.turtle_3, R.mipmap.turtle_4, R.mipmap.turtle_4,
      R.mipmap.turtle_5, R.mipmap.turtle_5, R.mipmap.turtle_6, R.mipmap.turtle_6,
      R.mipmap.turtle_7, R.mipmap.turtle_7, R.mipmap.turtle_8, R.mipmap.turtle_8,
      R.mipmap.turtle_9, R.mipmap.turtle_9, R.mipmap.turtle_10, R.mipmap.turtle_10,
    };

    private ImageButton preButton;

    private int VisibleCardCount;
    private TextView scoreTextView;


    private int score;

    public void setScore(int score) {
        this.score = score;
        scoreTextView.setText("Flips :"+score);
    }

    private ImageButton prevButton;
    private int visibleCardCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);

        startGame();
    }

    public void onBtnCard(View view) {
        if(view == prevButton){
            int color = getResources().getColor(R.color.purple_700);
            scoreTextView.setTextColor(color);
            return;
        }

        int color = getResources().getColor(R.color.gray);

        int prevCard = 0;
        if(prevButton != null){
            prevButton.setImageResource(R.mipmap.card_back_stone);
            prevCard = (Integer) prevButton.getTag();
        }

        int buttonIndex = getButtonIndex(view.getId());
        Log.d(TAG, "OnBtnCard() has been called, ID=" + view.getId() + "buttonIndex=" + buttonIndex);

        int card = cards[buttonIndex];
        ImageButton imageButton = (ImageButton)view;
        imageButton.setImageResource(card);

        if(card == prevCard) {
            imageButton.setVisibility(View.INVISIBLE);
            prevButton.setVisibility(View.INVISIBLE);
            prevButton = null;
            visibleCardCount -= 2;
            if (visibleCardCount == 0){
                askRestart();
            }
            return;
        }
        if(prevButton != null) {
            setScore((score + 1));
        }
        prevButton = imageButton;
    }



    private int getButtonIndex(int resId) {
        for (int i = 0; i < buttonIds.length; i++) {
            if (buttonIds[i] == resId) {
                return i;
            }
        }
        return -1;
    }

    public void OnBtnRestart(View view) {
        askRestart();
    }

    private void askRestart() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.restart_dialog_title);
        builder.setMessage(R.string.restart_dialog_message);
        builder.setPositiveButton(R.string.common_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startGame();
            }
        });
        builder.setNegativeButton(R.string.common_no,null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void startGame() {
        Random random = new Random();
        for (int i = 0; i < cards.length; i++) {
            int ri = random.nextInt(cards.length);
            int t = cards[i];
            cards[i] = cards[ri];
            cards[ri] = t;
        }

        for (int i = 0; i < buttonIds.length; i++) {
            ImageButton b = findViewById(buttonIds[i]);
            b.setTag(cards[i]);
            b.setVisibility(View.VISIBLE);
            b.setImageResource(R.mipmap.card_back_stone);
        }

        prevButton = null;
        visibleCardCount = cards.length;

        setScore(0);
    }
}

