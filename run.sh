# mvn clean compile && mvn -q exec:java -Dexec.mainClass=com.natucciEngine.Main 
mvn clean compile && mvn -q exec:java \
  -Dexec.mainClass=com.natucciEngine.Main \
  -Dexec.args="" \
  -Dexec.jvmArgs="-Xms512m -Xmx2g"

