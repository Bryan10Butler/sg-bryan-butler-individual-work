package com.sg.dao;

import com.sg.dto.VendingMachineItem;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendingMachineDaoDbImpl implements VendingMachineDao{

    //create
    private static final String SQL_INSERT_ITEM
            = "insert into Item "
            + "(`Name`, Price, Quantity) "
            + "VALUES (?, ?, ?)";

    //retrieve
    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from Item";

    //retrieve
    private static final String SQL_SELECT_ITEM
            = "select * from Item where ItemId = ?";

    //update
    private static final String SQL_UPDATE_ITEM
            = "update Item set "
            + "`Name` = ?, Price = ?, Quantity = ? "
            + "where ItemId = ?";

    //delete
    private static final String SQL_DELETE_ITEM
            = "delete from Item where ItemId = ?";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<VendingMachineItem> retrieveAllVendingMachineItems() throws VendingMachinePersistenceException {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS,
                new VendingMapper());
    }

    @Override
    public void updateItem(VendingMachineItem itemToBeUpdated) throws VendingMachinePersistenceException {

        jdbcTemplate.update(SQL_UPDATE_ITEM,
                itemToBeUpdated.getName(),
                itemToBeUpdated.getPrice(),
                itemToBeUpdated.getQuantity(),
                itemToBeUpdated.getItemId());
    }


    @Override
    public VendingMachineItem retrieveItemById(long itemId) throws VendingMachinePersistenceException {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM,
                    new VendingMapper(), itemId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just
            // want to return null in this case
            return null;
        }
    }

    @Override
    public void removeVendingMachineItemById(long itemId) throws VendingMachinePersistenceException {
        jdbcTemplate.update(SQL_DELETE_ITEM, itemId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public VendingMachineItem createVendingMachineItemById(VendingMachineItem createItem) throws VendingMachinePersistenceException {
        jdbcTemplate.update(SQL_INSERT_ITEM,
                createItem.getName(),
                createItem.getPrice(),
                createItem.getQuantity());
        long newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                long.class);
        createItem.setItemId(newId);
        return createItem;
    }

    private static final class VendingMapper implements RowMapper<VendingMachineItem> {
        public VendingMachineItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            VendingMachineItem vendingMachineItem = new VendingMachineItem();
            vendingMachineItem.setItemId(rs.getLong("ItemId"));
            vendingMachineItem.setName(rs.getString("Name"));
            vendingMachineItem.setPrice(rs.getBigDecimal("Price"));
            vendingMachineItem.setQuantity(rs.getInt("Quantity"));
            return vendingMachineItem;
        }
    }
}
