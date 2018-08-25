Ext.define('Admin.admin.user.view.UserEditor', {
    extend: 'Admin.system.base.BaseEditor',
    alias: 'widget.userEditor',
    height: 350,
    items: [
        {
            items: [
                {
                    fieldLabel: 'First name',
                    name: 'firstName',
                    allowBlank: false
                },
                {
                    fieldLabel: 'Last name',
                    name: 'lastName',
                    allowBlank: false
                }
            ]
        },
        {
            items: [

                {
                    xtype: 'basePassField',
                    fieldLabel: 'Password',
                    name: 'passw',
                    allowBlank: false
                },
                {
                    xtype: 'basePassField',
                    fieldLabel: 'Confirm password',
                    name: 'confirmPassword',
                    allowBlank: false,
                    init: function (data) {
                        if (data['passw']) {
                            this.setValue(data['passw']);
                        }
                    },
                    validator: function (val) {
                        var values = this.up().up().getValues();
                        if (val) {
                            if (val == values['passw']) {
                                return true;
                            } else {
                                return "Passwords do not match";
                            }
                        }
                    }
                }
            ]
        },
        {
            items: [
                {
                    xtype: 'baseStringField',
                    fieldLabel: 'Email',
                    name: 'email',
                    regex: /^([a-z]+[a-z1-9._-]*)@{1}([a-z1-9\.]{2,})\.([a-z]{2,3})$/, //solo email
                    regexText: "Only valid email addresses are allowed"
                },
                {
                    xtype: 'baseBooleanField',
                    fieldLabel: 'Active',
                    name: 'active'
                },
                {
                    xtype: 'idField'
                }
            ]
        }
    ]
});
