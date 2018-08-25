Ext.define('Admin.admin.role.grid.RoleGrid', {
    extend: 'Admin.system.base.BasePaginatedGrid',
    alias: 'widget.roleGrid',
    columns: { 
        items: [
            {
                xtype: 'idGridColumn'
            },            
            {
                xtype: 'booleanGridColumn',
                dataIndex: 'isadmin',
                text: 'Admin' 
            },
            {
                xtype: 'nameGridColumn' 
            },
                        
            {
                xtype: 'descriptionGridColumn',
                flex: 3
            }
        ]
    }
});


