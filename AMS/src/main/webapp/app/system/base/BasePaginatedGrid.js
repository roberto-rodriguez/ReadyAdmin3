Ext.define('Admin.system.base.BasePaginatedGrid', {
    extend: 'Ext.grid.Panel',
    xtype: 'basePaginatedGrid',
    alias: 'widget.basePaginatedGrid',
    frame: false,
    header: false,
    height: 415,
    width: '100%',
    titleAlign: 'center',
    closable: true,
    showigOnTop: true,
    forceFit: true, //Esto es una configuracion native de Ext, que cuando se usa flex hace que se cubra toda la tabla
    loadAdditionalInfoWhenDblClick: null, //If true, we consume the /load Web Service when DblClick 
    openViewTab: null, //Tab to open when doubleclick the grid  
    //(if not specified, it will be deduced from the grid's name) 
    expressions: null, //Filters to be applied to the grid
    style: {
        borderColor: '#808080',
        borderStyle: 'solid',
        'border-width': '1px 0px 1px 0px',
        marginTop: '10px'
    },
//  selModel: { selType: 'checkboxmodel' }, //Use this with the gridMultipageSelection plugin
    constructor: function (config) {
        var me = this;
        Ext.apply(me, config);
        me.callParent(arguments);
    },
    listeners: {
        cellclick: 'onGridItemClick',
        itemdblclick: function (row, record) {
            this.fireEvent('doGridItemdblclick', record.data, this);
        },
        doGridItemdblclick: 'onGridItemDblClick'
    },
    initComponent: function () {
        var me = this;
        Ext.apply(this,
                {
                    bbar: {
                        xtype: 'pagingtoolbar',
                        store: me.getStore(),
                        displayInfo: true,
                        displayMsg: 'Displaying {0} - {1} of {2}',
                        emptyMsg: "No entries to show",
                        style: {
                            color: '#555555',
                            align: 'center'
                        },
                        listeners: {
                            single: true,
                            render: function () {
                                var items = this.items;
                                items.insert(0, Ext.create('Ext.toolbar.Fill'));
                                items.add(Ext.create('Ext.toolbar.Fill'));
                            }
                        }
                    }
                });

        var expressionsObj = {};
        
        if(me.expressions){
            Ext.Array.each(me.expressions.split(C.AND), function(exp){var kv = exp.split(C.IS); expressionsObj[kv[0]] = kv[1];});
        } 

        me.columns.items.forEach(function (column) { 
            column.items = [{
                    xtype: column.filterType || 'filterString',
                    flex: 1,
                    expression: column.dataIndex && expressionsObj[column.dataIndex] 
                }];
        });

        me.columns.defaults = {flex: 1, align: 'center'};

        me.callParent(arguments); //important call this before set store and Model

        //_______________ Setting Store and Model _______________  
        var gridTab = me.up(),
                entity = gridTab.entity,
                idDv = gridTab.idDv,
                url = WS.HOST + idDv + '/' + entity + '/list';


        var store = Ext.create('Ext.data.Store', {
            autoLoad: false,
            autoSync: false,
            pageSize: 10,
            model: Ext.create('Ext.data.Model', {
                fields: Ext.Array.map(me.columns, function (col) {
                    return {name: col.dataIndex}
                })
            }),
            proxy: {// Need to declare the proxy here to make the Store singlenton
                type: 'ajax',
                api: {
                    read: url
                },
                reader:
                        {
                            type: 'json',
                            rootProperty: 'List',
                            totalProperty: 'TotalCount'
                        },
                headers: {
                    'TOKEN': Session.Principal.token
                }
            }
        });
        me.setStore(store);
    },
    plugins: [
        {ptype: "gridFilter"}
//      {ptype: "gridMultipageSelection"}
    ],
    getParams: function () {
        var me = this,
                searchParams = me.up().getFilters();
        me.columns.forEach(function (column) {
            var cmp = column.items.items[0];
            var val = cmp.getValue();

            if (val) {
                if (searchParams) {
                    searchParams += C.AND
                }

                searchParams += (column.filter || column.dataIndex) + C.IS + cmp.prefix + val;
            }
        });
        return searchParams;
    }
});