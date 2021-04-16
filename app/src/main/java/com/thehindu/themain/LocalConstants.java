package com.thehindu.themain;

import com.thehindu.themain.remote.ApiClient;
import com.thehindu.themain.remote.Network;

public class LocalConstants {
    public final static String PREF_TOKEN_VALUE = "token";
    public final static String PREF_USER_LOGIN = "loggedIn";
    public final static String PREF_TOKEN_BOOLEAN = "IsTokenAvailable";

    public final static ApiClient API_CLIENT = Network.Companion.getInstance().create(ApiClient.class);

}