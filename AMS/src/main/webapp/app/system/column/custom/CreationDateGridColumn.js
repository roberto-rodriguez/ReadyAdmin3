Ext.define('Admin.system.column.custom.CreationDateGridColumn', {
    extend: 'Admin.system.column.DateGridColumn',
    xtype: 'creationDateGridColumn',
    text: "Creation date",
    dataIndex: 'createdAt',
    format: 'm/d/Y'   
});
 