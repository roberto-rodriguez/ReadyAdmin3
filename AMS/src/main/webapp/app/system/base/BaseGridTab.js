Ext.define('Admin.system.base.BaseGridTab', {
    extend: 'Admin.system.base.BaseTab',
    xtype: 'baseGridTab',
    idDv: 0, //Id of the entity (will be overriden by the DVViewTab)  
    entity: null,
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
