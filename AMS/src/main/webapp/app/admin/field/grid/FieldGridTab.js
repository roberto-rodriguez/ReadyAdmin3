Ext.define('Admin.admin.field.grid.FieldGridTab', {
    extend: 'Admin.system.base.BaseGridTab',
    xtype: 'fieldGridTab',
    title: 'Fields',
    entity: 'field',
    tbar: [
        '->', 
        {
            xtype: 'editGridRowButton'
        },
        {
            xtype: 'deleteButton'
        } 
    ],
    items: [
        {
            xtype: 'fieldGrid'
        }
    ]
});

