package com.toec.market.repair.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;

public class HttpUtil {

    public static final int MaxLogBodyLength = 1024 * 4;

    public static byte[] getRequestBody(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        String transferEncoding = request.getHeader("Transfer-Encoding");
        if (contentLength >= 0) {
            return getRequestBodyByContentLength(request);
        } else if (transferEncoding != null && transferEncoding.trim().equals("chunked")) {
            return getRequestBodyByChunk(request);
        }

        return (new byte[0]);
    }

    private static byte[] getRequestBodyByContentLength(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int contentLength = request.getContentLength();
        if (contentLength > 0) {
            int sum = 0;
            byte[] buffer = new byte[contentLength];
            while (sum < contentLength) {
                int count = request.getInputStream().read(buffer);
                if (count > 0) {
                    outputStream.write(buffer, 0, count);
                    sum += count;
                } else if (count < 0) {
                    outputStream.close();
                   /* throw new IOException(String.format("getRequestBody read return %s. contentLength:%s sum:%s ",
                            count, contentLength, sum));*/
                }
            }
        }

        outputStream.flush();
        byte[] outputBuffer = outputStream.toByteArray();
        outputStream.close();
        return outputBuffer;
    }

    private static byte[] getRequestBodyByChunk(HttpServletRequest request) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String transferEncoding = request.getHeader("Transfer-Encoding");
        if (transferEncoding != null && transferEncoding.trim().equals("chunked")) {
            byte[] buffer = new byte[1024 * 64];
            int count;
            while ((count = request.getInputStream().read(buffer)) >= 0) {
                outputStream.write(buffer, 0, count);
            }
        }

        outputStream.flush();
        byte[] outputBuffer = outputStream.toByteArray();
        outputStream.close();
        return outputBuffer;
    }

    public static String getRequestString(HttpServletRequest request) {
        return getRequestString(request, null);
    }

    public static String getRequestString(HttpServletRequest request, String body) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %s\n", request.getMethod(), request.getRequestURI(), request.getProtocol()));
        for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements(); ) {
            String headName = e.nextElement();
            String headValue = request.getHeader(headName);
            sb.append(String.format("%s: %s\n", headName, headValue));
        }

        if (body != null && body.length() > 0) {
            sb.append("\n");
            sb.append(getLogBody(body));
        }

        return sb.toString();
    }

    public static String getResponseString(HttpServletResponse response) {
        return getResponseString(response, null);
    }

    public static String getResponseString(HttpServletResponse response, String body) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s %s\n", "HTTP/1.1", response.getStatus(), "XXXXX"));
        for (String name : response.getHeaderNames()) {
            String value = response.getHeader(name);
            sb.append(String.format("%s: %s\n", name, value));
        }

        if (body != null && body.length() > 0) {
            sb.append("\n");
            sb.append(getLogBody(body));
        }

        return sb.toString();
    }

    public static String getLogBody(String body) {
        if (body.length() <= MaxLogBodyLength) {
            return body;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(body.substring(0, MaxLogBodyLength));
        sb.append("...too long...");
        return sb.toString();
    }

}
