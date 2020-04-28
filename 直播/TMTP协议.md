### 直播技术
##### [RMTP协议相关][1]
##### [用ffmpeg+nginx+海康威视网络摄像头rtsp在手机端和电脑端实现直播][2]
[1]: https://blog.csdn.net/qq_34447388/category_7387047.html
[2]: https://blog.csdn.net/zfgogo/article/details/52439849



rtsp://admin:fuli1234@192.168.197.153:554/h264/ch1/main/av_stream

ffmpeg -i rtsp://admin:fuli1234@192.168.197.153:554 -vcodec copy -acodec aac -ar 44100 -strict -2 -ac 1 -f flv -s 1280x720 -tune zerolatency -preset ultrafast -q 10 -f flv rtmp://118.89.237.217:1935/hls/test

ffmpeg -re -i rtsp://admin:fuli1234@192.168.197.153:554 -vcodec libx264 -vprofile baseline -acodec aac -ar 44100 -strict -2 -ac 1 -f flv -s 1280x720 -q 10 rtmp://118.89.237.217:1935/hls/test

rtmp://118.89.237.217:1935/hls/test.m3u8