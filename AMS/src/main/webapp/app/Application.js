Ext.define('Admin.Application', {
    extend: 'Ext.app.Application',
    
    name: 'Admin',  
    stores: [
        'Admin.system.menu.NavigationTree' 
    ], 
    requires: [  
       'Admin.system.util.WS',
       'Admin.system.util.Constants',
       'Admin.system.util.Request',
       'Admin.system.util.Util',
       'Admin.system.util.Session'
    ],
    defaultToken : '',
//    defaultToken : 'dashboard',

    // The name of the initial view to create. This class will gain a "viewport" plugin
    // if it does not extend Ext.Viewport.
    //
    mainView: 'Admin.view.main.Main',

    onAppUpdate: function () {
        Ext.Msg.confirm('Application Update', 'This application has an update, reload?',
            function (choice) {
                if (choice === 'yes') {
                    window.location.reload();
                }
            }
        );
    }
});
