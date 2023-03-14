
import sys
import datetime
import Adafruit_DHT
now=datetime.datetime.now();
Y=now.year;
M=now.month;
D=now.day;
H=now.hour;
I=now.minute;
S=now.second;
CDATE="{}-{}-{}".format(Y,M,D);
CTIME="{}-{}-{}".format(H,I,S);
HUM,TEMPR= Adafruit_DHT.read(11,20)
#   gpio read data
print("{},{},{},{}".format(HUM,TEMPR,CDATE,CTIME))




