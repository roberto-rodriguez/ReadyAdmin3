Ext.define('Admin.system.column.DateGridColumn', {
    extend: 'Ext.grid.column.Date',
    xtype: 'dateGridColumn',
    sortable: true,
    format: 'm-d-Y',
    renderer: function (value, metaData) {
        if (value) {
            var date = new Date(value);
            return Ext.Date.format(date, metaData.column.format);
        } else {
            return '';
        }

    }
});
 