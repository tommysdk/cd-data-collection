-include dockerenv

all: build deploy

build:
	docker build -t cd-data-collection-jenkins jenkins/

deploy:
	kubectl apply -f k8s/

dockerenv: Makefile
	minikube docker-env | sed 's/\"//g' > $@
