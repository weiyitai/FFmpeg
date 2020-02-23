package com.qianbajin.ffmpegsplit;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
/**
 * ----------------------
 * 代码千万行
 * 注释第一行
 * 代码不注释
 * 改bug两行泪
 * -----------------------
 *
 * @author qianbajin
 * @date at 2020/2/22 0022
 */
public class Util {

    /**
     * 获取影片时长
     *
     * @param file 影片文件
     * @return 影片时长
     */
    static String getDuration(File file) {
        if (!file.exists()) {
            return "";
        }
        String line, time = "";
        try {
            String commandLind = "ffmpeg -i " + file.getAbsolutePath();
            System.out.println("commandLind:" + commandLind);
            Process process = Runtime.getRuntime().exec(commandLind);
            InputStream inputStream = process.getErrorStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader bis = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bis.readLine()) != null) {
                sb.append(line).append('\n');
                if (line.contains("Duration")) {
                    int i = line.indexOf(':');
                    int i1 = line.indexOf('.');
                    time = line.substring(i + 2, i1);
                    System.out.println(i + " i1:" + i1 + " time:" + time);
                }
            }
            System.out.println(sb.toString());
            inputStream.close();
            bis.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static long time2Long(String time) {
        if (isEmpty(time)) {
            return 0;
        }
        String[] split = time.split(":");
        if (split.length != 3) {
            return 0;
        }
        return parseLong(split[0]) * 3600 + parseLong(split[1]) * 60 + parseLong(split[2]);
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static long parseLong(String s) {
        System.out.println("parseLong:" + s);
        if (isEmpty(s)) {
            return 0;
        }
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String secTime2String(long l) {
        if (l == 0) {
            return "00:00:00";
        }
        long min = l / 60;
        long hour = min / 60;
        long sec = l % 60;
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, min, sec);
    }

    static String execCommand(String command) {
        System.out.println("execCommand:" + command);
        try {
            Process process = Runtime.getRuntime().exec(command);
            InputStream errorStream = process.getErrorStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(errorStream));
            char[] chars = new char[4096];
            StringBuilder sb = new StringBuilder(4096);
            int length;
            while ((length = reader.read(chars)) != -1) {
                sb.append(chars, 0, length);
            }
            reader.close();
            errorStream.close();
            process.destroy();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
