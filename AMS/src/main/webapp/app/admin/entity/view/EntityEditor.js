Ext.define('Admin.admin.entity.view.EntityEditor', {
    extend: 'Admin.system.base.BaseEditor',
    xtype: 'entityEditor',
    height: 310,
    items: [
        {
            labelWidth: 80,
            style: {
                'margin-right': '0px'
            },
            items: [
                {
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Entity name',
                    name: 'name',
                    allowBlank: false
                },
                {
                    xtype: 'iconPicker'
                }
            ]
        },
        {
            xtype: 'fieldset',
            title: 'Add Field',
            flex: 1,
            width: '100%',
            style: {
                'border-radius': '6px',
                'pading': '5px'
            },
            items: [
                {
                    xtype: 'fieldIcons',
                    reference: 'fieldIcons'
                },
//                {
//                    xtype: 'fieldEditor',
//                    reference: 'fieldEditor',
//                    hidden: true
//                }
            ]

        }
    ],
    getValues: function () {
        var fields = this.items.items[0].items.items,
                values = {};

        Ext.each(fields, function (field) {
            var kv = field.getKeyValue();
            values[kv.name] = kv.value;
        });

        return values;
    },
    bbar: [ 
        '->',
        {
            text: 'Create',
            ui: 'green',
            //TODO Make a Controller for the editor and put this logic and the validation in the Controller
            handler: function () {
                var me = this,
                        editor = me.up().up(),
                        data = editor.getValues(),
                        fieldsGrid = editor.up().down('createEntityFieldGrid'),
                        viewTab = editor.up(),
                        values = editor.getValues(),
                        url = viewTab.idDv + '/' + viewTab.entity + '/save';

                var fields = Ext.Array.map(fieldsGrid.getStore().data.items, function (item) {
                    return item.data
                });

                Ext.apply(data, {fields: fields});

                Request.load({
                    url: url,
                    method: 'POST',
                    mask: 'Saving...',
                    jsonData: data,
//                    ignoreError: true,
                    success: function (response) {
                        if (response.status === 500) {//en caso de lanzar una exepcion
                            Ext.Msg.alert('Information', response.statusMessage);
                        } else {

                        }
                    }
                });
            }
        },
        '           '
    ],
    initData: function () {
        var me = this,
                data = me.data,
                fieldsContainer = this.items.items[0];

        if (me.data.id) {
            //TODO editting
        } else {
            //Adding new entity
            data.fields = [
                {id: 1, readonly: true, grid: false, name: 'Creation Date', type: 'Date'},
                {id: 2, readonly: true, grid: false, name: 'Created By', type: 'User', ref: '_user.first_name'}
            ];
        }

        var select = {
            xtype: 'combobox',
            displayField: 'name',
            valueField: 'id',
            queryMode: 'local',
            store: Ext.create('Ext.data.Store', {
                fields: ['id', 'label'],
                data: data.fields
            }),
            getKeyValue: function () {
                return {
                    name: this.name,
                    value: this.rawValue
                };
            }
        }

        fieldsContainer.add(Ext.apply(select, {name: 'display', fieldLabel: 'Display Filed'}));
        fieldsContainer.add(Ext.apply(select, {name: 'order', fieldLabel: 'Order By', value: 1}));

        me.up().down('createEntityFieldGrid').getStore().add(data.fields);
    }
});

