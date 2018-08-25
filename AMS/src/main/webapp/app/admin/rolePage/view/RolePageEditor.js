Ext.define('Admin.admin.rolePage.view.RolePageEditor', {
    extend: 'Admin.system.base.BaseEditor',
    alias: 'widget.rolePageEditor',
    items: [
        {
            items: [
                {
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Role',
                    xtype: 'baseSelectField',
                    name: 'roleId',
                    url: 'role',
                    applyFilter: 'id=(I)',
                    filterPropertyName: 'superData.id',
                    disabledIfHasValue:true,
                    defaultPropertyValue:'superData.id'
                },
                {
                    fieldLabel: 'Page',
                    xtype: 'baseSelectField',
                    name: 'pageId',
                    url: 'page',
                    allowBlank : false,                    
                    emptyText : "Select...",
                    editable : false,
                    disabledIfHasValue:true
                }
                
            ]
        },
        {
           items: [                
                {
                    xtype     : 'baseBooleanField',
                    fieldLabel: 'Create', 
                    width: "30%",                   
                    name: 'create'                   
                },
                {
                    xtype     : 'baseBooleanField',
                    fieldLabel: 'Update',
                    width: "30%",
                    name: 'update'                   
                },
                {
                    xtype     : 'baseBooleanField',
                    fieldLabel: 'Delete',
                    width: "30%",
                    name: 'delete'                   
                }
            ]
        }
    ]
});



