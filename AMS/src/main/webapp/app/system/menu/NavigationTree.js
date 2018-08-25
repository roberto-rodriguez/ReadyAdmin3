Ext.define('Admin.system.menu.NavigationTree', {
    extend: 'Ext.data.TreeStore',
    storeId: 'NavigationTree',
    fields: [{
            name: 'text'
        }],
    root: {
        expanded: true,
        children: []
    },
    build: function () {
        var me = this;

        me.getRootNode().removeAll();

        if (Session.isInMainApp()) {
            this.setupMainApp();
        } else {
            this.setupApp();
        }
    },
    setupMainApp: function () {
        var me = this;
        me.getRootNode().appendChild(me.getNode('home'));
        me.getRootNode().appendChild(me.getNode('templates'));
    },
    setupApp: function () {
        var me = this;

        var appHome = me.getNode('appHome');
        appHome.children = Ext.Array.map(Session.CurrentApp.entities,
                function (entity) {
                    return {
                        text: entity.name,
                        idx: entity.idx,
                        viewType: entity.idx,
                        iconCls: entity.icon,
                        idDv: entity.id,
                        leaf: true,
                        menuType: 'dv'
                    };
                });
        me.getRootNode().appendChild(appHome);

        var views = me.getNode('views');
        views.children = Ext.Array.map(Session.CurrentApp.views,
                function (view) {
                    return {
                        text: view.name,
                        idx: view.idx,
                        viewType: view.idx,
                        iconCls: view.iconCls,
                        idDv: view.idDv,
                        isView: true,
                        leaf: true,
                        menuType: 'views',
                        data: view
                    };
                });
        me.getRootNode().appendChild(views);




        me.getRootNode().appendChild(me.getNode('appAdminPanel'));
    },
    getNode: function (name) {
        return Ext.apply({}, this[name]);
    },
    dashboard: {
        text: 'Dashboard',
        iconCls: 'x-fa fa-desktop',
        rowCls: 'nav-tree-badge',
        viewType: 'admindashboard',
        routeId: 'dashboard',
        leaf: true
    },
    home: {
        text: 'Applications',
        iconCls: 'x-fa fa-sitemap',
        viewType: 'home',
        leaf: true,
        routeId: '/'
    },
    templates: {
        text: 'Templates',
        iconCls: 'x-fa fa-tags',
        viewType: '_templates',
        leaf: true
    },
    appHome: {
        text: 'Entities',
        iconCls: 'x-fa fa-cube',
        viewType: 'appHome',
        routeId: '/',
        expanded: true,
        selectable: true,
        children: [],
        cls: 'parent-menu'
    },
    views: {
        text: 'Views',
        iconCls: 'x-fa fa-cubes',
        expanded: false,
        selectable: false,
        children: [],
        cls: 'parent-menu'
    },
    appAdminPanel: {
        text: 'Admin Panel',
        iconCls: 'x-fa fa-key',
        expanded: false,
        selectable: false,
        routeId: '/',
        children: [
            {
                text: 'Entities',
                iconCls: 'x-fa fa-cube',
                viewType: 'entity',
                leaf: true
            },
            {
                text: 'Users',
                iconCls: 'x-fa fa-group',
                viewType: 'user',
                leaf: true
            },
            {
                text: 'Roles',
                iconCls: 'x-fa fa-diamond',
                viewType: 'role',
                leaf: true
            }
        ]
    }
});
