echo "Java 8 And Maven 3.x is required"

echo "Building...."
mvn clean install
echo "Build done. Running. localhost:8080"
cd target
java -jar QuotesApplication-1.0.0.jar --spring.profiles.active=local


