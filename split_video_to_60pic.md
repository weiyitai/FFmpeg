@echo off &setlocal enabledelayedexpansion

::set /p var="������aar·��:"
set path=%1
echo path=%path%

:: �Ӿ���·���н�ȡ���·��
for /f %%a in ("%path%") do (
set dir=%%~na
echo name is %%~na
::��forѭ���жԱ�����ֵ���ȡ����Ҫ�ø�̾��
echo dir name is !dir!
)
::echo file name is !dir!

:: �����·����ȡ����·��
::FOR /F %%i IN ("%dir%") DO echo absolute path: %%~fi

:: rmdir ��ɾ��Ŀ¼����Ŀ¼�µ��ļ����ļ���  del ֻ��ɾ��Ŀ¼��Ŀ¼�µ��ļ�,����ɾ���ļ��б������ļ���
if exist !dir! (
rmdir /s/q !dir!
)
mkdir !dir!

:: Ҫ�������ٷֺ�  ��һ����ת�� %%05d.png
D:\app\ffmpeg-4.2.2-win64-static\bin\ffmpeg -i %path% -r 60 -f image2 -q:v 2 !dir!/%%05d.jpg

timeout /t 5 /nobreak
::pause