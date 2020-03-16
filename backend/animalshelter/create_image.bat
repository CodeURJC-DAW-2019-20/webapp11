mkdir animalshelter-app
cd animalshelter-app
git clone https://github.com/CodeURJC-DAW-2019-20/webapp11.git
cd webapp11/backend/animalshelter
mvn clean package
xcopy docker
docker build . -t animalshelter-app