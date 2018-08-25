Ext.define('Admin.system.tools.ViewsButton', {
    extend: 'Ext.SplitButton',
    xtype: 'viewsButton',
    text: 'Views',
    views: null,
    ui: 'green',
    tooltip: {text: 'Represent your data in different prospectives', title: 'Create Views'},
    iconCls: 'x-fa fa-cube',
    listeners: {
        render: function (cmp, b, c) {
            var me = this;

            if (Session.CurrentApp.views) {
                Ext.Array.each(Session.CurrentApp.views, function (view) {
                    me.menu.add({
                        text: view.name,
                        iconCls: view.iconCls,
                        handler: 'onClickViewItem',
                        data: view
                    });
                });

                me.menu.add('-');
            }

            Ext.Array.each(this.staticItems, function (item) {
                me.menu.add(item);
            });
        },
    },
    menu: {
        items: []
    },
    staticItems: [
        {
            text: 'Add a View',
            iconCls: 'x-fa fa-plus',
            handler: function () {},
            menu: {
                items: [
                    {
                        text: 'Table View',
                        iconCls: 'x-fa fa-table',
                        handler: 'onCreateTableView'
                    },
                    {
                        text: 'Calendar View',
                        iconCls: 'x-fa fa-calendar-o',
                        handler: function () {}
                    },
                    {
                        text: 'Kanban View',
                        iconCls: 'x-fa fa-columns',
                        handler: function () {}
                    }
                ]
            }
        }, {
            text: 'What is a <b>View</b> ?',
            iconCls: 'x-fa fa-question-circle',
            handler: function () {}
        }
    ]
}
);
 