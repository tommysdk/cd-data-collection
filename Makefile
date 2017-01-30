-include dockerenv

all: build clean deploy

build:
	docker build -t cd-data-collection-jenkins jenkins/

deploy:
	kubectl create configmap jenkins-jobdsl --from-file jenkins/jobdsl
	kubectl apply -f k8s/

dashboards:
	bash grafana/dashboards.sh

dockerenv: Makefile
	minikube docker-env | sed 's/\"//g' > $@

clean:
	kubectl delete -f --grace-period=0 k8s/ 2>/dev/null; true
	kubectl delete configmap jenkins-jobdsl 2>/dev/null; true
