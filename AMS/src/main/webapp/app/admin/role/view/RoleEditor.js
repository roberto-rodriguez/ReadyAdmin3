Ext.define('Admin.admin.role.view.RoleEditor', {
    extend: 'Admin.system.base.BaseEditor',
    alias: 'widget.roleEditor',
    items: [
        {
            items: [ 
                {
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Name',
                    name: 'name',
                    allowBlank : false
                },
                {
                    xtype     : 'baseStringAreaField',                    
                    name      : 'description',
                    fieldLabel: 'Description'
                }
            ]
        },
        {
            items: [ 
                {
                    xtype     : 'baseBooleanField',
                    fieldLabel: 'Admin',
                    name: 'isadmin' 
                }
            ]
        }
    ]
});


