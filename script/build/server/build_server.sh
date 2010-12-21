#!/bin/bash

#################################################
# Python Server Build Script 			#
# ---------------------------------------------	#
# The build script runs the unit tests and then #
# creates a new versioned directory and zips    #
# all files in there.                           #
#						#
# Author: Alex Michael.				#
#################################################

# Build script directory
BUILD_SCRIPT_DIR=`pwd`

# Source directories
SERVER_DIR=~/Desktop/Dev/IndividualProject/server
SERVER_DIR_MAIN=$SERVER_DIR/src/main
SERVER_DIR_TEST=$SERVER_DIR/src/test

# Run the unit tests. If they fail, exit.
cd $SERVER_DIR_TEST
echo "Running the server's unit tests.."
python run_tests.py
retCode=$?
if [[ $retCode -ne 0 ]] 
then
  echo "You have failed unit tests. Check the output from the executed tests, fix them, and build again. Bye."
  exit
fi

# Get current version and increment it.
cd $SERVER_DIR/build
VERSION=`cat version`
VERSION=`length=2; echo $VERSION + 0.1 | bc`

# Squeeze the python files.
echo "Installing python files.."
DATE=`date +%d%m%y.%H%M`
cd $SERVER_DIR_MAIN
mkdir $SERVER_DIR/build/build-$VERSION
tar -cjf server-$VERSION-$DATE.tar org/
mv server-$VERSION-$DATE.tar $SERVER_DIR/build/build-$VERSION
cd $SERVER_DIR/build
if [[ -d latest ]]
then
   rm -r latest
fi
ln -s build-$VERSION latest

# Write new version number.
echo $VERSION > version

echo "Done."
