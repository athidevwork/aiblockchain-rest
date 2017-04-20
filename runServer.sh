#!/bin/bash

#java -Dlog4j.debug -jar target/sap-hana-demo-jar-with-dependencies.jar netty 5000 
#java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/sap-hana-demo-jar-with-dependencies.jar netty 5000 
#java -Dlog4j.configuration=file://c/dev/git/aiblockchain/sap-hana-demo/log4j.properties -jar target/sap-hana-demo-jar-with-dependencies.jar netty 5000 
#java -cp lib/*:myproject-0.0.1-SNAPSHOT.jar org.foo.myproject.App $@
#java -cp target/lib/*:aiblockchain-rest.jar -Dlog4j.debug -Dlog4j.configuration=log4j.properties NettyServerMain 5000 $@
#java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/aiblockchain-rest-jar-with-dependencies.jar 5000 


#java -cp target/lib/*:target/aiblockchain-rest.jar -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/aiblockchain-rest.jar 5000 $@
#java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/aiblockchain-rest.jar 5000 $@
java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/original-aiblockchain-rest.jar 5000 $@
