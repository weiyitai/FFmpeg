## 去除一段视频中某段时间的水印
* 去除17秒到41秒的水印  
`ffmpeg -i sao_copy.mp4 -vf "delogo=15:767:140:70:enable='between(t,17,41)'" -c:a copy remove.mp4` 

* 去除10秒到1:43秒右上角水印 和 1:53到2:34秒右下角的水印  
`ffmpeg -i pen.mp4 -vf "delogo=329:2:148:78:enable='between(t,10,103)',delogo=330:774:144:71:enable='between(t,113,153)'" -c:a copy pen_remove.mp4`

## m3u8文件转MP4 [参考链接](https://blog.csdn.net/weixin_44647371/article/details/120640311)
`ffmpeg -allowed_extensions ALL -i .local.index.m3u8 -c copy -bsf:a aac_adtstoasc out.mp4`  

## 视频剪切  [参考链接](https://blog.csdn.net/matrix_laboratory/article/details/53157383)  

* -ss 开始时间  -t 时长,如果不指定时长则默认到视频结尾
*  将-ss， -t 参数放在-i参数之前 对输入文件执行seek操作，会seek到-ss设置的时间点前面的关键帧上,时间不精确，但是不会出现黑屏
`ffmpeg -ss 00:00:00 -t 00:00:13 -i I:\手机文件\pipixia\741d6f85e0bb4cbe9f21da4cad3a4268.mp4 -vcodec copy -acodec copy abc.mp4`

