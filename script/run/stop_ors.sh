#################
# Stop the ORS. #
#################

echo "Stopping ORS, if running."
pid=`ps -opid,comm,args -u"alex" | grep "/usr/bin/java -jar ors-" | grep -v grep | awk '{print $1}'`
if [ "$pid" != "" ]
  then
    kill $pid
    echo "ORS pid $pid, stopped."
fi

