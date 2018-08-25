Ext.define('Admin.system.tools.DeleteButton', {
    extend: 'Ext.button.Button',
    xtype: 'deleteButton',
    iconCls: 'x-fa fa-trash',
    tooltip: 'Delete',
    listeners: {
        click: 'deleteGridRow'
    }
});