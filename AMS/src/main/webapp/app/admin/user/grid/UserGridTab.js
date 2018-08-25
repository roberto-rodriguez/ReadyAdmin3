Ext.define('Admin.admin.user.grid.UserGridTab', {
    extend:'Admin.system.base.BaseGridTab',
    xtype: 'userGridTab', 
    title: 'Users',  
    entity:'user',
    filters:'user.id',
    items: [ 
        {
            xtype: 'userGrid' 
        }
    ]
});
