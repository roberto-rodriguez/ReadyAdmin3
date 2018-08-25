Ext.define('Admin.system.widget.IconPicker', {
    extend: 'Ext.button.Button',
    xtype: 'iconPicker',
    text: 'Icon: ',
    iconCls: 'x-fa fa-question-circle',
    iconAlign: 'right',
    ui: 'green',
    style: {
        'border-radius': '5px'
    },
    getKeyValue: function () {
        return {
            name: 'icon',
            value: this.iconCls
        };
    },
    listeners: {
        click: function (cmp, e) {

            var w = Ext.create('Ext.window.Window', {
                title: 'Select Icon',
                header: true,
                modal: true,
                bodyBorder: false,
                closable: true,
                floating: true,
                width: 510,
                height: 494,
                triggerButton: cmp,
                defaults: {
                    style: {
                        margin: '10px'
                    },
                    xtype: 'iconRow',
                },
                items: [
                    {
                        icons: ['line-chart', 'pie-chart', 'bar-chart', 'area-chart', /**/  'th-large', 'camera', 'rocket', 'shopping-cart', 'cloud', 'balance-scale', 'clone', 'database']
                    },
                    {
                        icons: ['bank', 'barcode', 'bars', 'sitemap', 'archive' /**/, 'copy', 'bed', 'beer', 'bell', 'calendar', 'external-link', 'feed']
                    },
                    {
                        icons: ['th-list', 'birthday-cake', 'star', 'tags', 'tasks', /**/ 'book', 'bookmark', 'briefcase', 'building', 'thumbs-up', 'fire', 'filter']
                    },
                    {
                        icons: ['bus', 'car', 'truck', 'ambulance', 'bicycle', /**/ 'ship', 'train', 'road', 'wrench', 'coffee', 'tree', 'refresh']
                    },
                    {
                        icons: ['cog', 'comments', 'credit-card', 'hand-o-right', 'cubes', /**/ 'cutlery', 'user', 'users', 'desktop', 'diamond', 'hourglass', 'umbrella']
                    },
                    {
                        icons: ['save', 'edit', 'envelope', 'exchange', 'eye', /**/'eyedropper', 'fax', 'suitcase', 'film', 'list-ol', 'random', 'music']
                    },
                    {
                        icons: ['flag', 'flask', 'trash', 'folder', 'folder-open', /**/'gamepad', 'gavel', 'gift', 'glass', 'globe', 'dollar', 'object-group', 'object-group']
                    },
                    {
                        icons: ['graduation-cap', 'university', 'chain', 'heart', 'home', /**/'table', 'language', 'lock', 'map-marker', 'money', 'newspaper-o', 'magnet']
                    }
                ]
            });
            w.show();
        }
    }
});

Ext.define('Admin.system.widget.IconRow', {
    extend: 'Ext.panel.Panel',
    xtype: 'iconRow',
    icons: [],
    defaults: {
        xtype: 'iconButton'
    },
    tbar: {
        defaults: {
            xtype: 'iconButton'
        },
        items: []
    },
    initComponent: function () {
        var me = this;

        me.tbar.items = Ext.Array.map(me.icons, function (icon) {
            return {iconCls: 'x-fa fa-' + icon};
        });
        this.callParent(arguments);
    }
});

Ext.define('Admin.system.widget.IconButton', {
    extend: 'Ext.button.Button',
    xtype: 'iconButton',
    iconCls: null,
    listeners: {
        click: function () {
            var w = this.up().up().up(),
                    triggerButton = w.triggerButton;

            triggerButton.setIconCls(this.iconCls);
            w.destroy();
        }
    }
});