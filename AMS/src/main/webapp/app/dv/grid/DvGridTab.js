Ext.define('Admin.dv.grid.DvGridTab', {
    extend: 'Admin.system.base.BaseGridTab',
    xtype: 'dvGridTab',
    title: null, //Will be set by the DVContainer 
    fields: null, //Will be set by the DVContainer 
    entity: null,
    idDv: null,
    filters: '.id',
    constructor: function (config) {
        var me = this;

        me.items = [{
                xtype: 'dvGrid',
                fields: config.fields
            }];

        me.callParent(arguments);
    },
    tbar: [
        {
            xtype: 'viewsButton'
        },
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
        },
        {
            xtype: 'splitbutton',
            handler: function () {},
            tooltip: {text: 'Configurations'},
            iconCls: 'x-fa fa-gear',
            menu: {
                
                items: [{
                        text: 'Configure Entity', handler: function () {},
                        iconCls: 'x-fa fa-gears' 
                    }, {
                        text: 'Add a Field',
                        iconCls: 'x-fa fa-plus',
                        handler: function () {},
                        menu: {
                            items: [
                                {
                                    text: 'Text',
//                                    iconCls: 'x-fa fa-table',
                                    handler: function () {}
                                },
                                {
                                    text: 'Number',
//                                    iconCls: 'x-fa fa-calendar-o',
                                    handler: function () {}
                                },
                                {
                                    text: 'Date',
//                                    iconCls: 'x-fa fa-columns',
                                    handler: function () {}
                                },
                                {
                                    text: 'Entity',
//                                    iconCls: 'x-fa fa-columns',
                                    handler: function () {}
                                }
                            ]
                        }
                    }, '-', {
                        text: 'What is an <b>Entity</b> ?',
                        iconCls: 'x-fa fa-question-circle',
                        handler: function () {}
                    }]
            }
        },
    ],
    items: [
        {
            xtype: 'dvGrid'
        }
//        ,
//        {
//            xtype: 'dvSubPanel'
//        }
    ] 
});


