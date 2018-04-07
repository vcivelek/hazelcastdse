package com.hazelcast.dse.mapstore;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.*;

public class HazelcastApp {

    public static void main(String[] args) {
        User vc = new User("volkan", "civelek");
        User mc = new User("mesut", "celik");

        // during the startup, loadAllKeys is called to load all the keys to cache.
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        IMap<String, User> engineers = hazelcastInstance.getMap("dse_hazelcast");

        // put new engineers to the cache, which will also get stored on dse.
        engineers.put(vc.getFirstName(),vc);
        engineers.put(mc.getFirstName(),mc);

        // get one user from cache
        System.out.println(engineers.get(vc.getFirstName()));

        // delete the user both from cache and db
        engineers.delete(mc.getFirstName());

        // get the user deleted above. this returns null.
        System.out.println(engineers.get(mc.getFirstName()));

        // load all the new engineers
        Set<String> names = new HashSet<String>();
        names.add("volkan");
        names.add("mesut");
        engineers.loadAll(names, true);

        // get one user from cache. this returns null.
        System.out.println(engineers.get(mc.getFirstName()));

        // get one user from cache. this returns the object from cache.
        System.out.println(engineers.get(vc.getFirstName()));

        System.exit(0);

    }
}
