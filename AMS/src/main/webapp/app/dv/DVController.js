Ext.define('Admin.system.dv.DVController', {
    extend: 'Admin.system.base.BaseController',
    alias: 'controller.dvController',
    init: function () {
        this.control({
             'views': {
                'openView': this.openView
            }
        });
        
        this.superclass.init.call(this);
    },
    onClickViewItem: function (cmp, b, c) {//TODO this will call openView()
        var gridTab = cmp.up('dvGridTab'),
                gridTabConfig = gridTab.config,
                data = cmp.config.data;

        this.openView(data, gridTabConfig.fields)
    },
    openView: function (data, fields) {
        var me = this,
                itemId = data.idx,
                existentTab = itemId && me.getView().down('#' + itemId);

        fields = fields || CurrentApp.fields; //When open from Main menu, take the fields from the Session

        if (existentTab) {
            me.getView().setActiveTab(existentTab);
        } else {
            var config = Ext.apply(data, {
                id: itemId,
                title: data.name,
                idDv: data.idDv,
                entity: data.entity,
                iconCls: data.iconCls,
                expressions: data.filters,
                closable: true,
                fields: fields
            });

            me.getView().setActiveTab(config);
        }
    },
    onCreateTableView: function (cmp) {
        var data = {
            xtype: 'tableView',
            iconCls: 'x-fa fa-table'
        };

        this.onCreateView(cmp, data, 'Table');
    },
    onCreateView: function (cmp, data, viewType) {
        var gridTab = cmp.up('dvGridTab'),
                grid = gridTab.items.items[0];

        data = Ext.apply(data, {
            idDv: gridTab.idDv,
            entity: gridTab.entity,
            iconCls: data.iconCls,
            fields: gridTab.fields,
            filters: grid.getParams(),
            menu: true
        });

        this.showEditViewWindow(data, viewType);
    },
    showEditViewWindow: function (data, viewType) {
        var me = this;

        var win = Ext.create('Admin.system.views.edit.' + viewType + 'ViewWindow', {
            onSave: function (cmp) {
                me.onSaveView(cmp);
            },
            data: data
        });
        win.show();
    },
    onSaveView: function (cmp, b, c) {
        var me = this,
                window = cmp.up().up(),
                data = window.getData(),
                gridTab = me.getView().down('dvGridTab'),
                gridTabConfig = gridTab.config,
                dataId = data.id;

        Request.load({
            url: '0/view/save',
            method: 'POST',
            mask: 'Saving View ' + data.name + '...',
            jsonData: data,
            ignoreError: true,
            success: function (response) {
                if (response.status === 500) {//en caso de lanzar una exepcion
                    Ext.Msg.alert('Information', response.statusMessage);
                } else {
                    data = Ext.apply(data, {
                        id: response.data.id,
                        idx: response.data.idx
                    });

                    me.openView(data, gridTabConfig.fields);

                    if (!dataId) {
                        var viewsMenu = gridTab.down('viewsButton');

                        viewsMenu && viewsMenu.menu.insert(0, {
                            text: data.name,
                            iconCls: data.iconCls,
                            handler: 'onClickViewItem',
                            data: data
                        });
                    }
                }

                window.close();
            }
        });
    }
});
