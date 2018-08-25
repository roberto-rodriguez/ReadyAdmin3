Ext.define('Admin.system.column.MaskGridColumn', {
    extend: 'Ext.grid.column.Column',
    xtype: 'maskGridColumn',  
    sortable: true,
    mask: '*****',
    falseVal: 'false',
    renderer: function (value, metaData) {  
        return value && ( metaData.column.mask + value);
    }
});
 