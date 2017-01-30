# Build
docker built -t cd-data-collection-jenkins .

# Run
docker run --name cd-data-collection-jenkins -p 8080:8080 -e JAVA_OPTS="-Djenkins.install.runSetupWizard=false" cd-data-collection-jenkins
