package com.example.rest;

import com.example.StocksConfig;
import com.example.rest.http.HeaderRequestInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.alfonz.rest.http.GzipRequestInterceptor;
import org.alfonz.utility.Logcat;

import java.util.concurrent.TimeUnit;

import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitClient {
	private static volatile Retrofit sRetrofit;

	private RetrofitClient() {}

	public static Retrofit getRetrofit() {
		if (sRetrofit == null) {
			synchronized (RetrofitClient.class) {
				if (sRetrofit == null) {
					sRetrofit = buildRetrofit();
				}
			}
		}
		return sRetrofit;
	}

	public static <T> T createService(Class<T> service) {
		return getRetrofit().create(service);
	}

	private static Retrofit buildRetrofit() {
		Retrofit.Builder builder = new Retrofit.Builder();
		builder.baseUrl(StocksConfig.REST_BASE_URL);
		builder.client(buildClient());
		builder.addConverterFactory(createConverterFactory());
		builder.addCallAdapterFactory(createCallAdapterFactory());
		return builder.build();
	}

	private static OkHttpClient buildClient() {
		OkHttpClient.Builder builder = new OkHttpClient.Builder();
		builder.connectTimeout(30, TimeUnit.SECONDS);
		builder.readTimeout(30, TimeUnit.SECONDS);
		builder.writeTimeout(30, TimeUnit.SECONDS);
		builder.certificatePinner(buildCertificatePinner());
		builder.addInterceptor(new HeaderRequestInterceptor());
		builder.addInterceptor(new GzipRequestInterceptor());
		builder.addNetworkInterceptor(createLoggingInterceptor());
		return builder.build();
	}

	private static CertificatePinner buildCertificatePinner() {
		CertificatePinner.Builder builder = new CertificatePinner.Builder();
		builder.add("*.markitondemand.com", "sha256/xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx=");
		builder.add("*.markitondemand.com", "sha256/yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy=");
		builder.add("*.markitondemand.com", "sha256/zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz=");
		return builder.build();
	}

	private static Interceptor createLoggingInterceptor() {
		HttpLoggingInterceptor.Logger logger = message -> Logcat.d(message);
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(logger);
		interceptor.setLevel(StocksConfig.LOGS ? HttpLoggingInterceptor.Level.BASIC : HttpLoggingInterceptor.Level.NONE);
		return interceptor;
	}

	private static Converter.Factory createConverterFactory() {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("EEE MMM d HH:mm:ss 'UTC'zzzzz yyyy");
		Gson gson = builder.create();
		return GsonConverterFactory.create(gson);
	}

	private static CallAdapter.Factory createCallAdapterFactory() {
		return RxJava3CallAdapterFactory.create();
	}
}
