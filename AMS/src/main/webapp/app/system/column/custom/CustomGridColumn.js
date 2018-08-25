Ext.define('Admin.system.column.custom.CustomGridColumn', {
    extend: 'Ext.grid.column.Date',
    xtype: 'customGridColumn',  
    sortable: true,
    data: {}, 
    renderer: function (value, metaData) {  
        return metaData.column.config.data[value];
    }
});
 