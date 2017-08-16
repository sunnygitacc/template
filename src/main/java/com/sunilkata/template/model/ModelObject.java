package com.sunilkata.template.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Sunil Kata on 7/30/2017.
 */

@Entity
@NamedNativeQueries({
        @NamedNativeQuery(name="insertTest", query="INSERT INTO TEST_TABLE (TEST_ID) VALUES (:testValue)"),
        @NamedNativeQuery(name="fetchTest", query="SELECT TEST_ID FROM TEST_TABLE")
})

public class ModelObject implements Serializable{
    @Id
    @Column(name="TEST_ID")
    @JsonProperty
    String testString;

    public ModelObject(String testString) {
        this.testString = testString;
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelObject that = (ModelObject) o;

        return testString != null ? testString.equals(that.testString) : that.testString == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (testString != null ? testString.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ModelObject{" +
                "testString='" + testString + '\'' +
                '}';
    }
}
