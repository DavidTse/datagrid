# Datagrid-Operator Installation
git clone git@github.com:alvarolop/rhdg8-server.git

cd rhdg8-server

oc process -f rhdg-operator/rhdg-01-operator.yaml | oc apply -f -

# Datagrid Installation
oc project rhdg8

oc apply -f openshift/rh-datagrid-route.yaml

The following are optionals but you will need at least one

oc apply -f openshift/sessions.yaml

oc apply -f openshift/async-cache.yaml

oc apply -f openshift/sync-cache.yaml

oc apply -f openshift/batch-cache.yaml

oc apply -f openshift/temperature.yaml

oc apply -f openshift/weather.yaml


oc get svc

oc get route

# Credentials
get username & password from rh-datagrid-generated-secret (${cluster-name}-generated-secret)
  
OpenShift Console->namespace->Workload->Secret

# Accessing Data Grid REST API within OpenShift
Go to a terminal in a pod, e.g.,  rh-datagrid-0

curl -k -v -u developer:htKRAuhLOKKi9YXl https://rh-datagrid:11222/rest/v2/caches

/rest/v2/caches/synccache

/rest/v2/caches/synccache/hello

/rest/v2/caches/synccache?action=stats

/rest/v2/caches/synccache?action=size

More examples are in https://access.redhat.com/documentation/en-us/red_hat_data_grid/8.2/html/data_grid_rest_api/rest_v2_api#rest_v2_cache_detail

# Accessing REST API from the outside of the DMZ (via Route)
curl -X POST -k -v -u developer:zFmzFUjYVgrvSUuh https://$hostname/rest/v2/caches/sync-cache/new 
-H "Key-Content-Type: application/x-java-object;type=java.lang.String" 
-d '{"new": "world"}'

curl -X POST -k -v -u developer:zFmzFUjYVgrvSUuh https://$hostname/rest/v2/caches/sync-cache/pi 
-H "Key-Content-Type: application/x-java-object;type=java.lang.String" 
-d '3.14159265384'

curl -k -v -u developer:htKRAuhLOKKi9YXl https://$hostname/rest/v2/caches/sync-cache/pi

# Accessing Hot Rod API from the outside of the DMZ (via Route)
Certificate & Truststore

rh-datagrid-cert-secret (crt & key, ${cluster-name}-cert-secret, OpenShift Console->${namespace}->Workload->Secret)

cat tls.crt | openssl x509 -noout -enddate

keytool -import -alias datagrid -file tls.crt -storetype PKCS12 -keystore truststore.pkcs12 (prompt for password)

hotrod-client.properties (must be in the classpath, see src/main/resources)

RemoteCacheManager remoteCacheManager = new RemoteCacheManager();

Spring-boot app tested using codes from https://github.com/redhat-developer/redhat-datagrid-tutorials/blob/RHDG_8.3.0/integrations/spring-boot/cache-remote
