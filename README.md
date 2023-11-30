# ClassiFocus-CaseStudy

ClassiFocus is a rest api for an online classified pages. 


# Tech Stack

Java11,
Spring Boot(web, test,data-redis, security, log4j2, cache, validation, aop),
Redis-OM-Spring,
Spring Security Oauth2,
REST,
Redis,
Maven,
Junit5, Mockito, Assertj,
Kubernetes


# Rest API

- UserRegistryController : Restfull Service for user registry
  - signUp
 
- ClassifiedController : Restfull Service for classified operations.
  - saveClassified
  - getClassified
  - updateClassified

- EventSourceController : Restfull Service for classified event operations.
  - getClassifiedEvents 
 
- ProcessLogController : Restfull Service for process log operations.
  - getProcessLogs

- StatisticsController : Restfull Service for statistics operations.
  - getClassifiedStatsByStatus
  - getClassifiedStatsByCategory
 
# Deployment

- Install minikube
  - https://minikube.sigs.k8s.io/docs/start/

- For loadbanacer run command below
  > minikube tunnel
   
- Build Image
  > docker build -t classifocus:latest -f /eclipse-workspace/ClassiFocus/src/main/resources/Dockerfile .
  
  > eval $(minikube docker-env)
  
- Deploy Applications
  > kubectl apply -f classifocus.yaml
  
- Kubernetes Dashboard
  > minikube dasboard
  
- Prometheus&Grafana
  > kubectl create namespace monitoring
  
  > helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
  
