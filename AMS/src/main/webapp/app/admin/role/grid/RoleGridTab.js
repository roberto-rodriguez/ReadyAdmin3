Ext.define('Admin.admin.role.grid.RoleGridTab', {
    extend: 'Admin.system.base.BaseGridTab',
    xtype: 'roleGridTab',
    title: 'Roles',
    filters: 'role.id',
    entity: 'role',
    items: [
        {
            xtype: 'roleGrid'
        },
//        {
//            xtype: 'roleSubPanel'
//        }
    ]
});



