# cd-data-collection
Continuous Delivery Data Collection

This project aims to provide a way to collect and visualize data of interest for Continuous Delivery development processes.

Overview
---

Components
---
* Jenkins
* Prometheus
* Grafana

We rely on Jenkins, since it is a widely used base for CI/CD today. We use Prometheus to gather data and metrics and visualize it using Grafana.

Run
---
    minikube start
    make all

You should now have your local Kubernetes cluster up and running. You should be able to access the Kubernetes dashboard on your VM ip on port 30000:

![Kubernetes Dashboard](https://raw.githubusercontent.com/tommysdk/cd-data-collection/master/k8s-dashboard-example.png)

Development / Requirements
---
* [minikube](https://github.com/kubernetes/minikube)
* [kubectl](https://kubernetes.io/docs/user-guide/kubectl-overview/)
* [VirtualBox](https://www.virtualbox.org/wiki/Downloads)
* [Docker](https://www.docker.com/)

Mac OS X:
* [xhyve](https://github.com/kubernetes/minikube/blob/master/DRIVERS.md#xhyve-driver)

Contributors
---
* [André Schaffer](https://github.com/andreschaffer)
* [Jefferson Girão](https://github.com/jeffersongirao)
* [Tommy Tynjä](https://github.com/tommysdk)

