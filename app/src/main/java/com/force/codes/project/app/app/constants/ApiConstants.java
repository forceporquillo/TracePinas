/*
 * Created by Force Porquillo on 6/5/20 3:28 PM
 * FEU Institute of Technology
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 7/12/20 2:22 AM
 */

package com.force.codes.project.app.app.constants;

public class ApiConstants{
    private static final String TWITTER_USER_ENDPOINT = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=";
    private static final String TWEET_MODE_EXTEND = "&count=25&tweet_mode=extended";
    private static final String CORONA_LMAO_NINJA_V_2 = "https://corona.lmao.ninja/v2/";
    private static final String[] url =
            new String[]{
                    "DOHGOVPH",
                    "rapplerdotcom",
                    "ABSCBNNews"
            };

    // region endpoint URLS
    public static final String URL_PATH = "https://corona.lmao.ninja/v2/";
    public static final String LOCAL_URL = "https://coronavirus-ph-api.herokuapp.com/doh-data-drop";
    public static final String GLOBAL_CASE = "https://covid19.mathdro.id/api/confirmed";
    public static final String NEWS_DATA = "https://newsapi.org/v2/everything?q=COVID, Philippines&from=2020&sortBy=publishedAt&apiKey=0bf6fe738d0f4279a5c1169f11fe4c3f&pageSize=100&page=1";
    public static final String TWITTER_BEARER_TOKEN = "AAAAAAAAAAAAAAAAAAAAAA5SDwEAAAAAnF1q%2BmhNSAysxKDenHX4De2H7x0%3DyUJnEvO0oet43pc2mWNGTlNFyF6kXOeprKwF2wx5RRpixV3WBu";
    //endregion

    ApiConstants(){
        // no instance itself
    }

    // custom endpoint
    public static String getTwitterEndpoint(String screenName){
        return TWITTER_USER_ENDPOINT + screenName;
    }

    public static String getUserTimeline(int timelineIndex){
        return url[timelineIndex].concat(TWEET_MODE_EXTEND);
    }

    public static String[] getUrl(){
        return url;
    }

    public static String getBaseUrlPath(String endpoint){
        return CORONA_LMAO_NINJA_V_2 + endpoint;
    }
}
