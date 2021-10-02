package com.pablojuice.musicapp.utills;

import static com.pablojuice.musicapp.utills.Constants.STANDARD_CHARSET;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.pablojuice.musicapp.R;
import com.pablojuice.musicapp.model.MusicItemDto;

import java.io.IOException;
import java.io.InputStream;

public class MusicUtil {
    public static MusicItemDto getMusicDtoFromJson(String res) {
        return new Gson().fromJson(res,
                                   MusicItemDto.class);
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

    public static void loadImageFromLink(String imageSrc,
                                         ImageView imageView, Context context) {
        imageView.post(() -> GlideApp.with(context)
                .load(imageSrc)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e,
                                                Object model,
                                                Target<Drawable> target,
                                                boolean isFirstResource) {
                        imageView.setImageDrawable(ResourcesCompat.getDrawable(context.getResources(),
                                                                               R.drawable.sync_error_icon,
                                                                               null));
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource,
                                                   Object model,
                                                   Target<Drawable> target,
                                                   DataSource dataSource,
                                                   boolean isFirstResource) {
                        return false;
                    }
                }).into(imageView));
    }
}
