########################
# Stop running project #
########################

echo "Stopping project.."
SCRIPT_DIR="/Users/alexis/Desktop/Dev/IndividualProject/script/run/"
cd $SCRIPT_DIR
./stop_ors.sh
./stop_server.sh
./stop_mongo.sh

