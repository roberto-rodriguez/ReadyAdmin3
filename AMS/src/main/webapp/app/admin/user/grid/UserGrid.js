Ext.define('Admin.admin.user.grid.UserGrid', {
    extend: 'Admin.system.base.BasePaginatedGrid',
    alias: 'widget.userGrid',
    columns: { 
        items: [
            {
                xtype: 'idGridColumn'
            },
            { 
                xtype: 'booleanGridColumn',
                text: 'Active', 
                dataIndex: 'active',
                filterType:'filterBoolean' 
            },
            {
                xtype: 'firstNameGridColumn' 
            },
            {
                xtype: 'lastNameGridColumn' 
            },            
            {
                text: "Email",
                dataIndex: 'email' 
            }    
        ]
    }
});