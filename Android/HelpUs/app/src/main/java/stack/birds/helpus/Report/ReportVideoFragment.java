package stack.birds.helpus.Report;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import stack.birds.helpus.R;

/**
 * Created by dsm2016 on 2017-08-25.
 */

public class ReportVideoFragment extends Fragment {
    View view;
    Context context;

    RecyclerView recycler;
    List<String> videoList;

    String path = Environment.getExternalStorageDirectory() + "/Download/Carol/ carol.mkv";

    @SuppressLint("ValidFragment")
    public ReportVideoFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.report_tab_video, container, false);

//        recycler = (RecyclerView) view.findViewById(R.id.report_video_list);
//
//        videoList = getAllMedia();
//        Log.d("video", videoList.size() + "");
//        Log.d("video_path", videoList.get(0));
//        VideoAdapter adapter = new VideoAdapter(videoList, context);
//        recycler.setAdapter(adapter);

        VideoView video = (VideoView) view.findViewById(R.id.report_video);
        video.setVideoPath(path);
        video.setMediaController(new MediaController(context));

        return view;
    }

    public List<String> getAllMedia() {
        HashSet<String> videoItemHashSet = new HashSet<>();
        String[] projection = { MediaStore.Video.VideoColumns.DATA ,MediaStore.Video.Media.DISPLAY_NAME};
        Cursor cursor = getContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        try {
            cursor.moveToFirst();
            do{
                videoItemHashSet.add((cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA))));
            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> videoList = new ArrayList<>(videoItemHashSet);
        return videoList;
    }
}

class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    private List<String> videoList;
    private List<Boolean> selectedVideo;
    private MediaController mediaController;
    private Context context;

    class VideoViewHolder extends RecyclerView.ViewHolder {
        public VideoView video;

        public VideoViewHolder(View view) {
            super(view);

            video = (VideoView) view.findViewById(R.id.report_video_view);
        }

    }
    public VideoAdapter(List<String> videoList, Context context) {
        this.videoList = videoList;
        this.context = context;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.report_tab_video_item, parent, false);

        selectedVideo = new ArrayList<Boolean>(Arrays.asList(new Boolean[videoList.size()]));
        Collections.fill(selectedVideo, Boolean.FALSE);

        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        String path = videoList.get(position);

        mediaController = new MediaController(context);

        holder.video.setVideoPath(path);
        holder.video.setMediaController(mediaController);
        holder.video.requestFocus();

        Bitmap thumnail = ThumbnailUtils.createVideoThumbnail(path,
                MediaStore.Images.Thumbnails.MINI_KIND);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(thumnail);
        holder.video.setBackgroundDrawable(bitmapDrawable);
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }
}
