Ext.define('Admin.admin.user.view.UserDetails', {
    extend: 'Admin.system.base.BaseDetails',
    xtype: 'userDetails',
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
                        '<td><b>First name: </b> {firstName}</td>',
                        '<td><b>Last name: </b> {lastName}</td>',
                    '</tr>',
                    '<tr>', 
                        '<td><b>Email: </b> {email}</td>',
                        '<td><b>Active:</b> {[Util.formatBoolean(values.active)]}</td>', 
                    '</tr>',
                '</table>',
                '<div>'
            ].join('')
                    )
        }
    ]
});
