W.EntityCol = W.Col.extend({
    options: {
        alias: 'entityCol'
    },
    header: 'Create Entity',
    className: 'column yellow',
    tbar: [{
            xtype: 'CloseIcon'
        }],
    items: [
        {
            xtype: 'EntityEditor'
        },
        {
            xtype: 'FieldEditor'
        }
    ]
});

 