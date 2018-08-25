Ext.define('Admin.system.filter.custom.FilterGenderSelectField', {
    extend: 'Admin.system.filter.FilterSelectField',
    xtype: 'filterGenderSelectField',
    prefix: '(S)',
    store:Ext.create('Ext.data.Store', {
        fields: ['id', 'name'],
        data : [ 
            {id:'M', name:"Male"}, 
            {id:'F', name:"Female"} 
        ]
    }) 
});