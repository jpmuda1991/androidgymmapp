package com.elab.elabo.Activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.elab.elabo.R

import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import com.elab.elabo.Application.BodyApplication
import com.elab.elabo.Models.ExercisesModel
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import java.lang.Exception
import android.util.SparseArray
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


class ExploreExrActivity : AppCompatActivity() {

    lateinit var backImg : AppCompatImageView
    lateinit var modelExercises : ExercisesModel
    lateinit var youTubePlayerView : YouTubePlayerView

    private var exoPlayer: SimpleExoPlayer? = null


    private var url :String = "https://www.youtube.com/watch?v=kJ32PRK3LRo"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_exr)

        createExoPlayer()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        modelExercises = BodyApplication.getExercisesModel()
        backImg = findViewById(R.id.back) as AppCompatImageView
        youTubePlayerView = findViewById(R.id.youtube_player_view) as YouTubePlayerView


        backImg.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                val intent = Intent(this@ExploreExrActivity, ExercisesActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        })


//

    }


    private fun createExoPlayer() {
        // Video Playing Exo Player Related //
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val trackSelector: TrackSelector =
            DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
        exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
    }


    private fun playVideo(video_uri: String, exoPlayerView: SimpleExoPlayerView) {
        val uri = Uri.parse(video_uri)
        try {
            val httpDataSourceFactory = DefaultHttpDataSourceFactory("exo_player")
            val extractorsFactory: ExtractorsFactory = DefaultExtractorsFactory()
            val videoSource: MediaSource =
                ExtractorMediaSource(uri, httpDataSourceFactory, extractorsFactory, null,
                    { error ->

                        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                        Log.e("EXO_LOADERROR = ", "" + error.message)
                    })
            if (exoPlayer != null) {
                exoPlayerView.player = exoPlayer
                exoPlayer!!.prepare(videoSource)
                exoPlayer!!.setPlayWhenReady(true)
            }
        } catch (e: Exception) {
            Log.e("EXCEPTION = ", "" + e.message)
        }
    }

}