
Ext.define('Admin.system.util.Request', {
    extend: 'Ext.data.Connection',
    alternateClassName: 'Request',
    singleton: true,
    autoAbort: false,
    constructor: function (config) {
        var me = this;
        me.callParent(arguments);
        me.on(Ext.apply(me.submitEvents, {scope: me}));
    },
    submitEvents: {
        beforerequest: "onBeforerequest",
        requestcomplete: "onRequestcomplete",
        requestexception: "onRequestexception"
    },
    load: function (config) {
        var me = this,
                token = Session.Principal ? Session.Principal.token : ''; 
        
        var obj = {
            headers: {
               'TOKEN': token
            },
            method: config.method || 'GET', //'POST',
            url: config.url,
            params: config.params,
            jsonData: config.jsonData,
            callback: config.callback,
            customSuccess: config.success,
            failure: config.failure,
            scope: config.scope,
            mask: config.mask,  
            ignoreError: config.ignoreError,
            host: config.host || WS.HOST
        };

        if (config.params) {
            obj['headers']['Content-Type'] = 'application/x-www-form-urlencoded';
        } else {
            obj['headers']['Content-Type'] = 'application/json; charset=utf-8';
        }

        me.request(obj);
    },
    onBeforerequest: function (conn, options, eOpts) { 
         options.url = options.host + options.url; 

        if (options.mask) {
           Ext.getBody().mask( options.mask );
        }
    },
    onRequestcomplete: function (conn, response, options, eOpts) {
        Ext.getBody().unmask();
        
        if(!response.responseText || response.responseText.length === 0){
            Ext.GlobalEvents.fireEvent('logOut');
            return;
        } 
         
        var responseAsJson = Ext.decode(response.responseText);
        
        if(responseAsJson.status && responseAsJson.status != 100 && !options.ignoreError){
//            alert(responseAsJson.statusMessage);
            return;
        }
        
        if (responseAsJson.error) {
           alert('Error');
        } else { 
            options.customSuccess(responseAsJson);
        } 
    },
    onRequestexception: function (conn, response, options, eOpts) {
        Ext.getBody().unmask();
//        alert('Server is down. Please contact customer service.');
    }
});