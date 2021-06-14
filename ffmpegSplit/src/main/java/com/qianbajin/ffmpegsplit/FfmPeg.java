package com.qianbajin.ffmpegsplit;

import java.io.File;
/**
 * @author weiyitai
 * @date 20200221
 */
public class FfmPeg {

    public static void main(String[] arg) {
//        split();
        Util.getWeChatTools();
    }

    /**
     * 调用ffmpeg将视频切割成多段
     */
    private static void split() {
        try {
            // 每段视频时长
            long perDur = 270L, from = 0, index = 0;
            File dir = new File("J:/publicagent/1cat");
            boolean exists = dir.exists();
            System.out.println(exists);
            File outDir = new File(dir, "sybil");
            if (!outDir.exists()) {
                boolean mkdirs = outDir.mkdirs();
            }
            File mp4 = new File(dir, "sy.mp4");
            String duration = Util.getDuration(mp4);
//            String duration = "00:40:27";
            // ffmpeg -ss 00:06:00 -i test.mp4 -vcodec copy -acodec copy abc.mp4
            // 将-ss， -t 参数放在-i参数之前 对输入文件执行seek操作，会seek到-ss设置的时间点前面的关键帧上。
            // 时间不精确，但是不会出现黑屏 https://blog.csdn.net/matrix_laboratory/article/details/53157383
            long time = Util.time2Long(duration);
            String durLong = Util.secTime2String(perDur);
            System.out.println("duration:" + duration + " time:" + time);
            String input = mp4.getAbsolutePath();
            String inputName = mp4.getName();
            for (long i = perDur; i <= time; i += perDur) {
                index++;
//                if (i + perDur > time) {
//                    i = time;
//                }
                StringBuilder builder = new StringBuilder(128);
                String startTime = Util.secTime2String(from);
                String endTime = Util.secTime2String(i);
                System.out.println(index + " from:" + startTime + " to:" + endTime);
                builder.append("ffmpeg -ss ").append(startTime);
                if (i != time) {
                    builder.append(" -t ").append(durLong);
                }
                File outFile = new File(outDir, index + "_" + inputName);
                builder.append(" -i ")
                        .append(input).append(" -vcodec copy -acodec copy -y ");
                builder.append(outFile.getAbsolutePath());
                from = i;
                String command = builder.toString();
                long l = System.currentTimeMillis();
//                Util.execCommand(command);
                System.out.println((System.currentTimeMillis() - l) + "  " + command);
                // -ss 开始时间  -t 时长
                // ffmpeg -ss 00:00:00 -t 00:00:13 -i I:\手机文件\pipixia\741d6f85e0bb4cbe9f21da4cad3a4268.mp4 -vcodec copy -acodec copy abc.mp4
            }
            if (from < time) {
                System.out.println(index + " from:" + from + " to:" + Util.secTime2String(time));
                StringBuilder builder = new StringBuilder(128);
                File out = new File(outDir, ++index + "_" + mp4.getName());
                builder.append("ffmpeg -ss ").append(Util.secTime2String(from)).append(" -i ")
                        .append(input).append(" -vcodec copy -acodec copy -y ").append(out.getAbsolutePath());
                String command = builder.toString();
//                Util.execCommand(command);
                System.out.println(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
