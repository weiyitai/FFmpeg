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

## 将视频转gif  
`ffmpeg -i in.mp4 out.gif`  

* 从视频中第二秒开始，截取时长为3秒的片段转化为 gif    
`ffmpeg -t 3 -ss 00:00:02 -i small.webm small-clip.gif`  

## 视频切片  
* 将一个视频进行切片,每个视频片段时长8秒  
`ffmpeg -re -i jiedong_remove.mp4 -c copy -f segment -segment_format mp4 -segment_time 8 out_%03d.mp4`  

## 视频合并  
* 将视频文件添加到 list.txt文件中  
`(for %i in (out_*.mp4) do @echo file '%i') > list.txt`  
结果如下  

		file 'out_0.mp4'
		file 'out_1.mp4'
		file 'out_2.mp4'  

## 将一个视频一秒切成60张图片
`ffmpeg -i D:\log\huaweiMeeting\视频\20200708-144839(eSpace).mp4 -r 60 -f image2 pic/%05d.png`  
-r 指定抽取的帧率，即从视频中每秒钟抽取图片的数量。1代表每秒抽取一帧，５就表示一秒抽５张  
-f 指定保存图片使用的格式，可忽略  
image2: 图像解析模式
## 以宽 540 高 960 倍率0.75播放一个视频 
`ffplay -i D:\log\huaweiMeeting\视频\20200708-144839(eSpace).mp4 -x 540 -y 960 -vf setpts=PTS/0.75`

## 将两个图片合并成一张图片
`ffmpeg -i a.jpg -i b.jpg -filter_complex hstack output.png`

如果要垂直堆叠，则改为使用vstack

## 图片裁剪
`ffmpeg -i input.jpg -filter:v "crop=width:height:x:y" out.jpg`  
其中width是结果图的宽度，height是结果图的高度，x是结果图在原图的起始x轴坐标，y是结果图在原图的起始y轴坐标

## 压缩音频
`ffmpeg -i ring_1.wav -b:a 64k -acodec mp3 -ac 1 ring_2.wav`  
命令参数解释：  
 -i "PZONKA190313.wav"  输入的音频文件  
 -b:a 64k               表示音频的码率为64kb/s，即8kB/s；  
 -acodec mp3            表示设定声音编解码器为mp3；  
 -ar 44100              表示设定采样率为44100；  
 "ring_2.wav"                表示输出保存的文件名。

 -acodec mp3 实现了音频压缩

## 提取视频帧  

参考
[https://www.nxrte.com/jishu/30204.html](https://www.nxrte.com/jishu/30204.html)  

* 提取视频的第一帧  
`ffmpeg -i Camera_xhs_1705894886278.mp4 -frames:v 1 first.png`  
参数 -frames:v 1 可确保在指定的时间戳处只提取一个帧

* 提取所有帧为 JPEG 格式  
`ffmpeg -i input.mp4 -q:v 1 frame%04d.jpg`  
参数 -q:v 1 设置输出图像的质量。质量值范围从 1（最高质量）到 31（最低质量）。对于 JPEG 输出，文件大小与质量值成反比，即质量值越低，文件大小越大  
**实测设置-q:v 得到的图片质量可以,和png的差别不大**  

* 提取特定时间戳下的精确帧  
`ffmpeg -ss 00:00:08 -i Camera_xhs_1705894886278.mp4 -frames:v 1 -q:v 1 sec2.jpg`  
或者时间可以这样精简  
`ffmpeg -ss 8 -i Camera_xhs_1705894886278.mp4 -frames:v 1 -q:v 1 sec2.jpg`  
* 从指定时间开始抽取指定的帧数  
`ffmpeg -ss 8 -i input1.mp4 -frames:v 1 -q:v 2 -vframes 6 %02d.jpg`  
-vframes 抽取指定的帧数
  


  


