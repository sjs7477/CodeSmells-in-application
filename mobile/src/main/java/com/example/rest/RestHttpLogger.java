package com.example.rest;

import androidx.annotation.NonNull;

import org.alfonz.rest.HttpLogger;
import org.alfonz.utility.Logcat;

public class RestHttpLogger implements HttpLogger {
	@Override
	public void logSuccess(@NonNull String message) {
		Logcat.d(message);
	}

	@Override
	public void logError(@NonNull String message) {
		Logcat.d(message);
	}

	@Override
	public void logFail(@NonNull String message) {
		Logcat.d(message);
	}
}
