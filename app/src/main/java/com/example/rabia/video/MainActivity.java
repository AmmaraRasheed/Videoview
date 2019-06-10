package com.example.rabia.video;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Arrays;
import java.util.List;

import at.huber.youtubeExtractor.YouTubeUriExtractor;
import at.huber.youtubeExtractor.YtFile;

public class MainActivity extends AppCompatActivity {
    String NewLink;
    String downloadUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ShowVideo(final View view){

        EditText link=findViewById(R.id.link);
        String link1=link.getText().toString();
            final YouTubeUriExtractor youTubeUriExtractor=new YouTubeUriExtractor(MainActivity.this) {
                @Override
                public void onUrisAvailable(String videoId, String videoTitle, SparseArray<YtFile> ytFiles) {
                    if(ytFiles!=null){

//                        int itag=18;
                        List<Integer> iTags = Arrays.asList(22, 137, 18);
                        for (Integer iTag : iTags) {

                            YtFile ytFile = ytFiles.get(iTag);

                            if (ytFile != null) {

                                downloadUrl = ytFile.getUrl();

                            }

                        }

//                        NewLink=ytFiles.get(itag).getUrl();
                        VideoView videoView=findViewById(R.id.videoView);
                        MediaController mediaController=new MediaController(getApplicationContext());
                        mediaController.setAnchorView(videoView);
                        videoView.requestFocus();
                        videoView.setVideoURI(Uri.parse(downloadUrl));
                        videoView.start();
                    }
                }
            };
            youTubeUriExtractor.execute(link1);

    }

}
