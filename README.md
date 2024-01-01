## 去除一段视频中某段时间的水印
* 去除17秒到41秒的水印
`ffmpeg -i sao_copy.mp4 -vf "delogo=15:767:140:70:enable='between(t,17,41)'" -c:a copy remove.mp4` 

* 去除10秒到1:43秒右上角水印 和 1:53到2:34秒右下角的水印  
`ffmpeg -i pen.mp4 -vf "delogo=329:2:148:78:enable='between(t,10,103)',delogo=330:774:144:71:enable='between(t,113,153)'" -c:a copy pen_remove.mp4`
