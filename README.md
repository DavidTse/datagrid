# datagrid
Usage:

oc new-project datagrid

oc apply -f infinispan-cluster.yaml

oc apply -f sync-cache.yaml

oc apply -f batch-cache.yaml

oc apply -f rh-datagrid-external.yaml

oc apply -f rh-datagrid-route.yaml


# Credentials
get username & password from rh-datagrid-generated-secret


# Test
Go to a terminal in a pod, e.g.,  rh-datagrid-0

curl -k -v -u developer:htKRAuhLOKKi9YXl https://rh-datagrid:11222/rest/v2/caches

/rest/v2/caches/synccache

/rest/v2/caches/synccache/hello

/rest/v2/caches/synccache?action=stats

/rest/v2/caches/synccache?action=size

More examples are in https://access.redhat.com/documentation/en-us/red_hat_data_grid/8.2/html/data_grid_rest_api/rest_v2_api#rest_v2_cache_detail

