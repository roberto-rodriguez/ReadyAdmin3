Ext.define('Admin.admin.entity.view.EntityDetails', {
    extend: 'Admin.system.base.BaseDetails',
    xtype: 'entityDetails',
    items: [
        {
            xtype: 'component',
            flex: 1, 
            data: {
            },
            tpl: new Ext.XTemplate([
                '<div style="padding:20px">',
                    '<table cellpadding="10" style="width:100%;cellspacing">',
                        '<tr>',
                            '<td><b>Entity name: </b> {name}</td>',
                            '<td><b>Icon: </b> {icon}</td>',
                        '</tr>',
                    '</table>',
                    '<table cellpadding="10" style="width:100%;cellspacing">',
                    '<tr>',                    
                        '<td><b>Description: </b> {description}</td>',                        
                    '</tr>',
                '</table>',
                '<div>'
            ].join('') 
            )
        }
    ]
});

