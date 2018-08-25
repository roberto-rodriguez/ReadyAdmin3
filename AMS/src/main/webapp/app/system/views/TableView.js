Ext.define('Admin.system.views.TableView', {
    extend: 'Admin.system.base.BaseTab',
    xtype: 'tableView',
    idDv: null, //Id of the entity (will be overriden by the DVViewTab)  
    entity: null,
    expressions: null, //Filters to be applied to the grid
    initComponent: function () {
        var me = this;
        me.filters = me.entity + me.filters;
        me.items = [{
                xtype: 'dvGrid',
                fields: this.fields,
                expressions: this.expressions
            }];

        this.callParent(arguments);
    },
    tbar: [
        '->',
        {
            xtype: 'createButton'
        },
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
