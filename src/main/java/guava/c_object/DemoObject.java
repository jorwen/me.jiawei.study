package guava.c_object;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class DemoObject {
    public static void main(String[] args) {
        //equals
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true

        //toString
        // Returns "MyObject{x=1}"
        MoreObjects.toStringHelper("MyObject").add("x", 1).toString();

        //hashCode
       Objects.hashCode(1L,2L,3L);
    }
}

//compare
class Person implements Comparable<Person>{
    private String lastName;
    private String firstName;
    private int zipCode;

    public int compareTo(Person that) {
        return ComparisonChain.start()
                .compare(this.lastName, that.lastName)
                .compare(this.firstName, that.firstName)
                .compare(this.zipCode, that.zipCode, Ordering.natural().nullsLast())
                .result();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
