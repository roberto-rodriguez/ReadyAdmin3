Ext.define('Admin.admin.role.view.RoleDetails', {
    extend: 'Admin.system.base.BaseDetails',
    xtype: 'roleDetails',
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
                        '<td><b>Admin: </b> {[Util.formatBoolean(values.isadmin)]}</td>',
                        '<td><b>Name: </b> {name}</td>',                        
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


