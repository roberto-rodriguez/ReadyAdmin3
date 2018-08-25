Ext.define('Admin.view.main.Main', {
    extend: 'Ext.container.Viewport',
    requires: [
        'Ext.button.Segmented',
        'Ext.list.Tree'
    ],
    controller: 'main',
    viewModel: 'main',
    cls: 'sencha-dash-viewport',
    itemId: 'mainView',
    layout: {
        type: 'vbox',
        align: 'stretch'
    },
    listeners: {
        render: 'onMainViewRender'
    },
    items: [
        {
            xtype: 'toolbar',
            cls: 'sencha-dash-dash-headerbar shadow',
            height: 64,
            itemId: 'headerBar',
            items: [
//                {
//                    xtype: 'panel',
//                    width: 250,
//                    tbar: [
//                        '->',
//                        {
//                            xtype: 'viewsButton'
//                        },
//                        '->']
//                }, 
                {
                    xtype: 'appsButton',
                    reference: 'appsButton' 
                },
                {
                    margin: '0 0 0 8',
                    ui: 'header',
                    iconCls: 'x-fa fa-navicon',
                    id: 'main-navigation-btn',
                    handler: 'onToggleNavigationSize'
                },
                '->',
                {
                    iconCls: 'x-fa fa-search',
                    ui: 'header',
                    hrefTarget: '_self',
                    tooltip: 'See latest search'
                },
                {
                    iconCls: 'x-fa fa-envelope',
                    ui: 'header',
                    hrefTarget: '_self',
                    tooltip: 'Check your email'
                },
                {
                    iconCls: 'x-fa fa-question',
                    ui: 'header',
                    hrefTarget: '_self',
                    tooltip: 'Help / FAQ\'s'
                },
                {
                    iconCls: 'x-fa fa-sign-out',
                    ui: 'header',
                    tooltip: 'Exit',
                    listeners: {
                        click: function () {
                            Ext.GlobalEvents.fireEvent('logOut');
                        }
                    }
                },
                {
                    xtype: 'tbtext',
                    reference: 'principalName',
                    cls: 'top-user-name',
                    label: null,
                    updateLabel: function () {
                        var principal = Session.Principal;
                        if (principal) {
                            this.setText(principal.firstName + ' ' + principal.lastName + ' <b>( ' + principal.role + ' )</b>')
                        }
                    },
                    listeners: {
                        render: function (a, b, c) {
                            this.updateLabel();
                        }
                    }
                },
                {
                    xtype: 'avatar',
                    reference: 'avatar'
                }
            ]
        },
        {
            xtype: 'maincontainerwrap',
            id: 'main-view-detail-wrap',
            reference: 'mainContainerWrap',
            flex: 1,
            items: [
                {
                    xtype: 'treelist',
                    reference: 'navigationTreeList',
                    itemId: 'navigationTreeList',
                    ui: 'navigation',
                    store: 'NavigationTree',
                    width: 250,
//                    width:  0, 
                    expanderFirst: false,
                    expanderOnly: false,
                    listeners: {
                        selectionchange: 'onNavigationTreeSelectionChange'
                    }
                },
                {
                    xtype: 'container',
                    flex: 1,
//                    height: 300,
                    reference: 'mainCardPanel',
                    cls: 'sencha-dash-right-main-container',
                    itemId: 'contentPanel',
                    id: 'contentPanel',
                    style: {
//                        'background-color': 'white'
                    },
                    layout: {
                        type: 'card',
                        anchor: '100%'
                    }
                }
            ]
        }
    ]
});
