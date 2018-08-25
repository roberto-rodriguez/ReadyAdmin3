Ext.define('Admin.admin.rolePage.grid.RolePageGrid', {
    extend: 'Admin.system.base.BasePaginatedGrid',
    alias: 'widget.rolePageGrid',
    columns: { 
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Page",
                dataIndex: 'page',
                filter: 'page.title',
                flex: 3
            },
            { 
                xtype: 'booleanGridColumn',
                text: 'Create', 
                dataIndex: 'create',
                filterType:'filterBoolean' 
            },
            { 
                xtype: 'booleanGridColumn',
                text: 'Update', 
                dataIndex: 'update',
                filterType:'filterBoolean' 
            },
            { 
                xtype: 'booleanGridColumn',
                text: 'Delete', 
                dataIndex: 'delete',
                filterType:'filterBoolean' 
            }
        ]
    }
});


