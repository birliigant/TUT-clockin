package com.sipc.clockin.handler.token;


import com.sipc.clockin.pojo.model.TokenModel;

public class TokenHandler {
    // todo：这里可以写上threadlocal的逻辑
    private static final ThreadLocal<TokenModel> tokenModelThreadLocal = new ThreadLocal<>();

    public static TokenModel getTokenModelThreadLocal() {
        return tokenModelThreadLocal.get();
    }

    public static void setTokenModelThreadLocal(TokenModel payload) {
        tokenModelThreadLocal.set(payload);
    }

    public static void remove() {
        tokenModelThreadLocal.remove();
    }
}