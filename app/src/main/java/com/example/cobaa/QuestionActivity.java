package com.example.cobaa;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
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

import com.example.cobaa.models.QuestionMap;

import java.io.IOException;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends AppCompatActivity {
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

//    private Question mQuestions = new Question(Question.JAWA);
//    private int mqQuestionsLength = mQuestions.mQuestions.length;
    private int mScore = 0;
    private int mqQuestionsLength;
    private QuestionMap mQuestions;
    private String mAnswer;
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
        initView();
    }

    void setup() {
        Intent intent = getIntent();
        if (intent != null) {
            String daerah = intent.getStringExtra("Daerah");
            mQuestions = new QuestionMap(daerah);
            mqQuestionsLength = mQuestions.mQuestions.length;
        }
        btnStart.setVisibility(View.VISIBLE);
        btnStop.setVisibility(View.GONE);
        mediaplayer = new MediaPlayer();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mediaplayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
        } else {
            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
        playerSeekBar.setMax(100);
        r = new Random();
        tvScore.setText(mScore + " / " + mqQuestionsLength);
        updateQuestion(r.nextInt(mqQuestionsLength), true);
    }

    void initView() {
        btnAnswerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnAnswerA.getText() == mAnswer) {
                    mScore++;
                    tvScore.setText(mScore + " / " + mqQuestionsLength);
                    updateQuestion(r.nextInt(mqQuestionsLength), false);
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
                    tvScore.setText(mScore + " / " + mqQuestionsLength);
                    updateQuestion(r.nextInt(mqQuestionsLength), false);
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
                    tvScore.setText(mScore + " / " + mqQuestionsLength);
                    updateQuestion(r.nextInt(mqQuestionsLength), false);
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
                    tvScore.setText(mScore + " / " + mqQuestionsLength);
                    updateQuestion(r.nextInt(mqQuestionsLength), false);
                } else {
                    gameOver();
                }
            }
        });
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
        Log.e("Filename", filename);
        mediaplayer = new MediaPlayer();
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            mediaplayer.setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_MEDIA).setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
//        } else {
//            mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        }
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaplayer.setDataSource(QuestionActivity.this, Uri.parse("android.resource://com.example.cobaa/raw/" + filename));
            mediaplayer.prepareAsync();
            mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    btnStart.setVisibility(View.GONE);
                    btnStop.setVisibility(View.VISIBLE);
                    mediaplayer.start();
                }
            });
            mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    btnStart.setVisibility(View.VISIBLE);
                    btnStop.setVisibility(View.GONE);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(QuestionActivity.this, "Maaf tidak dapat memutar lagu", Toast.LENGTH_SHORT).show();
        }
    }

    void test() {
//        final VideoView video = (VideoView) findViewById(R.id.videoplayer);
//        final MediaController controller = new MediaController(this);
//
//        video.setVideoURI(Uri.parse(getIntent().getStringExtra("url")));
//        video.setMediaController(controller);
//        controller.setMediaPlayer(video);
//        video.setOnPreparedListener(new OnPreparedListener() {
//
//            public void onPrepared(MediaPlayer mp) {
//                int duration = video.getDuration();
//                video.requestFocus();
//                video.start();
//                controller.show();
//
//            }
//        });
    }

    private void stopAudio() {
//        mediaplayer.stop();
        if (mediaplayer.isPlaying()) {
            mediaplayer.stop();
        }
        try {
//            mediaplayer.prepare();
//            mediaplayer.seekTo(0);
            if (mediaplayer.isPlaying()) {
                mediaplayer.prepareAsync();
                mediaplayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaplayer.seekTo(0);
                    }
                });
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        btnStart.setVisibility(View.VISIBLE);
        btnStop.setVisibility(View.GONE);
    }

//    private Runnable updater = new Runnable() {
//        @Override
//        public void run() {
//            updateSeekBar();
//            long currentDuration = mediaplayer.getCurrentPosition();
//            tvCurrentTime.setText(milliSecondsToTimer(currentDuration));
//        }
//    };

//    private void updateSeekBar() {
//        if (mediaplayer.isPlaying()) {
//            playerSeekBar.setProgress((int) (((float) mediaplayer.getCurrentPosition() / mediaplayer.getDuration()) * 100));
//            handler.postDelayed(updater, 1000);
//        }
//    }

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

    private void updateQuestion(int num, Boolean isFirst) {
        tvQuestion.setText(mQuestions.getQuestion(num));
        btnAnswerA.setText(mQuestions.getchoice1(num));
        btnAnswerB.setText(mQuestions.getchoice2(num));
        btnAnswerC.setText(mQuestions.getchoice3(num));
        btnAnswerD.setText(mQuestions.getchoice4(num));
        mAnswer = mQuestions.getCorrectAnswer(num);
        filename = mQuestions.getFileName(num);
//        if(!isFirst){
        stopAudio();
//        }
//        initAudio();
    }


    public void gameOver() {
        stopAudio();
        final Dialog dialog = new Dialog(QuestionActivity.this);
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
                Intent intent = new Intent(QuestionActivity.this, MapActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        dialog.show();

    }
}
