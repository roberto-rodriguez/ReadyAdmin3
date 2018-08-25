Ext.define('Admin.system.tools.EditGridRowButton', {
    extend: 'Ext.button.Button',
    xtype: 'editGridRowButton',
    iconCls: 'x-fa fa-edit',
    tooltip: 'Edit',
    listeners: {
        click: 'editGridRow'
    }
});