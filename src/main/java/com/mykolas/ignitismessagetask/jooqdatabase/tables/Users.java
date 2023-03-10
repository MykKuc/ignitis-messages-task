/*
 * This file is generated by jOOQ.
 */
package com.mykolas.ignitismessagetask.jooqdatabase.tables;


import com.mykolas.ignitismessagetask.jooqdatabase.App;
import com.mykolas.ignitismessagetask.jooqdatabase.Keys;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.records.UsersRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Check;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Users extends TableImpl<UsersRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>APP.USERS</code>
     */
    public static final Users USERS = new Users();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UsersRecord> getRecordType() {
        return UsersRecord.class;
    }

    /**
     * The column <code>APP.USERS.ID</code>.
     */
    public final TableField<UsersRecord, Long> ID = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>APP.USERS.EMAIL</code>.
     */
    public final TableField<UsersRecord, String> EMAIL = createField(DSL.name("EMAIL"), SQLDataType.VARCHAR(60), this, "");

    /**
     * The column <code>APP.USERS.NAME</code>.
     */
    public final TableField<UsersRecord, String> NAME = createField(DSL.name("NAME"), SQLDataType.VARCHAR(40), this, "");

    /**
     * The column <code>APP.USERS.PASSWORD</code>.
     */
    public final TableField<UsersRecord, String> PASSWORD = createField(DSL.name("PASSWORD"), SQLDataType.VARCHAR(250), this, "");

    /**
     * The column <code>APP.USERS.TOKEN</code>.
     */
    public final TableField<UsersRecord, String> TOKEN = createField(DSL.name("TOKEN"), SQLDataType.VARCHAR(250), this, "");

    /**
     * The column <code>APP.USERS.ROLE</code>.
     */
    public final TableField<UsersRecord, String> ROLE = createField(DSL.name("ROLE"), SQLDataType.VARCHAR(30), this, "");

    /**
     * The column <code>APP.USERS.ISACTIVE</code>.
     */
    public final TableField<UsersRecord, Boolean> ISACTIVE = createField(DSL.name("ISACTIVE"), SQLDataType.BOOLEAN.defaultValue(DSL.field("TRUE", SQLDataType.BOOLEAN)), this, "");

    private Users(Name alias, Table<UsersRecord> aliased) {
        this(alias, aliased, null);
    }

    private Users(Name alias, Table<UsersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>APP.USERS</code> table reference
     */
    public Users(String alias) {
        this(DSL.name(alias), USERS);
    }

    /**
     * Create an aliased <code>APP.USERS</code> table reference
     */
    public Users(Name alias) {
        this(alias, USERS);
    }

    /**
     * Create a <code>APP.USERS</code> table reference
     */
    public Users() {
        this(DSL.name("USERS"), null);
    }

    public <O extends Record> Users(Table<O> child, ForeignKey<O, UsersRecord> key) {
        super(child, key, USERS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : App.APP;
    }

    @Override
    public Identity<UsersRecord, Long> getIdentity() {
        return (Identity<UsersRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<UsersRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_4;
    }

    @Override
    public List<Check<UsersRecord>> getChecks() {
        return Arrays.asList(
            Internal.createCheck(this, DSL.name("ALLOW_ONLY_ADMIN_OR_USER"), "\"ROLE\" IN('ROLE_ADMIN', 'ROLE_USER')", true)
        );
    }

    @Override
    public Users as(String alias) {
        return new Users(DSL.name(alias), this);
    }

    @Override
    public Users as(Name alias) {
        return new Users(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Users rename(String name) {
        return new Users(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Users rename(Name name) {
        return new Users(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, String, String, String, String, String, Boolean> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
