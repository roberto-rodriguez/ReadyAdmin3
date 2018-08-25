/*!
 * Ext JS GridFilter plugin v0.1
 * https://github.com/roberto-rodriguez/ExtJs_GridFilterPlugin
 *
 *
 * Copyright 2016 Roberto Rodriguez
 * Licensed under GNU General Public License v3.
 * http://www.gnu.org/licenses/
 * 
 * 
 * @class Ext.ux.grid.GridFilter
 * @extends Ext.AbstractPlugin
 * 
 * Grid plugin that adds filtering row below grid header.
 * Allows remote filtering, supports pagination grids.
 *  
 *  
 * <b>What it does?</b>
 * When the user adds criterias to the filter and hits enter,
 * The plugin reload the grid, including in the query params the values of the filters
 *  
 */

Ext.define('Ext.ux.grid.GridFilter', {
    extend: 'Ext.AbstractPlugin',
    alias: 'plugin.gridFilter',
    init: function (grid) {

        var me = this;
        grid.relayEvents(grid.getStore(), ['viewready', 'load', 'beforeload', 'sortchange']);
        grid.addListener('beforeload', me.onBeforeLoad);
    },
    onBeforeLoad: function (store, operation, eOpts) {
        var me = this,
                searchParams = me.getParams(); 
        me.getStore().proxy.extraParams = {params: searchParams};
    }
});