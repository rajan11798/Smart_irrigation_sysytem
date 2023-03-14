
import datetime
import RPi.GPIO as GPIO
now=datetime.datetime.now();
Y=now.year;
M=now.month;
D=now.day;
H=now.hour;
I=now.minute;
S=now.second;
CDATE="{}-{}-{}".format(Y,M,D);
CTIME="{}-{}-{}".format(H,I,S);
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)
GPIO.setup(26,GPIO.IN)
if(GPIO.input(26)==GPIO.LOW):
	print("WET,{},{}".format(CDATE,CTIME))
else:
	print("DRY,{},{}".format(CDATE,CTIME))


GPIO.cleanup();




