One-jar standalone

Go to the project folder and run mvn clean package. 

The file target/aiblockchain-rest.jar will be created

Run Netty server on port 5000:

java -jar target/aiblockchain-rest-0.0.1-SNAPSHOT.jar 5000

===================================================================================
Hana Test

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
