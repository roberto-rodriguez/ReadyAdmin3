Ext.define('Admin.view.main.MainController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.main',
    listen: {
        controller: {
            '#': {
                unmatchedroute: 'onRouteChange'
            }
        }
    },
    routes: {
        ':node': 'onRouteChange'
    },
    lastView: null,
    init: function () {
        var me = this;
        Ext.GlobalEvents.on('logOut', 'onLogout', me);
        Ext.GlobalEvents.on('logIn', 'onLogin', me);
        Ext.GlobalEvents.on('selectApp', 'onSelectApp', me);
        Ext.GlobalEvents.on('selectView', 'onSelectView', me);
    },
    onSelectApp: function (route) {
        this.redirectTo(route);
    },
    onMainViewRender: function () {
        var sessionData = null;

        try {
            var sessionDataInputValue = document.getElementById('sessionData').value;

            if (sessionDataInputValue) {
                var response = JSON.parse(sessionDataInputValue);
                if (response && response.status && response.status === 100 && response.data && response.data.token) {
                    sessionData = response.data;
                }
            }
        } catch (error) {
            console.log(error);
        }

        if (sessionData) {
            Session.Principal = sessionData;
            this.onLogin(true);
            return;
        }
    },
    onNavigationTreeSelectionChange: function (tree, node) {
        var to = node && (node.get('routeId') || node.get('viewType'));

        if (to) {
            if (to === '/') {
                to = null;
            }

            this.onSelectView(to);
        }
    },
    onSelectView: function (view) {
        var to = Session.CurrentAppIdx === C.ROOT ? '' : Session.CurrentAppIdx + '/';

        if (view) {
            to += view;
        }

        this.redirectTo(to);
    },
    onRouteChange: function (route) {
        var me = this;

        if (Session.CurrentRoute && route == Session.CurrentRoute) {
            return;//then is the same page we already are
        } else {
            Session.CurrentRoute = route;
        }

        if (route === 'login' || !Session.Principal) {
            me.onLogout();
            return;
        }

        var path = {};

        if (!route && window.location.hash) {
            route = window.location.hash;
        }

        route = this.removeFirst(route, '#');

        var app = '/', view = '/';

        if (route && route != '/') {
            route = this.removeFirst(route, '/');

            var routes = route.split('/');

            if (routes.length > 1) {
                app = routes[0];

                if (routes[1]) {
                    view = routes[1];
                }
            } else {
                var singleRoute = routes[0];
                if (singleRoute) {
                    if (singleRoute.indexOf('_') === 0) {
                        view = singleRoute; //If is single route and starts with '_' is a page of the Main app
                    } else {
                        app = singleRoute;
                    }
                }
            }
        }

        path = {
            app: app,
            view: view
        }

        this.navigateToApp(path);
    },
    navigateToApp: function (path) {
        var me = this;

        if (Session.CurrentAppIdx == path.app) {
            me.doSetView(path.view);
        } else {
            if (path.app === '/') { //If changing to Main App
                Session.setMainApp();
                me.reloadNavigationTree(path);
            } else {
                var app = Session.getApp(path.app); //If exist, it will switch CurrentAppIdx to this

                if (!app) {
                    //If this app has not been open yet, check if this user has this app
                    var userApps = Session.getUserApp(path.app);

                    if (userApps && userApps.length > 0) {
                        Request.load({
                            url: userApps[0].appId + '/app/load',
                            mask: 'Loading ' + userApps[0].name,
                            success: function (response) {
                                app = Session.setApp(response.data);
                                me.reloadNavigationTree(path);
                            }
                        });
                    } else {
                        //If not, then this user does not have this app
                        path = {app: '/'};
                        Session.setMainApp();
                        me.reloadNavigationTree(path);
                    }
                } else {
                    me.reloadNavigationTree(path);
                }
            }
        }
    },
    reloadNavigationTree: function (path) {
        var me = this,
                refs = me.getReferences(),
                navigationList = refs.navigationTreeList,
                appsButton = refs.appsButton,
                store = navigationList && navigationList.getStore();

        appsButton.refresh();

        store.build();
        navigationList.setStore(store);

        me.doSetView(path.view);
    },
    doSetView: function (hashTag) {
        if (!hashTag || hashTag === '/') {
            hashTag = Session.isInMainApp() ? 'home' : 'appHome';
        }

        var me = this,
                refs = me.getReferences(),
                navigationList = refs.navigationTreeList,
                store = navigationList && navigationList.getStore(),
                node = store && (store.findNode('idv', hashTag) || store.findNode('viewType', hashTag)),
                view = (node && node.get('viewType')) || 'login', //'page404',                
                idDv = (node && node.get('idDv')) || 0,
                text = (node && node.get('text')) || null,
                menuType = (node && node.get('menuType')) || null,
                iconCls = (node && node.get('iconCls')) || null,
                existingItem = refs.mainCardPanel.child('component[routeId=' + hashTag + ']'),
                newView;

        if (node) {
            navigationList.setSelection(node);
        }

        if (!existingItem) {
            var config = {
                xtype: view,
                routeId: hashTag, // for existingItem search later
                hideMode: 'offsets',
                entity: view,
                idDv: idDv,
                viewName: text,
                viewIcon: iconCls
            }

            if (!idDv) {
                config.height = window.innerHeight - 134;
            }

            if (menuType) {
                config.xtype = menuType;

                switch (menuType) {
                    case 'dv':
                        me.loadDv(idDv, text, function (response) {
                            config.fields = response.fields;
                            me.showView(existingItem, config);
                        });
                        break;
                    case 'views':
                        var entity = Session.getEntity(idDv),
                                fields = entity && entity.fields;

                        data = node.get('data');
                        config.data = data;

                        if (fields) {
                            config.fields = fields;
                            me.showView(existingItem, config);
                        } else {
                            me.loadDv(idDv, text, function (response) {
                                config.fields = response.fields;
                                me.showView(existingItem, config);
                            });
                        }
                }

            } else { //New No DV 
                me.showView(existingItem, config);
            }
        } else { //Existent
            me.showView(existingItem);
        }
    },
    loadDv: function (idDv, text, callback) {
        Request.load({
            url: idDv + '/entity/load',
            mask: 'Loading ' + text,
            success: function (response) {
                if (response) {
                    var entity = Session.getEntity(idDv);
                    var data = response.data;
                    entity.fields = data.fields;
                    entity.views = data.views;

                    callback(data);
                }
            }
        });
    },
    showView: function (existingItem, config) {
        var me = this,
                refs = me.getReferences(),
                mainCard = refs.mainCardPanel,
                mainLayout = mainCard.getLayout(),
                lastView = me.lastView;

        var newView = config ? Ext.create(config) : null;

        // Kill any previously routed window
        if (lastView && lastView.isWindow) {
            lastView.destroy();
        }

        lastView = mainLayout.getActiveItem();

        if (existingItem) {
            // We don't have a newView, so activate the existing view.
            if (existingItem !== lastView) {
                mainLayout.setActiveItem(existingItem);
            }
            newView = existingItem;
        } else {
            // newView is set (did not exist already), so add it and make it the activeItem.
            Ext.suspendLayouts();
            mainLayout.setActiveItem(mainCard.add(newView));
            Ext.resumeLayouts(true);
        }

        if (newView && newView.isFocusable(true)) {
            newView.focus();
        }

        me.lastView = newView;
    },
    onLogin: function (autoLogin) {
        var me = this,
                principal = Session.Principal,
                refs = me.getReferences();

        if (refs.avatar.rendered) {
            refs.avatar.update(principal.firstName);
        } else {
            refs.avatar.label = principal.firstName;
        }

        if (refs.principalName.rendered) {
            refs.principalName.updateLabel();
        }

        if (autoLogin) {
            return;//In autoLogin, it will call the redirectTo("") automatically
        }

        var route = this.removeFirst(window.location.hash, '#');
        me.onRouteChange(route);
    },
    onLogout: function () {
        var me = this,
                mainCardPanel = me.getReferences().mainCardPanel;

        Session.reset();
        mainCardPanel.removeAll(true);
        Util.setCookie('');
        this.doSetView('login');
    },
    onToggleNavigationSize: function () {
        var me = this,
                refs = me.getReferences(),
                navigationList = refs.navigationTreeList,
                wrapContainer = refs.mainContainerWrap,
                collapsing = !navigationList.getMicro(),
                new_width = collapsing ? 64 : 250;

        if (Ext.isIE9m || !Ext.os.is.Desktop) {
            Ext.suspendLayouts();

            refs.appsButton.setWidth(new_width);

            navigationList.setWidth(new_width);
            navigationList.setMicro(collapsing);

            Ext.resumeLayouts(); // do not flush the layout here...

            // No animation for IE9 or lower...
            wrapContainer.layout.animatePolicy = wrapContainer.layout.animate = null;
            wrapContainer.updateLayout();  // ... since this will flush them
        } else {
            if (!collapsing) {
                // If we are leaving micro mode (expanding), we do that first so that the
                // text of the items in the navlist will be revealed by the animation.
                navigationList.setMicro(false);
            }

            // Start this layout first since it does not require a layout
            refs.appsButton.animate({dynamic: true, to: {width: new_width}});

            // Directly adjust the width config and then run the main wrap container layout
            // as the root layout (it and its chidren). This will cause the adjusted size to
            // be flushed to the element and animate to that new size.
            navigationList.width = new_width;
            wrapContainer.updateLayout({isRoot: true});
            navigationList.el.addCls('nav-tree-animating');

            // We need to switch to micro mode on the navlist *after* the animation (this
            // allows the "sweep" to leave the item text in place until it is no longer
            // visible.

            refs.appsButton.resize(collapsing);

            if (collapsing) {
                navigationList.on({
                    afterlayoutanimation: function () {
                        navigationList.setMicro(true);
                        navigationList.el.removeCls('nav-tree-animating');
                    },
                    single: true
                });
            }
        }
    },
    removeFirst: function (hash, characterToRemove) {
        if (hash && hash.length > 0 && hash.indexOf(characterToRemove) == 0) {
            hash = hash.substring(1);
        }
        return hash;
    }
});