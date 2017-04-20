This server can be run as a stand alone java server using runServer.sh or as a docker container which can be built using build.sh

build.sh script assumes docker is installed in the environment. If not docker commands will fail.

Go to the project folder and run mvn clean package docker:build. 

This would create two versions of the jar in the target folder, 
original-aiblockchain-rest.jar which does not have any dependant libraries used by runServer.sh 
and 
aiblockchain-rest.jar which was built specifically to be run a docker container with all dependant libraries included in it.


To run Netty server on port 5000:

java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/original-aiblockchain-rest.jar 5000 $@

===================================================================================
Hana Test scenarios as it stands,

http://localhost:5000/saphana2/share?bucketName=aiblockchain&keyName=upload.JPG

http://localhost:5000/saphana2/userAIResponse?objectHash= 97d1807e594bd4ea075989cf75ae1842

===================================================================================
Test using curl :

curl -X GET curl -X GET http://localhost:5000/users/xml

The result

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<users>
	<user>
		<userId>100</userId>
		<username>test</username>
	</user>
</users>
						
curl -X GET curl -X GET http://localhost:5000/users/json

The result [{"username":"test","userId":100}]


===================================================================================
Test using Rest Client :

Json
	http://localhost:5000/users/json/

	Result:
	[ { "userId": 100, "username": "test" }, { "userId": 200, "username": "test1" } ]

XML
	http://localhost:5000/users/xml/

Upload a file using FORMDATA : 

	http://localhost:5000/aiblockchain/upload

Upload a file using file url :

	http://localhost:5000/aiblockchain/getfile?fileurl=c:\dev\git\aiblockchain\sap-hana-demo\test.txt

Read file from AWS S3:
	
	http://localhost:5000/aiblockchain/readFromS3?bucket=aiblockchain&key=IMG_6422.JPG&file=s3pic.jpg

Write file to AWS S3:
	http://localhost:5000/aiblockchain/writeToS3?bucket=aiblockchain&key=upload.JPG&file=C:\Users\User\Dropbox\Camera Uploads\IMG_0112.JPG


For AWS S3 to make it work, a user has to be created with access id and key and proper permissions (AWSS3FullAccess or AWSS3ReadOnlyAccess as needed)

Also create the ~/.aws/credentials file with the following lines, with user details created in the earlier step.

[default]
aws_access_key_id=
aws_secret_access_key=

