#!/usr/bin/env bash

# example of the run script for running the fraud detection algorithm with a python file,
# but could be replaced with similar files from any major language

# I'll execute my programs, with the input directory paymo_input and output the files in the directory paymo_output
cd ./src/
javac -cp . digitalWallet/ReadData.java
javac -cp . digitalWallet/Feature.java
javac -cp . digitalWallet/UserGraph.java
javac -cp . digitalWallet/Main.java
java  -cp . digitalWallet/Main