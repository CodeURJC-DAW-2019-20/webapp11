cd ..

cd ./AngularAnimalShelter

docker run --rm --name angular-animalshelter-container -v "%cd%":/angular -w /angular node:12.16.1 /bin/bash -c "npm install; npm run-script build"

xcopy /E "%cd%"\dist\AngularAnimalShelter ..\backend\animalshelter\src\main\resources\static\new

cd ../backend/animalshelter

docker run --rm -v "%cd%":/usr/src/project -w /usr/src/project maven:alpine mvn package

cd ../../docker

docker build . -t animalshelter-app