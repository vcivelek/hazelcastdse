/*
 * Sample implementation of hazelcast mapstore with DSE object mapper.
 * https://docs.datastax.com/en/developer/java-driver-dse/1.5/manual/object_mapper/
 * http://docs.hazelcast.org/docs/latest/manual/html-single/#loading-and-storing-persistent-data
 */

package com.hazelcast.dse.mapstore;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.Result;
import com.hazelcast.core.MapStore;

import java.util.*;

public class DSEMapStore implements MapStore<String,User> {

    private Dse dse = new Dse();
    private Mapper<User> mapper = dse.getMapper(User.class);
    private UserAccessor userAccessor = dse.getAccessor();

    public void store(String key, User user) {
        mapper.save(user);
    }

    public void storeAll(Map<String, User> map) {
        Collection<User> users = map.values();
        for (User user:users) {
            mapper.save(user);
        }
    }

    public void delete(String name) {
        mapper.delete(name);
    }

    public void deleteAll(Collection<String> keys) {
        for (String name: keys) {
            mapper.delete(name);
        }
    }

    public User load(String key) {
        return mapper.get(key);
    }

    public Map<String, User> loadAll(Collection<String> names) {
        Map<String, User> result = new HashMap<String, User>();
        for (String name:names) {
            result.put(name, mapper.get(name));
        }
        return result;
    }

    public Iterable<String> loadAllKeys() {
        System.out.println("load all keys");

        Set<String> result = new HashSet<String>();
        Result<User> users = userAccessor.getAll();
        for (User user : users) {
            System.out.println("User : " + user.getFirstName());
            result.add(user.getFirstName());
        }
        return result;
    }
}
