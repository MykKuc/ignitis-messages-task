/*
 * This file is generated by jOOQ.
 */
package com.mykolas.ignitismessagetask.jooqdatabase.tables.records;


import com.mykolas.ignitismessagetask.jooqdatabase.tables.Users;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UsersRecord extends UpdatableRecordImpl<UsersRecord> implements Record6<Integer, String, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>APP.USERS.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>APP.USERS.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>APP.USERS.EMAIL</code>.
     */
    public void setEmail(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>APP.USERS.EMAIL</code>.
     */
    public String getEmail() {
        return (String) get(1);
    }

    /**
     * Setter for <code>APP.USERS.NAME</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>APP.USERS.NAME</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>APP.USERS.PASSWORD</code>.
     */
    public void setPassword(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>APP.USERS.PASSWORD</code>.
     */
    public String getPassword() {
        return (String) get(3);
    }

    /**
     * Setter for <code>APP.USERS.TOKEN</code>.
     */
    public void setToken(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>APP.USERS.TOKEN</code>.
     */
    public String getToken() {
        return (String) get(4);
    }

    /**
     * Setter for <code>APP.USERS.ROLE</code>.
     */
    public void setRole(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>APP.USERS.ROLE</code>.
     */
    public String getRole() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, String, String, String, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Users.USERS.ID;
    }

    @Override
    public Field<String> field2() {
        return Users.USERS.EMAIL;
    }

    @Override
    public Field<String> field3() {
        return Users.USERS.NAME;
    }

    @Override
    public Field<String> field4() {
        return Users.USERS.PASSWORD;
    }

    @Override
    public Field<String> field5() {
        return Users.USERS.TOKEN;
    }

    @Override
    public Field<String> field6() {
        return Users.USERS.ROLE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getEmail();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public String component4() {
        return getPassword();
    }

    @Override
    public String component5() {
        return getToken();
    }

    @Override
    public String component6() {
        return getRole();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getEmail();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public String value4() {
        return getPassword();
    }

    @Override
    public String value5() {
        return getToken();
    }

    @Override
    public String value6() {
        return getRole();
    }

    @Override
    public UsersRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public UsersRecord value2(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public UsersRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public UsersRecord value4(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public UsersRecord value5(String value) {
        setToken(value);
        return this;
    }

    @Override
    public UsersRecord value6(String value) {
        setRole(value);
        return this;
    }

    @Override
    public UsersRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UsersRecord
     */
    public UsersRecord() {
        super(Users.USERS);
    }

    /**
     * Create a detached, initialised UsersRecord
     */
    public UsersRecord(Integer id, String email, String name, String password, String token, String role) {
        super(Users.USERS);

        setId(id);
        setEmail(email);
        setName(name);
        setPassword(password);
        setToken(token);
        setRole(role);
    }
}
