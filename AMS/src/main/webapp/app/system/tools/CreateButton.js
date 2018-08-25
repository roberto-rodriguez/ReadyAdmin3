Ext.define('Admin.system.tools.CreateButton', {
    extend: 'Ext.button.Button',
    xtype: 'createButton',
    iconCls: 'x-fa fa-plus',
    tooltip: 'Create',
    listeners: {
        click: 'editGridRow'
    }
});