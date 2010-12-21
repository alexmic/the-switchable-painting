#!/bin/bash

######################################
# Project build-all script	     #
# ---------------------------------- #
# Shortcut script for building and   #
# installing the server and the ORS. #
#				     #
# Author: Alex Michael		     #
######################################

# Build the Python Server.
echo "--------------------------"
echo "Building the Python Server"
echo "--------------------------"
./server/build_server.sh

echo

# Build the ORS.
echo "-----------------"
echo "Building the ORS."
echo "-----------------"
#./ors/build_ors.sh

