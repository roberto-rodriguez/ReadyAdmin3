Ext.define('Admin.admin.rolePage.view.RolePageViewTab', {
    extend: 'Admin.system.base.BaseViewTab',
    xtype: 'rolePageViewTab',
    viewName: 'Page',
    entity: 'rolePage',
    items: [
        {
            xtype: 'rolePageDetails'
        },
        {
            xtype: 'rolePageEditor'
        }
    ],
    setTabTitle: function (newData) {
        var me = this,
                data = newData || me.config.data,
                title = '';

        if (data && data.id) {
            title = 'Role: ' + data.role + ' - Page: ' + data.page;
        } else {
            title = 'New ' + me.viewName;
        }
        me.setTitle(title);
    }
});


