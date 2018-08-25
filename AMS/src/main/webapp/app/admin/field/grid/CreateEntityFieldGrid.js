Ext.define('Admin.admin.field.grid.CreateEntityFieldGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'createEntityFieldGrid',
    frame: false,
    header: false,
    height: 250,
    width: '100%',
    titleAlign: 'center',
    closable: true,
    showigOnTop: false,
    forceFit: true,
    style: {
        borderColor: '#808080',
        borderStyle: 'solid',
        'border-width': '1px 0px 0px 0px',
        marginTop: '10px'
    },
    initComponent: function () {
        var me = this;
        me.callParent(arguments);

        me.setStore(Ext.create('Ext.data.Store', {
            autoLoad: false,
            autoSync: false,
            model: Ext.create('Ext.data.Model', {
                fields: Ext.Array.map(me.columns, function (item) {
                    return {name: item.dataIndex};
                })
            }),
            data: []
        }));
    },
    listeners: {
        cellclick: function (row, record, c, d) {
            this.up().up().up().down('fieldEditor').activate(d.data)
        }
    },
    columns: {
        defaults: {
            flex: 1,
            align: 'center'
        },
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
                dataIndex: 'reference'
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




