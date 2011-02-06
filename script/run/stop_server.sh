###########################
# Stop the Python server. #
###########################

echo "Stopping Tornado server, if running."
pid=`ps -opid,comm,args -u"alex" | grep "Python Boot.py" | grep -v grep | awk '{print $1}'`
if [ "$pid" != "" ]
  then
    kill $pid
    echo "Tornado server pid $pid, stopped."
fi

