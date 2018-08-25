Ext.define('Admin.view.home.Home', {
    extend: 'Ext.panel.Panel',
    xtype: 'home',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    padding: 10,
    autoScroll: true,
    overflowY: 'scroll', 
    listeners: {
        render: function (a, b, c) {
            var apps = Session.Principal.apps || [];

            var myApps = [];
            var collaboratorApps = [];

            for (var i = 0; i < apps.length; i++) {
                var app = {
                    name: apps[i].name,
                    icon: apps[i].icon,
                    data: apps[i]
                }

                if (apps[i].isAdmin) {
                    myApps.push(app);
                } else {
                    collaboratorApps.push(app);
                }
            }

            myApps.push({name: 'Create New Application', icon: 'fa-plus', color: 'grey'});

            this.add({
                title: 'My Applications',
                items: Util.getResponsiveRows(myApps, 180)
            });

            if (collaboratorApps && collaboratorApps.length > 0) {
                collaboratorApps = Util.getResponsiveRows(collaboratorApps, 180);
            }

            this.add({
                title: 'Applications where I am a collaborator',
                items: collaboratorApps
            });
 
        }
    },
    defaults: {
        expanded: true,
        layout: 'vbox',
        collapsible: true,
        titleCollapse: true,
        cls: 'add-border-bottom',
        iconCls: 'x-fa fa-caret-down',
        ui: 'light',
        defaults: {
            layout: 'hbox',
            defaults: {
                xtype: 'card',
                onCardClick: function (data) {
                    Ext.GlobalEvents.fireEvent('selectApp', data.idx);
                }
            }
        }
    },
    items: []
});
