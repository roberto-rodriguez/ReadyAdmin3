Ext.define('Admin.system.filter.custom.FilterRaceSelectField', {
    extend: 'Admin.system.filter.FilterSelectField',
    xtype: 'filterRaceSelectField',
    prefix: '(S)',
    store:Ext.create('Ext.data.Store', {
        fields: ['id', 'name'],
        data : [ 
            {id:'White', name:"White"}, 
            {id:'Black', name:"Black"}, 
            {id:'Asian', name:"Asian"}, 
            {id:'Hawaiian', name:"Hawaiian"}, 
            {id:'American Indian', name:"American Indian"}, 
            {id:'Other Pacific Islander', name:"Other Pacific Islander"}
        ]
    }) 
});