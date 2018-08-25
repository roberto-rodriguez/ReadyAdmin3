Ext.define('Admin.system.filter.FilterUser', {
    extend: 'Ext.form.ComboBox',
    xtype: 'filterUser',
    label: '',
    width: '100%',
    hidden: true,
    prefix: '(I)',
    autoSelect:true, 
    style: {
        'padding': '0px',
        'margin': '0px',
        'text-align': 'center'
    },
    valueField:'id',
    displayField:'name',
    value:0,    
    listeners: { 
        select: function (cmp, rec, idx) {
             cmp.up().up().up().getStore().loadPage(1); 
        }  
    },
    listConfig : ({
    getInnerTpl: function() {
            return '<div data-qtip="{name}">{name}</div>';
        }
  })
});