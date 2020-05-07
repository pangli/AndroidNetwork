package com.zorro.networking.common;

/**
 * Package:   com.zorro.networking.common
 * ClassName: Method
 * Created by Zorro on 2020/5/6 19:16
 * 备注： 请求方式
 */
public interface Method {
    int GET = 0;
    int POST = 1;
    int PUT = 2;
    int DELETE = 3;
    int HEAD = 4;
    int PATCH = 5;
    int OPTIONS = 6;
}
