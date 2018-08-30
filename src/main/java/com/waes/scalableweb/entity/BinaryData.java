package com.waes.scalableweb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


/*
 *Entity class which holds binary data in left, right or both.
 * @author ttdduman
 */

@Entity
public class BinaryData {

    @Id
    @Column(name="ID")
    private long id;

    @Column(name="LEFT")
    private String left;

    @Column(name="RIGHT")
    private String right;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public BinaryData(long id, String left, String right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public BinaryData() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryData that = (BinaryData) o;

        if (id != that.id) return false;
        if (!left.equals(that.left)) return false;
        return right.equals(that.right);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + left.hashCode();
        result = 31 * result + right.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BinaryData{" +
                "id=" + id +
                ", left='" + left + '\'' +
                ", right='" + right + '\'' +
                '}';
    }
}
