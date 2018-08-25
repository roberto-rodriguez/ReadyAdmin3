Ext.define('Admin.admin.entity.grid.EntityGridTab', {
    extend: 'Admin.system.base.BaseGridTab',
    iconCls: 'x-fa fa-cube',
    xtype: 'entityGridTab',
    title: 'Entities',
    entity: 'entity',
    filters: 'entity.id',
    getAdditionalFilters: function () {
        return "app.id" +  C.IS + '(I)' + Session.CurrentApp.id;
    },
    items: [
        {
            xtype: 'entityGrid'
        },
        {
            xtype: 'entitySubPanel'
        }
    ],
    tbar: [
        {
            text: 'Create Entity',
            cls: 'active-button',
            iconCls: 'x-fa fa-plus',
            ui: 'green',
            listeners: {
                click: 'editGridRow'
            }
        },
        '->',
        {
            xtype: 'editGridRowButton'
        },
        {
            xtype: 'deleteButton'
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


