package edu.bhcc.model;

import java.beans.JavaBean;

/**
 * Category Model.
 */
@JavaBean
public class Category {
    /**
     * Primary key.
     */
    private long id;

    /**
     * The name of the category.
     */
    private String name;

    /**
     * Money allocated (in cent).
     */
    private int allocated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAllocated() {
        return allocated;
    }

    public void setAllocated(int allocated) {
        this.allocated = allocated;
    }
}
