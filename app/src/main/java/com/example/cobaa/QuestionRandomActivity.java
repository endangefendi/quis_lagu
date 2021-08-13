package com.example.cobaa;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cobaa.models.QuestionRandom;

import java.io.IOException;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionRandomActivity extends AppCompatActivity {
    @BindView(R.id.tvScore)
    TextView tvScore;
    @BindView(R.id.btnStart)
    ImageView btnStart;
    @BindView(R.id.btnStop)
    ImageView btnStop;
    @BindView(R.id.playerSeekBar)
    SeekBar playerSeekBar;
    @BindView(R.id.tvQuestion)
    TextView tvQuestion;
    @BindView(R.id.btnAnswerA)
    Button btnAnswerA;
    @BindView(R.id.btnAnswerB)
    Button btnAnswerB;
    @BindView(R.id.btnAnswerC)
    Button btnAnswerC;
    @BindView(R.id.btnAnswerD)
    Button btnAnswerD;

    private QuestionRandom mQuestions = new QuestionRandom();
    private String mAnswer;
    private int mScore = 0;
    private int mqQuestionsLength = mQuestions.mQuestions.length;
    private MediaPlayer mediaplayer;
    private Handler handler = new Handler();
    Random r;
    String filename = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        setup();
        btnAnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnAnswerA.getText() == mAnswer) {
                    mScore++;
                    tvScore.setText(mScore + " / 20");
                    updateQuestion(r.nextInt(mqQuestionsLength));
                } else {
                    gameOver();
                }
            }
        });

        btnAnswerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnAnswerB.getText() == mAnswer) {
                    mScore++;
                    tvScore.setText(mScore + " / 20");
                    updateQuestion(r.nextInt(mqQuestionsLength));
                } else {
                    gameOver();
                }
            }
        });

        btnAnswerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnAnswerC.getText() == mAnswer) {
                    mScore++;
                    tvScore.setText(mScore + " / 20");
                    updateQuestion(r.nextInt(mqQuestionsLength));
                } else {
                    gameOver();
                }
            }
        });

        btnAnswerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnAnswerD.getText() == mAnswer) {
                    mScore++;
                    tvScore.setText(mScore + " / 20");
                    updateQuestion(r.nextInt(mqQuestionsLength));
                } else {
                    gameOver();
                }
            }
        });
    }

    void setup() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        btnStart.setVisibility(View.VISIBLE);
        btnStop.setVisibility(View.GONE);
        mediaplayer = new MediaPlayer();
        playerSeekBar.setMax(100);
        r = new Random();
        tvScore.setText(mScore + " / 20");
        updateQuestion(r.nextInt(mqQuestionsLength));
    }

    @OnClick({R.id.btnStart, R.id.btnStop})
    void onClick(View v) {
        if (v == btnStart) {
            playAudio(filename);
        } else if (v == btnStop) {
            stopAudio();
        }
    }

    private void playAudio(String filename) {
        Log.e("Song", filename);
        mediaplayer = new MediaPlayer();
        try {
            mediaplayer.setDataSource(QuestionRandomActivity.this, Uri.parse("android.resource://com.example.cobaa/raw/" + filename));
            btnStart.setVisibility(View.GONE);
            btnStop.setVisibility(View.VISIBLE);
            try {
                mediaplayer.prepare();
            } catch (IllegalStateException ex) {
                ex.printStackTrace();
            } catch (IOException ex1) {
                ex1.printStackTrace();
            }
            mediaplayer.start();
            mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    btnStart.setVisibility(View.VISIBLE);
                    btnStop.setVisibility(View.GONE);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(QuestionRandomActivity.this, "Maaf tidak dapat memutar lagu", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopAudio() {
        mediaplayer.stop();
        try {
            mediaplayer.prepare();
            mediaplayer.seekTo(0);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        btnStart.setVisibility(View.VISIBLE);
        btnStop.setVisibility(View.GONE);
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaplayer.getCurrentPosition();
        }
    };

    private void updateSeekBar() {
        if (mediaplayer.isPlaying()) {
            playerSeekBar.setProgress((int) (((float) mediaplayer.getCurrentPosition() / mediaplayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    private String milliSecondsToTimer(long milliSeconds) {
        String timerString = "";
        String secondString;

        int hours = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timerString = hours + ":";
        }
        if (seconds < 10) {
            secondString = "0" + seconds;
        } else {
            secondString = "" + seconds;
        }
        timerString = timerString + minutes + ":" + secondString;
        return timerString;
    }

    private void updateQuestion(int num) {
        tvQuestion.setText(mQuestions.getQuestion(num));
        btnAnswerA.setText(mQuestions.getchoice1(num));
        btnAnswerB.setText(mQuestions.getchoice2(num));
        btnAnswerC.setText(mQuestions.getchoice3(num));
        btnAnswerD.setText(mQuestions.getchoice4(num));
        mAnswer = mQuestions.getCorrectAnswer(num);
        filename = mQuestions.getFileName(num);
        stopAudio();
    }


    public void gameOver() {
        stopAudio();
        final Dialog dialog = new Dialog(QuestionRandomActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_info);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        TextView tvInfo = (TextView) dialog.findViewById(R.id.tvInfo);
        Button btnExit = (Button) dialog.findViewById(R.id.btnExit);
        Button btnNewGame = (Button) dialog.findViewById(R.id.btnNewGame);

        tvInfo.setText("Game over !, Your score is " + mScore + " points.");
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
                System.exit(0);
            }
        });
        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(QuestionRandomActivity.this, StartGameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        dialog.show();

    }
}