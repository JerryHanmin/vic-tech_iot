#!/bin/bash

. /etc/profile

programe_location="$(dirname -- $(readlink -f -- "$0"))"
#java_path="/usr/lib/jvm/jdk1.8.0_65/bin/"
config='--spring.cloud.config.uri=http://192.168.118.131:19300/config'
eureka='--service.eureka.uri=http://192.168.118.131:8761/eureka/'
cassandra='--service.cassandra.uri:192.168.118.131'
elasticsearch='--service.elasticsearch.uri=192.168.118.131 --service.elasticsearch.clusterName=changhongit_qa'
kafka='--service.kafka.zookeeper.connect=192.168.118.131:2181 --service.kafka.broker.connect=192.168.118.131:9092'
avrogit='--service.git.uri=http://sys@172.28.24.45:8118/git/changhongit.git --service.git.username=sys --service.git.password=sys'
mysql='--spring.datasource.url=jdbc:mysql://192.168.118.131:3306/${service.mysql.database}?characterEncoding=utf-8 --spring.datasource.username=sst_user --spring.datasource.password=123456'
ftp='--service.ftp.server=192.168.118.131 --service.ftp.port=21 --service.ftp.username=ftpuser --service.ftp.password=ftpuser'
log='--logging.config=/usr/local/thundersoft/logback.xml'

params="$config $eureka $cassandra $elasticsearch $kafka $avrogit $mysql $ftp $log"
java_mem="-Xms128m -Xmx256m"
delay_service="xxxx zzzz"
delay_time=60
input=`echo "$@"|sed "s/start \|startall \|stop \|stopall \|restart \|restartall //"`

case "$1" in
	start)
		cd $programe_location
		for i in $input;do
			if [[ `echo $delay_service|grep -c $i` -eq 1 ]];then
				if [[ `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|wc -l` -ne 1 ]];then
					nohup ${java_path}java -jar $java_mem $i.jar $params >> nohup.out 2>&1 &
					sleep $delay_time
					echo "Service $i started! "
				else
					echo "Service $i is already running! "
				fi
			else
				if [[ `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|wc -l` -ne 1 ]];then
					nohup ${java_path}java -jar $java_mem $i.jar $params >> nohup.out 2>&1 &
					echo "Service $i started! "
				else
					echo "Service $i is already running! "
				fi
			fi
			shift
		done
		echo "The services you required started! "
		;;
	startall)
		cd $programe_location
		for i in `echo $(ls *.jar)|sed 's/\.jar//g'`;do
			if [[ `echo $delay_service|grep -c $i` -eq 1 ]];then
				if [[ `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|wc -l` -ne 1 ]];then
					nohup ${java_path}java -jar $java_mem $i.jar $params >> nohup.out 2>&1 &
					sleep $delay_time
					echo "Service $i started! "
				else
					echo "Service $i is already running! "
				fi
			else
				if [[ `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|wc -l` -ne 1 ]];then
					nohup ${java_path}java -jar $java_mem $i.jar $params >> nohup.out 2>&1 &
					sleep 5
					echo "Service $i started! "
				else
					echo "Service $i is already running! "
				fi
			fi
		done
		echo "All services started! "
		;;
	stop)
		for i in $input;do
			kill -9 `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|tr -s " "|cut -d " " -f 2|tr "\n" " "`
			echo "Service $i stoped! "
			shift
		done
		echo "The services you required stoped! "
		;;
	stopall)
		cd $programe_location
		for i in `echo $(ls *.jar)|sed 's/\.jar//g'`;do
			kill -9 `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|tr -s " "|cut -d " " -f 2|tr "\n" " "`
			echo "Service $i stoped! "
		done
		echo "All services stoped! "
		;;
	restart)
		for i in $input;do
			kill -9 `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|tr -s " "|cut -d " " -f 2|tr "\n" " "`
			echo "Service $i stoped! "
		done
		echo "The services you required stoped! "
		sleep 2
		cd $programe_location
		for i in $input;do
			if [[ `echo $delay_service|grep -c $i` -eq 1 ]];then
				if [[ `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|wc -l` -ne 1 ]];then
					nohup ${java_path}java -jar $java_mem $i.jar $params >> nohup.out 2>&1 &
					sleep $delay_time
					echo "Service $i started! "
				else
					echo "Service $i is already running! "
				fi
			else
				if [[ `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|wc -l` -ne 1 ]];then
					nohup ${java_path}java -jar $java_mem $i.jar $params >> nohup.out 2>&1 &
					echo "Service $i started! "
				else
					echo "Service $i is already running! "
				fi
			fi
		done
		echo "The services you required started! "
		;;
	restartall)
		cd $programe_location
		for i in `echo $(ls *.jar)|sed 's/\.jar//g'`;do
			kill -9 `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|tr -s " "|cut -d " " -f 2|tr "\n" " "`
			echo "Service $i stoped! "
		done
		echo "All services stoped! "
		sleep 2
		for i in `echo $(ls *.jar)|sed 's/\.jar//g'`;do
			if [[ `echo $delay_service|grep -c $i` -eq 1 ]];then
				if [[ `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|wc -l` -ne 1 ]];then
					nohup ${java_path}java -jar $java_mem $i.jar $params >> nohup.out 2>&1 &
					sleep $delay_time
					echo "Service $i started! "
				else
					echo "Service $i is already running! "
				fi
			else
				if [[ `ps -ef|grep "$i.jar"|grep "java"|grep -v '00:00:00 grep'|wc -l` -ne 1 ]];then
					nohup ${java_path}java -jar $java_mem $i.jar $params >> nohup.out 2>&1 &
					sleep 5
					echo "Service $i started! "
				else
					echo "Service $i is already running! "
				fi
			fi
		done
		echo "All services started! "
		;;
	status)
		cd $programe_location
		for i in `echo $(ls *.jar)|sed 's/\.jar//g'`;do
			ps_ef=`ps -ef|grep ${i}.jar|grep "java"|grep -v '00:00:00 grep'`
			ps_ef_pid=`echo $ps_ef|tr -s " "|cut -d " " -f 2|tr "\n" " "`
			if [ "$ps_ef_pid" = " " ];then ps_ef_pid="PID_Is_Null";fi
			listen=`netstat -nplt|grep $ps_ef_pid|tr -s " "|cut -d " " -f 4|tr "\n" " "`
			echo -e "\033[44;37m$i \033[0m >>> \033[46;37mPID=${ps_ef_pid}\033[0m >>> \033[45;37mListen=${listen}\033[0m >>> \033[42;37mCount=`echo $ps_ef|grep ${i}.jar|grep "java"|grep -v '00:00:00 grep'|wc -l`\033[0m"
		done
		;;
	*)
		echo -e $"Usage: $0 {start|stop|restart} servicename1 servicename2...\nUsage: $0 {startall|stopall|restartall|status}\n"' 脚本可启动指定的一个或多个服务(数量无上线)，servicename为.jar文件的文件名:如abc.jar，则servicename为abc\n 启动指定服务，请按需要的启动顺序输入servicename1、servicename2等，否则请忽略顺序;\n 一次启动目录下所有服务如有启动顺序要求，请在文件名前加0-9标识顺序，最大支持标记10个.\n example: # ./service.sh start 1discovery-service 2config-service oauth2-cassandra user-location-baidu-push-jetty '
		;;
esac
