package com.pablojuice.musicapp.utills;

import static com.pablojuice.musicapp.utills.Constants.STANDARD_CHARSET;

import android.app.Activity;

import com.google.gson.Gson;
import com.pablojuice.musicapp.model.MusicItem;
import com.pablojuice.musicapp.model.MusicItemDto;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MusicUtil {
    public static List<MusicItem> getVideosFromJson(String res) {
        return new ArrayList<>(new Gson().fromJson(res,
                                                   MusicItemDto.class).getData());
    }

    public static String getJsonFromAsset(Activity activity, String filename) {
        String json;
        try {
            InputStream is = activity.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, STANDARD_CHARSET);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
