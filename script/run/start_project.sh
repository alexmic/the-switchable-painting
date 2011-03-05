###############################
# Start latest project build. #
###############################

echo "Starting latest project build.."
SCRIPT_DIR="/Users/alexis/Desktop/Dev/IndividualProject/script/run/"
cd $SCRIPT_DIR
./start_mongo.sh
./start_server.sh
./start_ors.sh
