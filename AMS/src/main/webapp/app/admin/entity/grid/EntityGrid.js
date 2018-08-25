Ext.define('Admin.admin.entity.grid.EntityGrid', {
    extend: 'Admin.system.base.BasePaginatedGrid',
    alias: 'widget.entityGrid',
    columns: { 
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Entity name",
                dataIndex: 'name' 
            },
            {
                text: "Icon",
                dataIndex: 'icon',
            },                        
            {
                xtype: 'descriptionGridColumn' 
            }
        ]
    }
});
