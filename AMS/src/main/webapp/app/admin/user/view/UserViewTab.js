Ext.define('Admin.admin.user.view.UserViewTab', {
    extend:'Admin.system.base.BaseViewTab',
    xtype: 'userViewTab', 
    viewName:'User', 
    entity:'user',
    items: [
        {
            xtype: 'userDetails'
        },
        {
            xtype: 'userEditor' 
        }
    ],
    setTabTitle: function (newData) {
        var me = this,
                data = newData || me.config.data,
                title = '';

        if (data && data.id) {
            title = 'User: ' + data.firstName + ' '+ data.lastName;
        } else {
            title = 'New ' + me.viewName;
        }
        me.setTitle(title);
    }
});
