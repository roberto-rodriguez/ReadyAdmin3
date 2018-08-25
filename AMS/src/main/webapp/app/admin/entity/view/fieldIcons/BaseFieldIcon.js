Ext.define('Admin.admin.entity.view.fieldIcons.BaseFieldIcon', {
    extend: 'Ext.panel.Panel',
    xtype: 'baseFieldIcon',
    layout: 'vbox',
    flex: 1,
    svgIcon: null,
    text: null,
    type: null,
    tooltip: null,
    header: false,
    layout: {
        type: 'vbox',
        align: 'center'
    },
    initComponent: function () {
        var me = this;
        me.items = [
            {
                xtype: 'button',
                iconCls: me.icon,
                cls: 'entity-icon',
                ui: 'green',
                tooltip: me.tooltip,
                tooltipType: 'title', 
                style: {
                    border: '1px solid #CCCCCC',
                    padding: '10px',
                    'border-radius': '5px',
                    'background-image': 'linear-gradient(#008000, #32b232)'
                },
                listeners: {
                    click: function (me) {
                        var button = me.up(),
                                fieldset = button.up().up(),
                                icons = fieldset.items.items[0],
                                type = button.type || button.text;

                        var xtype = 'fieldEditor';

                        switch (type) {
                            case 'Entity':
                                xtype = 'fieldEntityEditor';
                                break;
                            case 'List':
                                xtype = 'fieldListEditor';
                                break;
                        }

                        icons.hide();
                        fieldset.add({
                            xtype: xtype,
                            reference: 'fieldEditor',
                            data: {type: type}
                        });

//                        editor.activate({type: button.type || button.text});
                    }
                },
            },
            {
                html: '<p style="margin:0px; text-align:center; color:#404040">' + me.text + '</p>',
                style: {
                    'margin-top': '6px',
                    cursor: 'pointer'
                }
            }
        ];
        this.callParent(arguments);
    },
    items: []
});