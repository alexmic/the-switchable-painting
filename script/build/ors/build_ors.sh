#!/bin/bash

####################################
# ORS Service build script	   #
# -------------------------------- #
# Build and packages the ORS using #
# Apache buildr. 		   #
#				   #
# Author: Alex Michael		   # 
####################################

ORS_MAIN_DIR=~/Desktop/Dev/IndividualProject/ors

# cd into the main dir and invoke buildr.
cd $ORS_MAIN_DIR
echo "Building ORS with buildr.."
buildr release --trace
retCode=$?
if [[ $retCode -ne 0 ]]
then
  echo "Buildr build failed. Check its output.You have failed unit tests."
  exit
fi

echo "Installing ORS latest jar.."
VERSION=`cat version`
cd target/builds
mkdir build-$VERSION
mv ../*.jar build-$VERSION

if [[ -d latest ]]
then
   rm -r latest
fi
ln -s build-$VERSION latest

echo "Done."

