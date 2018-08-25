Ext.define('Admin.admin.field.view.FieldEntityEditor', {
    extend: 'Admin.admin.field.view.FieldEditor',
    xtype: 'fieldEntityEditor',
    listeners: {
        render: function () {
            var firstRow = this.items.items[0],
                    entitySelect = firstRow.items.items[2];

            entitySelect.init();
        }
    },
    setData: function (data) {}, //Override if needed
    getData: function () {
        var formData = this.getValues();

        formData['grid'] = this.down('toolbar').down('checkbox').value;

        return Ext.apply(this.data, formData);
    },
    items: [{
            marginRight: '10px',
            items: [{
                    xtype: 'idField'
                },
                {
                    fieldLabel: 'Name',
                    name: 'name',
                    labelWidth: 50
                },
                {
                    xtype: 'baseSelectField',
                    fieldLabel: 'Entity',
                    name: 'entity',
                    applyFilter: true,
                    rootProperty: 'List',
                    url: 'entity',
                    fullUrl: '0/entity/list',
                    trigger: 'field',
                    help: 'Indicates which Entity it refers to.'
                },
                {
                    xtype: 'baseSelectField',
                    fieldLabel: 'Field',
                    name: 'field',
                    rootProperty: 'List',
                    fullUrl: '0/field/list',
                    help: 'Indicates which field in that Entity will be displayed.'
                },
                {
                    xtype: 'baseBooleanField',
                    fieldLabel: 'Bidirectional',
                    name: 'bidirectional',
                    iconCls: 'x-fa fa-cube'
                }
            ]
        },
//        {
//            items: [{
//                    fieldLabel: 'Name',
//                    name: 'name2',
//                    labelWidth: 50
//                },
//                {
//                    xtype: 'baseBooleanField',
//                    fieldLabel: 'Include in Grid',
//                    name: 'grid2',
//                    value: true,
//                    width: '80px'
//                }]
//        }
    ]
});

