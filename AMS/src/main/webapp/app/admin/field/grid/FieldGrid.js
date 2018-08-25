Ext.define('Admin.admin.field.grid.FieldGrid', {
    extend: 'Admin.system.base.BasePaginatedGrid',
    alias: 'widget.fieldGrid',
    columns: { 
        items: [
            {
                xtype: 'idGridColumn'
            },
            {
                text: "Name",
                dataIndex: 'name',
                align: 'left'
            },
            {
                text: "Type",
                dataIndex: 'type'
            },
            {
                text: "Reference",
                dataIndex: 'ref'
            },
            {
                xtype: 'booleanGridColumn',
                text: 'Show in grid',
                dataIndex: 'grid',
                filterType: 'filterBoolean'
            }
        ]
    }
});
