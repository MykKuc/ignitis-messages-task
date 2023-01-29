/*
 * This file is generated by jOOQ.
 */
package com.mykolas.ignitismessagetask.jooqdatabase.tables;


import com.mykolas.ignitismessagetask.jooqdatabase.App;
import com.mykolas.ignitismessagetask.jooqdatabase.Keys;
import com.mykolas.ignitismessagetask.jooqdatabase.tables.records.MessagesRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Messages extends TableImpl<MessagesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>APP.MESSAGES</code>
     */
    public static final Messages MESSAGES = new Messages();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MessagesRecord> getRecordType() {
        return MessagesRecord.class;
    }

    /**
     * The column <code>APP.MESSAGES.ID</code>.
     */
    public final TableField<MessagesRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>APP.MESSAGES.AUTHOR_ID</code>.
     */
    public final TableField<MessagesRecord, Integer> AUTHOR_ID = createField(DSL.name("AUTHOR_ID"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>APP.MESSAGES.TIME</code>.
     */
    public final TableField<MessagesRecord, LocalDateTime> TIME = createField(DSL.name("TIME"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>APP.MESSAGES.LENGTH</code>.
     */
    public final TableField<MessagesRecord, Integer> LENGTH = createField(DSL.name("LENGTH"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>APP.MESSAGES.CONTENT</code>.
     */
    public final TableField<MessagesRecord, String> CONTENT = createField(DSL.name("CONTENT"), SQLDataType.VARCHAR(400), this, "");

    /**
     * The column <code>APP.MESSAGES.RECEIVER_ID</code>.
     */
    public final TableField<MessagesRecord, Integer> RECEIVER_ID = createField(DSL.name("RECEIVER_ID"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>APP.MESSAGES.AUTHORACTIVE</code>.
     */
    public final TableField<MessagesRecord, Boolean> AUTHORACTIVE = createField(DSL.name("AUTHORACTIVE"), SQLDataType.BOOLEAN.defaultValue(DSL.field("TRUE", SQLDataType.BOOLEAN)), this, "");

    private Messages(Name alias, Table<MessagesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Messages(Name alias, Table<MessagesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>APP.MESSAGES</code> table reference
     */
    public Messages(String alias) {
        this(DSL.name(alias), MESSAGES);
    }

    /**
     * Create an aliased <code>APP.MESSAGES</code> table reference
     */
    public Messages(Name alias) {
        this(alias, MESSAGES);
    }

    /**
     * Create a <code>APP.MESSAGES</code> table reference
     */
    public Messages() {
        this(DSL.name("MESSAGES"), null);
    }

    public <O extends Record> Messages(Table<O> child, ForeignKey<O, MessagesRecord> key) {
        super(child, key, MESSAGES);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : App.APP;
    }

    @Override
    public Identity<MessagesRecord, Integer> getIdentity() {
        return (Identity<MessagesRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<MessagesRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_1;
    }

    @Override
    public List<ForeignKey<MessagesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.AUTHOR_ID_FK, Keys.RECEIVER_ID_FK);
    }

    private transient Users _authorIdFk;
    private transient Users _receiverIdFk;

    /**
     * Get the implicit join path to the <code>APP.USERS</code> table, via the
     * <code>AUTHOR_ID_FK</code> key.
     */
    public Users authorIdFk() {
        if (_authorIdFk == null)
            _authorIdFk = new Users(this, Keys.AUTHOR_ID_FK);

        return _authorIdFk;
    }

    /**
     * Get the implicit join path to the <code>APP.USERS</code> table, via the
     * <code>RECEIVER_ID_FK</code> key.
     */
    public Users receiverIdFk() {
        if (_receiverIdFk == null)
            _receiverIdFk = new Users(this, Keys.RECEIVER_ID_FK);

        return _receiverIdFk;
    }

    @Override
    public Messages as(String alias) {
        return new Messages(DSL.name(alias), this);
    }

    @Override
    public Messages as(Name alias) {
        return new Messages(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Messages rename(String name) {
        return new Messages(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Messages rename(Name name) {
        return new Messages(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Integer, Integer, LocalDateTime, Integer, String, Integer, Boolean> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}