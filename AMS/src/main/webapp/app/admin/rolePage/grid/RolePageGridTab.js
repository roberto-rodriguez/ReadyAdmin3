Ext.define('Admin.admin.rolePage.grid.RolePageGridTab', {
    extend: 'Admin.system.base.BaseGridTab',
    xtype: 'rolePageGridTab',
    title: 'Role - Pages',
    entity: 'rolePage',
    items: [
        {
            xtype: 'rolePageGrid'
        }
    ],
    tbar: [
        '->',
        {
            xtype: 'createButton'
        },
        {
            xtype: 'editGridRowButton'
        },
        {
            xtype: 'excelReportButton'
        },
        {
            xtype: 'pdfReportButton'
        },
        {
            xtype: 'showFilters'
        }
    ]
});


