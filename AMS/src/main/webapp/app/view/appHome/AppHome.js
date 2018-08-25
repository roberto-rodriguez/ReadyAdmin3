Ext.define('Admin.view.home.AppHome', {
    extend: 'Ext.panel.Panel',
    xtype: 'appHome',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    padding: 10,
    autoScroll: true,
    overflowY: 'scroll',
    entities: [],
    appId: null,
    idx: null, //App idx
    initComponent: function () {
        var me = this,
                currentApp = Session.CurrentApp || {},
                entities = currentApp.entities || [];

        entities = Ext.Array.map(entities, function (entity) {
            return {
                name: entity.name,
                icon: entity.icon,
                data: Ext.apply({}, entity)
            }
        }).concat([{
                name: 'Create New Entity', icon: 'fa-plus', color: 'grey'
            }]);

        me.items = [
            {
                title: 'Entities',
                items: Util.getResponsiveRows(entities, 180),
                header: {
                    titlePosition: 0,
                    items: [
                        { 
                            ui: 'light',
                            iconCls: 'x-fa fa-question-circle',
                            handler: function () {
                                alert('Help');
                            }
                        }
                    ]
                }
            }
        ]

        this.callParent(arguments);
    },
    defaults: {
        expanded: true,
        layout: 'vbox',
        cls: 'add-border-bottom',
        ui: 'light',
        defaults: {
            layout: 'hbox',
            defaults: {
                xtype: 'card',
                onCardClick: function (data) {
                    Ext.GlobalEvents.fireEvent('selectView', data.idx);
                }
            }
        }
    },
    items: []
});
