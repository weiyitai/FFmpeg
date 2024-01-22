@echo off &setlocal enabledelayedexpansion

::set /p var="请输入aar路径:"
set path=%1
echo path=%path%

:: 从绝对路径中截取相对路径
for /f %%a in ("%path%") do (
set dir=%%~na
echo name is %%~na
::在for循环中对变量赋值后读取变量要用感叹号
echo dir name is !dir!
)
::echo file name is !dir!

:: 从相对路径获取绝对路径
::FOR /F %%i IN ("%dir%") DO echo absolute path: %%~fi

:: rmdir 会删除目录及子目录下的文件及文件夹  del 只会删除目录及目录下的文件,不会删除文件夹本身及子文件夹
if exist !dir! (
rmdir /s/q !dir!
)
mkdir !dir!

:: 要用两个百分号  第一个是转义 %%05d.png
D:\app\ffmpeg-4.2.2-win64-static\bin\ffmpeg -i %path% -r 60 -f image2 -q:v 2 !dir!/%%05d.jpg

timeout /t 5 /nobreak
::pause