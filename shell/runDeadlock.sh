#!/bin/bash
mvn clean
mvn install
java -jar ../target/what.jar 
