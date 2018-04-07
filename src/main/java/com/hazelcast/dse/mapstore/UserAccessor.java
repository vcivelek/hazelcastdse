package com.hazelcast.dse.mapstore;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface UserAccessor {
    @Query("SELECT * FROM user")
    Result<User> getAll();
}
