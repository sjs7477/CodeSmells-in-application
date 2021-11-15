package com.example.utility;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.R;
import com.example.StocksConfig;

import org.alfonz.graphics.drawable.CircularDrawable;
import org.alfonz.utility.Logcat;

public final class GlideUtility {
	private GlideUtility() {}

	public static void setupRequestBuilder(GenericRequestBuilder builder, Drawable placeholder, Drawable error) {
		builder.diskCacheStrategy(DiskCacheStrategy.RESULT);

		if (builder instanceof DrawableRequestBuilder) {
			((DrawableRequestBuilder<?>) builder).crossFade();
		}

		if (StocksConfig.LOGS) {
			builder.listener(createLogRequestListener());
		}

		if (placeholder != null) {
			builder.placeholder(placeholder);
		}

		if (error != null) {
			builder.error(error);
		} else {
			builder.error(R.drawable.placeholder);
		}
	}

	public static Target<?> createCircularTarget(final ImageView imageView) {
		return new BitmapImageViewTarget(imageView) {
			@Override
			protected void setResource(Bitmap resource) {
				CircularDrawable drawable = new CircularDrawable(resource);
				imageView.setImageDrawable(drawable);
			}
		};
	}

	private static RequestListener<String, Object> createLogRequestListener() {
		return new RequestListener<String, Object>() {
			@Override
			public boolean onException(Exception exception, String model, Target target, boolean isFirstResource) {
				Logcat.d("%s / %s / isFirstResource=%s", exception, model, isFirstResource);
				exception.printStackTrace();
				return false;
			}

			@Override
			public boolean onResourceReady(Object resource, String model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
				Logcat.d("%s / isFromMemoryCache=%s / isFirstResource=%s", model, isFromMemoryCache, isFirstResource);
				return false;
			}
		};
	}
}
