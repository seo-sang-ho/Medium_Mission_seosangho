package com.ll.medium.global.rsData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@RequiredArgsConstructor
@Getter
public class RsData<T> {
    private final String resultCode;
    private final String msg;
    private T data;
    private int statusCode;

    public static <T> RsData<T> of(String resultCode, String msg, T data) {
        int statusCode = Integer.parseInt(resultCode);

        return new RsData<>(resultCode,msg,data,statusCode);
    }

}
