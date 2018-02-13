#!/bin/bash

#java -Dlog4j.debug -jar target/sap-hana-demo-jar-with-dependencies.jar netty 5000 
#java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/sap-hana-demo-jar-with-dependencies.jar netty 5000 
#java -Dlog4j.configuration=file://c/dev/git/aiblockchain/sap-hana-demo/log4j.properties -jar target/sap-hana-demo-jar-with-dependencies.jar netty 5000 
#java -cp lib/*:myproject-0.0.1-SNAPSHOT.jar org.foo.myproject.App $@
#java -cp target/lib/*:aiblockchain-rest.jar -Dlog4j.debug -Dlog4j.configuration=log4j.properties NettyServerMain 5000 $@
#java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/aiblockchain-rest-jar-with-dependencies.jar 5000 


#java -cp target/lib/*:target/aiblockchain-rest.jar -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/aiblockchain-rest.jar 5000 $@
#java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/aiblockchain-rest.jar 5000 $@
#java -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/original-aiblockchain-rest.jar 20001 $@ 

if [ "$1" = "debug" ]; then
    export JAVA_OPTS="-Xdebug -Xnoagent -Djava.compile=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"
else
    export JAVA_OPTS=
fi

echo "java $JAVA_OPTS -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/original-aiblockchain-rest.jar 20001 $@ 2>&1 | tee server_output.log"
java $JAVA_OPTS -Dlog4j.debug -Dlog4j.configuration=log4j.properties -jar target/original-aiblockchain-rest.jar 20001 $@ 2>&1 | tee server_output.log
