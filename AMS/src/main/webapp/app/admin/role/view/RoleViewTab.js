Ext.define('Admin.admin.role.view.RoleViewTab', {
    extend: 'Admin.system.base.BaseViewTab',
    xtype: 'roleViewTab',
    viewName: 'Role',
    entity: 'role',
    items: [
        {
            xtype: 'roleDetails'
        },
        {
            xtype: 'roleEditor'
        },
//        {
//            xtype: 'roleSubPanel'
//        }
    ],
    setTabTitle: function (newData) {
        var me = this,
                data = newData || me.config.data,
                title = '';

        if (data && data.id) {
            title = 'Role: ' + data.name;
        } else {
            title = 'New ' + me.viewName;
        }
        me.setTitle(title);
    }
});

