package com.elab.elabo.Activities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.elab.elabo.Application.BodyApplication
import com.elab.elabo.Models.ExercisesModel
import com.elab.elabo.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutubeVidActivity : YouTubeBaseActivity() {

    private lateinit var exercisesModel: ExercisesModel
    private lateinit var backBtn: AppCompatImageView
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var titleTxt: AppCompatTextView
    private lateinit var player: YouTubePlayer
    private lateinit var playBtn: AppCompatImageView
    private var videoFound = false
    private lateinit var expandableOne: AppCompatTextView
    private lateinit var expandableTwo:AppCompatTextView
    private lateinit var expandableThree:AppCompatTextView
    private  lateinit var expandableChildOne:AppCompatTextView
    private  lateinit var expandableChildTwo:AppCompatTextView
    private  lateinit var expandableChildThree:AppCompatTextView

    private var isExpandedOne = false
    private var isExpandedTwo = false
    private var isExpandedThree = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_video)

        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        exercisesModel = BodyApplication.getExercisesModel()
        expandableOne = findViewById(R.id.expandable_one)
        expandableTwo = findViewById(R.id.expandable_two)
        expandableThree = findViewById(R.id.expandable_three)
        expandableChildOne = findViewById(R.id.expandable_child_one)
        expandableChildTwo = findViewById(R.id.expandable_child_two)
        expandableChildThree = findViewById(R.id.expandable_child_three)
        backBtn = findViewById(R.id.back)
        playBtn = findViewById(R.id.play)
        titleTxt = findViewById(R.id.textView)

        titleTxt.setText(exercisesModel.getTitle())
        expandableChildOne.setText(exercisesModel.getDescription())
        expandableChildTwo.setText(exercisesModel.getMuscles())
        expandableChildThree.setText(exercisesModel.getExecution())

        expandableOne.setOnClickListener(View.OnClickListener {
            if (!isExpandedOne) {
                val img = resources.getDrawable(R.drawable.ic_arr_dwn)
                img.setBounds(0, 0, 60, 60)
                expandableOne.setCompoundDrawables(null, null, img, null)
                isExpandedOne = true
                expandableChildOne.setVisibility(View.VISIBLE)
            } else {
                val img = resources.getDrawable(R.drawable.ic_arr_up)
                img.setBounds(0, 0, 60, 60)
                expandableOne.setCompoundDrawables(null, null, img, null)
                isExpandedOne = false
                expandableChildOne.setVisibility(View.GONE)
            }
        })
        expandableTwo.setOnClickListener(View.OnClickListener {
            if (!isExpandedTwo) {
                val img = resources.getDrawable(R.drawable.ic_arr_dwn)
                img.setBounds(0, 0, 60, 60)
                expandableTwo.setCompoundDrawables(null, null, img, null)
                isExpandedTwo = true
                expandableChildTwo.setVisibility(View.VISIBLE)
            } else {
                val img = resources.getDrawable(R.drawable.ic_arr_up)
                img.setBounds(0, 0, 60, 60)
                expandableTwo.setCompoundDrawables(null, null, img, null)
                isExpandedTwo = false
                expandableChildTwo.setVisibility(View.GONE)
            }
        })
        expandableThree.setOnClickListener(View.OnClickListener {
            if (!isExpandedThree) {
                val img = resources.getDrawable(R.drawable.ic_arr_dwn)
                img.setBounds(0, 0, 60, 60)
                expandableThree.setCompoundDrawables(null, null, img, null)
                isExpandedThree = true
                expandableChildThree.setVisibility(View.VISIBLE)
            } else {
                val img = resources.getDrawable(R.drawable.ic_arr_up)
                img.setBounds(0, 0, 60, 60)
                expandableThree.setCompoundDrawables(null, null, img, null)
                isExpandedThree = false
                expandableChildThree.setVisibility(View.GONE)
            }
        })
        youTubePlayerView = findViewById(R.id.youtube_player_view)
        val listener: YouTubePlayer.OnInitializedListener =
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean
                ) {
                    player = youTubePlayer
                    if (!TextUtils.isEmpty(exercisesModel.getVideo_id())) {
                        videoFound = true
                        player.loadVideo(exercisesModel.getVideo_id())
                        playBtn.setVisibility(View.VISIBLE)
                    } else {
                        videoFound = false
                        playBtn.setVisibility(View.GONE)
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    playBtn.setVisibility(View.GONE)
                    videoFound = false
                    println("Failed: $youTubeInitializationResult")
                    Toast.makeText(
                        this@YoutubeVidActivity,
                        "Failed: $youTubeInitializationResult",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        youTubePlayerView.initialize(resources.getString(R.string.youtube_api), listener)
        playBtn.setOnClickListener(View.OnClickListener {
            if (player != null) {
                if (videoFound) {
                    playBtn.setVisibility(View.GONE)
                    player.play()
                } else {
                    Toast.makeText(
                        this@YoutubeVidActivity,
                        "No video found for this exercise",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
        backBtn.setOnClickListener(View.OnClickListener {
            val backIntent = Intent(this@YoutubeVidActivity, ExercisesActivity::class.java)
            backIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(backIntent)
        })
    }
}