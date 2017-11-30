package com.example.yudhisthira.quandoo.model;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by yudhisthira
 */

public interface TableListClient {
    @GET("table-map.json")
    Flowable<List<Boolean>> fetchTableList();
}
