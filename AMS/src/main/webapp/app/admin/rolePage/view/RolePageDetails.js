Ext.define('Admin.admin.rolePage.view.RolePageDetails', {
    extend:'Admin.system.base.BaseDetails',
    xtype: 'rolePageDetails', 
    items: [
        {
            xtype: 'component', 
            flex: 1, 
            data: {  
            },
            tpl: new Ext.XTemplate([
                '<div style="padding:20px">', 
                '<table cellpadding="4" style="width:100%;cellspacing">',
                    '<tr>',
                        '<td><b>Role: </b> {role}</td>',
                        '<td><b>Page: </b> {page}</td>',
                    '</tr>', 
                    '<tr>',                        
                    '</tr>', 
                    '<tr>',
                        '<td><b>Create: </b> {[Util.formatBoolean(values.create)]}</td>',
                        '<td><b>Update: </b> {[Util.formatBoolean(values.update)]}</td>',
                        '<td><b>Delete: </b> {[Util.formatBoolean(values.delete)]}</td>',
                    '</tr>', 
                '</table>',
                '<div>'
            ].join('') 
            )
        }
    ]
});


