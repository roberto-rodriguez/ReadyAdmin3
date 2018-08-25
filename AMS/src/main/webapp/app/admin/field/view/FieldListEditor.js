Ext.define('Admin.admin.field.view.FieldListEditor', {
    extend:'Admin.admin.field.view.FieldEditor',
    xtype: 'fieldListEditor', 
    setData: function (data) {}, //Override if needed
    getData: function () {
        var formData = this.getValues();

        formData['grid'] = this.down('toolbar').down('checkbox').value;

        return Ext.apply(this.data, formData);
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

