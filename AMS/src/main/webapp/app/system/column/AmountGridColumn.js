Ext.define('Admin.system.column.AmountGridColumn', {
    extend: 'Ext.grid.column.Column',
    xtype: 'doubleGridColumn',  
    sortable: true, 
    renderer: function (value, metaData) { 
        return Ext.util.Format.number(value, '0,000.00');
    }
});
 