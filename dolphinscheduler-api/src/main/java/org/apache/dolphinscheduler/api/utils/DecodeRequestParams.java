package org.apache.dolphinscheduler.api.utils;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: fuyz
 * @date: 2020/11/28 14:19
 * @description:
 */
public  class DecodeRequestParams {
    final static Base64.Decoder decoder = Base64.getDecoder();

    public static String getParams(HttpServletRequest request, String key) throws UnsupportedEncodingException {
        String decodedParam = new String(decoder.decode(request.getHeader("params")), "UTF-8");
        Map<String, String> mapTR = Stream.of(decodedParam.split("&")).map(e -> e.split(":")).filter(e -> e.length == 2).collect(Collectors.toMap(e -> e[0], e -> e[1]));
        return mapTR.get(key);
    }
}
