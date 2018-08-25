Ext.define('Admin.admin.field.grid.CreateEntityFieldGridTab', {
    extend: 'Admin.system.base.BaseGridTab',
    xtype: 'createEntityFieldGridTab',
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
            xtype: 'createEntityFieldGrid'
        }
    ]
});

