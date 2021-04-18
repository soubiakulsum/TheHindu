package com.thehindu.themain;

import com.thehindu.themain.remote.ApiClient;
import com.thehindu.themain.remote.Network;

public class LocalConstants {
    public final static String PREF_TOKEN_VALUE = "token";
    public final static String PREF_USER_LOGIN = "loggedIn";
    public final static String PREF_TOKEN_BOOLEAN = "IsTokenAvailable";


    public final static ApiClient API_CLIENT = Network.Companion.getInstance().create(ApiClient.class);

    public static String getGoodString(String str) {
        String n = str;
        int m = str.length() - 1;
        m = m / 3;
        n = str.substring(0, m).concat("\n").concat('\n' + '\n' + str.substring(m));
        return n;
    }

}