package lesson6;

//Урок 6. Работа с сетью
//1. С помощью http запроса получить в виде json строки погоду в Москве на период времени,
//2. Подобрать источник самостоятельно. Можно использовать api accuweather

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Objects;

public class Main {

    // X-Yandex-API-Key: e90d3afa-a29d-4d1b-8a6b-a9206f1cdba1

    private static final String X_Yandex_API_Key = "e90d3afa-a29d-4d1b-8a6b-a9206f1cdba1";
    private static final String BASE_HOST = "api.weather.yandex.ru";
    private static final String API_VER="v2";
    private static final String PATH_SEGMENT = "forecast";
    private static final String LAT ="59.95";
    private static final String LON = "30.16";
    private static final String LIMIT = "5";
    private static final String LANG = "ru_RU";
    private static final String EXTRA = "true";

    public static void main(String[] args) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        // Формируем запрос.
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host(BASE_HOST)
                .addEncodedPathSegment(API_VER)
                .addEncodedPathSegment(PATH_SEGMENT)
                .addQueryParameter("lat", LAT).addQueryParameter("lon", LON)
                .addQueryParameter("lang", LANG).addQueryParameter("limit", LIMIT)
                .addQueryParameter("extra", EXTRA)
                .build();

        System.out.println("Выведем на экран созданный запрос:\n" + url);
        Request requestHttp = new Request.Builder()
                .addHeader("X-Yandex-API-Key", X_Yandex_API_Key)
                .url(url)
                .build();

        Response response = okHttpClient.newCall(requestHttp).execute();
        String body = Objects.requireNonNull(okHttpClient.newCall(requestHttp).execute().body()).string();

        System.out.println("Код ответа сервера: " + response.code());
        System.out.println("JSON строка:\n" + body);

    }
}
