Ext.define('Admin.view.authentication.Login', {
    extend: 'Admin.view.authentication.LockingWindow',
    xtype: 'login',
    header: false,
    requires: [
        'Admin.view.authentication.Dialog',
        'Ext.container.Container',
        'Ext.form.field.Text',
        'Ext.form.field.Checkbox',
        'Ext.button.Button'
    ],

    title: 'Log In',
    defaultFocus: 'authdialog', // Focus the Auth Form to force field focus as well
    // controller: 'authentication',
    tbar: [
        '->', ,
                {
                    xtype: 'component',
                    reference: 'aBankingLogo',
                    html: '<div class=""><img src="resources/images/logo.png" class="main-logo-image" style="height:50px;"></div>',
                    width: 250
                },
        '->'
    ],
    items: [
        {
            xtype: 'authdialog',
            defaultButton: 'loginButton',
            autoComplete: true,
            bodyPadding: '20 20',
            cls: 'auth-dialog-login',
            header: false,
            width: 415,
            layout: {
                type: 'vbox',
                align: 'stretch'
            },

            defaults: {
                margin: '5 0'
            },

            items: [
                {
                    xtype: 'label',
                    text: 'Log In'
                },
                {
//                    id: 'login_userid',
                    xtype: 'textfield',
                    reference: 'userNameField',
                    cls: 'auth-textbox',
                    name: 'email',
                    //bind: '{userid}',
                    height: 55,
                    //value :'adminDemo',
                    hideLabel: true,
                    allowBlank: false,
                    emptyText: 'Email',
                    triggers: {
                        glyphed: {
                            cls: 'trigger-glyph-noop auth-email-trigger'
                        }
                    }
                },
                {
                    xtype: 'textfield',
                    reference: 'passwordField',
                    cls: 'auth-textbox',
                    height: 55,
                    hideLabel: true,
                    emptyText: 'Password',
                    inputType: 'password',
                    name: 'password',
                    //value:'123',
                    // bind: '{password}',
                    allowBlank: false,
                    triggers: {
                        glyphed: {
                            cls: 'trigger-glyph-noop auth-password-trigger'
                        }
                    }
                },
                {
                    xtype: 'container',
                    layout: 'hbox',
                    items: [
                        {
                            xtype: 'checkboxfield',
                            flex: 1,
                            cls: 'form-panel-font-color rememberMeCheckbox',
                            height: 30,
                            bind: '{persist}',
                            boxLabel: 'Remember me'
                        },
                        {
                            xtype: 'box',
                            html: '<a href="#passwordreset" class="link-forgot-password" style="color:#2e3d54"> Forgot your password? ?</a>'
                        }
                    ]
                },
                {
                    xtype: 'button',
//                    id: 'loginButton',
//                    reference: 'loginButton',
                    scale: 'large',
//                    ui: 'soft-green',
                    iconAlign: 'right',
                    iconCls: 'x-fa fa-angle-right',
                    text: 'Log In',
                    formBind: true,
                    listeners: {
                        click: 'onLoginButton'
                    },
                    style: {
                        'background-color': '#2e3d54',
                        'border-color': '#2e3d54',
                        opacity: 1
                    }
                }
            ]
        }
    ],

    initComponent: function () {
        this.addCls('user-login-register-container');
        this.callParent(arguments);
    }
});
