docker run --name mysqlDataBase -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=animalshelter -d mysql
pause
docker build . -t users-mysql
pause
docker run -p 8443:8443 --name users-mysql --link mysqlDataBase:mysql -d users-mysql 
pause
