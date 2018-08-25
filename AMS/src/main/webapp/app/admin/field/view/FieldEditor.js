Ext.define('Admin.admin.field.view.FieldEditor', {
    extend: 'Ext.form.Panel',
    xtype: 'fieldEditor',
    width: '100%',
    height: 140,
    type: null,
    header: false,
    layout: 'vbox',
    flex: 1,
    data: null,
    cls: 'base-editor',
    style: {
        'margin-top': '10px'
    },
    activate: function (data) {
        var me = this,
                fieldset = me.up(),
                fields = this.items.items[0].items.items,
                gridField = this.down('toolbar').down('checkbox'),
                icons = fieldset.items.items[0];

        icons.hide();
        me.show();

        var id = data['id'];

        this.data = data;

        fields[0].setValue(id || null);
        fields[1].setValue(data['name'] || null);

        if (id) {
            gridField.setValue(data['grid']);
        } else {
            gridField.setValue(true);
        }

        this.setData(data);
    },
    setData: function (data) {}, //Override if needed
    getData: function () {
        var formData = this.getValues();

        formData['grid'] = formData['grid'] && formData['grid'] === 'on';

        return Ext.apply((this.config.data || {}), formData);
    },
    bbar: [
        {
            xtype: 'checkbox',
            fieldLabel: 'Show in Grid',
            name: 'grid'
        },
        '->',
        {
            xtype: 'button',
            ui: 'soft-green',
            text: 'Cancel',
            listeners: {
                click: 'showFieldIcons'
            }
        },
        {
            xtype: 'button',
            ui: 'green',
            text: 'Save',
            listeners: {
                click: 'saveField'
            }
        },
        '            '
    ],
    defaults: {
        xtype: 'baseFieldContainer'
    },
    items: [{
            items: [{
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Name',
                    name: 'name',
                    labelWidth: 50
                } 
            ]
        } 
    ]
});

