Ext.define('Admin.admin.role.RoleSubPanel', {
    extend: 'Admin.system.base.BaseSubPanel',
    xtype: 'roleSubPanel',
    config: {
        subPanels: [
            {
                xtype: 'rolePageGridTab'
            }
        ]
    }
});


