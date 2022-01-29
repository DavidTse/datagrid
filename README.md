# Datagrid Installation
oc new-project datagrid

oc apply -f rh-datagrid-route.yaml

oc apply -f async-cache.yaml

oc apply -f sync-cache.yaml

oc apply -f batch-cache.yaml

oc apply -f sync-cache-xml.yaml (work in progress)

oc get svc

oc get route

# Credentials
get username & password from rh-datagrid-generated-secret


# Test Behind DMZ (Internal)
Go to a terminal in a pod, e.g.,  rh-datagrid-0

curl -k -v -u developer:htKRAuhLOKKi9YXl https://rh-datagrid:11222/rest/v2/caches

/rest/v2/caches/synccache

/rest/v2/caches/synccache/hello

/rest/v2/caches/synccache?action=stats

/rest/v2/caches/synccache?action=size

More examples are in https://access.redhat.com/documentation/en-us/red_hat_data_grid/8.2/html/data_grid_rest_api/rest_v2_api#rest_v2_cache_detail

# Test Outside DMZ (External)
curl -X POST -k -v -u developer:zFmzFUjYVgrvSUuh https://$hostname/rest/v2/caches/sync-cache/new 
-H "Key-Content-Type: application/x-java-object;type=java.lang.String" 
-d '{"new": "world"}'

curl -X POST -k -v -u developer:zFmzFUjYVgrvSUuh https://$hostname/rest/v2/caches/sync-cache/pi 
-H "Key-Content-Type: application/x-java-object;type=java.lang.String" 
-d '3.14159265384'

curl -k -v -u developer:htKRAuhLOKKi9YXl https://$hostname/rest/v2/caches/sync-cache/pi
