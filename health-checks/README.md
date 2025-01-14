# health-checks

## Purpose
This test aims to demonstrate the behavior of Kubernetes' three main health checks: **Liveness**, **Readiness**, and **Startup Probes**.

## Local Environment
I've used Minikube as a local Kubernetes playground. You can find more information about Minikube [here](https://minikube.sigs.k8s.io/).

### Application Details
If you'd like to modify the Java application, the source code is attached. Alternatively, you can use my public Docker image, which is referenced in the Kubernetes manifests:
`micuemerson/springboot-k8s-health-checks:v1.0.7`

#### Java Application Endpoints
Each endpoint intentionally fails twice before succeeding on the third and subsequent attempts. This setup allows you to experiment with the `failureThreshold` configuration in your deployment file for each health check.