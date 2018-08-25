Ext.define('Admin.system.filter.NoFilter', {
    extend: 'Ext.form.Text',
    xtype: 'noFilter',
    width: '100%',
    hidden: true,
    disabled:true,
    prefix:'(I)',
    style: {
        'padding': '0px',
        'margin': '0px',
        'text-align':'center'
    },
    listeners: {
//        specialkey: function (cmp, e) { 
//            if (e.getKey() == e.ENTER) {
//                 cmp.up().up().up().getStore().loadPage(1); 
//            }
//        } 
    }
});