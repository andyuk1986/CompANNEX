package com.compannex.dao;

import java.io.Serializable;

import com.compannex.model.Industry;

/**
 * The DAO interface which includes all necessary methods for working with DB.
 */
public interface IndustryDao extends Serializable{
    /**
     * Returns the Industry by the given ID.
     * @param id    the ID of the Industry in DB.
     * @return      the Industry object representing the one in DB.
     */
    public Industry getIndustryById(final int id);

    /**
     * Saves the Industry according to the given entity.
     * @param doc   the Industry to save.
     */
    public void saveIndustry(final Industry ind);
    
    /**
     * Updates the Industry according to the given entity.
     * @param doc   the Industry to edit.
     */
    public void editIndustry(final Industry inds);
    
    /**
     * Returns Industry by name.
     * @param indName the name of searched Industry.
     * @return Industry entity.
     */
    public Industry getIndustryByName(final String indName);
    
    
}
