FROM bellsoft/liberica-openjdk-alpine:17.0.12-cds

# Install curl jq
RUN apk add curl jq

# workspace
WORKDIR /home/selenium-docker

# Add the required files
ADD target/docker-resources  ./
ADD runner.sh                runner.sh

# Environment variables
# BROWSER
# HUB_HOST
# TEST_SUITE
# THREAD_COUNT

## Fix for windows
#RUN dos2unix runner.sh

# Run the tests
# Start the runner.sh
ENTRYPOINT sh runner.sh
#ENTRYPOINT java -cp 'libs/*' -D"selenium.grid.enabled"=true -D"selenium.grid.hubHost"=${HUB_HOST} -Dbrowser=${BROWSER} org.testng.TestNG -threadcount ${THREAD_COUNT} test-suites/${TEST_SUITE}