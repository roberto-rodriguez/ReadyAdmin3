Ext.define('Admin.system.widget.Card', {
    extend: 'Ext.panel.Panel',
    xtype: 'card',
    cls: 'card',
    width: 140, 
    header: false,
    icon: null,
    label: null,
    color: null,
    layout: {
        type: 'vbox',
        align: 'center',
        pack: 'center'
    },
    margin: '20px',
    initComponent: function () {
        this.style = {
            padding: '10px',
            border: '1px solid #ddd',
            'border-radius': '5px',
            cursor: 'pointer'
        }

        this.items = [
            {
                xtype: 'square',
                icon: this.icon,
                label: this.name,
                color: this.color
            },
            {
                width: '100%',
                html: '<p style="width:100%; text-align:center;word-wrap:break-word;">' + this.name + '</p>'
            }
        ];
        this.callParent(arguments);
    },
    listeners: {
        render: function (c) {
            c.body.on('click', function () {
                c.onCardClick(c.config.data);
            });
        }
    },
    onCardClick: function () {
       alert('This should be overriden');
    } 
});
 