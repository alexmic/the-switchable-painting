#####################
# Stop mongo deamon #
#####################

echo "Stopping the mongo deamon, if running."
pid=`ps -u"alex" -opid,comm | grep "mongod" | awk '{print $1}'`
if [ "$pid" != "" ]
  then
    kill $pid
    echo "mongod pid $pid, stopped."
fi
