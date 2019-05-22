#!/bin/bash

cd userlistservice
source ./env-variable.sh
mvn clean package
cd ..
cd favouritelistservice
source ./env-variable.sh
mvn clean package
cd ..
cd frontend
npm install
ng build --prod --base-href
