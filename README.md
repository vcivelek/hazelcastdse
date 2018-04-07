This is a sample implementation of Hazelcast MapStore with DSE Cassandra using DSE Object Mapper.
Hazelcast has a hook (MapStore) that allows to persist the cache to a database. In this case it's DSE Cassandra.
In this example MapStore stores the cache to DSE Cassandra database. 
You can also load the data back to the cache from DB during initilization or on demand. 

Please see the links below for more details:
 * https://docs.datastax.com/en/developer/java-driver-dse/1.5/manual/object_mapper/
 * http://docs.hazelcast.org/docs/latest/manual/html-single/#loading-and-storing-persistent-data
