Ext.define('Admin.view.authentication.AuthenticationController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.authentication', 
    onLoginButton: function () {
        var me = this;

        var credentials = {
            email: me.getView().getReferences()['userNameField'].getValue(),
            password: me.getView().getReferences()['passwordField'].getValue()
        };
         

        Request.load({
            url: 'auth/login',
            method: 'POST',
            mask: 'Loging...',
            jsonData: credentials,
            ignoreError: true,
            success: function (response) {
                if (response && response.status && response.status === 100 && response.data && response.data.token) {
                    Session.Principal = response.data;
                    
                    Util.setCookie(response.data.token);
 
                    Ext.GlobalEvents.fireEvent('logIn');  
                } else {
                    Ext.Msg.alert('Error', response.statusMessage);
                    me.getView().getReferences()['userNameField'].el.dom.getElementsByClassName('x-form-trigger-wrap-default')[0].classList.add('x-form-trigger-wrap-invalid')
                    me.getView().getReferences()['passwordField'].el.dom.getElementsByClassName('x-form-trigger-wrap-default')[0].classList.add('x-form-trigger-wrap-invalid')
                }
            }
        });



    },
    onLoginAsButton: function () {
        alert('OK');
        //   this.redirectTo('login', true);
    },
    onNewAccount: function () {
        this.redirectTo('register', true);
    },
    onSignupClick: function () {
        this.redirectTo('dashboard', true);
    },
    onResetClick: function () {
        this.redirectTo('dashboard', true);
    }
});