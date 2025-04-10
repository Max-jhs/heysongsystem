package com.heysong.util;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应工具类
 */
public class ResponseUtils {
    public static void writer(HttpServletResponse response, String json) {
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
