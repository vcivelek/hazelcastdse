package com.hazelcast.dse.mapstore;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;

import java.io.Serializable;

@Table(keyspace = "mapper_test", name = "user",
        readConsistency = "ONE",
        writeConsistency = "ONE")

public class User implements Serializable{

    @PartitionKey
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    public User() {
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[firstName='%s', lastName='%s']",
                this.firstName, this.lastName);
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}